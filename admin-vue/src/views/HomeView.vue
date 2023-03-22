<template>
  <div class="home">
    <header-nav />
    <div class="banner">
      <pie-chart v-if="loaded" :chartData="pieData" :chartOption="pieOption"></pie-chart>
      <bar-chart v-if="loaded" :chartData="barData" :chartOption="barOption"></bar-chart>
    </div>
    <article-tabs :notice="noticeData" :event="eventData"></article-tabs>
  </div>
</template>

<script>
// @ is an alias to /src
import { productList } from "@/api/product";
import { articleList } from "@/api/article";
import PieChart from "@/components/chart/PieChart.vue";
import BarChart from "@/components/chart/BarChart.vue";
import ArticleTabs from "@/components/article/ArticleTabs.vue";
import HeaderNav from "@/components/header/HeaderNav.vue";

export default {
  name: "HomeView",
  components: {
    HeaderNav,
    PieChart,
    BarChart,
    ArticleTabs,
  },
  data() {
    return {
      data: null,
      saledata: null,
      loaded: false,
      pieData: null,
      barData: null,
      noticeData: null,
      eventData: null,
    };
  },
  methods: {
    getToday() {
      const today = new Date();
      const year = today.getFullYear();
      const month = today.getMonth() + 1;
      const day = today.getDate();
      return `${year}-${month < 10 ? "0" + month : month}-${day}`;
    },
  },
  created() {
    productList(
      "",
      ({ data }) => {
        this.data = data;
        this.loaded = true;
      },
      {}
    );

    articleList(true, ({ data }) => {
      this.eventData = data.filter((item) => item.endEvent >= this.getToday());
    });

    articleList(false, ({ data }) => {
      this.noticeData = data;
    });
  },

  watch: {
    loaded() {
      this.saledata = this.data.filter((item) => item.productInfo.sale);
      const regiCnt = this.data.length;
      const saleCnt = this.saledata.length;

      this.pieData = {
        labels: ["미판매중인 제품", "판매중인 제품"],
        datasets: [
          {
            data: [regiCnt - saleCnt, saleCnt],
            backgroundColor: ["rgba(255,99,132,0.5)", "rgba(54,162,235,0.5)"],
            borderColor: ["rgb(255,99,132)", "rgb(54,162,235)"],
          },
        ],
        chartOptions: {
          responsive: true,
        },
      };

      this.pieOption = {
        responsive: true,
        plugins: {
          legend: {
            position: "top",
          },
          title: {
            display: true,
            text: "제품 등록 현황",
            font: {
              size: 20,
            },
          },
        },
      };

      const dataLabel = this.saledata.reduce((acc, curr) => {
        const { mainName } = curr;
        if (acc[mainName]) acc[mainName].push(curr);
        else acc[mainName] = [curr];
        return acc;
      }, {});
      const discountLabel = this.saledata
        .filter((item) => item.productInfo.discount)
        .reduce((acc, curr) => {
          const { mainName } = curr;
          if (acc[mainName]) acc[mainName].push(curr);
          else acc[mainName] = [curr];
          return acc;
        }, {});
      Object.keys(dataLabel).forEach((name) => {
        if (Object.keys(discountLabel).indexOf(name) === -1) {
          discountLabel[name] = [];
        }
      });
      const saleData = [];

      Object.keys(dataLabel).forEach((item) => saleData.push(discountLabel[item].length));
      const nonSaleData = Object.values(dataLabel)
        .map((item) => item.length)
        .map((x, y) => (isNaN(saleData[y]) ? x : x - saleData[y]));

      this.barData = {
        labels: Object.keys(dataLabel),
        datasets: [
          {
            label: "행사가 제품",
            data: saleData,
            borderWidth: 1,
            backgroundColor: "rgb(54,162,235)",
          },
          {
            label: "정상가 제품",
            data: nonSaleData,
            borderWidth: 1,
            backgroundColor: "rgb(100,100,100)",
          },
        ],
      };

      this.barOption = {
        responsive: true,
        plugins: {
          legend: {
            position: "top",
          },
          title: {
            display: true,
            text: "행사 제품 현황",
            font: {
              size: 20,
            },
          },
        },
      };
    },
  },
};
</script>

<style scoped>
.home {
  position: relative;
}

.banner {
  background-color: gainsboro;
  display: flex;
  justify-content: space-around;
  align-items: bottom;
  margin-top: 20px;
}
</style>

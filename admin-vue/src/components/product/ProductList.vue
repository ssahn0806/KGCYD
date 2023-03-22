<template>
  <div id="home">
    <div id="container">
      <b-row>
        <b-col>
          <b-row class="optionBox">
            <b-col cols="2">
              <b-dropdown id="search-drop" :text="dropText" variant="warning">
                <b-dropdown-item @click="changeDrop(0)">카테고리</b-dropdown-item>
                <b-dropdown-item @click="changeDrop(1)">제품명칭</b-dropdown-item>
              </b-dropdown>
            </b-col>
            <b-col cols="8">
              <template v-if="dropOption == 0">
                <b-row>
                  <b-col>
                    <b-form-select
                      v-model="mainCode"
                      :options="mainOpts"
                      @change="subLoad"
                    ></b-form-select>
                  </b-col>
                  <b-col>
                    <b-form-select
                      v-model="subCode"
                      :options="subOpts"
                      :disabled="subOpts.length == 1"
                      @change="productsLoad"
                    ></b-form-select>
                  </b-col>
                </b-row>
              </template>
              <template v-if="dropOption == 1">
                <b-form-input
                  placeholder="검색할 제품명을 입력하세요..."
                  v-model="productName"
                  @keyup="productsLoad"
                ></b-form-input>
              </template>
            </b-col>
            <b-col cols="2">
              <b-button variant="warning" :disabled="!canSearch" @click="productsLoad"
                >조회</b-button
              >
            </b-col>
          </b-row>
        </b-col>
        <b-col>
          <b-row align-v="center" align-h="center">
            <b-col>
              <b-row class="actionBox" align-h="center" align-v="center">
                <b-col cols="4">
                  <b-form-group label="취급여부" v-slot="{ ariaDescribedby }">
                    <b-form-radio-group
                      class="opts"
                      v-model="filterOpt1"
                      :options="filterOpts1"
                      :aria-describedby="ariaDescribedby"
                      :disabled="products.length == 0"
                    ></b-form-radio-group
                  ></b-form-group>
                </b-col>
                <b-col cols="4">
                  <b-form-group label="할인여부">
                    <b-form-radio-group
                      class="opts"
                      v-model="filterOpt2"
                      :options="filterOpts2"
                      :disabled="products.length == 0"
                    ></b-form-radio-group>
                  </b-form-group>
                </b-col>
                <b-col cols="4">
                  <vue-slider
                    v-model="filterOpt3"
                    dotSize="12"
                    :min="priceRange[0]"
                    :max="priceRange[1]"
                    :interval="interval"
                    tooltip="always"
                    silent
                    adsorb
                    :enable-cross="false"
                    :min-range="minRange"
                    :max-range="priceRange[1]"
                    lazy
                    width="250px"
                    :tooltip-formatter="'{value}만원'"
                    :tooltip-placement="['bottom', 'top']"
                    :disabled="productRows == 0"
                  ></vue-slider>
                </b-col>
              </b-row>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
      <b-row align-h="end" v-show="userInfo">
        <b-col class="mt-3 mr-1" cols="1">
          <b-button variant="danger" @click="move(`/products/regist`)">제품 등록</b-button>
        </b-col>
      </b-row>
      <hr />
      <b-row class="tableBox">
        <b-table
          id="product-table"
          head-variant="dark"
          hover
          striped
          show-empty
          primary-key="productCode"
          empty-text="조회된 제품이 없습니다."
          :items="filteredData"
          :fields="productField"
          :per-page="productPerPage"
          :current-page="productCurrent"
          @row-clicked="rowEventHandler"
        >
          <template #cell(composition)="data">
            {{
              data.item.productInfo.composition
                ? data.item.productName + " " + data.item.productInfo.composition
                : data.item.productInfo.volume
                ? data.item.productName + " " + data.item.productInfo.volume
                : data.item.productName
            }}
          </template>
          <!-- <template #cell(discounted)="data">
            {{ data.item.productInfo.price - data.item.productInfo.totalPrice }}
          </template> -->
        </b-table>
      </b-row>
      <b-row class="pagination">
        <b-pagination
          v-model="productCurrent"
          :total-rows="productRows"
          :per-page="productPerPage"
          aria-controls="product-table"
          align="center"
          class="mt-2"
          pills
          limit="10"
          @change="changeProduct"
        >
        </b-pagination>
      </b-row>
    </div>
  </div>
</template>

<script>
import { mainCodeList, subCodeList, productList, productListByName } from "@/api/product";
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";
import { mapState } from "vuex";
const userStore = "UserStore";
const dropTexts = ["카테고리", "제품명칭"];
export default {
  components: {
    VueSlider,
  },
  data() {
    return {
      dropOption: 0,
      dropText: dropTexts[0],

      mainCode: null,
      subCode: null,
      mainOpts: [],
      subOpts: [],

      filterOpts1: [
        { text: "전체", value: 0 },
        { text: "취급", value: true },
        { text: "미취급", value: false },
      ],
      filterOpt1: 0,
      filterOpts2: [
        { text: "전체", value: 0 },
        { text: "정상", value: false },
        { text: "할인", value: true },
      ],
      filterOpt2: 0,

      filterOpt3: [0, 0],

      filterKeys: ["sale", "discount", "totalPrice"],
      interval: 5,
      minRange: 5,

      productName: "",
      products: [],
      productPerPage: 15,
      productCurrent: 1,
      productRows: 178,
      productField: [
        {
          key: "productCode",
          label: "상품번호",
          sortable: true,
        },
        {
          key: "mainName",
          label: "대분류",
          sortable: true,
        },
        {
          key: "subName",
          label: "중분류",
          sortable: true,
        },
        {
          key: "composition",
          label: "제품명",
        },
        {
          key: "productInfo.sale",
          label: "판매여부",
          sortable: true,
          formatter: (value) => {
            return value ? "판매" : "미취급";
          },
          tdClass: (value) => {
            return value ? "table-success" : "table-danger";
          },
        },

        {
          key: "productInfo.price",
          label: "정상가격(원)",
          sortable: true,
          formatter: this.getPriceFormat,
        },
        {
          key: "productInfo",
          label: "할인여부",
          sortable: true,
          formatter: (info) => {
            if (info.discount) {
              if (info.startdiscount <= this.today && this.today <= info.enddiscount) {
                return "행사중";
              } else if (info.startdiscount > this.today) {
                return "행사예정";
              } else {
                return "";
              }
            } else {
              return "";
            }
          },
          tdClass: (info) => {
            if (info.discount) {
              if (info.startdiscount <= this.today && this.today <= info.enddiscount) {
                return "table-info";
              } else if (info.startdiscount > this.today) {
                return "table-warning";
              } else {
                return "";
              }
            } else {
              return "";
            }
          },
        },
        {
          key: "productInfo.discountRate",
          label: "할인율(%)",
          sortable: true,
          formatter: (value) => {
            return value > 0 ? value + "%" : "";
          },
        },
        {
          key: "productInfo.discountPrice",
          label: "할인금액(원)",
          sortable: true,
          formatter: this.getPriceFormat,
        },
        // {
        //   key: "discounted",
        //   label: "총 할인금액(원)",
        //   formatter: (value, key, item) => {
        //     console.log(value, key);
        //     return this.getPriceFormat(item.productInfo.price - item.productInfo.totalPrice);
        //   },
        // },
        {
          key: "productInfo.totalPrice",
          label: "판매가격(원)",
          sortable: true,
          formatter: this.getPriceFormat,
        },
        {
          key: "productInfo.health",
          label: "건강기능",
          sortable: true,
          formatter: (value) => {
            return value == 1 ? "O" : "";
          },
          tdClass: (value) => {
            return value == 1 ? "table-primary" : "";
          },
        },
        {
          key: "productInfo.productLevel",
          label: "BEST",
          sortable: true,
          formatter: (value) => {
            return value == 1 ? "추천" : "";
          },
          tdClass: (value) => {
            return value == 1 ? "table-info" : "";
          },
        },
      ],
      canSearch: false,
    };
  },

  methods: {
    changeDrop(value) {
      this.dropText = dropTexts[value];
      this.dropOption = value;
    },
    mainLoad() {
      this.clearOption("main");
      this.clearOption("sub");

      mainCodeList(({ data }) => {
        data.map((item) => this.mainOpts.push({ value: item.mainCode, text: item.mainName }));
      });
    },

    subLoad() {
      this.clearOption("sub");
      this.productsLoad();
      this.activeBtn();
      subCodeList(this.mainCode, ({ data }) => {
        data.map((item) => this.subOpts.push({ value: item.subCode, text: item.subName }));
      });
    },

    productsLoad() {
      switch (this.dropOption) {
        case 0:
          productList(this.subCode ? this.subCode : this.mainCode, ({ data }) => {
            this.products = data;
            this.productRows = this.products.length;
          });
          break;
        case 1:
          productListByName(this.productName, ({ data }) => {
            let fixedData = [];
            data.forEach((item) => {
              const { category, ...rest } = item;
              fixedData.push({ ...category, productInfo: rest });
            });
            this.products = fixedData;
            this.productRows = this.products.length;
            this.changeProduct(1);
            this.productCurrent = 1;
          });
          break;
      }
    },

    getPriceFormat(value) {
      const part = value.toString().split(".");
      part[0] = part[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      return part.join(".");
    },
    activeBtn() {
      this.canSearch = true;
    },
    clearOption(name) {
      if (name == "main") {
        this.mainCode = null;
        this.subCode = null;
        this.mainOpts = [{ value: null, text: "대분류 선택" }];
        this.subOpts = [{ value: null, text: "중분류 선택" }];
      }
      if (name == "sub") {
        this.subCode = null;
        this.subOpts = [{ value: null, text: "중분류 선택" }];
      }
      this.canSearch = false;
    },
    rowEventHandler(item) {
      this.move(`/products/detail/${item.productCode}`);
    },
    changeProduct(page) {
      window.sessionStorage.setItem("productCurrent", JSON.stringify({ page }));
    },

    move(payload) {
      this.$router.push(`${payload}`);
    },
  },

  computed: {
    // productRows() {
    //   return this.products.length;
    // },
    today() {
      const now = new Date();
      const today = `${now.getFullYear()}-${
        now.getMonth() + 1 < 10 ? "0" + (now.getMonth() + 1) : now.getMonth() + 1
      }-${now.getDate() < 10 ? "0" + now.getDate() : now.getDate()}`;
      return today;
    },
    ...mapState(userStore, ["userInfo"]),
    priceRange() {
      return [
        0,
        Math.max(...this.products.map((item) => Math.ceil(item.productInfo.price / 10000))),
      ];
    },
    filteredData() {
      return this.products.filter((item) => {
        // console.log(item);
        // // console.log(this.filterOpt1),item[this.filterKeys[0]]);
        let keep = true;
        keep =
          keep &
          (this.filterOpt1 === 0 || item.productInfo[this.filterKeys[0]] === this.filterOpt1);
        keep =
          keep &
          (this.filterOpt2 === 0 || item.productInfo[this.filterKeys[1]] === this.filterOpt2);
        keep =
          keep &
          (item.productInfo[this.filterKeys[2]] >= this.filterOpt3[0] * 10000 &&
            item.productInfo[this.filterKeys[2]] <= this.filterOpt3[1] * 10000);
        return keep;
      });
    },
  },
  created() {
    this.mainLoad();
    productList("", ({ data }) => {
      this.products = data;
      this.filterOpt3 = [
        0,
        Math.max(...this.products.map((item) => item.productInfo.price)) / 10000,
      ];
      this.productRows = this.products.length;
    });
  },
  beforeMount() {
    const productPage = JSON.parse(window.sessionStorage.getItem("productCurrent"));
    this.productCurrent = productPage ? productPage.page : 1;
  },
  watch: {
    dropOption(value) {
      if (value == 0) {
        this.mainLoad();
        productList("", ({ data }) => {
          this.products = data;
          this.filterOpt3 = [
            0,
            Math.max(...this.products.map((item) => item.productInfo.price)) / 10000,
          ];
          this.productRows = this.products.length;
        });
      } else {
        this.productName = "";
        this.products = [];
        this.canSearch = false;
      }
      this.filterOpt1 = 0;
      this.filterOpt2 = 0;
      this.filterOpt3 = this.priceRange;
    },
    productName(value) {
      if (value.length >= 1) {
        this.canSearch = true;
      } else {
        this.canSearch = false;
      }
    },
    priceRange(value) {
      this.filterOpt3 = value;
      this.minRange = this.interval = 1;
    },
    filteredData(data) {
      this.productRows = data.length;
    },
  },
};
</script>

<style scoped>
#home {
  position: relative;
}

#container {
  width: 88%;
  margin: 0 auto;
  height: 110vh;
  background: white;
  margin-top: 20px;
  border-radius: 10px;
}

.optionBox {
  position: relative;
  width: 90%;
  left: 4%;
  top: 24%;
  background: rgba(0, 0, 0, 0.7);
  padding: 16px;
  border-radius: 5px;
}

.actionBox {
  display: relative;
  /* width: 95%; */
  margin-top: 2.5%;
  margin-right: 2.5%;
  background: gainsboro;
  /* padding: 13px; */
  border-radius: 5px;
}

.tableBox {
  position: relative;
  width: 98%;
  margin: 0 auto;
  margin-top: 1%;
}

.pagination {
  position: relative;
  margin: 0 auto;
}

.button {
  position: relative;
  top: 3%;
  left: -45.5%;
}

.opts {
  border: 1px solid black;
  border-radius: 10px;
}
</style>

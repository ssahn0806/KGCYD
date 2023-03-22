<template>
  <div class="home">
    <div id="container" :visible="visible">
      <b-card no-body class="card">
        <b-tabs class="card" card justified :value="tabIdx" @input="changeTab">
          <b-tab title="공지사항">
            <div class="btncon" v-show="userInfo">
              <b-button class="mb-2" variant="danger" @click="move(`/articles/regist/n`)"
                >공지 등록하기</b-button
              >
            </div>
            <b-table
              id="notice-table"
              head-row-variant="info"
              hover
              striped
              show-empty
              empty-text="등록된 공지사항이 없습니다."
              :items="notice"
              :fields="noticeField"
              :per-page="noticePerPage"
              :current-page="noticeCurrent"
              @row-clicked="eventClick"
            ></b-table>
          </b-tab>
          <b-tab title="행사현황">
            <div class="btncon">
              <b-row align-h="center" align-v="center">
                <b-col cols="mb-1" class="actionBox mb-2 mr-4">
                  <b-form-group label="행사상태" v-slot="{ ariaDescribedby }">
                    <b-form-radio-group
                      class="opts"
                      v-model="filterOpt1"
                      :options="filterOpts1"
                      :aria-describedby="ariaDescribedby"
                      :disabled="notice.length == 0"
                    ></b-form-radio-group
                  ></b-form-group>
                </b-col>
                <b-col cols="ml-2" class="actionBox mr-3 ml-4" v-show="userInfo">
                  <b-button variant="info" @click="move(`/articles/regist/e`)"
                    >행사 등록하기</b-button
                  ></b-col
                >
              </b-row>
            </div>
            <b-table
              id="event-table"
              head-row-variant="danger"
              hover
              striped
              show-empty
              empty-text="진행중인 행사가 없습니다."
              :items="filteredData"
              :fields="eventField"
              :per-page="eventPerPage"
              :current-page="eventCurrent"
              @row-clicked="eventClick"
            ></b-table>
          </b-tab>
        </b-tabs>
        <div v-show="tabIdx == 0">
          <b-pagination
            v-model="noticeCurrent"
            :total-rows="noticeRows"
            :per-page="noticePerPage"
            @change="changeNotice"
            aria-controls="notice-table"
            align="center"
            class="mt-2"
            pills
          >
          </b-pagination>
        </div>
        <div v-show="tabIdx == 1">
          <b-pagination
            v-model="eventCurrent"
            :total-rows="eventRows"
            :per-page="eventPerPage"
            @change="changeEvent"
            aria-controls="event-table"
            align="center"
            class="mt-2"
            pills
          >
          </b-pagination>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import { articleList } from "@/api/article";
import { mapState } from "vuex";

const userStore = "UserStore";
export default {
  data() {
    return {
      visible: false,
      notice: [],
      event: [],

      filterOpts1: [
        { text: "전체", value: 0 },
        { text: "진행", value: true },
        { text: "종료", value: false },
      ],

      filterOpt1: true,
      noticeCurrent: 1,
      noticePerPage: 10,
      noticeRows: 178,

      noticeField: [
        {
          key: "articleNo",
          label: "등록 번호",
          sortable: false,
        },
        {
          key: "subject",
          label: "공지 제목",
          sortable: false,
        },
        {
          key: "registTime",
          label: "작성 일자",
          sortable: true,
        },
      ],
      eventCurrent: 1,
      eventPerPage: 10,
      eventRows: 178,

      eventField: [
        {
          key: "articleNo",
          label: "등록 번호",
          sortable: false,
        },
        {
          key: "subject",
          label: "행사 제목",
          sortable: false,
        },
        {
          key: "startEvent",
          label: "행사 시작일",
          sortable: true,
        },
        {
          key: "endEvent",
          label: "행사 종료일",
          sortable: true,
        },
      ],
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    filteredData() {
      return this.event.filter((item) => {
        // console.log(item);
        // console.log(this.filterOpt1, this.getToday());
        let keep = true;
        keep =
          keep & (this.filterOpt1 === 0 || this.filterOpt1 == item.endEvent >= this.getToday());
        //  &&
        //   (item.startEvent == null || item.startEvent <= this.getToday())));
        return keep;
      });
    },
  },
  methods: {
    setPage(page) {
      this.currentPage = page;
    },
    setLSPage(page) {
      window.localStorage.setItem("currentPage", JSON.stringify({ currentPage: page }));
    },
    eventClick(item) {
      this.$router.push(`/articles/detail/${item.articleNo}`);
    },
    getToday() {
      const today = new Date();
      const year = today.getFullYear();
      const month = today.getMonth() + 1;
      const day = today.getDate();
      return `${year}-${month < 10 ? "0" + month : month}-${day < 10 ? "0" + day : day}`;
    },
    move(payload) {
      this.$router.push(`${payload}`);
    },
    changeTab(tabIdx) {
      window.sessionStorage.setItem("tabIdx", JSON.stringify({ tabIdx }));
    },
    changeNotice(page) {
      window.sessionStorage.setItem("noticeCurrent", JSON.stringify({ page }));
    },
    changeEvent(page) {
      window.sessionStorage.setItem("eventCurrent", JSON.stringify({ page }));
    },
  },

  created() {
    articleList(true, ({ data }) => {
      this.event = data;
      this.eventRows = this.event.length;
    });
    articleList(false, ({ data }) => {
      this.notice = data;
      this.noticeRows = this.notice.length;
    });
  },
  beforeMount() {
    const eventPage = JSON.parse(window.sessionStorage.getItem("eventCurrent"));
    this.eventCurrent = Number(eventPage ? eventPage.page : 1);
    const noticePage = JSON.parse(window.sessionStorage.getItem("noticeCurrent"));
    this.noticeCurrent = Number(noticePage ? noticePage.page : 1);
    const tabIdx = JSON.parse(window.sessionStorage.getItem("tabIdx"));
    this.tabIdx = Number(tabIdx ? tabIdx.tabIdx : 0);
  },

  watch: {
    filteredData(data) {
      this.eventRows = data.length;
    },
  },
};
</script>

<style scoped>
.home {
  position: relative;
}

#container {
  width: 70%;
  margin: 0 auto;
  border-radius: 5px;
  margin-top: 30px;
}

.card {
  height: 800px;
  background: rgba(255, 255, 255, 1);
  border-radius: 10px;
}

.btncon {
  display: flex;
  justify-content: flex-end;
}

.actionBox {
  display: relative;
  /* width: 95%; */
  /* margin-top: 2.5%; */
  /* margin-right: 2.5%; */
  background: rgba(0, 0, 0, 0.2);
  /* padding: 13px; */
  border-radius: 5px;
}

.opts {
  border: 1px solid black;
  border-radius: 10px;
}
</style>

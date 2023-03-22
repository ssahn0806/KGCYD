<template>
  <div id="container">
    <b-row align-h="center">
      <b-button class="mt-3 mr-3" @click="move('/products')" variant="success"
        >제품 목록 보기</b-button
      >
      <b-button
        class="mt-3 ml-3"
        @click="visible = !visible"
        :aria-expanded="visible ? 'true' : 'false'"
        aria-controls="collapse-1"
        :variant="visible ? 'dark' : 'danger'"
        >{{ visible ? "게시글 목록 닫기" : "게시글 목록 열기" }}</b-button
      >
    </b-row>
    <b-collapse id="collapse-1" v-model="visible" class="mt-3" appear>
      <b-card no-body class="card">
        <b-tabs class="card" card justified>
          <b-tab title="공지사항">
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

            <b-pagination
              v-model="noticeCurrent"
              :total-rows="noticeRows"
              :per-page="noticePerPage"
              aria-controls="notice-table"
              align="center"
              pills
            >
            </b-pagination>
          </b-tab>
          <b-tab title="행사현황">
            <b-table
              id="event-table"
              head-row-variant="danger"
              hover
              striped
              show-empty
              empty-text="진행중인 행사가 없습니다."
              :items="event"
              :fields="eventField"
              :per-page="eventPerPage"
              :current-page="eventCurrent"
              @row-clicked="eventClick"
            ></b-table>
            <b-pagination
              v-model="eventCurrent"
              :total-rows="eventRows"
              :per-page="eventPerPage"
              aria-controls="event-table"
              align="center"
              pills
            >
            </b-pagination>
          </b-tab>
        </b-tabs>
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
export default {
  props: {
    notice: {
      type: Array,
      default: () => [],
    },
    event: {
      type: Array,
      default: () => [],
    },
  },

  data() {
    return {
      visible: true,
      noticePerPage: 3,
      noticeCurrent: 1,
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
      eventPerPage: 3,
      eventCurrent: 1,
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
    noticeRows() {
      return this.notice != null ? this.notice.length : 0;
    },
    eventRows() {
      return this.event != null ? this.event.length : 0;
    },
  },

  methods: {
    eventClick(item) {
      this.move(`/articles/detail/${item.articleNo}`);
    },
    move(payload) {
      //this.$router.push("/notice/list");
      this.$router.push(`${payload}`);
    },
  },
};
</script>

<style scoped>
#container {
  width: 87.5%;
  margin: 0 auto;
  border-radius: 5px;
  /* margin-top: 10px; */
}

.card {
  /* height: 330px; */
  background: rgba(255, 255, 255, 1);
  border-radius: 10px;
}
</style>

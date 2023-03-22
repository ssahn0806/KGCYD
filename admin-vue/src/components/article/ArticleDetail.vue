<template>
  <div>
    <div id="board">
      <b-card bg-variant="light">
        <b-card-img
          :src="url"
          alt="등록된이미지가 없습니다"
          style="max-width: 20rem"
          @click="openModal"
        >
        </b-card-img>
        <hr />
        <b-row>
          <b-col>이미지 다운로드</b-col>
          <b-col
            ><a :href="`${this.baseUrl}/image/download?fileName=${article.thumbnail}`">{{
              article.thumbnail
            }}</a></b-col
          >
        </b-row>
        <hr />
        <b-card-header
          :header="article.subject"
          header-bg-variant="dark"
          header-border-variant="dark"
          header-text-variant="light"
          style="border-radius: 10px"
        ></b-card-header>
        <hr />
        <b-row>
          <b-col>
            <h5>작성자 아이디 : {{ article.userId }}</h5>
          </b-col>
          <b-col>
            <h5 v-show="article.event">
              행사 기간 : {{ article.startEvent }}~{{ article.endEvent }}
            </h5>
            <h5 v-show="!article.event">작성 일자 : {{ article.registTime }}</h5>
          </b-col>
        </b-row>
        <hr />
        <b-form-textarea v-model="article.content" max-rows="14" readonly></b-form-textarea>
        <b-row class="mr-0 mt-2" align-h="end" align-v="center">
          <b-button class="ml-2" variant="danger" @click.prevent="onClickRemove" v-show="userInfo"
            >삭제</b-button
          >
          <b-button
            class="ml-2"
            variant="success"
            @click="move(`/articles/modify/${articleNo}`)"
            v-show="userInfo"
            >수정</b-button
          >
          <b-button class="ml-2" variant="primary" @click="move(`/articles`)">목록</b-button>
        </b-row>
      </b-card>
      <b-modal id="imageViewer" size="lg" ok-only ok-title="닫기">
        <b-embed type="embed" aspect="4by3" :src="url" allowfullscreen></b-embed>
      </b-modal>
    </div>
  </div>
</template>

<script>
import { articleDetail, articleRemove } from "@/api/article.js";
import { mapState } from "vuex";
const userStore = "UserStore";
export default {
  props: {
    articleNo: {
      type: String,
    },
  },

  data() {
    return {
      article: {},
      baseUrl: process.env.VUE_APP_BASE_URL,
      url: "",
    };
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    openModal() {
      this.$bvModal.show("imageViewer");
    },
    move(payload) {
      this.$router.push(payload);
    },
    onClickRemove() {
      if (confirm("삭제하시겠습니까?")) {
        articleRemove(this.articleNo, () => {
          this.move(`/articles`);
        });
      }
    },
  },
  created() {
    this.url = this.baseUrl + process.env.VUE_APP_IMAGE_URL;
    articleDetail(this.articleNo, ({ data }) => {
      this.article = data;
      this.url += this.article.thumbnail;
    });
  },
};
</script>

<style scoped>
#board {
  width: 70%;
  margin: 0 auto;
  margin-top: 30px;
  border-radius: 10px;
}
</style>

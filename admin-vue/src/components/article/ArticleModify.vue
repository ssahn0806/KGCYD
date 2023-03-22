<template>
  <div>
    <div id="board" v-show="visible">
      <b-card bg-variant="light">
        <b-card-img
          :src="url"
          alt="등록된이미지가 없습니다"
          style="max-width: 20rem"
          @click="openModal"
        >
        </b-card-img>
        <hr />
        <b-row align-h="center" align-v="center">
          <b-col cols="7">변경할 이미지 첨부</b-col>
          <b-col cols="4"
            ><b-form-file
              accept="image/*"
              :placeholder="ph"
              v-model="file"
              browse-text="이미지 찾기"
            ></b-form-file
          ></b-col>
          <b-col>
            <b-button variant="warning" @click="clearFileInfo">삭제</b-button>
          </b-col>
        </b-row>
        <hr />
        <b-card-header
          header-bg-variant="dark"
          header-border-variant="dark"
          header-text-variant="light"
          style="border-radius: 10px"
          ><b-form-input v-model="article.subject"></b-form-input
        ></b-card-header>
        <hr />
        <b-row align-v="center" align-h="center">
          <b-col cols="4">
            <b-row align-v="center">
              <b-col cols="4">작성자 아이디</b-col>
              <b-col><b-form-input readonly v-model="article.userId"></b-form-input></b-col>
            </b-row>
          </b-col>
          <b-col cols="8">
            <b-row align-v="center" align-h="center">
              <b-col cols="4"
                ><b-form-checkbox v-model="article.event">행사 글 여부</b-form-checkbox></b-col
              >
              <b-col cols="8">
                <b-row align-v="center" v-show="article.event">
                  <b-col>
                    <b-button id="btn1" @click="openPop(1)">{{
                      article.startEvent != null ? article.startEvent : "등록되지 않음"
                    }}</b-button
                    ><b-popover
                      target="btn1"
                      title="행사 시작일"
                      triggers="click"
                      placement="bottom"
                      :show="pop1"
                    >
                      <b-calendar
                        v-model="article.startEvent"
                        block
                        @input="closePop(1)"
                      ></b-calendar> </b-popover
                  ></b-col>
                  ~
                  <b-col
                    ><b-button id="btn2" @click="openPop(2)">{{
                      article.endEvent ? article.endEvent : "등록되지 않음"
                    }}</b-button
                    ><b-popover
                      target="btn2"
                      title="행사 종료일"
                      triggers="click"
                      placement="bottom"
                      :show="pop2"
                    >
                      <b-calendar
                        v-model="article.endEvent"
                        block
                        @input="closePop(2)"
                      ></b-calendar> </b-popover
                  ></b-col> </b-row
              ></b-col>
            </b-row>
          </b-col>
        </b-row>
        <hr />
        <b-form-textarea v-model="article.content" max-rows="14"></b-form-textarea>

        <b-row class="mr-0 mt-2" align-h="end" align-v="center">
          <b-button class="ml-2" variant="primary" @click="move(`/articles/detail/${articleNo}`)"
            >취소</b-button
          >
          <b-button class="ml-2" variant="danger" @click="onClickUpdate">수정</b-button>
        </b-row>
      </b-card>
      <b-modal id="imageViewer" size="lg" ok-only ok-title="닫기">
        <b-embed type="embed" aspect="4by3" :src="url" allowfullscreen></b-embed>
      </b-modal>
    </div>
  </div>
</template>

<script>
import { articleDetail, articleModify } from "@/api/article.js";

export default {
  props: {
    articleNo: {
      type: String,
    },
  },

  data() {
    return {
      visible: false,
      article: {},
      baseUrl: process.env.VUE_APP_BASE_URL,
      url: "",
      pop1: false,
      pop2: false,
      file: null,
      ph: "",
    };
  },

  methods: {
    openModal() {
      this.$bvModal.show("imageViewer");
    },
    move(payload) {
      this.$router.push(payload);
    },
    onClickUpdate() {
      if (this.article.event && this.article.startEvent == null && this.article.endEvent == null) {
        alert("행사 글인 경우 기간을 등록해야 합니다.");
        return;
      }

      let formData = new FormData();
      formData.append(
        "board",
        new Blob([JSON.stringify(this.article)], { type: "application/json" })
      );
      formData.append("upfile", this.file);
      articleModify(formData, () => {
        this.move(`/articles/detail/${this.articleNo}`);
      });
    },
    clearFileInfo() {
      this.file = null;
      this.ph = "";
    },
    openPop(id) {
      if (id == 1 && !this.pop1) {
        this.pop1 = true;
      }
      if (id == 2 && !this.pop2) {
        this.pop2 = true;
      }
    },
    closePop(id) {
      if (id == 1 && this.pop1) {
        this.pop1 = false;
      }
      if (id == 2 && this.pop2) {
        this.pop2 = false;
      }
    },
    async convertURLtoFile(url) {
      const response = await fetch(url);
      const data = await response.blob();
      const ext = url.split(".").pop(); // url 구조에 맞게 수정할 것
      const filename = url.split("/").pop(); // url 구조에 맞게 수정할 것
      const metadata = { type: `image/${ext}` };
      return new File([data], filename, metadata);
    },
  },
  created() {
    this.url = this.baseUrl + process.env.VUE_APP_IMAGE_URL;
    articleDetail(this.articleNo, ({ data }) => {
      this.article = { ...data };
      this.url += this.article.thumbnail;
      this.convertURLtoFile(this.url).then((res) => {
        this.file = res;
        this.ph = this.file.name;
        this.visible = true;
      });
    });
  },

  watch: {
    "article.event"(to) {
      if (!to) {
        // const today = new Date();
        // const year = today.getFullYear();
        // const month = today.getMonth() + 1 < 10 ? `0${today.getMonth() + 1}` : today.getMonth() + 1;
        // const day = today.getDate() < 10 ? `0${today.getDate()}` : today.getDate();
        if (!this.article.endEvent) this.article.endEvent = null;
        if (!this.article.startEvent) this.article.startEvent = null;
      }
    },
    file(to) {
      if (to != null) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.url = e.target.result;
        };
        reader.readAsDataURL(this.file);
      } else {
        this.url = "";
      }
    },
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

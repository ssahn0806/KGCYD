<template>
  <div id="container">
    <b-row class="justify-content-center pt-5">
      <b-img :src="require(`@/assets/kgc.png`)" width="500px" />
    </b-row>
    <b-row class="justify-content-center pt-5">
      <b-card
        bg-variant="danger"
        style="width: 500px"
        text-variant="black"
        sub-title-text-variant="white"
        title="챗봇 서버 관리자센터"
        sub-title="관리자라면 로그인하세요."
      >
        <b-alert show variant="danger" v-if="isLoginError"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <b-row>
          <b-col class="col-9">
            <b-row align-v="center">
              <b-col class="col-3">
                <b-icon-person-fill font-scale="3"></b-icon-person-fill>
              </b-col>
              <b-col class="col-9">
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  placeholder="아이디 입력..."
                  @keyup.enter="confirm"
                ></b-form-input
              ></b-col>
            </b-row>
            <b-row align-v="center">
              <b-col class="col-3">
                <b-icon-key-fill font-scale="3"></b-icon-key-fill>
              </b-col>
              <b-col class="col-9">
                <b-form-input
                  id="userpwd"
                  v-model="user.userpwd"
                  required
                  type="password"
                  placeholder="비밀번호 입력..."
                  @keyup.enter="confirm"
                ></b-form-input> </b-col
            ></b-row>
          </b-col>
          <b-col class="col-3" align-self="center">
            <b-button variant="warning" @click="confirm">
              <b-icon-unlock-fill font-scale="4.5"></b-icon-unlock-fill>
            </b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-row>
    <b-row align-v="center" align-h="center" class="mt-3">
      <b-button size="lg" variant="primary" @click="move(`/`)"
        >관리자가 아니라면 이곳을 클릭하세요</b-button
      >
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "UserStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    move(payload) {
      this.$router.push(payload);
    },
  },
};
</script>

<style scoped>
#container {
  width: 80%;
  margin: 0 auto;
}
</style>

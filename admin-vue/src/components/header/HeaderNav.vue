<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="danger">
      <b-navbar-brand href="/">
        <b-img :src="require(`@/assets/kgc.png`)" height="50" @click="move(`/`)"></b-img>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav v-show="userInfo">
          <b-nav-item active-class="active" @click="move(`/`)">메인</b-nav-item>
          <b-nav-item active-class="active" @click="move(`/products`)">제품 관리</b-nav-item>
          <b-nav-item active-class="active" @click="move(`/articles`)">게시글 관리</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav v-show="userInfo == null">
          <b-nav-item active-class="active" @click="move(`/`)">메인</b-nav-item>
          <b-nav-item active-class="active" @click="move(`/products`)">제품 조회</b-nav-item>
          <b-nav-item active-class="active" @click="move(`/articles`)">게시글 조회</b-nav-item>
        </b-navbar-nav>
        <div id="time">
          <h3 style="color: black; font-size: 25px">{{ nowDate }} {{ nowTime }}</h3>
        </div>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <strong>{{ userInfo.username }}({{ userInfo.userid }})님</strong> 환영합니다.
            </template>
            <b-dropdown-item @click="move(`/user/myPage`)">내정보 보기</b-dropdown-item>
            <b-dropdown-item @click.prevent="onClickLogout">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-show="userInfo == null">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <strong>관리자이신가요?</strong>
            </template>
            <b-dropdown-item @click="move(`/user/login`)">로그인하기</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const UserStore = "UserStore";
export default {
  name: "HeaderNav",

  data() {
    return {
      nowDate: "",
      nowTime: "00:00:00",
    };
  },
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  created() {
    this.setTime();
    setInterval(() => this.setTime(), 1000);
  },
  methods: {
    ...mapActions(UserStore, ["userLogout"]),
    setTime() {
      this.nowDate = new Date().toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "numeric",
        day: "numeric",
        weekday: "long",
      });
      const now = new Date();
      const hh = now.getHours() < 10 ? "0" + now.getHours() : now.getHours();
      const mm = now.getMinutes() < 10 ? "0" + now.getMinutes() : now.getMinutes();
      const ss = now.getSeconds() < 10 ? "0" + now.getSeconds() : now.getSeconds();
      this.nowTime = `${hh}:${mm}:${ss}`;
    },
    onClickLogout() {
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/user/login") this.$router.push({ name: "userLogin" });
    },
    move(payload) {
      this.$router.push(`${payload}`);
    },
  },
};
</script>

<style scoped>
#time {
  width: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<template>
  <div class="home">
    <header-nav />
    <div class="info">
      <b-jumbotron>
        <template #header>마이 페이지</template>

        <template #lead> 현재 로그인중인 관리자의 정보를 확인할 수 있습니다. </template>

        <hr class="my-4" />

        <b-row>
          <b-col>
            <b-row class="subinfo">
              <b-col>ID</b-col>
              <b-col>{{ userInfo.userid }}</b-col>
            </b-row>
            <b-row class="subinfo">
              <b-col>성명</b-col>
              <b-col>{{ userInfo.username }}</b-col>
            </b-row>
            <b-row class="subinfo">
              <b-col>이메일</b-col>
              <b-col>{{ userInfo.email }}</b-col>
            </b-row>
            <b-row class="subinfo">
              <b-col>연락처</b-col>
              <b-col>{{ userInfo.tel }}</b-col>
            </b-row>
            <b-row class="subinfo">
              <b-col>링크</b-col>
              <b-col
                ><b-link target="_blank" :href="userInfo.link">{{ userInfo.link }}</b-link></b-col
              >
            </b-row>
            <hr class="my-4" />

            <b-row>
              <div id="map"></div>
            </b-row>
            <b-row class="subinfo">
              <b-col>주소</b-col>
              <b-col>{{ userInfo.address }}</b-col>
            </b-row>
          </b-col>
        </b-row>

        <hr class="my-4" />
        <b-row align-h="center">
          <b-button class="mr-2" variant="danger" @click.prevent="onClickLogout">로그아웃</b-button>
          <b-button class="ml-2" variant="success" @click="move(`/user/modify`)">정보수정</b-button>
        </b-row>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import HeaderNav from "@/components/header/HeaderNav.vue";
import { mapState, mapActions } from "vuex";

const userStore = "UserStore";
export default {
  components: {
    HeaderNav,
  },

  data() {
    return {
      map: null,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    onClickLogout() {
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/user/login") this.$router.push({ name: "userLogin" });
    },
    move(payload) {
      this.$router.push(`${payload}`);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.userInfo.lat, this.userInfo.lng),
        level: 1,
        zoomable: true,
        draggable: false,
      };
      //지도 생성
      this.map = new kakao.maps.Map(container, options);
      this.initControl();
    },

    initControl() {
      var mapTypeControl = new kakao.maps.MapTypeControl();

      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      this.initMarker();
    },

    initMarker() {
      var markerPosition = new kakao.maps.LatLng(this.userInfo.lat, this.userInfo.lng);

      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
  },

  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_MAP_KEY}&libraries=services`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },
};
</script>

<style scoped>
.home {
  position: relative;
}

.info {
  width: 40%;
  margin: 0 auto;
  margin-top: 10px;
}

.subinfo {
  width: 90%;
  margin: 0 auto;
  margin-top: 5px;
}

#map {
  width: 90%;
  height: 240px;
  margin: 0 auto;
}
</style>

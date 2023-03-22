<template>
  <div class="home">
    <header-nav />
    <div class="info">
      <b-jumbotron>
        <template #header>정보 수정</template>

        <template #lead> 현재 로그인중인 관리자의 정보를 수정할 수 있습니다. </template>

        <hr class="my-4" />

        <b-row>
          <b-col>
            <b-row class="subinfo" align-v="center">
              <b-col>ID</b-col>
              <b-col><b-form-input readonly v-model="user.userid"></b-form-input></b-col>
            </b-row>
            <b-row class="subinfo" align-v="center">
              <b-col>PW</b-col>
              <b-col><b-form-input type="password" v-model="user.userpwd"></b-form-input></b-col>
            </b-row>
            <b-row class="subinfo" align-v="center">
              <b-col>성명</b-col>
              <b-col><b-form-input v-model="user.username"></b-form-input></b-col>
            </b-row>
            <b-row class="subinfo" align-v="center">
              <b-col>이메일</b-col>
              <b-col><b-form-input type="email" v-model="user.email"></b-form-input></b-col>
            </b-row>
            <b-row class="subinfo" align-v="center">
              <b-col>연락처</b-col>
              <b-col><b-form-input v-model="user.tel"></b-form-input></b-col>
            </b-row>
            <b-row class="subinfo" align-v="center">
              <b-col>링크</b-col>
              <b-col><b-form-input type="url" v-model="user.link"></b-form-input></b-col>
            </b-row>
            <hr class="my-4" />

            <b-row class="subinfo">
              <b-col
                ><h5 style="color: coral">
                  클릭한 위치로 마커가 이동하고, 변경된 주소가 하단에 표시됩니다.
                </h5></b-col
              >
            </b-row>
            <b-row>
              <div id="map"></div>
            </b-row>
            <b-row class="subinfo">
              <b-col>주소</b-col>
              <b-col>{{ this.user.address }}</b-col>
            </b-row>
          </b-col>
        </b-row>

        <hr class="my-4" />
        <b-row align-h="center">
          <b-button class="mr-2" variant="primary" @click="onClickUpdate">수정</b-button>
          <b-button class="ml-2" variant="danger" @click="move(`/user/myPage`)">취소</b-button>
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
      geocoder: null,
      user: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout", "modifyUserInfo"]),

    onClickUpdate() {
      this.modifyUserInfo(this.user).then(() => {
        this.move("/user/myPage");
      });
    },
    move(payload) {
      this.$router.push(`${payload}`);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.userInfo.lat, this.userInfo.lng),
        level: 1,
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
      this.geocoder = new kakao.maps.services.Geocoder();

      var markerPosition = new kakao.maps.LatLng(this.user.lat, this.user.lng);

      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
      kakao.maps.event.addListener(this.map, "click", (mouseEvent) => {
        var latlng = mouseEvent.latLng;
        marker.setPosition(latlng);

        this.user.lat = new String(latlng.getLat()).substr(0, 9);
        this.user.lng = new String(latlng.getLng()).substr(0, 10);

        const coord = new kakao.maps.LatLng(this.user.lat, this.user.lng);
        const cb = (res, status) => {
          if (status === kakao.maps.services.Status.OK) {
            this.user.address =
              res[0].road_address != null
                ? res[0].road_address?.zone_no + " " + res[0].road_address.address_name
                : res[0].address?.zip_code + " " + res[0].address.address_name;
          }
        };

        this.geocoder.coord2Address(coord.getLng(), coord.getLat(), cb);
      });
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

  created() {
    this.user = { ...this.userInfo };
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

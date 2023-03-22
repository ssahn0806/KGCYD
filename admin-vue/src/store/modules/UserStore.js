import jwtDecode from "jwt-decode";
import router from "@/router";
import { login, findById, tokenRegeneration, logout, modify } from "@/api/user";
import Constant from "@/common/Constant.js";

const UserStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
  },
  getters: {
    userInfo(state) {
      return state.userInfo;
    },
    checkUserInfo(state) {
      return { ...state.userInfo };
    },
    checkToken(state) {
      return state.isValidToken;
    },
  },
  mutations: {
    [Constant.SET_USERINFO](state, payload) {
      state.userLogin = false;
      state.userInfo = payload;
    },
    [Constant.MODIFY_USERINFO](state, payload) {
      state.userLogin = true;
      state.userInfo = payload;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    // [Constant.MODIFY_MEMBER](context, payload) {
    //   return restApi.put("/user", payload).then(({ data }) => {
    //     context.commit(Constant.MODIFY_USERINFO, data);
    //   });
    // },
    // [Constant.REGIST_MEMBER](context, payload) {
    //   return restApi.post("/user", payload).then(() => {});
    // },
    // [Constant.DELETE_MEMBER](context, payload) {
    //   return restApi.delete(`/user/${payload}`).then(() => {
    //     context.commit(Constant.SET_USERINFO, null);
    //   });
    // },

    async modifyUserInfo({ commit }, user) {
      await modify(user, ({ data }) => {
        commit("SET_IS_LOGIN", true);
        commit("SET_USER_INFO", data);
      });
    },
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        }
        // (error) => {
        //   // console.log(error);
        // }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            // console.log("유저 정보 없음!!!!");
          }
        },
        async () => {
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      // console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            // console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            // console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  // console.log("리프레시 토큰 제거 성공");
                } else {
                  // console.log("리프레시 토큰 제거 실패");
                }
                alert("다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "userLogin" });
              },
              () => {
                // console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            // console.log("유저 정보 없음!!!!");
          }
        },
        () => {
          // console.log(error);
        }
      );
    },
  },
};

export default UserStore;

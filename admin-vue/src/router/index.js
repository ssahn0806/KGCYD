import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserModify from "@/components/user/UserModify.vue";
import store from "@/store";

Vue.use(VueRouter);

//user auth
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["UserStore/checkUserInfo"];
  const checkToken = store.getters["UserStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("UserStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "userLogin" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    // beforeEnter: onlyAuthUser,
  },
  {
    path: "/products",
    name: "products",
    redirect: "/products/list",
    component: () => import("@/views/ProductView.vue"),
    children: [
      {
        path: "list",
        component: () => import("@/components/product/ProductList.vue"),
      },
      {
        path: "regist",
        component: () => import("@/components/product/ProductRegist.vue"),
        beforeEnter: onlyAuthUser,
      },
      {
        path: "detail/:productCode",
        component: () => import("@/components/product/ProductDetail.vue"),
        props: true,
      },
      {
        path: "modify/:productCode",
        component: () => import("@/components/product/ProductModify.vue"),
        props: true,
        beforeEnter: onlyAuthUser,
      },
    ],
  },
  {
    path: "/articles",
    name: "articles",
    redirect: "/articles/list",
    component: () => import("@/views/ArticleView.vue"),
    children: [
      {
        path: "regist/:event",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/article/ArticleRegist.vue"),
        props: true,
      },
      {
        path: "detail/:articleNo",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/article/ArticleDetail.vue"),
        props: true,
      },
      {
        path: "modify/:articleNo",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/article/ArticleModify.vue"),
        props: true,
      },
      {
        path: "list",
        component: () => import("@/components/article/ArticleList.vue"),
        // beforeEnter: onlyAuthUser,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "userLogin",
        component: UserLogin,
      },
      {
        path: "myPage",
        name: "userPage",
        component: UserDetail,
        beforeEnter: onlyAuthUser,
      },
      {
        path: "modify",
        name: "userModify",
        component: UserModify,
        beforeEnter: onlyAuthUser,
      },
    ],
  },
];

const router = new VueRouter({
  routes,
  mode: "history",
});

export default router;

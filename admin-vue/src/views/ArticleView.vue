<template>
  <div>
    <header-nav></header-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNav from "@/components/header/HeaderNav.vue";
export default {
  components: {
    HeaderNav,
  },
};
</script>

<style scoped></style>

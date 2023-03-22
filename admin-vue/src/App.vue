<template>
  <div id="app">
    <router-view />
    <div id="chat">
      <a id="chat-channel-button" @click="chatChannel">
        <img
          :src="require(`@/assets/question_large_yellow_pc.png`)"
          alt="카카오톡 채널 채팅하기 버튼"
          width="80%"
        />
      </a>
    </div>
  </div>
</template>

<script>
window.Kakao.init(process.env.VUE_APP_MAP_KEY);
export default {
  methods: {
    chatChannel() {
      window.Kakao.Channel.chat({
        channelPublicId: "_xnTuxaxj",
      });
    },
  },
};
</script>
<style>
#app {
  background-color: gainsboro;
  height: 140vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#chat {
  position: fixed;
  right: -0.4%;
  bottom: 15%;
}
</style>

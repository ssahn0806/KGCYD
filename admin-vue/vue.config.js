const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  // outputDir: "../BackServer/src/main/resources/static",
  // indexPath: "../BackServer/static/index.html",
  transpileDependencies: true,
  devServer: {
    // overlay: false,
    // historyApiFallback: true,
    // proxy: {
    //   "/": {
    //     target: "http://13.115.100.242/",
    //     changeOrigin: true,
    //     secure: false,
    //   },
    // },
  },
  chainWebpack: (config) => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  },
});

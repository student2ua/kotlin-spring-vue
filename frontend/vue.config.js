const CopyWebpackPlugin = require("copy-webpack-plugin");
// https://cli.vuejs.org/ru/config/
module.exports = {
  outputDir: "target/dist",
  // Используется для размещения сгенерированных статических ресурсов (js, css, img, шрифты); (после упаковки проекта статические ресурсы будут помещены в эту папку)
  assetsDir: "static",
  // publicPath: "/"
  // publicPath: process.env.NODE_ENV === "production" ? "/openAPI/" : "/openAPI/", //"/", see backend
  publicPath: process.env.VUE_APP_BASE_URL, // "/", see backend
  //https://www.npmjs.com/package/vue-cli-plugin-webpack-bundle-analyzer
  // Если вам не нужны производственные исходные карты, вы можете установить для этого параметра значение false, чтобы ускорить производственные сборки.
  // ,productionSourceMap:false
  pluginOptions: {
    webpackBundleAnalyzer: {
      openAnalyzer: false,
    },
  },
  configureWebpack: {
    devtool: "source-map", // без eval
    plugins: [
      new CopyWebpackPlugin([
        { from: 'src/assets/img/logo_logo128.gif', to: 'logo_logo128.gif' },
        { from: 'src/assets/img/logo_logo.png', to: 'logo_logo.png' }
      ])
    ],
  },
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8098) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080/",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};

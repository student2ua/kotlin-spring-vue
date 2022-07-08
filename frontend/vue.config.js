// https://cli.vuejs.org/ru/config/
module.exports = {
  outputDir: "target/dist",
  assetsDir: "static",
  // publicPath: "/"
  publicPath: process.env.NODE_ENV === "production" ? "/openAPI/" : "/"
  // ,productionSourceMap:false

  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8098) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080/",
        ws: true,
        changeOrigin: true
      }
    }
  }
};

// https://cli.vuejs.org/ru/config/
module.exports = {
  outputDir: "target/dist",
  assetsDir: "static",
  // publicPath: "/"
  publicPath: process.env.NODE_ENV === "production" ? "/openAPI/" : "/"
  // ,productionSourceMap:false
};

/*module.exports = {
  presets: ["@vue/app"]
};*/
module.exports = {
  presets: [
    [
      "@babel/preset-env",
      {
        useBuiltIns: "entry",
        corejs: 3,
      },
    ],
  ],
};

import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import {store} from "./store";
import BootstrapVue from "bootstrap-vue";
// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Loading from "./components/lib/loading";
// import VueNotification from "vue-notification";
import Notifications from "vue-notification";

Vue.config.productionTip = false;
Vue.config.performance = true;
Vue.use(BootstrapVue);
Vue.use(Notifications);
Vue.component("loading", Loading);


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

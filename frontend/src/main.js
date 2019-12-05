import Vue from "vue";
import App from "./App.vue";
import router from "./router";
//import store from "./store";
import {store} from "./store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Loading from './components/lib/loading';

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.component('loading', Loading);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

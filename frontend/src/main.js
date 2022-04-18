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
import {AXIOS} from "./components/http-commons";

Vue.config.productionTip = false;
Vue.config.performance = true;
Vue.use(BootstrapVue);
Vue.use(Notifications);
Vue.component("loading", Loading);

/** Axios Response Intercept */
AXIOS.interceptors.response.use(
  function(response) {
    return response;
  },
  function(err) {
    if (err.response.status === 401) {
      // logout()
      this.$store.dispatch("logout");
      this.$router.push("/");
      /*  // localStorage.removeItem('authToken');
        // store.dispatch('toggleAuthState', false);
        router.push({
          name: 'Login',
          params: {
            message: 'Session has expired, please login again',
          },
        });*/
    }
    return Promise.reject(err);
  }
);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

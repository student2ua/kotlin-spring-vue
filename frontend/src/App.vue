<template>
  <div id="app">
    <b-navbar style="width: 100%" type="dark" variant="dark">
      <b-navbar-brand id="nav-brand" href="#"
        >Сайт Mark ХНЕУ ім. С. Кузнеця</b-navbar-brand
      >
      <notifications group="foo" position="bottom right"></notifications>
      <router-link to="/">
        <img
          src="./assets/img/logo_logo128.gif"
          height="30"
          width="30"
          alt="ХНЕУ ім. С. Кузнеця"
        />
      </router-link>
      <router-link
        to="/markj"
        class="nav-link text-light"
        v-if="this.$store.getters.isAuthenticated"
        >Оцінки</router-link
      >
      <router-link
        to="/login"
        class="nav-link text-light"
        v-if="!this.$store.getters.isAuthenticated"
        >Login</router-link
      >
      <a
        href="#"
        class="nav-link text-light"
        v-if="this.$store.getters.isAuthenticated"
        v-on:click="logout"
        >Logout
      </a>
    </b-navbar>
    <router-view></router-view>
  </div>
</template>

<script>
    // import {AXIOS} from "./components/http-commons";
    import Pusher from "pusher-js";

    export default {
  name: "app",
  methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push("/");
    }
  },
  created() {
    const pusher = new Pusher(process.env.VUE_APP_PUSHER_APP_KEY, {
      cluster: "eu",
      encrypted: true
    });
    const channel = pusher.subscribe("mark");
    channel.bind("new-event", newValue => {
      this.$notify({
        group: "foo",
        type: "warn",
        duration: -1,
        title: "Warning " + new Date(),
        text: newValue
      });
    });
  }
  /* see main js
 created: function () {
    AXIOS.interceptors.response.use(undefined, function (err) {
      return new Promise(function (resolve, reject) {
        if (err.status === 401 && err.config && !err.config.__isRetryRequest) {
          // if you ever get an unauthorized, logout the user
          this.logout();
          // you can also redirect to /login if needed !
        }
        throw err;
      });
    });
  }*/
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

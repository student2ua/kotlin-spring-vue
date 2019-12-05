<template>
  <div id="app">
    <b-navbar style="width: 100%" type="dark" variant="dark">
      <b-navbar-brand id="nav-brand" href="#">Сайт Mark ХНЕУ ім. С. Кузнеця</b-navbar-brand>
      <router-link to="/">
        <img src="./assets/img/logo_logo.png" height="30" width="30" alt="ХНЕУ ім. С. Кузнеця"/>
      </router-link>
      <router-link
        to="/user"
        class="nav-link text-light"
        v-if="this.$store.getters.isAuthenticated"
        >User</router-link
      >
      <router-link
        to="/selectvue"
        class="nav-link text-light"
        v-if="
          this.$store.getters.isAuthenticated
        "
        >Demo</router-link
      >
      <router-link
        to="/handson"
        class="nav-link text-light"
        v-if="
          this.$store.getters.isAuthenticated
        "
        >HandsonTable</router-link
      >
     <!-- <router-link
        to="/register"
        class="nav-link text-light"
        v-if="!this.$store.getters.isAuthenticated"
        >Register</router-link
      >-->
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
export default {
  name: "app",
  methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push("/");
    }
  }
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

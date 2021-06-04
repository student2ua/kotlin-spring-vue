import Vue from "vue";
import Router from "vue-router";
import Home from "@/components/Home";
import SignIn from "@/components/SignIn";
import selectvue2 from "@/components/SelectVue2";

Vue.use(Router);
const router = new Router({
  mode: "history",
  base: "/openAPI/",
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/home",
      name: "Home",
      component: Home
    },
    {
      path: "/login",
      name: "SignIn",
      component: SignIn
    },
    {
      path: "/markj",
      name: "selectvue2",
      component: selectvue2
    },
    // catch all redirect
    { path: "*", redirect: "/" }
  ]
});
/*router.beforeEach((to, from, next) => {
  if (!this.$store.getters.isAuthenticated) next("/login");
  else next();
});*/
export default router;

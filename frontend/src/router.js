import Vue from "vue";
import Router from "vue-router";
import HelloWorld from "@/components/HelloWorld";
import Home from "@/components/Home";
import SignIn from "@/components/SignIn";
import SignUp from "@/components/SignUp";
// import AdminPage from "@/components/AdminPage";
import UserPage from "@/components/UserPage";
import HandsonTablePage from "@/components/testvue/HandsonTablePage";
import selectvue from "@/components/SelectVue";
// import demoAxis from "@/components/testvue/demoAxis";
//import Greeting from "@/components/Greeting";

Vue.use(Router);
const router = new Router({
  mode: "history",
  base:"/openAPI/",
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
      path: "/register",
      name: "SignUp",
      component: SignUp
    },
    {
      path: "/handson",
      name: "HandsonTablePage",
      component: HandsonTablePage
    },
    {
      path: "/selectvue",
      name: "selectvue",
      component: selectvue
    },
    {
      path: "/user",
      name: "UserPage",
      component: UserPage
    },
    {
      path: "/hello-world",
      name: "HelloWorld",
      component: HelloWorld
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

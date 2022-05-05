import Vue from "vue";
import Vuex from "vuex";
// import auth from "./modules/auth";
Vue.config.devtools = process.env.NODE_ENV === "development";
Vue.use(Vuex);

const state = {
  token: sessionStorage.getItem("user-token") || "",
  role: sessionStorage.getItem("user-role") || "",
  username: sessionStorage.getItem("user-name") || "",
  authorities: sessionStorage.getItem("authorities") || "",
  status: ""
};

const getters = {
  authStatus: state => state.status,
  isAuthenticated: state => !!state.token,
  // {return state.token != null && state.token != '';}

  isAdmin: state => {
    if (state.role === "admin") {
      return true;
    } else {
      return false;
    }
  },

  getUsername: state => {
    return state.username;
  },
  getAuthorities: state => {
    return state.authorities;
  },
  getToken: state => {
    return state.token;
  }
};

const mutations = {
  auth_login: (state, user) => {
    sessionStorage.setItem("user-token", user.token);
    sessionStorage.setItem("user-name", user.name);
    sessionStorage.setItem("user-authorities", user.roles);
    state.token = user.token;
    state.username = user.username;
    state.authorities = user.roles;
    var isUser = false;
    var isAdmin = false;
    for (var i = 0; i < user.roles.length; i++) {
      if (user.roles[i].authority === "ROLE_USER") {
        isUser = true;
      } else if (user.roles[i].authority === "ROLE_ADMIN") {
        isAdmin = true;
      }
    }
    if (isUser) {
      sessionStorage.setItem("user-role", "user");
      state.role = "user";
    }
    if (isAdmin) {
      sessionStorage.setItem("user-role", "admin");
      state.role = "admin";
    }
  },
  auth_logout: () => {
    state.token = "";
    state.role = "";
    state.username = "";
    state.authorities = [];
    state.status = "";
    sessionStorage.removeItem("user-token");
    sessionStorage.removeItem("user-role");
    sessionStorage.removeItem("user-name");
    sessionStorage.removeItem("user-authorities");
  }
};
const actions = {
  login: (context, user) => {
    context.commit("auth_login", user);
  },
  logout: context => {
    context.commit("auth_logout");
  }
};

export const store = new Vuex.Store({
  state,
  getters,
  mutations,
  actions
});
// export default new Vuex.Store({
/*  export const store = new Vuex.Store({
  modules: {
    auth
  }
});*/

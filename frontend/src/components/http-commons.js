import axios from "axios";

let store, router;

// init main.js
export function initAxios(_store, _router) {
  store = _store;
  router = _router;
}

const AXIOS = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Добавляем токен авторизации к каждому запросу
AXIOS.interceptors.request.use(function (config) {
  if (store && store.getters && store.getters.getToken) {
    config.headers.Authorization = "Bearer " + store.getters.getToken;
  }
  return config;
});

/** Axios Response Intercept */
AXIOS.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    if (
      error.response &&
      error.response.status === 401 &&
      !error.config.url.includes("/auth/signin") &&
      store &&
      router &&
      typeof store.dispatch === "function" &&
      typeof router.push === "function"
    ) {
      store.dispatch("logout");
      if (this.$route.path !== "/login") {
        this.$router.push("/login");
      }
      // router.push("/login");
    }
    return Promise.reject(error);
  }
);
/*
AXIOS.interceptors.response.use(
  function (response) {
    return response;
  },
  function (err) {
    if (typeof err.response !== "undefined") {
      if (err.response && err.response.status === 401) {
        // logout()
        this.$store.dispatch("logout");
        this.$router.push("/");
        /!*  // localStorage.removeItem('authToken');
                    // store.dispatch('toggleAuthState', false);
                    router.push({
                      name: 'Login',
                      params: {
                        message: 'Session has expired, please login again',
                      },
                    });*!/
      }
    }

    return Promise.reject(err);
  }
);*/

export default AXIOS;

/*AXIOS.interceptors.response.use(
  res => res,
  e => {
    const defaultMessage =
      "Виникла помилка під час виконання запиту до сервера";
    AXIOS.$notifier.error(e?.response?.data?.error?.message || defaultMessage);
    return Promise.reject(e);
  }
);
  headers: {
    "Access-Control-Allow-Origin": [
      "http://localhost:8080",
      "http://localhost:8081",
      "https://mark.hneu.edu.ua/",
    ],
    "Access-Control-Allow-Methods": "GET,POST,DELETE,PUT,OPTIONS",
    "Access-Control-Allow-Headers": "*",
    "Access-Control-Allow-Credentials": true,
  },
*/

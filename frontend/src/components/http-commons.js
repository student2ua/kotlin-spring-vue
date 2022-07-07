import axios from "axios";

const AXIOS = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: {
    "Access-Control-Allow-Origin": [
      "http://localhost:8080",
      "http://localhost:8081",
      "https://mark.hneu.edu.ua/"
    ],
    "Access-Control-Allow-Methods": "GET,POST,DELETE,PUT,OPTIONS",
    "Access-Control-Allow-Headers": "*",
    "Access-Control-Allow-Credentials": true
  }
});
/** Axios Response Intercept */
AXIOS.interceptors.response.use(
  function(response) {
    return response;
  },
  function(err) {
    if (typeof err.response !== "undefined") {
      if (err.response && err.response.status === 401) {
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
    }

    return Promise.reject(err);
  }
);

export default AXIOS;

/*AXIOS.interceptors.response.use(
  res => res,
  e => {
    const defaultMessage =
      "Виникла помилка під час виконання запиту до сервера";
    AXIOS.$notifier.error(e?.response?.data?.error?.message || defaultMessage);
    return Promise.reject(e);
  }
);*/

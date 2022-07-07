<template>
  <div div="signin">
    <div class="login-form">
      <b-card
        title="Login"
        tag="article"
        style="max-width: 20rem;"
        class="mb-2"
      >
        <div>
          <b-alert
            :show="dismissCountDown"
            dismissible
            variant="danger"
            @dismissed="dismissCountDown = 0"
            @dismiss-count-down="countDownChanged"
          >
            {{ alertMessage }}
            <b-progress
              variant="warning"
              :max="dismissSecs"
              :value="dismissCountDown"
              height="4px"
            ></b-progress>
          </b-alert>
        </div>
        <div>
          <b-form-input
            type="text"
            placeholder="Username"
            v-model="username"
            :state="usernameState"
            aria-describedby="input-live-help input-live-feedback"
            trim
            required
          />
          <div class="mt-2"></div>

          <b-form-input
            type="password"
            placeholder="Password"
            v-model="password"
            required
            maxlength="16"
            @keydown.enter.native="login"
          />
          <!--:state="passwordState"-->
          <div class="mt-2"></div>
        </div>

        <b-button v-on:click="login" variant="primary" :disabled="isDisabled"
          >Login</b-button
        >

        <!-- <hr class="my-4" />

        <b-button variant="link">Forget password?</b-button>-->
      </b-card>
    </div>
  </div>
</template>

<script>
  // import {AUTH_REQUEST} from "../store/actions/auth";
  import AXIOS from "./http-commons";

  export default {
  name: "SignIn",
  data() {
    return {
      username: "",
      password: "",
      dismissSecs: 5,
      dismissCountDown: 0,
      alertMessage: ""
    };
  },
  methods: {
    /*      login: function () {
          const {username, password} = this
          this.$store.dispatch(AUTH_REQUEST, {
              username,
              password
          }).then(() => {
              this.$router.push("/home")
          })
  }*/
    login() {
      let self = this;
      AXIOS.post(`/auth/signin`, {
        email: this.$data.username,
        password: this.$data.password
      })
        .then(
          response => {
            // console.log(response);
            this.$store.dispatch("login", {
              token: response.data.accessToken,
              roles: response.data.authorities,
              username: response.data.username
            });
            this.$router.push("/markj");
          },
          error => {
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            self.alertMessage =
              /*error.response.data.message.length < 150
                ? error.response.data.message
                :*/ "Request error. Please, report this error website owners";
            console.log(error);
            if (error.response.status === 404)
              self.alertMessage =
                "404 not found. Сервер не може знайти дані згідно з запитом";
            this.showAlert();
          }
        )

        .catch(e => {
          console.log("catch " + e);
          if (e.response) {
            // Request made and server responded
            console.log(e.response.data);
            console.log(e.response.status);
            console.log(e.response.headers);
          } else if (e.request) {
            console.log(e.request);
          }
          this.showAlert();
        });
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs;
    }
  },
  computed: {
    usernameState() {
      return this.username.length > 2 ? true : false;
    },
    passwordState() {
      return this.password.length > 4 ? true : false;
    },
    isDisabled() {
      return !this.username || !this.password;
    }
  }
};
</script>

<style>
.login-form {
  margin-left: 38%;
  margin-top: 50px;
}
</style>

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
          /><!--:state="passwordState"-->
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
    import {AXIOS} from "./http-commons";

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
    login() {
      AXIOS.post(`/auth/signin`, {'username': this.$data.username,'password': this.$data.password
      })
        .then(
          response => {
            console.log(response);
            this.$store.dispatch("login", {
              token: response.data.accessToken,
              roles: response.data.authorities,
              username: response.data.username
            });
            this.$router.push("/home");
          },
          error => {
            this.$data.alertMessage =
              error.response.data.message.length < 150
                ? error.response.data.message
                : "Request error. Please, report this error website owners";
            console.log(error);

          }
        )

        .catch(e => {
          console.log(e);
          console.log(e.response.data.error);
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

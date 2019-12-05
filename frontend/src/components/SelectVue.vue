<template>
  <div id="selectvue">
    <!--   <section v-if="errors !== null">
            <p>We're sorry , we have Error</p>
            <p v-for="error1 in errors" v-bind:value="error1">{{error1}}}</p>
        </section>
        <section v-else>-->
    <loading v-if="loading" />
    <!--    <div v-if="loading">Loading...</div>-->
    <div v-else>
      <b-container fluid>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="selected-Sbj">Предмет:</label>
          </b-col>
          <b-col sm="10">
            <b-form-select
              id="selected-Sbj"
              v-model="selectedSbj"
              :options="subjects"
              value-field="first"
              text-field="second"
              @change="handleChangeSbj"
            >
              <template v-slot:first>
                <option :value="null" disabled
                  >-- Выберите один из вариантов --</option
                >
              </template>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="selected-LT">LessonType:</label>
          </b-col>
          <b-col sm="10">
            <div class="mt-2"></div>
            <b-form-select
              id="selected-LT"
              v-model="selectedLt"
              :options="lessonType"
              value-field="first"
              text-field="second"
              @change="handleChangeLT"
            >
              <template v-slot:first>
                <option :value="null" disabled selected
                  >-- Выберите один из вариантов --</option
                >
              </template>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="selected-PSG">PSG:</label>
          </b-col>
          <b-col sm="10">
            <div class="mt-2"></div>
            <b-form-select
              id="selected-PSG"
              v-model="selectedPSG"
              :options="psgs"
              value-field="first"
              text-field="second"
              @change="handleChangePSG"
            >
              <template v-slot:first>
                <option :value="null" disabled selected
                  >-- Выберите один из вариантов --</option
                >
              </template>
            </b-form-select>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>
<script>
    import {AXIOS} from "./http-commons";

    const BASE_URL = "/mark/rest/v199";
export default {
  name: "selectvue",
  data: () => ({
    subjects: [],
    errors: [],
    selectedSbj: null,
    lessonType: [],
    selectedLt: null,
    psgs: [],
    selectedPSG: null,
    handsontable: null,
    loading: true
  }),
  created() {
    let self = this;
    const header = { Authorization: "Bearer " + this.$store.getters.getToken };

    AXIOS.get(`${BASE_URL}/subjects`, { headers: header })
      .then(function(response) {
        console.log(
          `GET: Here's the list of subjects`,
          JSON.stringify(response.data, null, "\t")
        );
        self.subjects = response.data;
      })
      .catch(e => {
        self.errors.push(e);
      })
      .finally(() => (self.loading = false));
  },
  methods: {
    handleChangeSbj: function(e) {
      console.log("handleChangeSbj" + e);
      let self = this;
      this.lessonType = [];
      /* this.lessonType.push(
              AXIOS.get(`${BASE_URL}/subjects/${e}/lessontypes`,
                  { headers: { Authorization: "Bearer " + this.$store.getters.getToken } }));*/
      AXIOS.get(`${BASE_URL}/subjects/${e}/lessontypes`, {
        headers: { Authorization: "Bearer " + this.$store.getters.getToken }
      })
        .then(function(response) {
          console.log(
            `GET: Here's the list of LessonType`,
            JSON.stringify(response.data, null, "\t")
          );
          self.lessonType = response.data;
        })
        .catch(e => {
          self.errors.push(e);
        })
        .finally(() => (self.loading = false));
    },
    handleChangeLT: function(e) {
      console.log("handleChangeLT " + e);
      let self = this;
      this.psgs = [];
      AXIOS.get(
        `${BASE_URL}/subjects/${self.selectedSbj}/lessontypes/${e}/psgs`,
        { headers: { Authorization: "Bearer " + this.$store.getters.getToken } }
      )
        .then(function(response) {
          console.log(
            `GET: Here's the list of PSG`,
            JSON.stringify(response.data, null, "\t")
          );
          self.psgs = response.data;
        })
        .catch(e => {
          self.errors.push(e);
        })
        .finally(() => (self.loading = false));
    },
    handleChangePSG: function(e) {
      console.log("handleChangeLT " + e);
      let self = this;
      this.handsontable = null;
      AXIOS.get(
        `${BASE_URL}/subjects/${self.selectedSbj}/lessontypes/${self.selectedLt}/psgs/${e}/handsontable`,
        { headers: { Authorization: "Bearer " + this.$store.getters.getToken } }
      )
        .then(function(response) {
          console.log(
            `GET: Here's the list of HandsonTable`,
            JSON.stringify(response.data, null, "\t")
          );
          self.handsontable = response.data;
        })
        .catch(e => {
          self.errors.push(e);
        })
        .finally(() => (self.loading = false));
    },

    loadLt() {
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      /*   let configLT = {
                    params: {
                        subjectsId: this.selectedSbj 
                    },
                } configLT.subjectsId*/
      // this.$bar.start();
      AXIOS.get(`${BASE_URL}/subjects/${this.selectedSbj}/lessontypes`, {
        headers: header
      })
        .then(function(response) {
          console.log(
            `GET: Here's the list of lessontypes`,
            JSON.stringify(response.data, null, "\t")
          );
          self.lessonType = response.data;
        })
        .catch(e => {
          self.errors.push(e);
        });
      // this.$bar.finish();
    },
    getSubjects: function() {
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      return AXIOS.get(`${BASE_URL}/subjects`, { headers: header });
    },
    getLT: function() {
      if (!this.selectedSbj) return;
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      return AXIOS.get(`${BASE_URL}/subjects/${this.selectedSbj}/lessontypes`, {
        headers: header
      });
    }
  }
};
</script>

<style scoped></style>

<template>
  <div id="selectvue">
    <!--   <section v-if="errors !== null">
            <p>We're sorry , we have Error</p>
            <p v-for="error1 in errors" v-bind:value="error1">{{error1}}}</p>
        </section>
        <section v-else>-->
    <div v-if="loading">Loading...</div>
    <div v-else>
      <b-form-select v-model="selectedSbj" :options="subjects" value-field="first" text-field="second" @change="handleChange">
        <template v-slot:first>
          <option :value="null" disabled>-- Выберите один из вариантов --</option>
        </template>
      </b-form-select>

  <!--    <select v-model="selectedSbj">
        <option disabled value="">Выберите один из вариантов</option>
        <option v-for="iterSbj in subjects" v-bind:value="iterSbj">{{
          iterSbj.second
        }}</option>
      </select>-->
      <br />
      <span v-if="selectedSbj !== null">
     <!--   Выбрано: {{ selectedSbj.first }} - {{ selectedSbj.second }}-->
        Выбрано: {{ selectedSbj}} - {{ selectedSbj }}
      </span>
      <br />
  <select v-model="selectedLt">
        <option disabled value="">Выберите один из вариантов</option>
        <option v-for="iterLt in lessonType" v-bind:value="iterLt">{{
          iterLt.second
        }}</option>
      </select>
    </div>
    <!--        </section>-->
  </div>
</template>
<script>
import { AXIOS } from "./http-commons";

const BASE_URL = "/mark/rest/v199";
export default {
  name: "selectvue",
  data: () => ({
    subjects: [],
    errors: [],
    selectedSbj: null,
    lessonType: [],
    selectedLt: null,
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
    computed:{
         getSubjects:function () {
             const header = { Authorization: "Bearer " + this.$store.getters.getToken };
            return AXIOS.get(`${BASE_URL}/subjects`, { headers: header });

         },
        getLT:function () {
           if (!this.selectedSbj) return ;
            const header = { Authorization: "Bearer " + this.$store.getters.getToken };
            return AXIOS.get(`${BASE_URL}/subjects/${this.selectedSbj}/lessontypes`, { headers: header });
        }
    },
  methods: {
      handleChange: function (e) {
          console.log(e);
          let self = this;
          this.lessonType=[];
         /* this.lessonType.push(
              AXIOS.get(`${BASE_URL}/subjects/${e}/lessontypes`,
                  { headers: { Authorization: "Bearer " + this.$store.getters.getToken } }));*/
          AXIOS.get(`${BASE_URL}/subjects/${e}/lessontypes`, { headers: { Authorization: "Bearer " + this.$store.getters.getToken }  })
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
    }
  }
};
</script>

<style scoped></style>

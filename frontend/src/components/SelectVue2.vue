<template>
  <div id="selectvue2">
    <loading v-if="loading" />
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
                  >-- Оберіть один з варіантів --
                </option>
              </template>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="selected_LT">Тип викладання:</label>
          </b-col>
          <b-col sm="10">
            <div class="mt-2"></div>
            <b-form-select
              id="selected_LT"
              v-model="selectedLt"
              :options="lessonType"
              value-field="first"
              text-field="second"
              @change="handleChangeLT"
            >
              <template v-slot:first>
                <option :value="null" disabled selected
                  >-- Оберіть один з варіантів --
                </option>
              </template>
            </b-form-select>
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="2">
            <label for="selected-PSG">Група:</label>
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
                  >-- Оберіть один з варіантів --
                </option>
              </template>
            </b-form-select>
          </b-col>
        </b-row>
        <div>
          <hot-table
            class="hot handsontable htRowHeaders htColumnHeaders"
            ref="hottable"
            licenseKey="non-commercial-and-evaluation"
            :settings="hotSettingsVue"
            :data="datasets"
          />
        </div>
      </b-container>
    </div>

    <!--    <loading v-if="loading" />
    <div v-else></div>-->
  </div>
</template>
<script>
  import AXIOS from "./http-commons";
  import {HotTable} from "@handsontable/vue";

  const BASE_URL = "/mark/rest/v199";

export default {
  name: "selectvue2",
  data() {
    return {
      errors: [],
      subjects: [],
      selectedSbj: null,
      lessonType: [],
      selectedLt: null,
      psgs: [],
      selectedPSG: null,
      loading: true,

      datasets: [["", "", ""]],
      hotSettingsVue: {
        nestedHeaders: [
          ["Студент", { label: "Оцінки", colspan: 2 }],
          ["Імя", "Кол-во пропусков", "Накопительный бал"],
        ],
        columns: [{ type: "text" }, { type: "numeric" }, { type: "numeric" }],
        autoColumnSize: { useHeaders: true },
        fixedColumnsLeft: 1,
        startRows: 1,
        startCols: 3,
        selectionMode: "single",
        stretchH: "all",
        // colHeaders:"true",
        preventOverflow: 'horizontal',
        formulas: true,
        observeChanges: true,
        afterChange: changes => {
          if (changes)
            changes.forEach(([row, prop, oldValue, newValue]) => {
              console.log("afterChange: ", row, prop, oldValue, newValue);
              this.saveCell(row, prop, oldValue, newValue);
              console.log("afterSave");
            });
        }
      },
      rows: [],
      col: { "01": "test" }
    };
  },
  created() {
    let self = this;
    MarkAPI.getSubjects(this.$store)
      .then(function (response) {
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
    handleChangeSbj(e) {
      console.log("handleChangeSbj" + e);
      let self = this;
      this.lessonType = [];
      MarkAPI.getLessonTypes(this.$store, e)
        .then(function (response) {
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
    handleChangeLT(e) {
      console.log("handleChangeLT " + e);
      let self = this;
      this.psgs = [];
      MarkAPI.getPsgs(this.$store, self.selectedSbj, e)
        .then(function (response) {
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
    handleChangePSG(e) {
      let self2 = this;
      console.log("handleChangeLT " + e);
      const xah_obj_to_map = obj => {
        const mp = new Map();
        Object.keys(obj).forEach(k => {
          mp.set(k, obj[k]);
        });
        return mp;
      };
      self2.loading = true;
      self2.dataTable = [];
      // self.$store.status = "loading";
      console.time("loadUserContent");
      MarkAPI.getTable(this.$store, self2.selectedSbj, self2.selectedLt, e)
        .then((response) => {
          console.timeEnd("loadUserContent");
          self2.hotSettingsVue.nestedHeaders = response.data.nestedHeaders;
          self2.hotSettingsVue.columns = response.data.columns;
          self2.col = xah_obj_to_map(response.data.col);
          self2.rows = response.data.rows;
          // load?
          setTimeout(() => {
            self2.datasets = response.data.tabledata;
          }, 0);
          // console.log("col=" + JSON.stringify(response.data.col, null, "\t"));
          /* self2.columns = response.data.columns;
                  self2.nestedHeaders = response.data.nestedHeaders;
                  self2.dataTable = response.data.tabledata;
                  self2.rows = response.data.rows;
                  self2.col = xah_obj_to_map(response.data.col);

                  self2.$refs.hottable.hotInstance.updateSettings({
                    columns: self2.columns,
                    nestedHeaders: self2.nestedHeaders,
                    data: self2.dataTable,
                    maxRows: 20,
                    formulas: true,
                    observeChanges: true,
                    fixedColumnsLeft: 1,
                    // });
                    afterChange: changes => {
                      if (changes)
                        changes.forEach(([row, prop, oldValue, newValue]) => {
                          console.log("afterChange: ", row, prop, oldValue, newValue);
                          this.saveCell(row, prop, oldValue, newValue);
                          console.log("afterSave");
                        });
                    }
                  });*/
        })
        .catch(function(error) {
          console.log(error);
        })
        .finally(() => {
          self2.loading = false;
        });
    },
    saveCell(row, prop, oldValue, newValue) {
      let self = this;
      // self.$store.status = "loading";
      console.time("save Cell");

      const rowId = this.rows[row];
      const propId = this.col.get(prop);

      const payload = {
        row: rowId,
        prop: propId,
        oldValue,
        newValue,
      };

      const subjID = this.selectedSbj;
      const lt = this.selectedLt;
      const psgId = this.selectedPSG;

      MarkAPI.saveCell(this.$store, subjID, lt, psgId, payload)
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          self.$notify({
            group: "foo",
            type: "success",
            title: "Save",
            text: newValue
          });
        })
        .catch(function(error) {
          console.log(error);
          self.$notify({
            group: "foo",
            type: "warn",
            title: "Error on Save",
            text: error
          });
        })
        .finally(() => {
          console.timeEnd("save Cell");
        });
    }
  },
  components: {
    HotTable
  }
};
</script>

<style>
@import "~handsontable/dist/handsontable.full.css";
.htCore .htDimmed {
  text-align: left;
}

.htCore > tbody > tr:nth-child(even) > td {
  background-color: #dddddd;
}

.htCore > tbody > tr:nth-child(odd) > td {
  background-color: #fff;
}
</style>

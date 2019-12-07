<template>
  <div div="HandsonTablePageSet">
    <loading v-if="loading" />
    <!--       :settings="settings"    rowHeaders="true"    v-show="datasets.length > 0"   -->
    <hot-table

      class="hot handsontable htRowHeaders htColumnHeaders"
      ref="hottable"
      licenseKey="non-commercial-and-evaluation"
      :settings="hotSettingsVue"
      :data="datasets"

    />
  </div>
</template>

<script>
  import {HotTable} from "@handsontable/vue";
  import {AXIOS} from "../http-commons";

  export default {
  name: "HandsonTablePageSet",
  data() {
    return {
      loading: false,
      datasets: [
        ["", "Ford", "Volvo", "ToyotaNissan", "Honda"],
        ["2016", 10, 11, 12, 11],
        ["2017", 20, 11, 15, 14.5],
        ["2018", 40, 25, 12, 13],
        ["2019", 50, 22.01, 25, 15]
      ],
      hotSettingsVue: {
        nestedHeaders: [],
        columns: [
          { type: "text" },
          { type: "numeric" },
          { type: "numeric" },
          { type: "numeric" },
          { type: "numeric" }
        ],
        data: [
          ["", "Ford", "Volvo", "ToyotaNissan", "Honda"],
          ["2016", 10, 11, 12, 11],
          ["2017", 20, 11, 15, 14.5],
          ["2018", 40, 25, 12, 13],
          ["2019", 50, 22.01, 25, 15]
        ],
        autoColumnSize: { useHeaders: true },
        fixedColumnsLeft: 1,
        startRows: 5,
        startCols: 5,
        selectionMode: 'single',
        // colHeaders:"true",
        formulas: true,
        observeChanges: true,
       /* afterLoadData: initialLoad => {
          console.log("afterLoadData:", initialLoad);
        },
        afterSetDataAtCell: (changes, source) => {
          console.log(
            "afterSetDataAtCell:",
            changes,
            source,
            this,
            this.hotCore
          );
        },*/
        afterChange: changes => {
          if (changes)
            changes.forEach(([row, prop, oldValue, newValue]) => {
              console.log("afterChange: ", row, prop, oldValue, newValue);
              this.saveCell(row, prop, oldValue, newValue);
              console.log("afterSave");
            });
        },
        onAfterRemoveRow() {
          setTimeout(() => {
            this.setState({datasets: [['updated state dataset']]});
          }, 0);
        }
      },
      rows: [],
      col: { "01": "test" }
    };
  },
  methods: {
    loadUserContent() {
      let self = this;
      const xah_obj_to_map = obj => {
        const mp = new Map();
        Object.keys(obj).forEach(k => {
          mp.set(k, obj[k]);
        });
        return mp;
      };
      self.loading = true;
      // self.$store.status = "loading";
      console.time("loadUserContent");
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      const subjID = 9549;
      const lt = 1;
      const psgId = 42301;
      self.datasets=[];
      AXIOS.get(
        `/mark/rest/v199/subjects/${subjID}/lessontypes/${lt}/psgs/${psgId}/handsontable`,
        { headers: header }
      )
        .then(response => {
          console.timeEnd("loadUserContent");
          // self.$store.status = 'success';
          console.log("col=" + JSON.stringify(response.data, null, "\t"));
          /*    self.self.columns = response.data.columns;
          self.nestedHeaders = response.data.nestedHeaders;
          self.dataTable = response.data.tabledata;
          self.col = xah_obj_to_map(response.data.col);
          self.rows = response.data.rows;*/

            self.hotSettingsVue.nestedHeaders = response.data.nestedHeaders;
            self.hotSettingsVue.columns = response.data.columns;
          self.col = xah_obj_to_map(response.data.col);
          self.rows = response.data.rows;
          // self.hotSettingsVue.data = response.data.tabledata;
                // self.datasets = response.data.tabledata;
          setTimeout(() => {
            self.datasets = response.data.tabledata;
          }, 0);
          /*   self.$refs.hottable.hotInstance.updateSettings({
            columns: self.columns,
            nestedHeaders: self.nestedHeaders,
            data: self.dataTable,
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
          self.loading = false;
        });
    },
    saveCell(row, prop, oldValue, newValue) {
      let self = this;
      // self.$store.status = "loading";
      console.time("save Cell");
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };

      prop = self.col.get(prop);
      row = self.rows[row];
      const subjID = 9549;
      const lt = 1;
      const psgId = 42301;
      AXIOS.post(
        `/mark/rest/v199/subjects/${subjID}/lessontypes/${lt}/psgs/${psgId}/handsontable`,
        { row, prop, oldValue, newValue },
        { headers: header }
      ).catch(function(error) {
        console.log(error);
      });
      console.timeEnd("save Cell");
    }
  },
  mounted() {
    this.loadUserContent();
  },

  components: {
    HotTable
  }
};
</script>

<style scoped>
@import "~handsontable/dist/handsontable.full.css";
</style>

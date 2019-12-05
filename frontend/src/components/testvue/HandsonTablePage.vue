<template>
  <div div="HandsonTablePage">
    <loading v-if="loading" />
    <!--       :settings="settings"    rowHeaders="true"     -->
    <hot-table
      class="hot handsontable htRowHeaders htColumnHeaders"
      ref="hottable"
      licenseKey="non-commercial-and-evaluation"
      :data="dataTable"
      selectionMode="single"
      colHeaders="true"
    />
  </div>
</template>

<script>
  import {HotTable} from "@handsontable/vue";
  import {AXIOS} from "../http-commons";
  // import loading from "../lib/loading";
// import { mapGetters } from "vuex";

export default {
  name: "HandsonTablePage",
  data() {
    return {
      loading: false,
      col: { "01": "test" },
      dataTable: [],
      nestedHeaders: [],
      columns: [],
      rows: [],
      settings: {
        data: [
          ["", "Ford", "Volvo", "ToyotaNissan", "Honda"],
          ["2016", 10, 11, 12, 11],
          ["2017", 20, 11, 15, 14.5],
          ["2018", 40, 25, 12, 13],
          ["2019", 50, 22.01, 25, 15]
        ],

        autoColumnSize: { useHeaders: true }, // 列幅をヘッダー要素指定にする
        columns: [
          {
            type: "text"
          },
          {
            type: "numeric"
          },
          {
            type: "numeric"
          },
          {
            type: "numeric"
          },
          {
            type: "numeric"
          }
        ],
        fixedColumnsLeft: 1,
        startRows: 5,
        startCols: 5,
        // nestedHeaders: [],
        formulas: true,
        observeChanges: true,
        afterLoadData: initialLoad => {
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
        },
        afterChange: changes => {
          changes.forEach(([row, prop, oldValue, newValue]) => {
            console.log("1: ", row, prop, oldValue, newValue);
          });
        }
      }
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
      AXIOS.get(
              `/mark/rest/v199/subjects/${subjID}/lessontypes/${lt}/psgs/${psgId}/handsontable`,
        { headers: header }
      )
        .then(response => {
          console.timeEnd("loadUserContent");
          // self.$store.status = 'success';
          console.log("col=" + JSON.stringify(response.data.col, null, "\t"));
          self.columns = response.data.columns;
          self.nestedHeaders = response.data.nestedHeaders;
          self.dataTable = response.data.tabledata;
          self.rows = response.data.rows;
          self.col = xah_obj_to_map(response.data.col);

          self.$refs.hottable.hotInstance.updateSettings({
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
          });
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
  /*  computed: {
    ...mapGetters(["authStatus"]),
    loading: function() {
      return this.authStatus === "loading";
    }
  },*/
  components: {
    HotTable
  }
};
</script>

<style scoped>
  /*handsontable.full.min.css*/
  @import '~handsontable/dist/handsontable.full.css';
/*@import url("../../../node_modules/handsontable/dist/handsontable.full.css");*/
</style>

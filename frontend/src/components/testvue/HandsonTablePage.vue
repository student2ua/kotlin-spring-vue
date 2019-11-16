<template>
  <div div="HandsonTablePage">
    <loading v-if="loading" />
    <!--       :settings="settings"         -->
    <hot-table
      ref="hottable"
      licenseKey="non-commercial-and-evaluation"
      :data="dataTable"
      selectionMode="single"
      colHeaders="true"
      rowHeaders="true"
    />
  </div>
</template>

<script>
    import {HotTable} from "@handsontable/vue";
    import {AXIOS} from "../http-commons";
    // import { mapGetters } from "vuex";

export default {
  name: "HandsonTablePage",
  data() {
    return {
      col: new Map(),
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
        // formulas: true,
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

      // self.$store.status = "loading";
      console.time("loadUserContent");
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      AXIOS.get(
        "/mark/rest/v199/subjects/1/lessontypes/1/psgs/1/handsontable",
        { headers: header }
      )
        .then(response => {
          console.timeEnd("loadUserContent");
          // self.$store.status = 'success';
          console.log(
            "col=" + JSON.stringify(response.data.col, null, "\t")
          );
          self.columns = response.data.columns;
          self.nestedHeaders = response.data.nestedHeaders;
          self.dataTable = response.data.tabledata;
          self.rows = response.data.rows;
          self.col = response.data.col;

          self.$refs.hottable.hotInstance.updateSettings({
            columns: self.columns,
            nestedHeaders: self.nestedHeaders,
            data: self.dataTable,
            maxRows: 20,
            // formulas: true
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
        });
    },
    saveCell(row, prop, oldValue, newValue) {
      let self = this;
      // self.$store.status = "loading";
      console.time("save Cell");
      const header = {
        Authorization: "Bearer " + this.$store.getters.getToken
      };
      console.log(self.col);
      console.log(prop);
      console.log(self.col.get(prop));
      prop=self.col.get(prop);
      AXIOS.post(
        "/mark/rest/v199/subjects/1/lessontypes/1/psgs/1/handsontable",
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

<style
  src="../../../node_modules/handsontable/dist/handsontable.full.css"
></style>

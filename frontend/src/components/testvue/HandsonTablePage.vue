<template>
    <div div="HandsonTablePage">
        <!--                   :dataformatas="columns"-->
        <hot-table :settings="settings" licenseKey="non-commercial-and-evaluation"
                   :data="dataTable"
                   rowHeaders="true"
                   colHeaders="true"
        />
    </div>
</template>

<script>
    import {HotTable} from '@handsontable/vue';
    import {AXIOS} from "../http-commons";

    export default {
        name: "HandsonTablePage",
        data() {
            return {
                dataTable: [],
                nestedHeaders: [],
                columns: [],
                settings: {
                    fixedColumnsLeft: 1  ,
                    afterLoadData: (initialLoad) => {
                        console.log("afterLoadData:", initialLoad);
                    },
                    afterSetDataAtCell: (changes, source) => {
                        console.log("afterSetDataAtCell:", changes, source, this, this.hotCore);
                    },
                }
            };
        },
        methods: {
            loadUserContent() {
                const header = {'Authorization': "Bearer " + this.$store.getters.getToken};
                AXIOS.get("/mark/rest/v199/subjects/1/lessontypes/1/psgs/1/handsontable", {headers: header})
                    .then(response => {
                        console.log('tabledata=' + JSON.stringify(response.data.tabledata, null, "\t"));
                        this.dataTable = response.data.tabledata;
                        this.columns = response.data.columns;
                        this.nestedHeaders = response.data.nestedHeaders;
                        // this.settings.nestedHeaders
                     /*   this.HotTable.updateSettings({
                            fixedColumnsLeft: 1,
                            columns: this.columns,
                            nestedHeaders: this.nestedHeaders
                        });*/
                    })
                    .catch(error => {
                        console.log("ERROR: " + error);
                    });
            }
        },
        mounted() {
            this.loadUserContent();
        },
        components: {
            HotTable
        }
    }

</script>

<style src="../../../node_modules/handsontable/dist/handsontable.full.css">

</style>
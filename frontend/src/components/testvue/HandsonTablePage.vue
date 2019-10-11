<template>
    <div div="HandsonTablePage">
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
                dataTable:""
            };
        },
        methods: {
            loadUserContent() {
                const header = {'Authorization': "Bearer " + this.$store.getters.getToken};
                AXIOS.get("/mark/rest/v199/subjects/1/lessontypes/1/psg/1/handsontable", { headers: header })
                    .then(response => {
                        this.$data.dataTable = JSON.parse(response.data).tabledata;
                    })
                    .catch(error => {
                        console.log("ERROR: " + error.response.data);
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
<template>
    <div id="example1" class="hot">
        <hot-table ref="hotTableComponent" :settings="hotSettings"></hot-table>
        <br>
        <button v-on:click="swapHotData">Load new data!</button>
    </div>

</template>

<script>
    import Vue from 'vue';
    import { HotTable } from '@handsontable/vue';
    import Handsontable from 'handsontable';

    new Vue({
        name: "ht2page",
        data: function() {
            return {
                hotSettings: {
                    data: Handsontable.helper.createSpreadsheetData(4, 4),
                    colHeaders: true
                }
            }
        },
        methods: {
            swapHotData: function() {
// The Handsontable instance is stored under the `hotInstance` property of the wrapper component.
                this.$refs.hotTableComponent.hotInstance.loadData([['new', 'data']]);
            }
        },
        components: {
            HotTable
        }
    });

</script>

<style scoped>

</style>

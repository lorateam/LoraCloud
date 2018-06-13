<template>
    <el-table
        v-bind:data="tableData4"
        style="width: 100%"
        height="50vh">
        <el-table-column
          prop="date"
          label="日期"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地点"
          width="100%">
        </el-table-column>
            <el-table-column
          prop="event"
          label="事件"
          width="100%">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100%">
        <template slot-scope="scope">
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tableData4)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
    </el-table>
</template>

<script>
export default{
  name: 'infoBox',
  props: ['tableData4'],
  data() {
    return {
    };
  },
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
  },
};
</script>

<style scoped>
</style>

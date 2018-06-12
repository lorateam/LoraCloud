<template>
  <div>
  <el-table
    v-bind:data="currentData"
    style="width: 100%"
    height="50vh">

    <el-table-column
      prop="dataName"
      label="名称"
      width="100%">
    </el-table-column>

    <el-table-column
      prop="time"
      label="时间"
      width="100%">
    </el-table-column>

    <el-table-column
      prop="value"
      label="数值"
      width="100%">
    </el-table-column>

    <el-table-column
      fixed="right"
      label="操作"
      width="100%">
      <template slot-scope="scope">

        <el-popover
          placement="left"
          width="500"
          trigger="click">

          <history-box />

          <el-button
            @click.native.prevent="getHistoryInfoForModel(scope.row)"
            slot="reference"
            type="text"
            size="small">
            移除
          </el-button>

        </el-popover>
      </template>
    </el-table-column>
    
  </el-table>
  </div>
</template>

<script>
import { fetchOneAddressOneHistorySensorInfo } from '@/api/historyInfo';
import historyBox from '@/components/historyBox';

export default {
  name: 'currentData',
  props: ['currentData'],
  data() {
    return {
      dialogVisible: false,
    };
  },
  methods: {
    // 传送信息到historyBox中
    sendRowInfo(index, rows) {
      console.log(fetchOneAddressOneHistorySensorInfo({ 'addressId': rows[index].addressId, 'sensorName': rows[index].sensorName }));
    },
    getHistoryInfoForModel(row) {
      fetchOneAddressOneHistorySensorInfo({ addressId: row.addressId, sensorName: row.sensorName })
        .then((response) => {
          console.log(response);
      });
      this.dialogVisible = true;
    },
  },
  components: {
    historyBox,
  },
};
</script>

<style scoped>

</style>

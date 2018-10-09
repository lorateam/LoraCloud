<template>
  <div class="currentDataDiv">
    <el-table
      v-bind:data="currentData"
      border="2"
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

            <history-box ref="historyBox" :historyData="historyData" />

            <el-button
              @click.native.prevent="getHistoryInfoForModel(scope.row)"
              slot="reference"
              type="text"
              size="small">
              历史数据
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
      historyData: {},
    };
  },
  methods: {
    // 获取历史数据
    getHistoryInfoForModel(row) {
      fetchOneAddressOneHistorySensorInfo({ addressId: row.addressId, sensorName: row.sensorName })
        .then((response) => {
          const historyTime = [];
          const historyHighData = [];
          const historyLowData = [];
          response.data.forEach((element) => {
            // console.log(element);
            historyTime.push(element.time);
            historyHighData.push(element.highValue);
            historyLowData.push(element.lowValue);
          });
          this.historyData.historyTime = historyTime;
          this.historyData.historyHighData = historyHighData;
          this.historyData.historyLowData = historyLowData;
          this.$refs.historyBox.initChart();
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
.currentDataDiv {
  float: left;
}
</style>

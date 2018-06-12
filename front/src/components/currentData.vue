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
            @after-enter="initChart"
            trigger="click">

            <history-box ref="historyBox" />

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
      chart: '',
      options: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        },
        yAxis: {
          type: 'value',
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true,
        }],
      },
    };
  },
  methods: {
    getHistoryInfoForModel(row) {
      fetchOneAddressOneHistorySensorInfo({ addressId: row.addressId, sensorName: row.sensorName })
        .then((response) => {
          let historyTime = [];
          let historyHighData = [];
          let historyLowData = [];
          response.data.forEach((element) => {
            historyTime.push(element.time);
          });
      });
      this.dialogVisible = true;
    },
    initChart() {
      this.$refs.historyBox.initChart();
    },
  },
  components: {
    historyBox,
  },
};
</script>

<style scoped>

</style>

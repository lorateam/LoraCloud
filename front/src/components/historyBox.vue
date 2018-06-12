<template>
  <div id="historyBox" style="width: 300px; height:300px;">
    <!-- <el-button type="primary" @click="initChart"></el-button> -->
    <div class="et-chart" style="width: 300px; height: 200px; background-color: red;"></div>
  </div>
</template>

<script>
import echarts from 'echarts';

export default {
  name: 'historyBox',
  props: ['gridData'],
  data() {
    return {
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
    initChart() {
      const divs = document.getElementsByClassName('et-chart');
      const index = divs.length - 1;
      this.chart = echarts.init(divs[index]);
      this.chart.setOption(this.options);
    },
  },
};
</script>

<style>
</style>


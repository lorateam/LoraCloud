<template>
  <div id="historyBox" style="width: 400px; height:400px;">
    <div class="et-chart" style="width: 400px; height: 400px;"></div>
  </div>
</template>

<script>
import echarts from 'echarts';

export default {
  name: 'historyBox',
  props: ['historyData'],
  data() {
    return {
      chart: '',
    };
  },
  methods: {
    initChart() {
      const that = this;
      const options = {
        xAxis: {
          type: 'category',
          data: that.historyData.historyTime,
        },
        yAxis: {
          type: 'value',
        },
        series: [{
          data: that.historyData.historyHighData,
          type: 'line',
          smooth: true,
        },{
          data: that.historyData.historyLowData,
          type: 'line',
          smooth: true,
        }],
      };
      const divs = document.getElementsByClassName('et-chart');
      const index = divs.length - 1;
      this.chart = echarts.init(divs[index]);
      this.chart.setOption(options);
    },
  },
};
</script>

<style>
</style>


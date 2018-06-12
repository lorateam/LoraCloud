<template>
  <div id="historyBox" :style="{width: '300px', height:'300px'}">
    <el-button type="primary" @click="initChart()">Click</el-button>
    <div id="laji" style="width: 300px; height: 200px; background-color: red;"></div>
  </div>
</template>

<script>
import echarts from 'echarts';

export default {
  name: 'historyBox',
  props: ['gridData'],
  // mounted() {
  //   this.initChart();
  // },
  data() {
    return {
      chart: '',
      options: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true
        }],
      },
    };
  },
  methods: {
    initChart(){
      this.chart = echarts.init(document.getElementById('laji'));
      console.log(this.chart);
      console.log('initing');
      this.chart.setOption(this.options);
    },
  },
};

</script>

<style>

</style>


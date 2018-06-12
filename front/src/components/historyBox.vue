<template>
  <div id="historyBox" style="width: 500px; height: 400px;">
    <div class="qin"></div>
  </div>
</template>

<script>
import echarts from 'echarts';

export default {
  name: 'historyBox',
  props: ['historyData'],
  mounted() {
    this.initChart();
  },
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
      setTimeout(()=>{
      const getDivsByClass = document.getElementsByClassName('qin');
      this.chart = echarts.init(getDivsByClass[getDivsByClass.length-1]);
      this.chart.setOption(this.options);
      },0)
    },
  },
};

</script>

<style>

</style>


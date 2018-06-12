<template>
  <div>
    <el-row :gutter="20">
    <el-col :span="6">
      <infoBox :tableData4="tableData4"></infoBox>
    </el-col>
    <el-col :span="12">
        <gao-de-map @getCurrentInfo="getCurrentFromMap"/>
    </el-col>
    <el-col :span="6">
      <current-data :currentData="infoBox"/>
    </el-col>
    </el-row>
  </div>
</template>

<script>
import { fetchAllAddressInfo } from '@/api/baseInfo';
import { fetchOneAddressOneCurrentSensorInfo } from '@/api/currentInfo';
import infoBox from '@/components/infoBox';
import gaoDeMap from '@/components/map';
import currentData from '@/components/currentData';

export default {
  name: 'name',
  data() {
    return {
      infoBox: [], // 右侧实时信息栏
      tableData4: [], // 信息显示窗口的信息
      addressInfo: [], // 地点基本信息
      // 消息信息
      info: {
        date: '',
        address: '',
        event: '',
      },
    };
  },
  mounted() {
    // this.getAddressBaseInfo();
  },
  methods: {
    // 获取所有地点的基本信息
    getAddressBaseInfo() {
      fetchAllAddressInfo().then((response) => {
        this.addressInfo = response.data;
        this.getFireOrNot();
      });
    },
    // 获取当前时间并格式化
    getNowFormatDate() {
      const date = new Date();
      return date.toLocaleString();
    },
    // 判断目前所有地点是否发生了火灾
    getFireOrNot() {
      const that = this;
      setInterval(() => {
        that.addressInfo.forEach((a) => {
          // 获取某一个地点的火灾报警器情况
          fetchOneAddressOneCurrentSensorInfo({ addressId: a.id, sensorName: 'fire' })
            .then((response) => {
              if (response.data.value === 0.0) {
                that.info.date = that.getNowFormatDate();
                that.info.address = a.name;
                that.info.event = '发生火灾';
                that.tableData4.push(that.info);
              }
            });
        });
      }, 5000);
    },
    getCurrentFromMap(data) {
      this.infoBox = data;
    },
  },
  components: {
    infoBox,
    gaoDeMap,
    currentData,
  },
};
</script>

<style>
  .map{
    height: 50vh;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>

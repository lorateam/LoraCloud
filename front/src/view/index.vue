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
    <el-row :gutter="20">
      <el-col :span="2" style="height: 43vh;background-color: #bdcadb; margin-right: 30px;font-size: 60px;">
        <span>视<br>频<br>监<br>控</span>
      </el-col>
      <el-col :span="7" style="height: 43vh; margin-right: 18px;">
        <video id="video1" controls autoplay style="width: 100%; height: 100%"></video>
      </el-col>
      <el-col :span="7" style="height: 43vh;margin-right: 18px;">
        <video id="video2" controls autoplay style="width: 100%; height: 100%"></video>
      </el-col>
      <el-col :span="7" style="height: 43vh;">
        <video id="video3" controls autoplay style="width: 100%; height: 100%"></video>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { fetchAllAddressInfo } from "@/api/baseInfo";
import { fetchOneAddressOneCurrentSensorInfo } from "@/api/currentInfo";
import { fetchOneAddressVideo } from "@/api/video"
import infoBox from "@/components/infoBox";
import gaoDeMap from "@/components/map";
import currentData from "@/components/currentData";

export default {
  name: "name",
  data() {
    return {
      infoBox: [], // 右侧实时信息栏
      tableData4: [], // 信息显示窗口的信息
      addressInfo: [], // 地点基本信息
      // 消息信息
      info: {
        date: "",
        address: "",
        event: ""
      },
      IMap: {}
    };
  },
  mounted() {
    this.getAddressBaseInfo();
    this.initVideo();
  },
  methods: {
    // 获取所有地点的基本信息
    getAddressBaseInfo() {
      fetchAllAddressInfo().then(response => {
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
        that.addressInfo.forEach(a => {
          // 获取某一个地点的火灾报警器情况
          fetchOneAddressOneCurrentSensorInfo({
            addressId: a.id,
            sensorName: "fire"
          }).then(response => {
            if (response.data.value === 1) {
              const info = {
                date: that.getNowFormatDate(),
                address: a.name,
                event: "发生火灾"
              };
              that.tableData4.push(info);
              var circle = new AMap.Circle({
                center: new AMap.LngLat("103.993753", "30.553332"),// 圆心位置
                radius: 18, //半径
                strokeColor: "#F33", //线颜色git
                strokeOpacity: 1, //线透明度
                strokeWeight: 3, //线粗细度
                fillColor: "#ee2200", //填充颜色
                fillOpacity: 0.35//填充透明度
            });
              circle.set(that.IMap);
            }
          });
        });
      }, 5000);
    },
    //从map地点获取当前信息
    getCurrentFromMap(data) {
      this.infoBox = data.infoBox;
      this.IMap = data.IMap;
    },
    // 显示视频
    initVideo() {
      const baiduUrl1 = "rtsp://admin:ckkjb208@192.168.199.59:554/MPEG-4/ch1/main/av_stream";
      const baiduUrl2 = "rtsp://admin:ckkjb208@192.168.199.54:554/MPEG-4/ch1/main/av_stream";
      const baiduUrl3 = "rtsp://admin:ckkjb208@192.168.199.60:554/MPEG-4/ch1/main/av_stream";
      const videoDiv1 = document.getElementById('video1');
      const videoDiv2 = document.getElementById('video2');
      const videoDiv3 = document.getElementById('video3');
      videoDiv1.src = baiduUrl1;
      videoDiv2.src = baiduUrl2;
      videoDiv3.src = baiduUrl3;
      Streamedian.player('video1', {
        socket: 'ws://192.168.199.155:8081/ws/',
      });
      Streamedian.player('video2', {
        socket: 'ws://192.168.199.155:8081/ws/',
      });
      Streamedian.player('video3', {
        socket: 'ws://192.168.199.155:8081/ws/',
      });
    },
  },
  components: {
    infoBox,
    gaoDeMap,
    currentData
  }
};
</script>

<style>
.map {
  height: 50vh;
}
.el-row {
  margin-bottom: 10px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #bdcadb;
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

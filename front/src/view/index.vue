<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <gao-de-map @getCurrentInfo="getCurrentFromMap"/>
      </el-col>
      <el-col :span="12" style="height: 100vh">
        <el-row>
          <el-col :span="24"><div id="divPlugin" class="plugin" /></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="2">实时数据</el-col>
          <el-col :span="10"><current-data :currentData="infoBox"/></el-col>
          <el-col :span="2">消息提示框</el-col>
          <el-col :span="10"><infoBox :tableData4="tableData4" /></el-col>
        </el-row>
      </el-col>
    </el-row>
    <!-- <el-row :gutter="20">
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
            <el-col :span="4" style="height: 43vh;margin-right: 30px;font-size: 60px;">
      </el-col>
      <el-col :span="10" style="height: 50vh; margin-right: 18px;">
        <div id="divPlugin" class="plugin">
        </div>
      </el-col>
    </el-row> -->
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
      var that = this;
      fetchAllAddressInfo().then(response => {
        that.addressInfo = response.data;
        that.getFireOrNot();
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
    //初始化video
    initVideo() {
      // 检查插件是否已经安装过
      if (-1 === WebVideoCtrl.I_CheckPluginInstall()) {
        alert("您还未安装过插件，双击开发包目录里的WebComponents.exe安装！");
        return;
      }
      // 初始化插件参数及插入插件
      WebVideoCtrl.I_InitPlugin('100%', '100%', {
        iWndowType: 3
      });
      WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
      // 检查插件是否最新
      if (-1 === WebVideoCtrl.I_CheckPluginVersion()) {
        alert("检测到新的插件版本，双击开发包目录里的WebComponents.exe升级！");
        return;
      }
      // 窗口事件绑定
      $(window).bind({
        resize: function () {
          var $Restart = $("#restartDiv");
          if ($Restart.length > 0) {
            var oSize = getWindowSize();
            $Restart.css({
              width: oSize.width + "px",
              height: oSize.height + "px"
            });
          }
        }
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
.plugin {
  height: 50vh;
}
</style>

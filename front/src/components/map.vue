<template>
  <div class="map" id="container"></div>
</template>

<script>
// eslint-disable-next-line
import AMap from 'AMap';
import { fetchAllAddressInfo } from '@/api/baseInfo';
import { fetchOneAddressAllCurrentSensorInfo } from '@/api/currentInfo';
import { fetchOneAddressVideo } from '@/api/video'

export default {
  name: 'gaoDeMap',
  data() {
    return {
      addressInfo: [],
      gaoDeMap: {},
    };
  },
  mounted() {
    //生成地图
    this.gaoDeMap = new AMap.Map('container', {
      // rotateEnable: true,
      // rotation: 40,
      expandZoomRange: true,
      zoom: 19,
      zooms: [19, 19],
      center:[103.993805,30.553578],
      dragEnable: false,
    });
    //拉取地图中所有点的信息
    fetchAllAddressInfo().then((response) => {
      this.addressInfo = response.data;
      this.addressInfo.forEach((a) => {
        this.initMapPoint(a.name, a.agent, a.id, a.longitude, a.latitude);
        this.gaoDeMap.setFitView();//显示地图
      });
    });
  },

  methods: {
    initMapPoint(name, agent, tempAddressId, longitude, latitude) {
      const that = this;
      function showAddressSensorAndVideo(e) {
        fetchOneAddressAllCurrentSensorInfo({ addressId: tempAddressId })
          .then((response) => {
            const json = response.data;
            // 框体信息
            const info = [];
            // 右侧信息显示栏
            const infoBox = [];
            info.push(`<div><div style="padding:0px 0px 0px 4px;"><b>${name}</b>`); // FIXME
            info.push(`管理员${agent}`);
            // 获取时间
            const currentData = (new Date()).toLocaleString();
            for (let i = 0; i < json.length; i++) {
              if (json[i] !== null) {
                info.push(`${json[i].name} ${json[i].value} ${json[i].unit}`);
                const temp = {};
                temp.dataName = json[i].name;
                temp.time = currentData;
                temp.value = `${json[i].value} ${json[i].unit}`;
                temp.sensorName = `${json[i].sensor_name}`;
                temp.addressId = json[i].address_id;
                infoBox.push(temp);
              }
            }
            // 向父组件传递信息
            that.$emit('getCurrentInfo', {"infoBox":infoBox,"IMap":that.gaoDeMap});
            const infoWindow = new AMap.InfoWindow({
              content: info.join('<br/>'), // 使用默认信息窗体框样式，显示信息内容.</br>是为了使每一次push的文字分开
              offset: new AMap.Pixel(0, -30), // 设置弹出窗口离marker的距离
            });
            infoWindow.open(that.gaoDeMap, e.target.getCenter());
          });
        //显示一个地点的video信息  
        fetchOneAddressVideo({ addressId: tempAddressId }).then((response) =>{
          //在开始播放前先关闭所有正在播放的视频
          for(let i = 0; i < 9; i++){
            WebVideoCtrl.I_Stop(i);
          }
          const json = response.data;
          console.log(json);
          for(let i = 0; i < json.length; i++){
            if(json[i] !== null){
              that.loginAndPlayVideo(json[i].ip, json[i].port, json[i].user_name, json[i].password, i);
            }
          }
        })
      }
      const mapPoint = new AMap.Circle({
        center: [longitude, latitude],
        map: this.gaoDeMap,
        radius: 1.5,
        cursor: "pointer",
        fillOpacity: 0.6,
        content: "test",
      });
      mapPoint.on("click",showAddressSensorAndVideo);
    },
    // 登录并播放视频
    loginAndPlayVideo(szIP, szPort, szUsername, szPassword, wndIndex) {
      var that = this;
      if ("" === szIP || "" === szPort) {
          return;
      }
      var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
        success: function (xmlDoc) {
          console.log("登陆成功!");
          that.playVideo(szIP, wndIndex);
        },
        error: function () {
          console.log("登陆失败!");
        }
      });
      //如果返回值为-1，则表明已经登录过，则直接播放
      if (-1 == iRet) {
        that.playVideo(szIP, wndIndex);
      }
    },
    // 开始预览
    playVideo(szIp, wndIndex) {
      var iRet = WebVideoCtrl.I_StartRealPlay(szIp, {
        iStreamType: 2,
        iWndIndex: wndIndex,
      });
    }
  },
};
</script>

<style scoped>
.map {
  height: 100vh;
}
</style>

<template>
  <div class="map" id="container"></div>
</template>

<script>
// eslint-disable-next-line
import AMap from 'AMap';
import { fetchAllAddressInfo } from '@/api/baseInfo';
import { fetchOneAddressAllCurrentSensorInfo } from '@/api/currentInfo';

export default {
  name: 'gaoDeMap',
  data() {
    return {
      addressInfo: [],
      gaoDeMap: {},
    };
  },
  mounted() {
    this.gaoDeMap = new AMap.Map('container', {
      pitch: 60,
      rotation: 150,
      resizeEnable: true,
      expandZoomRange: true,
      zooms: [17, 20],
      // viewMode: '3D',
      center:[103.993805,30.553578],
    });
    fetchAllAddressInfo().then((response) => {
      this.addressInfo = response.data;
      this.addressInfo.forEach((a) => {
        this.initMapPoint(a.name, a.agent, a.id, a.longitude, a.latitude);
        this.gaoDeMap.setFitView();
      });
    });
    this.initMap();
  },

  methods: {
    initMapPoint(name, agent, tempAddressId, longitude, latitude) {
      const that = this;
      function showSensorCurrentInfor(e) {
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
                temp.sensorName = `${json[i].sensorName}`;
                temp.addressId = json[i].addressId;
                infoBox.push(temp);
              }
            }
            // 向父组件传递信息
            that.$emit('getCurrentInfo', {"infoBox":infoBox,"IMap":that.gaoDeMap});
            const infoWindow = new AMap.InfoWindow({
              content: info.join('<br/>'), // 使用默认信息窗体框样式，显示信息内容.</br>是为了使每一次push的文字分开
              offset: new AMap.Pixel(0, -30), // 设置弹出窗口离marker的距离
            });
            infoWindow.open(that.gaoDeMap, e.target.getPosition());
          });
      }
      const mapPoint = new AMap.Marker({
        position: [longitude, latitude],
        map: this.gaoDeMap,
      });
      mapPoint.on('click', showSensorCurrentInfor);
    },
    initMap(){
      var circleMarker = new AMap.Marker({
        center:[103.994686,30.553971],
        map: this.gaoDeMap,
        icon: new AMap.Icon({            
          size: new AMap.Size(40, 50),  //图标大小
          image: "https://webapi.amap.com/theme/v1.3/images/newpc/way_btn4.png",
          imageOffset: new AMap.Pixel(0, -60)
        }),
      });
    }
  },
};
</script>

<style scoped>
</style>

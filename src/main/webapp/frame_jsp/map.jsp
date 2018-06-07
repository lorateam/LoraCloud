<%--
  Created by IntelliJ IDEA.
  User: Bolvvv
  Date: 2018/4/14
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="frame_js_foot.jsp"%>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    //初始化地图对象，加载地图
    var map = new AMap.Map("mapContainer", {
        pitch:60,
        rotation:150,
        resizeEnable: true,
        expandZoomRange:true,
        //设置缩放区间
        zooms: [17,20],
        viewMode:'3D'
    });
    $.post("/action/baseInfo/address",function(data){
        var baseInforJson = JSON.parse(data);
        for(address in baseInforJson){
            initMapPoint(baseInforJson[address].name,baseInforJson[address].agent,
                baseInforJson[address].id,baseInforJson[address].longitude,baseInforJson[address].latitude)
        }
        map.setFitView();
    });
    function initMapPoint(name,agent,tempAddressId,longitude, latitude)
    {
        var mapPoint = new AMap.Marker({
            position: [longitude,latitude],
            map: map
        });
        mapPoint.on('click',showSensorCurrentInfor);
        function showSensorCurrentInfor(e){
            $.post("/action/currentInfor/address/boxes",{addressId:tempAddressId},function(data){
                //框体信息
                var info = [];
                info.push("<div><div style=\"padding:0px 0px 0px 4px;\"><b>"+name+"</b>");
                info.push("管理员"+agent);
                //在开始动态渲染前先删除掉原有的元素
                removeElementDiv("smallBoxContainer");
                var json = JSON.parse(data);
                for(var i=0,l=json.length;i<l;i++){
                    if(json[i] === null) continue;
                    else{
                        info.push(json[i].name+"&nbsp&nbsp&nbsp"+":"+json[i].value+" "+json[i].unit);
                        addElementDiv("smallBoxContainer",json[i].name,json[i].value,json[i].unit,i);
                    }
                }
                infoWindow = new AMap.InfoWindow({
                    content: info.join("<br/>"),  //使用默认信息窗体框样式，显示信息内容.</br>是为了使每一次push的文字分开
                    offset:new AMap.Pixel(0,-30) //设置弹出窗口离marker的距离
                });
                infoWindow.open(map, e.target.getPosition());
            })
        }
    }
</script>
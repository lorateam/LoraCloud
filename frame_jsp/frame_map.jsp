<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<!--秦大佬的地图jsp版本-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>环境监测</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main.css"/>
    <%--<link rel="stylesheet" type="text/css" href="demo/css/index.css">--%>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.2&key=4e09e15a4affc04339b662d63ab7b3e2"></script>
    <script type="text/javascript" src="demo/js/jquery-3.3.1.js"></script>
</head>
<body>
<div class="box box-warning">
    <div class="box-header">
        <div class="box-title">Calendar</div>
    </div><!-- /.box-header -->
    <div class="box-body no-padding" style="height: 650px">
        <div id="mapContainer"></div>
    </div>
</div>
</body>
</html>
<%--引入地图js--%>
<%@include file="/frame_jsp/map.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!--
如果要修改元素内容，直接找到include的文件进行修改，注意div.class布局
-->
<head>
    <meta charset="UTF-8">
    <title>Lora设备监控</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="frame_jsp/frame_css_head.jsp"%>
    <script src="BolvvvJS/Bolvvv.js"></script>
        <!--隐藏日历内部元素-->
    <style>
        div.fc-event-container{
            visibility: hidden;
        }
    </style>

</head>

<body class="skin-blue">
<%@include file="frame_jsp/frame_header.jsp"%>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar 左边菜单栏-->
    <%@include file="frame_jsp/frame_sidebar.jsp"%>
    <!-- Right side column. Contains the navbar and content of the page 右边内容-->
    <aside class="right-side">
        <!--右边分节栏-->
        <%@include file="frame_jsp/frame_content_header.jsp"%>
        <section class="content">
            <!-- Main row右边一排 -->
            <div class="row">
                <!-- Left col右边第一列 -->
                <section class="col-lg-6 connectedSortable">
                    <!--这个地图是嵌在日历表面的，include的日历不要删，删了的话地图就不能显示了-->
<<<<<<< HEAD
                    <%@include file="/frame_jsp/frame_map.jsp"%>
                    <%--<%@include file="/frame_jsp/frame_todolist.jsp"%>--%>
                    <%--<%@include file="/frame_jsp/frame_linechart.jsp"%>--%>
=======
                    <%@include file="frame_jsp/frame_map.jsp"%>
                    <%--<%@include file="frame_jsp/frame_todolist.jsp"%>--%>
                    <%--<%@include file="frame_jsp/frame_linechart.jsp"%>--%>
>>>>>>> 869af81588bc240029c3ef8bef800e16f76a66ed
                </section>
                <!-- Right col 右边内容第二列-->
                <section class="col-lg-6 connectedSortable">
                    <div class="box box-warning">
                        <div class="box-body no-padding" style="align-content: center;">
                            <%--rtsp://admin:ckkjb208@192.168.1.182:554/MPEG-4/ch1/main/av_stream--%>
                            <div class="video">
                                <div>
<<<<<<< HEAD
                                    <input id="url1" value="rtsp://admin:ckkjb208@192.168.1.182:554/MPEG-4/ch1/main/av_stream" size="36">
                                    <button id="set_new_url" onclick = "set_url('url1','video1')">Set</button>
=======
                                    <input id="url1" value="rtsp://admin:1234abcd@192.168.199.157:554/MPEG-4/ch1/main/av_stream" size="36">
                                    <button id="set_new_url" onclick = "set_url(new_url,'url1','video1')">Set</button>
>>>>>>> 869af81588bc240029c3ef8bef800e16f76a66ed
                                </div>
                                <video id="video1" controls autoplay></video>
                            </div>
                            <div class="video">
                                <div>
<<<<<<< HEAD
                                    <input id="url2" value="rtsp://admin:ckkjb208@192.168.199.187:554/MPEG-4/ch1/main/av_stream" size="36">
                                    <button id="set_new_ur" onclick = "set_url('url2','video2')">Set</button>
=======
                                    fd
                                    <input id="url2" value="rtsp://admin:1234abcd@192.168.199.180:554/MPEG-4/ch1/main/av_stream" size="36">
                                    <button id="set_new_ur" onclick = "set_url(new_url,'url2','video2')">Set</button>
>>>>>>> 869af81588bc240029c3ef8bef800e16f76a66ed
                                </div>
                                <video id="video2" controls autoplay></video>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </section>
        <%@include file="frame_jsp/frame_smallbox.jsp"%>
    </aside>
</div>
<%--<%@include file="frame_jsp/frame_js_foot.jsp"%>--%>
<!-- 火险模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="color: red">
                    警告!
                </h4>
            </div>
            <div class="modal-body">
                <span style="color: red;font-size: 20px">发生火灾</span><br><br>
                <span>火险情况通知已发送短信至负责人</span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script src="rtsp/free.player.1.8.js" ></script>
<script>
    function set_url(input_id,player_id)
    {
        var text = document.getElementById(input_id).value;
        // url = text;
        var player = document.getElementById(player_id);
        player.src = text;
        Streamedian.player(player_id, {socket:"ws://192.168.199.179:8081/ws"});
    }
//TODO:下面的方式特别指出了address，需要轮询所有的address
    <%--火险轮询--%>
    setInterval(function () {
        $.post("/action/currentInfo/address/boxes/sensor",{addressId:2,sensorName:"fire"},function (data) {
            var json = JSON.parse(data);
            var circle = new AMap.Circle({
                center: new AMap.LngLat("103.993753", "30.553332"),// 圆心位置
                radius: 18, //半径
                strokeColor: "#F33", //线颜色git
                strokeOpacity: 1, //线透明度
                strokeWeight: 3, //线粗细度
                fillColor: "#ee2200", //填充颜色
                fillOpacity: 0.35//填充透明度
            });
                if(json.value === 1.0)
                {
                    circle.setMap(map);
                    $('#myModal').modal('show')
                }
                else {
                    circle.setMap(null);
                }
        })
    },1000);
</script>

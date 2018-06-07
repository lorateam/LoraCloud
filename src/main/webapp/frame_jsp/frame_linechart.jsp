<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#home" data-toggle="tab">
            菜鸟教程
        </a>
    </li>
    <li><a href="#ios" data-toggle="tab">iOS</a></li>
    <li><a href="#hhh" data-toggle="tab">iOS</a></li>
    <li><a href="#bbb" data-toggle="tab">iOS</a></li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home">
    </div>
    <div class="tab-pane fade" id="ios">
    </div>
    <div class="tab-pane fade" id="hhh">
    </div>
    <div class="tab-pane fade" id="bbb">
    </div>
</div>

<script>
    // LINE CHART
    var line = new Morris.Area({
        element: 'home',
        resize: true,

        data: [
            {y: '1', temp: 26.66, hum: 26.66},
            {y: '2', temp: 27.78, hum: 22.94},
            {y: '3', temp: 49.12, hum: 19.69},
            {y: '4', temp: 37.67, hum: 35.97},
            {y: '5', temp: 68.10, hum: 19.14},
            {y: '6', temp: 56.70, hum: 42.93},
            {y: '7', temp: 48.20, hum: 37.5},
            {y: '8', temp: 15.073, hum: 59.67},
            {y: '9', temp: 10.687, hum: 44.60},
            {y: '10', temp: 84.32, hum: 57.13}
        ],
        xkey: 'y',
        ykeys: ['temp', 'hum'],
        labels: ['最高温', '最低温'],
        lineColors: ['#a0d0e0', '#3c8dbc'],
        hideHover: 'auto'
    });
    var ios = new Morris.Area({
        element: 'ios',
        resize: true,

        data: [
            {y: '1', temp: 16.66, hum: 26.66},
            {y: '2', temp: 21.78, hum: 22.94},
            {y: '3', temp: 41.12, hum: 19.69},
            {y: '4', temp: 17.67, hum: 35.97},
            {y: '5', temp: 28.10, hum: 19.14},
            {y: '6', temp: 36.70, hum: 12.93},
            {y: '7', temp: 18.20, hum: 37.5},
            {y: '8', temp: 15.073, hum: 59.67},
            {y: '9', temp: 10.687, hum: 44.60},
            {y: '10', temp: 84.32, hum: 57.13}
        ],
        xkey: 'y',
        ykeys: ['temp', 'hum'],
        labels: ['最高温', '最低温'],
        lineColors: ['#a0d0e0', '#3c8dbc'],
        hideHover: 'auto'
    });
    var hhh = new Morris.Area({
        element: 'hhh',
        resize: true,

        data: [
            {y: '1', temp: 16.66, hum: 26.66},
            {y: '2', temp: 21.78, hum: 22.94},
            {y: '3', temp: 41.12, hum: 19.69},
            {y: '4', temp: 17.67, hum: 35.97},
            {y: '5', temp: 28.10, hum: 19.14},
            {y: '6', temp: 36.70, hum: 12.93},
            {y: '7', temp: 18.20, hum: 37.5},
            {y: '8', temp: 15.073, hum: 59.67},
            {y: '9', temp: 10.687, hum: 44.60},
            {y: '10', temp: 84.32, hum: 57.13}
        ],
        xkey: 'y',
        ykeys: ['temp', 'hum'],
        labels: ['最高温', '最低温'],
        lineColors: ['#a0d0e0', '#3c8dbc'],
        hideHover: 'auto'
    });
    var bbb = new Morris.Area({
        element: 'bbb',
        resize: true,

        data: [
            {y: '1', temp: 16.66, hum: 26.66},
            {y: '2', temp: 21.78, hum: 22.94},
            {y: '3', temp: 41.12, hum: 19.69},
            {y: '4', temp: 17.67, hum: 35.97},
            {y: '5', temp: 28.10, hum: 19.14},
            {y: '6', temp: 36.70, hum: 12.93},
            {y: '7', temp: 18.20, hum: 37.5},
            {y: '8', temp: 15.073, hum: 59.67},
            {y: '9', temp: 10.687, hum: 44.60},
            {y: '10', temp: 84.32, hum: 57.13}
        ],
        xkey: 'y',
        ykeys: ['temp', 'hum'],
        labels: ['最高温', '最低温'],
        lineColors: ['#a0d0e0', '#3c8dbc'],
        hideHover: 'auto'
    });
</script>

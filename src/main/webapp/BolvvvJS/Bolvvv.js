// 创建smallBox
function addElementDiv(obj,sensorName,sensorValue,sensorUnit,count) {
    var parent = document.getElementById(obj);
    //添加div
    var div = document.createElement("div");
    if(count%4 === 0)
    {
        div.innerHTML=
            "<div class=\"col-lg-3 col-xs-6\">"+
            "<div class=\"small-box bg-aqua\">"+
            "            <div class=\"inner\">" +
            "                <h3>" +sensorValue+
            "                    <sup style=\"font-size: 20px\">"+sensorUnit+"</sup>" +
            "                </h3>" +
            "                <p>" +sensorName+
            "                    " +
            "                </p>" +
            "            </div>" +
            "            <div class=\"icon\">" +
            "                <i class=\"ion ion-stats-bars\"></i>" +
            "            </div>" +
            "            <a href=\"#\" class=\"small-box-footer\">" +
            "                More info <i class=\"fa fa-arrow-circle-right\"></i>" +
            "            </a>" +
            "        </div>" +
            " </div>";
    }
    else if(count%4 === 1)
    {
        div.innerHTML=
            "<div class=\"col-lg-3 col-xs-6\">"+
            "<div class=\"small-box bg-green\">"+
            "            <div class=\"inner\">" +
            "                <h3>" +sensorValue+
            "                    <sup style=\"font-size: 20px\">"+sensorUnit+"</sup>" +
            "                </h3>" +
            "                <p>" +sensorName+
            "                    " +
            "                </p>" +
            "            </div>" +
            "            <div class=\"icon\">" +
            "                <i class=\"ion ion-stats-bars\"></i>" +
            "            </div>" +
            "            <a href=\"#\" class=\"small-box-footer\">" +
            "                More info <i class=\"fa fa-arrow-circle-right\"></i>" +
            "            </a>" +
            "        </div>" +
            " </div>";
    }
    else if(count%4 === 2)
    {
        div.innerHTML=
            "<div class=\"col-lg-3 col-xs-6\">"+
            "<div class=\"small-box bg-red\">"+
            "            <div class=\"inner\">" +
            "                <h3>" +sensorValue+
            "                    <sup style=\"font-size: 20px\">"+sensorUnit+"</sup>" +
            "                </h3>" +
            "                <p>" +sensorName+
            "                    " +
            "                </p>" +
            "            </div>" +
            "            <div class=\"icon\">" +
            "                <i class=\"ion ion-stats-bars\"></i>" +
            "            </div>" +
            "            <a href=\"#\" class=\"small-box-footer\">" +
            "                More info <i class=\"fa fa-arrow-circle-right\"></i>" +
            "            </a>" +
            "        </div>" +
            " </div>";
    }
    else if(count%4 === 3)
    {
        div.innerHTML=
            "<div class=\"col-lg-3 col-xs-6\">"+
            "<div class=\"small-box bg-yellow\">"+
            "            <div class=\"inner\">" +
            "                <h3>" +sensorValue+
            "                    <sup style=\"font-size: 20px\">"+sensorUnit+"</sup>" +
            "                </h3>" +
            "                <p>" +sensorName+
            "                    " +
            "                </p>" +
            "            </div>" +
            "            <div class=\"icon\">" +
            "                <i class=\"ion ion-stats-bars\"></i>" +
            "            </div>" +
            "            <a href=\"#\" class=\"small-box-footer\">" +
            "                More info <i class=\"fa fa-arrow-circle-right\"></i>" +
            "            </a>" +
            "        </div>" +
            " </div>";
    }
    parent.appendChild(div);
}
function removeElementDiv(obj) {
    var parent = document.getElementById(obj);
    parent.innerHTML="";
}
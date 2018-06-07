/*
setInterval(function(){
    askForInformation();//向服务器请求数据
},3000);
function askForInformation() {
    $.post("../jsp/indexBack.jsp", function (data) {
        var json = eval("(" + data + ")");
        var list = json.fireConfition;
        //这里返回的是字符串的true
        if (list === 1) {
            $("#showInformation").append("<P style='color: red'>发生火灾了</P>")
        }
        else {
            $("#showInformation").append("<P>看到这句话证明没有发生火灾</P>")
        }
    })
}*/

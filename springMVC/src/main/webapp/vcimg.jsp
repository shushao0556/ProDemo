<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码</title>
</head>
<body>
<input type="text" id="">
<img src="${pageContext.request.contextPath}/vc/" alt="" id="img">
<a onclick="refreshCode()">看不清，换一张</a>
<script>
    function refreshCode(){
        var img=document.getElementById("img");
        img.src="${pageContext.request.contextPath}/vc/?"+Math.random();
    }
</script>
</body>
</html>

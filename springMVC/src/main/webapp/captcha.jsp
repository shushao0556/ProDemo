<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码</title>
</head>
<body>
<input type="text" name="vcode" id="">
<img src="${pageContext.request.contextPath}/captcha" alt="" id="img">
<a onclick="refreshCode()">看不清，换一张</a>
<script>
    function refreshCode(){
        var img=document.getElementById("img");
        img.src="${pageContext.request.contextPath}/captcha";
    }
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--rest--%>
<form action="${pageContext.request.contextPath}/rest/user" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="提交">
</form>
<input type="button" onclick="putX()" value="ajax">


<script>
    function putX(){
        var xhr=new XMLHttpRequest();
        xhr.open("put","${pageContext.request.contextPath}/rest/users");//ajax正常发送put请求
        xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
        xhr.send("id=1&username=admin&password=admin888");//携带参数
    }
    function putXPlus(){
        var xhr=new XMLHttpRequest();
        xhr.open("put","${pageContext.request.contextPath}/rest/users");//ajax正常发送put请求
        xhr.setRequestHeader("content-type","application/json");
        xhr.send('{"id":1,"username":"admin","password":"admin888"}');//携带json数据参数
    }

</script>
</body>
</html>

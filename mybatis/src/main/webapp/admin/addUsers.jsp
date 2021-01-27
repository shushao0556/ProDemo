<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/" method="post">
    username : <input type="text" name="username"> <br>
    password : <input type="password" name="password"><br>
    rolename : <input type="text" name="rolename"><br>
    identity : <input type="text" name="identity"> <br>
    mobile : <input type="text" name="mobile"> <br>
    <input type="submit" value="add">
</form>
</body>
</html>

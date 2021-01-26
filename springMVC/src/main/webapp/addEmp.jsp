<%@ page import="java.util.List" %>
<%@ page import="com.evshou.admin.entity.Emp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.evshou.admin.entity.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/">
    username : <input type="text" name="name"> <br>
    sex :
    <input type="radio" name="sex" value="男" checked>男
    <input type="radio" name="sex" value="女">女 <br>
    age : <input type="text" name="age"> <br>
    zipCode : <input type="text" name="zipCode"> <br>
    address : <input type="text" name="address"> <br>
    <input type="submit" value="register"><input type="reset" value="reset">
</form>
</body>
</html>

<%@ page import="com.evshou.admin.entity.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
</head>
<body>
<%
    Users users = (Users) request.getAttribute("users");
    request.setAttribute("users",users);
%>
<form action="${pageContext.request.contextPath}/admin/${users.id}" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="hidden" id="id" name="id" value="${users.id}">
    username : <input type="text" id="username" readonly name="username" value="${users.username}"> <br>
    password : <input type="password" id="password" name="password" value="${users.password}"><br>
    rolename : <input type="text" id="rolename" name="rolename" value="${users.rolename}"><br>
    identity : <input type="text" id="identity" name="identity" value="${users.identity}"> <br>
    mobile : <input type="text" id="mobile" name="mobile" value="${users.mobile}"> <br>
    <button onclick="update()">update</button>
</form>
<script>
    function update(){
        var xhr=new XMLHttpRequest();
        var id=document.getElementById("id").value;
        var username=document.getElementById("username").value;
        var password=document.getElementById("password").value;
        var rolename=document.getElementById("rolename").value;
        var identity=document.getElementById("identity").value;
        var mobile=document.getElementById("mobile").value;
        xhr.open("put","${pageContext.request.contextPath}/admin/"+id);//ajax正常发送put请求
        xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
        xhr.send("id="+id+"&username="+username+"&password="+password+"&rolename="+rolename+"&identity="+
            identity+"&mobile="+mobile);//携带参数
        location.href="${pageContext.request.contextPath}/admin/AllUsersList.jsp";
    }
</script>
</body>
</html>

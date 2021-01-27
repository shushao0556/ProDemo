<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询所有用户</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/admin/addForm">添加用户</a>
<table border="1">
    <tr>
        <th>用户编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>真实姓名</th>
        <th>身份证号</th>
        <th>手机号</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="users">
        <tr>
            <td>${users.id}</td>
            <td>${users.username}</td>
            <td>${users.password}</td>
            <td>${users.rolename}</td>
            <td>${users.identity}</td>
            <td>${users.mobile}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/${users.id}">修改</a>
                <a href="" onclick="del(${users.id})">删除</a>
            </td>
        </tr>
    </c:forEach>
    <script>
        function del(even){
            var xhr=new XMLHttpRequest();
            xhr.open("delete","${pageContext.request.contextPath}/admin/"+even);//ajax正常发送delete请求
            xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
            xhr.send("id="+even);//携带参数
            location.href="${pageContext.request.contextPath}/admin/AllUsersList.jsp";
        }
    </script>
</table>
</body>
</html>

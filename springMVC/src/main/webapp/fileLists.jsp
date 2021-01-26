<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件列表</title>
</head>
<body>
<table>
    <tr>
        <th>文件名</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${sessionScope.files}" var="file1">
        <tr>
            <td>${file1.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/upload/download?filename=${file1.name}">下载</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

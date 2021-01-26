<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载页面</title>
</head>
<body>
    <h3>下载列表</h3>
    <table border="1">
        <tr>
            <th>文件名</th>
            <th>操作</th>
        </tr>
        <c:forEach var="files" items="${requestScope.map}">
            <tr>
                <td>文件名:${files.value}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/uploads/download?filename=${files.key}">下载</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

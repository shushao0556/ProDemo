<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/uploads/" enctype="multipart/form-data" method="post">
        file : <input type="file" name="mfs"> <br>
        <input type="submit" value="up">
    </form>
</body>
</html>

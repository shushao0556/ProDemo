<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/up/upload" method="post"
      enctype="multipart/form-data">
    username : <input type="text" name="username"> <br>
    file1 : <input type="file" name="filename"><br>
    file2 : <input type="file" name="filename"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>

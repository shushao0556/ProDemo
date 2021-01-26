<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/hello/arrList">
    <input type="checkbox" name="feed" value="apple">苹果 <br>
    <input type="checkbox" name="feed" value="pear">梨子 <br>
    <input type="checkbox" name="feed" value="orange">橘子 <br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4 style="color: red">${ErrorMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    Login<input type="text" name="login">
    password<input type="password" name="pwd">
    <button type="submit" >Log in</button>
</form>
</body>
</html>

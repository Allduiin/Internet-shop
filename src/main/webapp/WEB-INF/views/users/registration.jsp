<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h4 style="color: red">${message}</h4>
    <h3>
    <form method="post" action="${pageContext.request.contextPath}/users/registration">
        Please provide your login<label>
        <input type="text" name="login">
    </label>
        password<input type="password" name="pwd">
        repeat password<input type="password" name="pwd-repeat">
        <button type="submit">Register</button>
    </form>
    </h3>
</body>
</html>

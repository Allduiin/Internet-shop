<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<div class="card text-white bg-dark mx-auto mt-3" style="width: 30%">
    <form method="post" action="${pageContext.request.contextPath}/registration">
        <div class="form-group">
            <label>Login</label>
            <input class="form-control" type="text" name="login">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input class="form-control" type="password" name="pwd">
        </div>
        <div class="form-group">
            <label>Repeat password</label>
            <input class="form-control" type="password" name="pwd-repeat">
        </div>
        <h2 style="color: red">${message}</h2>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
</body>
</html>

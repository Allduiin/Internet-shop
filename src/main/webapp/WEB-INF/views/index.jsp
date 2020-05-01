<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><div style="text-align: center;">Hi! Current time is ${time}</h2>
<p><a href="${pageContext.request.contextPath}/injectData">Add test data to DB</a>
<p><a href="${pageContext.request.contextPath}/users/all">All users</a>
<p><a href="${pageContext.request.contextPath}/login">Login</a>
<p><a href="${pageContext.request.contextPath}/registration">Registration</a>
<p><a href="${pageContext.request.contextPath}/products/all">User Products</a>
<p><a href="${pageContext.request.contextPath}/products/adminPage">Admin Products</a>
<p><a href="${pageContext.request.contextPath}/shoppingCart/get">Get shopping cart</a>
<p><a href="${pageContext.request.contextPath}/orders/all">All orders</a>
</div>
</body>
</html>

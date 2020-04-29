<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<internetshop.model.Order>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="order" items="${orders}">
    <tr>
        <td>
            <c:out value="${order.id}"/>
        </td>
        <td>
            <c:out value="${order.user.login}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/?id=${order.id}">Details</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<internetshop.model.Order>"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>User Orders</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<table border="1">
    <tr>
        <th>ID</th>
        <th>UserId</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach var="product" items="${order.products}">
                        <tr>
                            <td>
                                <c:out value="${product.id}"/>
                            </td>
                            <td>
                                <c:out value="${product.name}"/>
                            </td>
                            <td>
                                <c:out value="${product.price}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

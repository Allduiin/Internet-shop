<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="order" scope="request" type="internetshop.model.Order"/>
<html>
<head>
    <title>Order Info</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<p>UserId: ${order.userId}
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
    <a href="${pageContext.request.contextPath}/orders/all">Return to order list</a>
</table>
</body>
</html>

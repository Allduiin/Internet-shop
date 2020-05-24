<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="products" scope="request" type="java.util.List<internetshop.model.Product>"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Products</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<h6 style="color: #5eb431">${message}</h6>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
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
        <td>
            <a href="${pageContext.request.contextPath}/shoppingCart/add?id=${product.id}">Add to Shopping Cart</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>

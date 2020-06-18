<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<h6>
    <form method="post" action="${pageContext.request.contextPath}/products/add">
        Please provide product<label>
        name<input type="text" name="name">
    </label>
        price<input type="number" name="price">
        <button type="submit">Add</button>
    </form>
</h6>
</body>
</html>

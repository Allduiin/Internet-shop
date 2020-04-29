<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
<h3>
    <form method="post" action="${pageContext.request.contextPath}/users/registration">
        Please provide product<label>
        name<input type="text" name="name">
    </label>
        price<input type="number" name="price">
        <button type="submit">Add</button>
    </form>
</h3>
</body>
</html>

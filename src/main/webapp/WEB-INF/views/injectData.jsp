    <%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Demo</title>
</head>
<body>
<div class="card-body">
    <%@include file="/WEB-INF/views/menu.jsp" %>
</div>
<h2>Your test data was added to DB</h2>
<a href="${pageContext.request.contextPath}/">Return to main page</a>
</body>
</html>

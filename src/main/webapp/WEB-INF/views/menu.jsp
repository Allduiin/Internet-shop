<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Title</title>
</head>
<body style="height: 10%">
    <div class="btn-toolbar mx-auto" role="toolbar" aria-label="Toolbar with button groups">
        <div class="btn-group mr-2" role="group">
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/injectData"'>Add test data to DB
            </button>
        </div>
        <div class="btn-group mr-2" role="group" aria-label="Log actions">
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/login"'>Login
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/registration"'>Registration
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/logout"'>Logout
            </button>
        </div>
        <div class="btn-group mr-2" role="group" aria-label="User actions">
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/products/all"'>User products
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/shoppingCart/get"'>Get shopping cart
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/orders/userOrders"'>Get user orders
            </button>
        </div>

        <div class="btn-group" role="group" aria-label="Admin actions">
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/users/all"'>Users
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/products/adminPage"'>Admin Products
            </button>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="${pageContext.request.contextPath}/orders/all"'>All orders
            </button>
        </div>
    </div>
</body>
</html>

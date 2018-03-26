<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <h2 class="error">${requestScope.message}</h2>
    <a class="button btn-blue" href="/online-store">Go Home</a>
</body>
</html>

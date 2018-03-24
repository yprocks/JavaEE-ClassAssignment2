<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Phone Order complete message</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

</head>
<body>
<div>
    <h1>Thank you for your order.</h1>
    <h2 class="error">${requestScope.errormessage}</h2>
    <h2 class="message">${requestScope.message}</h2>
    Start new order : <a href="/online-store">Click here!</a>
</div>
</body>
</html>
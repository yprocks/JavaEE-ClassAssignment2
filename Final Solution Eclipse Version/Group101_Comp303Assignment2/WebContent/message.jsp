<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Phone Order complete message</title>
<link rel="stylesheet" type="text/css" href="css/overwrite.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
	<style>
	body{
		padding: 40px;
	}
	</style>
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
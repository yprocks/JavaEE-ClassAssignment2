<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome to Online Phone Store </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

</head>
<body>
<div>
    <h1 class="title">Welcome to Online Phone Store</h1>
    <p>
    <div class="error"> ${requestScope.message}</div>
    </p>
    <hr/>
    <form action="${pageContext.request.contextPath}/phone?action=display-phone" method="post">
        <table>
            <tbody>
            <tr>
                <td>
                    <label>Enter your name: </label>
                </td>
                <td>
                    <input type="text" value="" name="orderId">
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td class="align-right">
                    <input class="button btn-blue" type="submit" value="Place an order">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
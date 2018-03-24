<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <title>Phone Order Confirmation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

</head>
<body>
<div>
    <h2>Please confirm your order ${sessionScope.orderId}</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Number</th>
            <th>Description</th>
            <th>Company</th>
            <th>Unit Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="totalCost">0.0</c:set>
        <c:forEach items="${sessionScope.order}" var="item">
            <tr>
                <td>${item.itemNo}</td>
                <td class="align-vertical">
                    <div class="width-150 inline-block text-center">
                        <img class="phone-image" src="${pageContext.request.contextPath}/resources/images/${item.image}"/>
                        <div class="m-t-10">${item.itemDesc}</div>
                    </div>
                </td>
                <td>${item.company}</td>
                <td><fmt:formatNumber type="currency">${item.itemPrice}</fmt:formatNumber></td>
                <td>${item.quantity}</td>
                <c:set var="cost">${item.itemPrice*item.quantity}</c:set>

                <td><fmt:formatNumber type="currency">${cost}</fmt:formatNumber></td>
                <c:set var="totalCost">${cost + totalCost }</c:set>

            </tr>
        </c:forEach>
        <tr>
            <td>&nbsp;</td>
            <td>TOTAL cost of order</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><fmt:formatNumber type="currency">${totalCost}</fmt:formatNumber></td>
        </tr>
        </tbody>
    </table>

    <div class="m-t-10 align-right ">
        <div>
            <span class="message">To EDIT, use the BACK button</span>
            <form class="form-inline" action="${pageContext.request.contextPath}/phone?action=place-order" method="post">
                <input class="button btn-red" type="submit" value="CANCEL" name="confirm"/>
            </form>
            <form class="form-inline" action="${pageContext.request.contextPath}/phone?action=place-order" method="post">
                <input class="button btn-blue" type="submit" value="CONFIRM" name="confirm"/>
            </form>
        </div>
    </div>

</div>
</body>
</html>
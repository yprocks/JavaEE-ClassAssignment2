<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Phone Order Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

</head>
<body>
<div>
    <h2>Building order with OrderID: ${sessionScope.orderId }</h2>
    <p>Select the quantity of each item you want to order and then proceed.</p>
    <h4 class="error">${requestScope.message}</h4>
    <form action="${pageContext.request.contextPath}/phone?action=add-to-cart" method="post">
        <table class="table">
            <thead>
            <tr>
                <th>Number</th>
                <th>Description</th>
                <th>Company</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.list}" var="item">
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
                    <td>
                        <select name="${item.itemNo}">
                            <option value="0"></option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>

        <div class="m-t-10 align-right">
            <input class="button btn-blue" type="submit" value="Order Phone/s"/>
        </div>

    </form>
</div>
</body>
</html>
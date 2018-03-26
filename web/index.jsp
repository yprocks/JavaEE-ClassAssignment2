<%@ page import="java.util.Vector" %>
<%@ page import="model.Phone" %>
<%@ page import="model.PhoneOrder" %>
<%@ page import="java.text.NumberFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2018-03-22
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
    <%--Bootstrap--%>
    <link rel="stylesheet" type="text/css" href="css/overwrite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

  </head>
  <body>
    <%--Product container--%>
    <div class="container">

      <h1 class="display-3 text-center">Devices</h1>

      <%--Row of three products--%>
      <div class="row">
        <c:forEach items="${requestScope.list}" var="item">
          <%--Product column, fits 3 per row--%>
          <div class="col-sm-3 border border-light rounded divSize">
            <form action="OrderProcessor">
              <%--Product Image--%>
              <div class="text-center prodImg">
                <input type="hidden" name="itemImg" value="${item.image}">
                <img src="${item.image}" class="img-fluid" alt="Responsive image">
              </div>

              <%--Product description--%>
              <div class="container">
                <INPUT TYPE="HIDDEN" NAME="itemSKU" VALUE="${item.SKU}">
                <h3 class="text-center prodDes">${item.description}</h3>
                <hr class="aligContentBelow">
              </div>

              <%--Product price--%>
              <h4 class="text-center prodPrice"><fmt:setLocale value = "en_US"/><fmt:formatNumber type="currency">${item.unitPrice}</fmt:formatNumber></h4>

              <%--In Stock and Color--%>
              <div class="clearfix container">
                <p class="text-center font-italic prodColor float-left"><span class="font-weight-bold">In Stock: </span>${item.numberInStock}</p>
                <p class="text-center font-italic prodColor float-right"><span class="font-weight-bold">Color: </span>${item.color}</p>
              </div>

              <%--Quantity--%>
              <p class="">Select Quantity: <input type="number" name="numItems" style="width: 129px; margin-left: 8px"></p>

              <%--Button  --%>
              <div>
                <input class="btn btn-dark btn-block" type="submit" value="Add to cart">
              </div>
            </form>
          </div>
        </c:forEach>
       </div>
        <%--row end--%>
    </div>
    <%--container end--%>
  </body>
</html>

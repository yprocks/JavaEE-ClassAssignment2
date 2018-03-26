<%@ page import="model.PhoneOrder" %>
<%@ page import="java.util.Vector" %>
<%@ page import="servlets.ShoppingCart" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2018-03-23
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Summary</title>
    <%--Bootstrap--%>
    <link rel="stylesheet" type="text/css" href="css/overwrite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

</head>
<body>
    <div class="container">

        <h1 class="display-3 text-center">Order Summary</h1>

        <%
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
            if (cart == null || cart.getNumOrdered() == 0)
            {
                out.println("<H2><I>No items in your cart...</I></H2>");
            }
            else
            {
        %>
        <table class="table" style="margin-top: 5%">
            <thead>
            <tr>
                <th scope="col">SKU</th>
                <th scope="col">Product</th>
                <th scope="col">Unit Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total Price</th>
            </tr>
            </thead>
            <tbody>
            <%
                Vector itemsOrdered = cart.getItemsOrdered();
                PhoneOrder order;
                double cartTotal = 0;
                double tempTotal= 0;

                for(int i=0; i<itemsOrdered.size(); i++)
                {
                    order= (PhoneOrder) itemsOrdered.elementAt(i);
                    tempTotal= order.getProdTotal();
                    cartTotal= tempTotal+ cartTotal;
            %>
            <tr>
                <td>
                    <span><%=order.getItemSKU()%></span>
                </td>
                <td>
                    <div class="row">
                        <div class="col" style="flex-grow: 0.2">
                            <img src="<%=order.getImgPath()%>" class="h-50" style="margin-left: -29px">
                        </div>
                        <div class="col-sm">
                            <span><%=order.getDes()%></span>
                        </div>
                    </div>
                </td>
                <td><%=formatter.format(order.getUnitPrice())%></td>
                <td>
                    <form action="OrderProcessor">
                        <input type="hidden" name="itemSKU" value="<%=order.getItemSKU()%>">
                        <input type="hidden" name="itemImg" value="<%=order.getImgPath()%>">
                        <input type="number" name="numItems" style="width: 129px; margin-left: 8px" value="<%=order.getNumItems()%>">
                        <input class="btn btn-dark btn-sm" type="submit" value="Update Order" style="margin-top: -6px; line-height: normal">
                    </form>
                </td>
                <td><%=formatter.format(order.getProdTotal())%></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><span class="float-right"> Cart Total: </span></td>
                <td class="font-weight-bold"><%=formatter.format(cartTotal)%></td>
            </tr>
            </tbody>
        </table>
        <%
            } //end else
        %>

        <form action="ShowProductList">
            <input class="btn btn-dark float-left" type="submit" value="Continue Shopping">
        </form>
        <form action="ThankYou">
            <input class="btn btn-dark float-right" type="submit" value="Checkout">
        </form>

    </div>
</body>
</html>

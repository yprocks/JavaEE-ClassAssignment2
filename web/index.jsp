<%@ page import="java.util.Vector" %>
<%@ page import="model.Item" %>
<%@ page import="model.ItemImage" %>
<%@ page import="model.ItemOrder" %>
<%@ page import="java.text.NumberFormat" %>

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
    <%--<link rel="stylesheet" type="text/css" href="css/bootstrap.css">--%>
    <link rel="stylesheet" type="text/css" href="css/overwrite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>--%>
  </head>
  <body>
    <%--Product container--%>
    <div class="container">

      <h1 class="display-3 text-center">Devices</h1>

      <%--Row of three products--%>
      <div class="row">
      <%
        //Get attributes from request
        Vector v =(Vector) request.getAttribute("list");
        Vector img =(Vector) request.getAttribute("listImage");

        //Instantiate the models
        ItemImage imageItem= null;
        Item item = null;
        ItemOrder order= null;

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        //Run through vector "v" and assign contents to divs
        for (int i=0; v!=null && i<v.size() ; i++)
        {
            item= (Item) v.elementAt(i);
            imageItem = (ItemImage) img.elementAt(i);
      %>
            <%--Product column, fits 3 per row--%>
            <div class="col-sm-3 border border-light rounded divSize">
              <form action="OrderProcessor">
                <%--Product Image--%>
                <div class="text-center prodImg">
                  <INPUT TYPE="HIDDEN" NAME="itemImg" VALUE="<%=imageItem.getImagePath()%>">
                  <img src="<%=imageItem.getImagePath()%>" class="img-fluid" alt="Responsive image">
                </div>

                <%--Product description--%>
                <div class="container">
                  <INPUT TYPE="HIDDEN" NAME="itemSKU" VALUE="<%=item.getSKU()%>">
                  <h3 class="text-center prodDes"><%=item.getDescription()%></h3>
                  <hr class="aligContentBelow">
                </div>

                <div>
                  <%--Product price--%>
                  <h4 class="text-center prodPrice"><%=formatter.format(item.getUnitPrice())%></h4>

                  <%--In Stock and Color--%>
                  <div class="clearfix container">
                      <p class="text-center font-italic prodColor float-left"><span class="font-weight-bold">In Stock: </span><%=item.getNumberInStock()%></p>
                      <p class="text-center font-italic prodColor float-right"><span class="font-weight-bold">Color: </span><%=item.getColor()%></p>
                  </div>
                  <%--Quantity--%>
                  <p class="">Select Quantity: <input type="number" name="numItems" style="width: 129px; margin-left: 8px"></p>

                  <%--Button  --%>
                  <div>
                    <input class="btn btn-dark btn-block" type="submit" value="Add to cart">
                  </div>
                </div>

              </form>
            </div>
      <%
        }
      %>
       </div>
        <%--row end--%>
    </div>
    <%--container end--%>
  </body>
</html>

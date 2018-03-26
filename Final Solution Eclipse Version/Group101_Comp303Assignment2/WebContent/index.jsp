<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<title>Home</title>
<%--Bootstrap--%>
<%--<link rel="stylesheet" type="text/css" href="css/bootstrap.css">--%>
<link rel="stylesheet" type="text/css" href="css/overwrite.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>--%>
</head>
<body>
	<%--Product container--%>
	<div class="container">

		<h1 class="display-3 text-center">Devices</h1>

		<%--Row of three products--%>
		<div class="row">
			
			<c:forEach items="${list}" var="item">
			
			<%--Product column, fits 3 per row--%>
			<div class="col-sm-3 border border-light rounded divSize">
				<form action="${pageContext.request.contextPath}/phone?action=add-to-cart" method="post">
					<%--Product Image--%>
					<div class="text-center prodImg">
						<%-- <INPUT TYPE="HIDDEN" NAME="itemImg"
							VALUE="${item.sku}">  --%>
							<img
							src="${pageContext.request.contextPath}/img/${item.image}" class="img-fluid"
							alt="Responsive image">
					</div>

					<%--Product description--%>
					<div class="container">
						<INPUT TYPE="HIDDEN" NAME="itemSKU" VALUE="${item.SKU}">
						<h3 class="text-center prodDes">${item.description}</h3>
						<hr class="aligContentBelow">
					</div>

					<div>
						<%--Product price--%>
						<h4 class="text-center prodPrice"><fmt:formatNumber type="currency" >${item.unitPrice}</fmt:formatNumber></h4>

						<%--In Stock and Color--%>
						<div class="clearfix container">
							<p class="text-center font-italic prodColor float-left">
								<span class="font-weight-bold">In Stock: </span>${item.numberInStock}</p>
							<p class="text-center font-italic prodColor float-right">
								<span class="font-weight-bold">Color: </span>${item.color}</p>
						</div>
						<%--Quantity--%>
						<p class="">
							Select Quantity: <input type="number" name="numItems" value="1"
								min="1" max="10" style="width: 129px; margin-left: 8px">
						</p>

						<%--Button  --%>
						<div>
							<input class="btn btn-dark btn-block" type="submit"
								value="Add to cart">
						</div>
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

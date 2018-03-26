<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Order Summary</title>
<%--Bootstrap--%>
<link rel="stylesheet" type="text/css" href="css/overwrite.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="container">

		<h1 class="display-3 text-center">Order Summary</h1>

		<c:choose>
			<c:when test="${fn:length(cart) eq 0}">
				<H2>
					<I>No items in your cart...</I>
				</H2>
				<a href="${pageContext.request.contextPath}/phone">Continue to
					Shop</a>
			</c:when>
			<c:otherwise>
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
						<c:set var="totalCost" value="0.0"></c:set>
						<c:forEach items="${cart}" var="order">

							<c:set var="prodTotal" value="0.0"></c:set>
							<tr>
								<td><span>${order.item.SKU}</span></td>
								<td>
									<div class="row">
										<div class="col" style="flex-grow: 0.2">
											<img
												src="${pageContext.request.contextPath}/img/${order.item.image}"
												class="cart-image" style="margin-left: -29px">
										</div>
										<div class="col-sm">
											<span>${order.item.description}</span>
										</div>
									</div>
								</td>
								<td><fmt:formatNumber type="currency">${order.item.unitPrice}</fmt:formatNumber></td>
								<td>
									<form action="${pageContext.request.contextPath}/phone?action=add-to-cart" method="post">
										<input type="hidden" name="itemSKU" value="${order.item.SKU}">
										<input type="number" name="numItems"
											style="width: 129px; margin-left: 8px"
											value="${order.numItems}"> <input
											class="btn btn-dark btn-sm" type="submit"
											value="Update Order"
											style="margin-top: -6px; line-height: normal">
									</form>
								</td>

								<c:set var="prodTotal">${order.numItems*order.item.unitPrice}</c:set>
								<td><fmt:formatNumber type="currency">
										<c:out value="${prodTotal}"></c:out>
									</fmt:formatNumber></td>
							</tr>
	
							<c:set var="totalCost">${totalCost+prodTotal}</c:set>

						</c:forEach>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><span class="float-right"> Cart Total: </span></td>
							<td class="font-weight-bold"><fmt:formatNumber
									type="currency">${totalCost}</fmt:formatNumber></td>
						</tr>
					</tbody>
				</table>

			</c:otherwise>
		</c:choose>
		
		<form action="${pageContext.request.contextPath}/phone">
			<input class="btn btn-dark float-left" type="submit"
				value="Continue Shopping">
		</form>
		<form action="${pageContext.request.contextPath}/phone?action=checkout" method="post">
			<input class="btn btn-dark float-right" type="submit"
				value="Checkout">
		</form>

	</div>
</body>
</html>

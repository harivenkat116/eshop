<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
table, td, th {
	border: 1px solid blue;
}
th {
	background-color: blue;
	color: white;
}
</style>
</head>
<body>
	<h3>You have added the following items to the cart.</h3>

	<table>
		<tr>
			<th>Name</th>
			<th>Type</th>
			<th>Price</th>
			<th></th>
		</tr>
		<c:forEach items="${ShoppingCart}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.type}</td>
				<td>${product.price}</td>

				<td><a href="/eshop3/cart?productId=${product.id}">Add to
						Cart</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<h2>Do you want to shop more?Please click <a href="/eshop3">here</a> </h2> 
	<h3>If you are done shopping, please click <a href="/eshop3/checkout">here</a></h3>

</body>
</html>
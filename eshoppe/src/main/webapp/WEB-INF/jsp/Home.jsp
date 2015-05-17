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
	border: 1px solid green;
}
th {
	background-color: green;
	color: white;
}
</style>

</head>
<body>

	<h2>Welcome to eShop</h2>
	
	<table>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Type</th>
		<th>Price</th>
		<th></th>
	</tr>
		<c:forEach items="${AllProducts}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.type}</td>
				<td>${product.price}</td>
				
				<td><a href="/eshop3/cart?productId=${product.id}">Add to Cart</a></td>
			</tr>	
		</c:forEach>
	</table>	
	

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 24/06/2019
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/product">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["product"].getName()}</td>
    </tr>
    <tr>
        <td>Descreption: </td>
        <td>${requestScope["product"].getDescreption()}</td>
    </tr>
    <tr>
        <td>Price: </td>
        <td>${requestScope["product"].getPrice()}</td>
    </tr>
    <tr>
        <td>Status: </td>
        <td>${requestScope["product"].isStatus()}</td>
    </tr>
    <tr>
        <td>Avatar: </td>
        <td><img src="<%request.getServletContext().getRealPath("");%>/uploadDir/${product.getAvatar()}" id="image"></td>
    </tr>
</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 21/06/2019
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<center>
    <h1>Product</h1>
    <p>
        <a href="/product?action=create">Create new product</a>
        <br>
    <form method="post" action="/product?action=seach" id="formSeach">
        <fieldset>
            <legend>Seach</legend>
            <table>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="nameSeach" id="nameSeach"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Seach product"></td>
                </tr>
            </table>
        </fieldset>
    </form>

    </p>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Descreption</td>
            <td>Price</td>
            <td>Status</td>
            <td>Edit</td>
            <td>Delete</td>
            <td>Avatar</td>
        </tr>
        <c:forEach items='${requestScope["products"]}' var="product">
            <tr>
                <td><a href="/product?action=view&id=${product.getId()}">${product.getName()}</a></td>
                <td>${product.getDescreption()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.isStatus()}</td>
                <td><a href="/product?action=edit&id=${product.getId()}">edit</a></td>
                <td><a href="/product?action=delete&id=${product.getId()}">delete</a></td>
                <td><img src="${product.getAvatar()}"></td>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 24/06/2019
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit product</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/product">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["product"].getName()}"></td>
            </tr>
            <tr>
                <td>Descreption: </td>
                <td><input type="text" name="descreption" id="descreption" value="${requestScope["product"].getDescreption()}"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price" value="${requestScope["product"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Status: </td>
                <td><input type="text" name="status" id="status" value="${requestScope["product"].isStatus()}"></td>
            </tr>
            <tr>
                <td>Avatar: </td>
                <td><input type="file" name="avatar" id="avatar" value="${requestScope["product"].getAvatar()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>

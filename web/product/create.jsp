<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 22/06/2019
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>
    <p>
        <c:if test='${requestScope["message"] != null}'>
            <span class="message">${requestScope["message"]}</span>
        </c:if>
    <p>
        <a href="/product">Back to product list</a>
    </p>
    <form method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Product information</legend>
            <table>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td>Descreption: </td>
                    <td><input type="text" name="descreption" id="descreption"></td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td><input type="text" name="price" id="price"></td>
                </tr>
                <tr>
                    <td>Status: </td>
                    <td><input type="text" name="status" id="status"></td>
                </tr>
                <tr>
                    <td>Avartar: </td>
                    <td><input type="file" name="file" id="avatar"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Create product"></td>
                </tr>
            </table>
        </fieldset>
    </form>
    </p>
</body>
</html>

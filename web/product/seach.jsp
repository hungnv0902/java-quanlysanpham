<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 25/06/2019
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seach product</title>
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
<form method="post">
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
</body>
</html>

<%@ page import="crudServlet.User" %>
<%@ page import="crudServlet.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Up
  Date: 06.01.2019
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IndexJSP</title>
</head>
<body>
This is JSP page!

<form action="<%=request.getContextPath()%>/user" method="post">
    name: <input type="text" name="name" value="name"> <br>
    login: <input type="text" name="login" value="login"> <br>
    email: <input type="text" name="email" value="email"> <br>
    <input type="submit" name="action" value="add"> <br>
</form>
<br>
List users
<table border="1">
    <tr>
        <td> id</td>
        <td> name</td>
        <td> login</td>
        <td> e-mail</td>
        <td> update</td>
        <td> delete</td>
    </tr>
    <% for (User user : ValidateService.getInstance().findAllMap().values()) { %>
    <form action="<%=request.getContextPath()%>/user" method="post">
        <tr>
            <td><input type='text' name='id' value="<%=user.getId()%>" readonly></td>
            <td><input type='text' name='name' value="<%=user.getName()%>"></td>
            <td><input type='text' name='login' value="<%=user.getLogin()%>"></td>
            <td><input type='text' name='email' value="<%=user.getEmail()%>"></td>
            <td><input type="submit" name="action" value="update"></td>
            <td><input type="submit" name="action" value="delete"></td>
        </tr>
    </form>
    <%}%>
</table>
</body>
</html>
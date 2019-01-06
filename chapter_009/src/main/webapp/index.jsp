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


<table>
    <tr>
        <th>
            <form action="<%=request.getContextPath()%>/user" method="post">
                name: <input type="text" name="name" value="name"> <br>
                login: <input type="text" name="login" value="login"> <br>
                email: <input type="text" name="email" value="email"> <br>
                <input type="submit" name="action" value="add"> <br>
            </form>
        </th>
        <th>
            <form action="<%=request.getContextPath()%>/edit" method="post">
                id: <input type="text" name="id" value="enter id"> <br>
                name: <input type="text" name="name" value="enter name"> <br>
                login: <input type="text" name="login" value="enter login"> <br>
                email: <input type="text" name="email" value="enter email"> <br>
                <input type="submit" name="action" value="update"> <br>
            </form>
        </th>
    </tr>
</table>
<br>
List users
<table>
    <% for (User user : ValidateService.getInstance().findAllMap().values()) { %>
    <form action="<%=request.getContextPath()%>/user" method="post">
        <tr>
            <td><input type='text' name='id' value="<%=user.getId()%>" readonly></td>
            <td><input type='text' name='name' value="<%=user.getName()%>" ></td>
            <td><input type='text' name='login' value="<%=user.getLogin()%>" ></td>
            <td><input type='text' name='email' value="<%=user.getEmail()%>" ></td>
            <td><input type="submit" name="action" value="update"></td>
            <td><input type="submit" name="action" value="delete"></td>
        </tr>
    </form>
    <%}%>
</table>
</body>
</html>

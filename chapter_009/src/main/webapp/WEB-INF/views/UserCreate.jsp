<%--
  Created by IntelliJ IDEA.
  User: Up
  Date: 14.01.2019
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUserJSP</title>
</head>
<body>
This is JSP page, when you can create NEW User !
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    name: <input type="text" name="name" value="name"> <br>
    login: <input type="text" name="login" value="login"> <br>
    email: <input type="text" name="email" value="email"> <br>
    role:
    <select name="role">
        <option value="admin">admin</option>
        <option value="user">user</option>
        <option value="guest">guest</option>
    </select><br>
    <input type="submit" name="action" value="addPasswordRole"> <br>
</form>
<br>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Up
  Date: 16.01.2019
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LoginViewJSP</title>
</head>
<body>
This is JSP page for LoginView.<br>
<c:if test="${error != ''}">
    <div style="color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit"><br>
</form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
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

<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="submit" name="action" value="exit" style="color: red">
</form>

<c:if test="${sessionScope.get('access')==1}">
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        name: <input type="text" name="name" value="name"> <br>
        login: <input type="text" name="login" value="login"> <br>
        email: <input type="text" name="email" value="email"> <br>
        <input type="submit" name="action" value="add"> <br>
    </form>
    <br>
</c:if>

List users
<table border="1">
    <tr>
        <td> id</td>
        <td> name</td>
        <td> login</td>
        <td> e-mail</td>
        <c:if test="${sessionScope.get('access')<3}">
        <td> update</td>
        <td> delete</td>
        </c:if>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <td><input type='text' name='id' value="<c:out value="${user.id}"></c:out>" readonly></td>
                <td><input type='text' name='name' value="<c:out value="${user.name}"></c:out>"></td>
                <td><input type='text' name='login' value="<c:out value="${user.login}"></c:out>"></td>
                <td><input type='text' name='email' value="<c:out value="${user.email}"></c:out>"></td>
                <c:if test="${sessionScope.get('access')<3}">
                <td><input type="submit" name="action" value="update"></td>
                <td><input type="submit" name="action" value="delete"></td>
                </c:if>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
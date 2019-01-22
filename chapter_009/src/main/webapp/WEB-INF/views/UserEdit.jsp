<%--
  Created by IntelliJ IDEA.
  User: Up
  Date: 14.01.2019
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserEdit</title>
</head>
<body>
This is JSP page, when you can edit User !
<table border="1">
    <tr>
        <td> id</td>
        <td> name</td>
        <td> login</td>
        <td> e-mail</td>
        <td> update</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <form action="${pageContext.servletContext.contextPath}/edit" method="post">
                <td><input type='text' name='id' value="<c:out value="${user.id}"></c:out>" readonly></td>
                <td><input type='text' name='name' value="<c:out value="${user.name}"></c:out>"></td>
                <td><input type='text' name='login' value="<c:out value="${user.login}"></c:out>"></td>
                <td><input type='text' name='email' value="<c:out value="${user.email}"></c:out>"></td>
                <td><input type="submit" name="action" value="update"></td>
                <%--<c:param name="loginOld" value="<c:out value='${user.login}'></c:out>"></c:param>--%>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>

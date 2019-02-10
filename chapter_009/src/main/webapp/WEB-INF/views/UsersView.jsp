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
    <script>
        function chosenCountry(country) {
            var listTown0 = ["Choose first country"];
            var listTown1 = ["Moscow", "SPG", "Tagil"];
            var listTown2 = ["Minsk", "Gomel", "Brest"];
            var listTown3 = ["Kiev", "Lvov", "Lugansk"];
            return country == 1 ? listTown1 : country == 2 ? listTown2 : country==3 ? listTown3 : listTown0;
        }

        function addTown(country) {
            var town = chosenCountry(country);
            var elementForDelete = document.querySelectorAll("option.town");
            var addOpt;
            var opt = document.getElementById("town");
            var j;
            for (j = 0; j < elementForDelete.length; j++) {
                elementForDelete[j].parentNode.removeChild(elementForDelete[j]);
            }
            for (j = 0; j < town.length; j++) {
                addOpt = document.createElement("option");
                addOpt.text = town[j];
                addOpt.setAttribute("value", j);
                addOpt.setAttribute("id", "town" + j);
                addOpt.setAttribute("class", "town");
                opt.appendChild(addOpt);
            }
        }

        function ololo() {
            var myElement = document.querySelectorAll("option.town");
            alert(myElement.length);
            for (var t = 0; t < myElement.length; t++) {
                myElement[t].parentNode.removeChild(myElement[t]);
            }
        }
    </script>
</head>
<body>
This is UsersView JSP page! <br>
You enter as <b><c:out value="${sessionScope.get('login')} "></c:out></b> and you role is <b><c:out
        value="${sessionScope.get('role')}"></c:out>.</b><br>

<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="submit" name="action" value="exit" style="color: red">
</form>

<c:if test="${sessionScope.get('addNot') != ''}">
    Attention !!!
    <div style="color: red">
        <c:out value="${sessionScope.get('addNot')}"></c:out>
    </div>
    <br>
</c:if>

<c:if test="${sessionScope.get('updateNot')!= ''}">
    Attention !!!
    <div style="color: red">
        <c:out value="${sessionScope.get('updateNot')}"></c:out>
    </div>
    <br>
</c:if>

<c:if test="${sessionScope.get('access')<3}">
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        name: <input type="text" name="name" value="name"> <br>
        login: <input type="text" name="login" value="login"> <br>
        email: <input type="text" name="email" value="email"> <br>
        password: <input type="text" name="password" value=""> <br>
        role:
        <select name="role">
            <option value="admin">admin</option>
            <option value="user">user</option>
            <option value="guest">guest</option>
        </select><br>
        <input type="submit" name="action" value="addPasswordRole"> <br>
    </form>
    <br>
</c:if>
<br>
<select name="countryName" id="country" class="coutryAdd" onchange="addTown(value)">
    <option value="0" class="country">Choose country</option>
    <option value="1" class="country">Russia</option>
    <option value="2" class="country">Belarus</option>
    <option value="3" class="country">Ukraine</option>
</select>
<select name="townName" id="town" class="townAdd">
    <option value="notChoose" class="town"> First choose country</option>
</select>
<br>List users
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
            <form action="${pageContext.servletContext.contextPath}/edit" method="post">
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

<button type="button" onclick="ololo()">Waaagh</button>
</body>
</html>
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function countryFromAjax() {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "./json?value=country", false);
            xhttp.send();
            var addOpt;
            var opt = document.getElementById("country");
            var obj = JSON.parse(xhttp.responseText);
            for (var j = 0; j < obj.length; j++) {
                addOpt = document.createElement("option");
                addOpt.text = obj[j];
                addOpt.setAttribute("value", obj[j]);
                addOpt.setAttribute("id", "country" + j);
                addOpt.setAttribute("class", "country");
                opt.appendChild(addOpt);
            }
        }

        function addTownFromAjax(country) {
            var elementForDelete = document.querySelectorAll("option.town");
            for (j = 0; j < elementForDelete.length; j++) {
                elementForDelete[j].parentNode.removeChild(elementForDelete[j]);
            }
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "./json?value=" + country, false);
            xhttp.send();
            var addOpt;
            var opt = document.getElementById("town");
            var obj = JSON.parse(xhttp.responseText);
            for (var j = 0; j < obj.length; j++) {
                addOpt = document.createElement("option");
                addOpt.text = obj[j];
                addOpt.setAttribute("value", obj[j]);
                addOpt.setAttribute("id", "town" + j);
                addOpt.setAttribute("class", "town");
                opt.appendChild(addOpt);
            }
        }

        function cleanForm() {
            document.getElementById("createName").setAttribute("value", "");
            document.getElementById("createLogin").setAttribute("value", "");
            document.getElementById("createEmail").setAttribute("value", "");
        }

        function validate() {
            var result = true;
            var name = {va: document.getElementById("createName").value};
            if (name.va == '') {
                alert("Enter name");
                result = false;
            }
            var surname = {va: document.getElementById("createLogin").value};
            if (surname.va == '') {
                alert("Enter login");
                result = false;
            }
            var email = {va: document.getElementById("createEmail").value};
            if (email.va == '') {
                alert("Enter email");
                result = false;
            }
            var pwd = {va: document.getElementById("createPassword").value};
            if (pwd.va == '') {
                alert("Enter password");
                result = false;
            }
            return result;
        }


    </script>
</head>
<body onload="countryFromAjax()">
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
        <div class="form-group">
            <label>name: </label> <input type="text" name="name" value="name" id="createName"></div>
        <div class="form-group"><label>login: </label><input type="text" name="login" value="login" id="createLogin">
        </div>
        <div class="form-group"><label>email: </label><input type="email" name="email" value="email" id="createEmail">
        </div>
        <div class="form-group"><label>password: </label><input type="password" name="password" value=""
                                                                id="createPassword"></div>
        role:
        <select name="role">
            <option value="admin">admin</option>
            <option value="user">user</option>
            <option value="guest">guest</option>
        </select><br>
        <input type="submit" name="action" value="addPasswordRole" onclick="return validate();"> <br>
    </form>
    <br>
</c:if>
<br>
<select name="countryName" id="country" class="coutryAdd" onchange="addTownFromAjax(value)">
    <option value="chooseCountry" class="country">Choose country</option>
</select>
<select name="townName" id="town" class="townAdd" onchange="cleanForm()">
    <option value="notChoose" class="town"> First choose country</option>
</select>
<br><br>List users
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
</body>
</html>
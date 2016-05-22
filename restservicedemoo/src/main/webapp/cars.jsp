<%@ page import="com.example.restservicedemo.service.PersonManager" %>
<%@ page import="com.example.restservicedemo.domain.Person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: elzoy
  Date: 5/22/2016
  Time: 5:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="./interface.js"></script>
    <link rel="stylesheet" type="text/css" href="./public/global.css">
</head>
<body>
<div class="menu">
    <ul>
        <a href="./index.jsp"><li>Home page</li></a>
        <a href="./persons.jsp"><li>Persons</li></a>
        <a href="./cars.jsp"><li>Cars</li></a>
    </ul>
</div>
<h1>The service is running!</h1>
<p>ladzia</p>
<%
    PersonManager pm = new PersonManager();
    List<Person> plist = pm.getAllPersons();
%>

<c:forEach items="${plist}" var="item">
    ${item}<br>
</c:forEach>

<%= request.getParameter("name") %>
</body>
</html>

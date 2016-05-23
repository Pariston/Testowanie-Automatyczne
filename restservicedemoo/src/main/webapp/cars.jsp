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
    <script src="./car_interface.js"></script>
    <link rel="stylesheet" type="text/css" href="./public/global.css">
</head>
<body>
<div class="menu">
    <ul class="ulmenu">
        <a href="./index.jsp"><li>Home page</li></a>
        <a href="./persons.jsp"><li>Persons</li></a>
        <a href="./cars.jsp"><li>Cars</li></a>
    </ul>
</div>
<ul class="submenu">
    <li class="view">View all cars</li>
    <li class="add">Add a car</li>
</ul>

<div id="carAddContainer">

</div>
<div id="carsList">
    <table></table>
</div>
</body>
</html>

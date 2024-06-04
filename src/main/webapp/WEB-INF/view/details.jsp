<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Person</title>
</head>
<body>
<h2>Deatils Person</h2>
<form action="${pageContext.request.contextPath}/details" method="get">
    <input type="hidden" id="id" name="id" value="${person.id}"><br>
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${person.name}" required><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname" value="${person.surname}" required><br>
    <label for="middleName">Middle Name:</label><br>
    <input type="text" id="middleName" name="middleName" value="${person.middleName}" required><br><br>
</form>
</body>
</html>

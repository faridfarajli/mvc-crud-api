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
<h2>Delete Person</h2>
<form method="post" action="/delete">
    <input type="text" id="id" name="id" value="${person.id}"><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>

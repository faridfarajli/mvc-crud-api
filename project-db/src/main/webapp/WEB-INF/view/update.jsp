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
    <h2>Update Person</h2>
    <form method="post" action="/update">
        <input type="hidden" id="id" name="id" value="${person.id}"><br>
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" value="${person.name}"><br>
        <label for="surname">Surname:</label><br>
        <input type="text" id="surname" name="surname" value="${person.surname}"><br>
        <label for="middleName">Middle Name:</label><br>
        <input type="text" id="middleName" name="middleName" value="${person.middleName}"><br><br>
        <input type="submit" value="Update">
    </form>
    </body>
    </html>

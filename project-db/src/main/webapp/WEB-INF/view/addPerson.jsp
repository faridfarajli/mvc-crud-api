<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Person</title>
</head>
<body>
<h1>Add New Person</h1>
<% String message = (String)request.getAttribute("message"); %>
<% if (message != null && !message.isEmpty()) { %>
<p style="color:red;"><%= message %></p>
<% } %>
<form action="${pageContext.request.contextPath}/addPerson" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" required> <br><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname" required><br><br>
    <label for="middleName">Middle Name:</label><br>
    <input type="text" id="middleName" name="middleName" required><br><br>
    <label for="sex">Sex:</label><br>
    <select id="sex" name="sex" required>
        <option value="20000015">Kisi</option>
        <option value="20000016">Qadin</option>
    </select><br><br>
    <input type="submit" value="Save">
</form>
</body>
</html>

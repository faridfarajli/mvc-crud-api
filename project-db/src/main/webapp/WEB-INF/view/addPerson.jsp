<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Person</title>
</head>
<body>
<h1>Add New Person</h1>
<form action="${pageContext.request.contextPath}/addPerson" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${person.name}" required> <br><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname" value="${person.surname}" required><br><br>
    <label for="middleName">Middle Name:</label><br>
    <input type="text" id="middleName" name="middleName" value="${person.middleName}"><br><br>
    <label for="sex">Sex:</label><br>
    <input type="text" id="sex" name="sex" value="${person.sex}"><br><br>
    <label for="comPersonUniqId">Com Person Uniq ID:</label><br>
    <input type="text" id="comPersonUniqId" name="comPersonUniqId" value="${person.comPersonUniqId}"><br><br>
    <label for="active">Active:</label><br>
    <input type="text" id="active" name="active" value="${person.active}"><br><br>
    <label for="notificationStatus">Notification Status:</label><br>
    <input type="text" id="notificationStatus" name="notificationStatus" value="${person.notificationStatus}"><br><br>
    <label for="newC">New C:</label><br>
    <input type="text" id="newC" name="newC" value="${person.newC}"><br><br>
    <label for="oldUsername">Old Username:</label><br>
    <input type="text" id="oldUsername" name="oldUsername" value="${person.oldUsername}"><br><br>
    <input type="submit" value="Save">
</form>
</body>
</html>

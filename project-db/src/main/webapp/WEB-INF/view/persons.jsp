<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person Info</title>
    <style>
        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); /* Adjusted to fit all columns */
            grid-gap: 5px;
            padding: 5px;
            overflow-x: auto; /* Enable horizontal scrolling if needed */
        }
        .grid-item {
            border: 1px solid #ccc;
            padding: 5px;
            text-align: center;
        }
        .grid-item-header {
            font-weight: bold;
            cursor: pointer;
        }
    </style>
    <script>
        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.getElementById("personTable");
            switching = true;
            dir = "asc";
            while (switching) {
                switching = false;
                rows = table.getElementsByTagName("TR");
                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];
                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch= true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch= true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount ++;
                } else {
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }
    </script>
</head>
<body>
<h1>Person Information</h1>



<select id="" name="links" size="1" onchange="window.location.href=this.value">
    <option <c:if test = "${param.type==0}">selected</c:if> value="http://localhost:8080/person-info?type=0">All</option>
    <option <c:if test = "${param.type==1}">selected</c:if> value="http://localhost:8080/student-info?type=1">Student</option>
</select>
<a href="http://localhost:8080/addPerson/">Add</a>

<div class="grid-container">
    <div class="grid-item" onclick="sortTable(0)">ID</div>
    <div class="grid-item" onclick="sortTable(1)">Name</div>
    <div class="grid-item" onclick="sortTable(2)">Surname</div>
    <div class="grid-item" onclick="sortTable(3)">Middle Name</div>
    <div class="grid-item" onclick="sortTable(4)">Sex</div>
    <div class="grid-item" onclick="sortTable(5)">Birth Date </div>
    <div class="grid-item" onclick="sortTable(6)">Com Person Uniq ID</div>
    <div class="grid-item" onclick="sortTable(7)">Change Date</div>
    <div class="grid-item" onclick="sortTable(8)">Active</div>
    <div class="grid-item" onclick="sortTable(9)">Notification Status</div>
    <div class="grid-item" onclick="sortTable(10)">New C</div>
    <div class="grid-item">Actions</div>

</div>
<div class="grid-container">

    <c:forEach items="${persons}" var="person">

            <div class="grid-item">${person.id}</div>
            <div class="grid-item">${person.name}</div>
            <div class="grid-item">${person.surname}</div>
            <div class="grid-item">${person.middleName}</div>
            <div class="grid-item">${person.sex}</div>
            <div class="grid-item">${person.birthDate}</div>
            <div class="grid-item">${person.comPersonUniqId}</div>
            <div class="grid-item">${person.changeDate}</div>
            <div class="grid-item">${person.active}</div>
            <div class="grid-item">${person.notificationStatus}</div>
            <div class="grid-item">${person.newC}</div>
            <div class="grid-item">
                <a href="http://localhost:8080/update/${person.id}">Edit</a> /
                <a href="http://localhost:8080/delete/${person.id}">Delete</a>
            </div>

    </c:forEach>
</div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person Info</title>
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
<table border="1" id="personTable">
    <thead>
    <tr>
        <th onclick="sortTable(0)">ID</th>
        <th onclick="sortTable(1)">Name</th>
        <th onclick="sortTable(2)">Surname</th>
        <th onclick="sortTable(3)">Middle Name</th>
        <th onclick="sortTable(4)">Sex</th>
        <th onclick="sortTable(5)">Birth Date</th>
        <th onclick="sortTable(6)">Com Person Uniq ID</th>
        <th onclick="sortTable(7)">Change Date</th>
        <th onclick="sortTable(8)">Active</th>
        <th onclick="sortTable(9)">Notification Status</th>
        <th onclick="sortTable(10)">New C</th>
        <th onclick="sortTable(11)">Old Username</th>
        <th>Edit</th>
        <th>Delete</th>
        <td><a href="http://localhost:8080/addPerson">Add</a></td>



    </tr>
    </thead>

    <tbody>
    <c:forEach items="${persons}" var="person">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.surname}</td>
            <td>${person.middleName}</td>
            <td>${person.sex}</td>
            <td>${person.birthDate}</td>
            <td>${person.comPersonUniqId}</td>
            <td>${person.changeDate}</td>
            <td>${person.active}</td>
            <td>${person.notificationStatus}</td>
            <td>${person.newC}</td>
            <td>${person.oldUsername}</td>
            <td><a href="http://localhost:8080/update/${person.id}">Edit</a></td>
            <td><a href="http://localhost:8080/delete/${person.id}">Delete</a></td>


        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

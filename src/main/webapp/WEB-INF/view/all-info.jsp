<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person Info</title>
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/plugins/ui.multiselect.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-easyui@1.5.21/js/jquery.easyui.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#searchIdInput, #searchNameInput, #searchSurnameInput, #typeSelector, #activeSelector, #sexSelector').on('input change', fetchResults);

            function fetchResults() {
                const id = $('#searchIdInput').val();
                const queryName = $('#searchNameInput').val();
                const querySurname = $('#searchSurnameInput').val();
                const type = $('#typeSelector').val();
                const active = $('#activeSelector').val();
                const sex = $('#sexSelector').val();

                $.ajax({
                    url: 'search',
                    method: 'GET',
                    data: { id, name: queryName, surname: querySurname, type, active, sex },
                    success: function(data) {
                        $('#searchResults').empty();

                        if (data && data.length > 0) {
                            data.forEach(item => {
console.log(item);

                                const row = `<tr>
                                    <td>` + item.ID + `</td>
                                    <td>` + item.NAME + `</td>
                                    <td>` + item.SURNAME + `</td>
                                    <td>` + item.MIDDLE_NAME  + `</td>
                                    <td>` + item.SEX + `</td>
                                    <td>` + item.BIRTH_DATE +`</td>
                                    <td>` + item.COM_PERSON_UNIQ_ID +`</td>
                                    <td>` + item.CHANGE_DATE + `</td>
                                    <td>` + item.ACTIVE + `</td>
                                    <td>` + item.NOTIFICATION_STATUS + `</td>
                                    <td>` + item.NEW_C + `</td>
                                    <td>
                                        <a href="http://localhost:8080/update/${item.id}">Update</a> /
                                        <a href="http://localhost:8080/delete/${item.id}">Delete</a>
                                    </td>
                                </tr>`;
                                $('#searchResults').append(row);
                            });
                        } else {
                            const noDataRow = `<tr><td colspan="12" style="text-align: center;">No results found.</td></tr>`;
                            $('#searchResults').append(noDataRow);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("Error fetching data:", status, error);
                    }
                });
            }
        });
    </script>
</head>
<body>
    <input type="number" id="searchIdInput" placeholder="Search by ID...">
    <input type="text" id="searchNameInput" placeholder="Search by name...">
    <input type="text" id="searchSurnameInput" placeholder="Search by surname...">

    <select id="typeSelector">
        <option value="">All</option>
        <option value="student">Student</option>
    </select>

    <select id="activeSelector">
        <option value="">All</option>
        <option value="1">Active</option>
        <option value="0">Inactive</option>
    </select>

    <select id="sexSelector">
        <option value="">All</option>
        <option value="20000015">Male</option>
        <option value="20000016">Female</option>
    </select>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Middle Name</th>
                <th>Sex</th>
                <th>Birth Date</th>
                <th>Com Person Uniq ID</th>
                <th>Change Date</th>
                <th>Active</th>
                <th>Notification Status</th>
                <th>New C</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="searchResults"></tbody>
    </table>
</body>
</html>

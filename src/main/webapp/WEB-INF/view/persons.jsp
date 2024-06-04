<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-easyui@1.5.21/js/jquery.easyui.min.js"></script>

<body>
<input type="text" id="searchInput" placeholder="Search by name...">
<ul id="searchResults"></ul>
<script>
    const searchInput = document.getElementById('searchInput');
    const searchResults = document.getElementById('searchResults');

    searchInput.addEventListener('input', function() {
        const query = searchInput.value;

        if (query === '') {
            searchResults.innerHTML = '';
            return;
        }

        fetch('searchbyname?query=' + query)
            .then(response => response.json())
            .then(data => {
                searchResults.innerHTML = '';
                data.forEach(item => {
                    const li = document.createElement('li');
                    li.textContent = item.name;
                    li.addEventListener('click', function() {
                        window.location.href = 'details/' + item.id;
                    });
                    searchResults.appendChild(li);
                });
            })
            .catch(error => console.error('Error:', error));
    });
</script>
</body>

<style>
    .grid-container {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
        grid-gap: 5px;
        padding: 5px;
        overflow-x: auto;
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
    function setType(type){
        sessionStorage.setItem("selectedType", type)
    }

    function getSelectedType(){
        return sessionStorage.getItem("selectedType")
    }

    window.onload = function (){
        var selectedType = getSelectedType()
        if (selectedType){
            document.getElementById("typeSelector").value=selectedType
        }
    }

</script>


</head>
<body>
<h1>Person Information</h1>

<select id="typeSelector" name="links" size="1" onchange="setType(this.value); window.location.href=this.value">
    <option value="http://localhost:8080/person-info/1">All</option>
    <option value="http://localhost:8080/student-info">Student</option>
</select>

<a href="http://localhost:8080/addPerson/">Add</a>
<button id="downloadExcel">Download All Data</button>
<button id="downloadDataExcel">Download Excel</button>



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

<table id="dg" title="" style="width:300px;height:50px"></table>

<script type="text/javascript">
    $(function() {
        $('#dg').datagrid({
            url: '/person-info',
            method: 'post',
            columns: [[
                {field: 'id', width: 150},
                {field: 'name', width: 120},
                {field: 'surname', width: 120},
                {field: 'middleName', width: 80},
                {field: 'sex', width: 120},
                {field: 'birthDate', width: 100},
                {field: 'comPersonUniqId', width: 120},
                {field: 'changeDate', width: 150},
                {field: 'active', width: 100},
                {field: 'notificationStatus', width: 130},
                {field: 'newC', width: 20},
                {
                    field: 'actions',
                    width: 150,
                    align: 'right',
                    formatter: function (value, row, index) {
                        var id = row.id;

                        var updateBtn = '<a href="http://localhost:8080/update/' + id + '">Update</a>';
                        var deleteBtn = '<a href="http://localhost:8080/delete/' + id + '">Delete</a>';
                        return updateBtn + ' | ' + deleteBtn;
                    }
                }
            ]],

            pagination: true,
            pageSize: 10,
            pageNumber: 1,
            pageList: [10, 20, 30,40],
            onLoadSuccess: function(data) {
                if (data.total) {
                    $(this).datagrid('options').queryParams.totalRecords = data.total;
                }
            }
        });
        $('#downloadExcel').on('click', function() {
            window.location.href = '/excel';
        });


        $('#downloadDataExcel').on('click', function() {
            var opts = $('#dg').datagrid('getPager').data('pagination').options;
            var page = opts.pageNumber;
            var pageSize = opts.pageSize;
            window.location.href = '/excel-data?page=' + page + '&pageSize=' + pageSize;
        });
        });





</script>





<style>
    .datagrid-pager {
        position:relative;
        bottom: 180px;
        left: 300px;
        width: 190%;
    }
</style>




<%--<div class="grid-container">--%>
<%--    <c:forEach items="${persons}" var="person">--%>
<%--        <div class="grid-item">${person.id}</div>--%>
<%--        <div class="grid-item">${person.name}</div>--%>
<%--        <div class="grid-item">${person.surname}</div>--%>
<%--        <div class="grid-item">${person.middleName}</div>--%>
<%--        <div class="grid-item">${person.sex}</div>--%>
<%--        <div class="grid-item">${person.birthDate}</div>--%>
<%--        <div class="grid-item">${person.comPersonUniqId}</div>--%>
<%--        <div class="grid-item">${person.changeDate}</div>--%>
<%--        <div class="grid-item">${person.active}</div>--%>
<%--        <div class="grid-item">${person.notificationStatus}</div>--%>
<%--        <div class="grid-item">${person.newC}</div>--%>
<%--        <div class="grid-item">--%>
<%--            <a href="http://localhost:8080/update/${person.id}">Edit</a> /--%>
<%--            <a href="http://localhost:8080/delete/${person.id}">Delete</a>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</div>--%>

<%--<button id="showMoreButton" onclick="showNextPage()">Show More</button>--%>

<%--<script>--%>
<%--    function storeScrollPosition() {--%>
<%--        sessionStorage.setItem('scrollPosition', window.scrollY);--%>
<%--    }--%>

<%--    function scrollToStoredPosition() {--%>
<%--        var scrollPosition = sessionStorage.getItem('scrollPosition');--%>
<%--        if (scrollPosition) {--%>
<%--            window.scrollTo(0, scrollPosition);--%>
<%--            sessionStorage.removeItem('scrollPosition');--%>
<%--        }--%>
<%--    }--%>

<%--    function showNextPage() {--%>
<%--        var currentPage = window.location.pathname.split('/').pop();--%>
<%--        var nextPage = parseInt(currentPage) + 1;--%>
<%--        if (nextPage == 6) {--%>
<%--            alert("Not Found");--%>
<%--        } else {--%>
<%--            storeScrollPosition();--%>
<%--            var nextPageUrl = 'http://localhost:8080/person-info/' + nextPage;--%>
<%--            window.location.href = nextPageUrl;--%>
<%--        }--%>
<%--    }--%>
<%--    window.onload = scrollToStoredPosition;--%>
<%--</script>--%>


</body>
</html>
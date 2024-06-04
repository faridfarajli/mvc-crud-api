<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <meta charset="UTF-8">
    <title>Add Person</title>
</head>
<body>
<h1>Add New Person</h1>

<%--<script>--%>
<%--    $(function() {--%>
<%--        // Dialog penceresini gizle--%>
<%--        $("#dialog").hide();--%>

<%--        $("form").submit(function(event) {--%>
<%--            event.preventDefault();--%>

<%--            $.ajax({--%>
<%--                type: "POST",--%>
<%--                url: $(this).attr("action"),--%>
<%--                data: $(this).serialize(),--%>
<%--                success: function(response) {--%>
<%--                    // Form gönderildiğinde dialog penceresini aç--%>
<%--                    $("#dialog").dialog();--%>
<%--                },--%>
<%--                error: function(xhr, status, error) {--%>
<%--                    console.error(xhr.responseText);--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>

<div id="dialog" title="Success">
    <p>Person successfully added</p>
</div>

<%String errorMessage = (String)request.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
<p style="color: red;"><%= errorMessage %></p>
<% } %>

<form id="personForm" action="${pageContext.request.contextPath}/addPerson" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" required><br><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname" required><br><br>
    <label for="middleName">Middle Name:</label><br>
    <input type="text" id="middleName" name="middleName" required><br><br>
    <label for="sex">Sex:</label><br>
    <select id="sex" name="sex" required>
        <option value="20000015">Male</option>
        <option value="20000016">Female</option>
    </select><br><br>
    <input type="submit" value="Save">
</form>

</body>
</html>

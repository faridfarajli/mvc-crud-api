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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
</head>
<body>
<h2>Update Person</h2>
<form id="updateForm" action="${pageContext.request.contextPath}/update" method="post">
    <input type="hidden" id="id" name="id" value="${person.id}"><br>
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${person.name}" required><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname" value="${person.surname}" required><br>
    <label for="middleName">Middle Name:</label><br>
    <input type="text" id="middleName" name="middleName" value="${person.middleName}" required><br><br>
    <input type="submit" value="Update">
</form>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script>
    $(document).ready(function() {
        $('#updateForm').submit(function(event) {
            event.preventDefault(); // Prevent the form from submitting

            // Regex pattern for name validation
            var regex = /^[A-Z][a-zA-Z]{2,14}$/;

            // Get form values
            var name = $('#name').val();
            var surname = $('#surname').val();
            var middleName = $('#middleName').val();

            // Validate each field
            if (!regex.test(name)) {
                toastr.error("Invalid Name", "Error", {
                    closeButton: true,
                    timeOut: 5000,
                    extendedTimeOut: 1000,
                    positionClass: "toast-top-center"
                });
                return;
            }

            if (!regex.test(surname)) {
                toastr.error("Invalid Surname", "Error", {
                    closeButton: true,
                    timeOut: 5000,
                    extendedTimeOut: 1000,
                    positionClass: "toast-top-center"
                });
                return;
            }

            if (!regex.test(middleName)) {
                toastr.error("Invalid Middle Name", "Error", {
                    closeButton: true,
                    timeOut: 5000,
                    extendedTimeOut: 1000,
                    positionClass: "toast-top-center"
                });
                return;
            }

            // If all fields are valid, submit the form
            $.ajax({
                type: "POST",
                url: $('#updateForm').attr('action'),
                data: $('#updateForm').serialize(),
                success: function(response) {
                    toastr.success("Person successfully updated", "Success", {
                        closeButton: true,
                        timeOut: 5000,
                        extendedTimeOut: 1000,
                        positionClass: "toast-top-center"
                    });

                    setTimeout(function() {
                        window.location.href = "${pageContext.request.contextPath}/person-info";
                    }, 2000);
                },
                error: function(xhr, status, error) {
                    toastr.error("Error updating person", "Error", {
                        closeButton: true,
                        timeOut: 5000,
                        extendedTimeOut: 1000,
                        positionClass: "toast-top-center"
                    });
                }
            });
        });
    });
</script>
</body>
</html>

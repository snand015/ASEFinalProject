


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Skills Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<h2>Skills List</h2>

<!-- Display Skills -->
<table border="1">
    <tr>
        <th>Skill</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="skill" items="${skills}">
        <tr>
            <td>${skill.skill}</td>
            <td>${skill.description}</td>
            <td>
                <a href="<c:url value='skills/update/${skill.id}'/>">Update</a>
                <a href="<c:url value='skills/delete/${skill.id}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<button type="button" onclick="window.location.href='addskills'">add More Skills</button>

<button type="button" onclick="window.location.href='home'">Back</button>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Skill</title>
</head>
<body>

<h2>Update Skill</h2>
<form:form action="/updateSkill" method="post" modelAttribute="skill">
    <label for="skill">Skill:</label> <form:input type="text" path="skill" value="${skillToUpdate.skill}" readonly="true" />
    <label for="skill">Skill:</label><form:input type="text" path="description" value="${skillToUpdate.description}" required="true" />
      <form:input type="hidden" path="id" value="${skillToUpdate.id}"/>
      <form:button type="submit" value="update skills">Update Skill</form:button>
</form:form>
  <button type="button" onclick="window.location.href='/myprofile'">Back</button>
</body>
</html>

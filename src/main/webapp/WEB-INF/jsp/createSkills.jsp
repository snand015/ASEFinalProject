<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Add skillList</title>
 

</head>
<body>
    <!-- Add skillList Form -->
    <h2>Add skillList</h2>
    <form:form action="/skills/add" method="post" modelAttribute="skill">
        <div id="skillForm">

                <div class="form-group">
                    <label for="skill">Skill:</label>
                    <form:input path="skill" class="form-control" required="true"/>

                    <label for="description">Description:</label>
                    <form:textarea path="description" class="form-control" required="true"/>
                </div>


         
            <form:button type="submit" value="Add skills">Add Skill</form:button>
        </div>
        <button type="button" onclick="window.location.href='/myprofile'">Back</button>
    </form:form>
</body>
</html>

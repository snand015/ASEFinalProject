<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add skillList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<script>
    function validateImageUpload() {
        var input = document.getElementById('images');
        if (input.files.length > 3) {
            alert("You can only upload up to 3 images.");
            return false;
        }
        return true;
    }
</script>
    <!-- Add skillList Form -->
    <h2>Add skillList</h2>
    <form:form action="skills/add" method="post" enctype="multipart/form-data"   modelAttribute="skill" onsubmit="return validateImageUpload()">
        <div id="skillForm">
            <div class="form-group">
                <label for="skill">Skill:</label>
                <form:input type="text" path="skill" class="form-control" required="true"/>

                <label for="description">Description:</label>
                <form:textarea path="description" class="form-control" required="true"></form:textarea>
            </div>
            
            <!-- Input field for uploading multiple images -->
            <div class="form-group">
                <label for="images">Upload Images:</label>
                <input type="file" name="images" multiple="true" accept="image/*" class="form-control-file"/>
            </div>

            <form:button type="submit" class="btn btn-primary">Add Skill</form:button>
        </div>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='myprofile'">Back</button>
    </form:form>
</body>
</html>

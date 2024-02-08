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
<form:form action="/updateSkill" method="post" modelAttribute="skill" enctype="multipart/form-data" >
    <label for="skill">Skill:</label> <form:input type="text" path="skill" value="${skillToUpdate.skill}" readonly="true" />
    <label for="skill">Skill:</label><form:input type="text" path="description" value="${skillToUpdate.description}" required="true" />
      <form:input type="hidden" path="id" value="${skillToUpdate.id}"/>
      
        <c:forEach var="image" items="${skillToUpdate.imagePaths}">
         <c:if test="${image!=null}">
        <div id="imagefield">
        <img src="${image}" width="100" height="100" />
        <button type="button" onclick="removeImage('${image}')">Remove</button>
        </div>
        </c:if>
    </c:forEach>

    <!-- Input field for adding new images -->
    <input type="file" id="newImages" name="newImages" accept="image/*" multiple />
      
      <form:button type="submit" value="update skills">Update Skill</form:button>
</form:form>
  <button type="button" onclick="window.location.href='/myprofile'">Back</button>
  <script>
    function removeImage(imageUrl) {
        // Remove the image from the UI
        var imageElement = document.getElementById('imagefield');
        imageElement.remove();

        // Send AJAX request to notify server of image deletion
        $.ajax({
            url: '/deleteImage',
            method: 'POST',
            data: { imageUrl: imageUrl },
            success: function(response) {
                // Handle success
                console.log('Image deleted successfully.');
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error('Error deleting image:', error);
            }
        });
    }
    
    function validateImageUpload() {
        var input = document.getElementById('newImages');
        if (input.files.length > 3) {
            alert("You can only upload up to 3 images.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>

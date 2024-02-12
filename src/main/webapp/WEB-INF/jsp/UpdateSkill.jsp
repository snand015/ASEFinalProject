<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Skill</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<h2>Update Skill</h2>

<form:form action="/skillapp/updateSkill" method="post" modelAttribute="skill" enctype="multipart/form-data" >
    <label for="skill">Skill:</label> <form:input type="text" path="skill" value="${skillToUpdate.skill}" readonly="true" />
    <label for="skill">Skill:</label><form:input type="text" path="description" value="${skillToUpdate.description}" required="true" />
      
        <c:forEach var="image" items="${skillToUpdate.imagePaths}">
         <c:if test="${image!=null}">
        <div id="imagefield">

        <img src="/${image}" width="100" height="100" />
        <button type="button" onclick="removeImage('${image}')">Remove</button>
        </div>
        </c:if>
    </c:forEach>
<form:input type="hidden" path="id" value="${skillToUpdate.id}"/>
        <input type="hidden" name="userName" value="${userName}"/>
    <!-- Input field for adding new images -->
    <input type="file" id="newImages" name="newImages" accept="image/*" multiple />
      
      <form:button type="submit" value="update skills">Update Skill</form:button>
</form:form>
  <button type="button" onclick="window.location.href='myprofile'">Back</button>
  <script>
    function removeImage(imageUrl) {
        // Remove the image from the UI
        var imageElement = document.getElementById('imagefield');
        imageElement.remove();

        // Send AJAX request to notify server of image deletion
        $.ajax({
            url: '/skillapp/deleteImage',
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
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

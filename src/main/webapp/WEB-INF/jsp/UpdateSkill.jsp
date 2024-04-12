<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Skill</title>
         <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <img src="/images/icon.jpeg" alt="Icon" class="icon">
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
        <div class="card-local">
          <div class="card-body">
            <div class=" align-items-center">
<h2>Update Skill</h2>

<form:form action="/skillapp/updateSkill" method="post" modelAttribute="skill" enctype="multipart/form-data" >
<div class="form-group">
    <label class="form-label" for="skill">Skill:</label> 
    <form:input class="form-control-local" type="text" path="skill" value="${skillToUpdate.skill}" readonly="true" />
    </div>
    <div class="form-group">
    <label class="form-label" for="skill">Skill:</label>
    <textarea class="textarea" id="description" name="description" cols="50" rows="5">${skillToUpdate.description}</textarea>
    
      </div>
        <c:forEach var="image" items="${skillToUpdate.imagePaths}">
         <c:if test="${image!=null}">
        <div id="imagefield">

        <img src="/${image}" class="img" />
        <button class="btn btn-primary f-s-12 rounded-corner" type="button" onclick="removeImage('${image}')">Remove</button>
        </div>
        </c:if>
    </c:forEach>
<form:input type="hidden" path="id" value="${skillToUpdate.id}"/>
        <input type="hidden" name="userName" value="${userName}"/>
    <!-- Input field for adding new images -->
    <input type="file" id="newImages" name="newImages" accept="image/*" multiple />
      
      <form:button  class="btn btn-primary f-s-12 rounded-corner" type="submit" value="update skills">Update Skill</form:button>
</form:form>
  <button type="button" class="btn btn-primary f-s-12 rounded-corner" onclick="window.location.href='/skillapp/myprofile'">Back</button>
  </div></div></div></div></div></div></div></div>
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

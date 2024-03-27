<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Skills Management</title>
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
<h2>Profile</h2>
<c:if test="${not empty successMessage}">
    <div class="alert alert-success">
${successMessage}</div>
</c:if>
<c:if test="${not empty ErrorMessage}">
    <div class="alert alert-warning">
${ErrorMessage}</div>
</c:if>
  <div class="skill-info">
        <span class="skill-label">First Name:</span>
        <span class="skill-value"> ${profile.firstName}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Last Name:</span>
        <span class="skill-value">${profile.lastName}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Email:</span>
        <span class="skill-value"> ${profile.email}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Address:</span>
        <span class="skill-value"> ${profile.address}</span>
    </div>
   
<button class="btn btn-primary" type="button"onclick="window.location.href='update-user?userName=${profile.userName}'">updateProfile</button>
<button class="btn btn-primary" type="button" onclick="window.location.href='addskills?userName=${profile.userName}'">Add More Skills</button>
<c:if test="${sessionScope.role!=null || sessionScope.role=='admin'}">
<form action="<c:url value='deleteUser?userName=${profile.userName}'/>" method="post" style="display: inline;">
                            <button  class="btn btn-primary" type="submit">Delete User</button>
                        </form>

</c:if>
<!-- Display Skills -->
<h3>Skills:</h3>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">Skill</th>
            <th scope="col">Description</th>
            <th scope="col">Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="skill" items="${profile.skillSet}">
            <tr>
                <td>${skill.skill}</td>
                <td>${skill.description}</td>
                <td>
                <c:if test="${sessionScope.role==null || sessionScope.role!='admin'}">
                    <a href="<c:url value='skills/update/${skill.id}'/>">Update</a>
                    </c:if>
                    <a href="<c:url value='skills/delete/${skill.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<c:if test="${sessionScope.role==null || sessionScope.role!='admin'}">
<button type="button" class="btn btn-primary" onclick="window.location.href='home'">Back</button>
</c:if>
<c:if test="${sessionScope.role == 'admin'}">
    <button type="button"  class="btn btn-primary" onclick="window.location.href='AdminHome'">Back to AdminHome</button>

</c:if>
</div></div></div></div></div></div></div></div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
        <div class="card">
          <div class="card-body">
            <div class=" align-items-center">
<h2>Update User</h2>

<form:form action="update-user" method="post"  modelAttribute="user">
     <div class="form-group">
    <label>First Name:</label>
    <form:input type="text" class="form-control"   path="firstName" value="${user.firstName}" /><br>
    </div>
     <div class="form-group">
    <label>Last Name:</label>
    <form:input type="text" class="form-control" path="lastName" value="${user.lastName}"/><br>
    </div>
     <div class="form-group">
    <label>Email:</label>
    <form:input type="email" class="form-control"  path="email" value="${user.email}"/><br>
    </div>
     <div class="form-group">
    <label>Address:</label>
    <form:input type="text"  class="form-control" path="address" value="${user.address}"/><br>
    </div>
     <div class="form-group">
     <label>password:</label>
    <form:input type="password"  class="form-control" path="password" value="${user.password}"/><br>
    </div>
    <!-- Add hidden field for user ID -->
    <form:input type="hidden"  class="form-control"  path="userName" value="${user.userName}"/>
    
   <c:if test="${sessionScope.role == 'admin'}">
     <div class="form-group">
        <label for="role">Make user Admin</label>
          <form:checkbox path="role"  class="form-check-label" id="rolecheck" value="admin"/>
      </div>
</c:if> 

<div class="row">
<form:button type="submit"  class="btn btn-primary">Update</form:button>

<c:if test="${sessionScope.role==null || sessionScope.role!='admin'}">
<button type="button" class="btn btn-primary" onclick="window.location.href='myprofile'">Back</button>
</c:if>
<c:if test="${sessionScope.role == 'admin'}">
    <button type="button"  class="btn btn-primary" onclick="window.location.href='UserDetails?userName=${user.userName}'">Back</button>

</c:if>
</div>
</form:form>
</div></div></div></div></div></div></div>
</body>
</html>

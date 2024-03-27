<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
 <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <img src="/images/icon.jpeg" alt="Icon" class="icon">
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
       <!--  <div class="card">
          <div class="card-body"> -->
            <div class=" align-items-center">
    <h1>User List</h1>
    <ul  >
     <c:forEach var="user" items="${userList}">
      <li>  <div class="tile" onclick="window.location.href='/skillapp/admin/UserDetails?userName=${user.userName}'">
      
      <div class="form-group">
        <span class="skill-label"> Name: </span>
    <span> ${user.firstName} ${user.lastName}</span>
       </div>
         <div class="form-group">
        <span class="skill-label"> userID: </span>
    <span> ${user.userName}</span>
       </div>
         <div class="form-group">
        <span class="skill-label"> emailID: </span>
    <span> ${user.firstName} ${user.email}</span>
       </div>
        
         
         
            </div>
            </li>
        </c:forEach>
        
    </ul>
   
    <button type="button"  class="btn btn-primary" onclick="window.location.href='AdminHome'">Back</button>


    </div></div></div></div></div></div>
    <!-- </div></div> -->
</body>
</html>

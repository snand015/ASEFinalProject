<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Skill Search</title>
    <!-- Add Bootstrap CSS link here -->
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
        
            <div class=" align-items-center">
 
        <span class="skill-label">Welcome ${userName}</span>
    

    <!-- Navigation Tabs -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="home">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="myprofile">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="inbox"> Inbox</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="MyRequests">MyRequests</a>
        </li>
         <li class="nav-item">
            <a class="nav-link" href="logout">logout</a>
        </li>
        <!-- Add more tabs as needed -->
    </ul>
<div id="confirmationblock">
<c:if test="${not empty complaint}">
    <div class="alert alert-success" role="alert">
        ${complaint}
    </div>
</c:if>
<c:if test="${not empty requestSuccess}">
    <div class="alert alert-success" role="alert">
        ${requestSuccess}
    </div>
</c:if>
<c:if test="${not empty searchfailed}">
    <div class="alert alert-warning" role="alert">
        ${searchfailed}
    </div>
</c:if>
</div>
    <!-- Skill Search Form -->
    <div class="mt-3">
        <h3>Skill Search</h3>
        <form action="search" method="get" class="form-inline">
            <div class="form-group">
                <label for="skillInput" class="mr-2">Enter Skill:</label>
                <form:input type="text" class="form-control-local"  name="skill" path="skill" />
            </div>
             <input name="userName"  type="hidden"  value="${userName}"/>
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form >
           </div>

         <ul style= "list-style:none" >
         <c:if test="${not empty resultSkills }">
        <c:forEach var="skill" items="${resultSkills}">
              <div class="tile" onclick="window.location.href='/skillapp/skillDetails?skill=${skill.skill}&user=${skill.userName}'">
             <div class="skill-content col-md-20 col-lg-20 col-xl-20 ">
            <li>${skill.skill}</li>
             <li>${skill.description}</li>
             </div>
             </div>
        </c:forEach>
        </c:if>
    </ul>
  
 </div>
</div></div></div></div></div>
</body>
</html>
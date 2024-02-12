<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Skill Search</title>
    <!-- Add Bootstrap CSS link here -->
     <link rel="stylesheet" href="css/style.css" type="text/css"/>
</head>
<body>

<div class="container mt-5">
  <div class="form-group">
        <span class="form-control">Welcome ${userName}</span>
    
      </div>
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
<span class="form-control"> ${complaint} </span>
<span class="form-control"> ${requestSuccess} </span>

</div>
    <!-- Skill Search Form -->
    <div class="mt-3">
        <h3>Skill Search</h3>
        <form action="search" method="get" class="form-inline">
            <div class="form-group">
                <label for="skillInput" class="mr-2">Enter Skill:</label>
                <input type="text" class="form-control" name="skill" path="skill" />
            </div>
             <input name="userName"  type="hidden"  value="${userName}"/>
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form >
           </div>
</div>
         <ul>
        <c:forEach var="skill" items="${resultSkills}">
              <div class="tile" onclick="window.location.href='/skillapp/skillDetails?skill=${skill.skill}&user=${skill.userName}'">
            <li>${skill.skill}</li>
             <li>${skill.description}</li>
             </div>
        </c:forEach>
    </ul>
   
 



</body>
</html>
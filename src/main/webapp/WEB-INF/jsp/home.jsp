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
    <!-- Navigation Tabs -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="/home">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/skill_profile">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/inbox">Messages</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/MyRequests">Messages</a>
        </li>
         <li class="nav-item">
            <a class="nav-link" href="/logout">logout</a>
        </li>
        <!-- Add more tabs as needed -->
    </ul>

    <!-- Skill Search Form -->
    <div class="mt-3">
        <h3>Skill Search</h3>
        <form action="/search" method="get" class="form-inline">
            <div class="form-group">
                <label for="skillInput" class="mr-2">Enter Skill:</label>
                <input type="text" class="form-control" name="skill" path="skill" />
            </div>
           
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form >
           </div>
</div>
         <ul>
        <c:forEach var="skill" items="${resultSkills}">
              <div class="tile" onclick="window.location.href='/skillDetails?skill=${skill.skill}&user=${skill.userName}'">
            <li>${skill.skill}</li>
             <li>${skill.description}</li>
             </div>
        </c:forEach>
    </ul>
   
 



</body>
</html>
<!DOCTYPE html>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
   
    
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Admin Home</h2>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
         <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#search"> search Skill</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#addUser">Add User</a>
            </li>
           
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#findUser">UserDetails</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#fetch-flagged" onclick="fetchFlaggedPosts()">Fetch Flagged Posts</a>
            </li>
              <li class="nav-item">
                <a class="nav-link"  href="logout" >logout</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div  class="tab-content mt-3">
         <span > ${dataSaved} </span>
         <span > ${errorMessage} </span>
         <div id="search" class="container tab-pane active">
                <h3>Search posts</h3>
                <!-- search posts form -->
                 <form action="search" method="get" class="form-inline">
            <div class="form-group">
                <label for="skillInput" class="mr-2">Enter Skill:</label>
                <input type="text" class="form-control" name="skill" path="skill" />
            </div>
             <input name="userName"  type="hidden"  value="${userName}"/>
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form >
          <c:if test="${not empty resultSkills }">
        <c:forEach var="skill" items="${resultSkills}">
              <div class="tile" onclick="window.location.href='/skillapp/skillDetails?skill=${skill.skill}&user=${skill.userName}'">
            <li>${skill.skill}</li>
             <li>${skill.description}</li>
             </div>
        </c:forEach>
        </c:if>
            </div>

            <div id="addUser" class="container tab-pane fade">
            <span > ${errorMessage} </span>
            <form:form action="addUser" method="post" modelAttribute="user">
      <div class="form-group">
        <label for="firstName">First Name *</label>
        <form:input type="text" class="form-control" path="firstName" placeholder="Enter first name" /><form:errors path="firstName" cssClass="error"/><br/>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <form:input type="text" class="form-control" path="lastName" placeholder="Enter last name"/><form:errors path="lastName" cssClass="error"/><br/>
      </div>
      <div class="form-group">
        <label for="email">Email address *</label>
        <form:input type="email" class="form-control" path="email" placeholder="Enter email" />
        <form:errors path="email" cssClass="error"/></br>
      </div>
            <div class="form-group">
        <label for="firstName">userId *</label>
      <form:input path="userName" class="form-control" type="text" /><form:errors path="userName" cssClass="error"/><br/>
      </div>
       <div class="form-group">
        <label for="firstName">password *</label>
      <form:input path="password" type="password" class="form-control" /><form:errors path="password" cssClass="error"/><br/>
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <form:textarea class="form-control" path="address" rows="3" placeholder="Enter address"></form:textarea>
      </div>
      <div class="form-group">
        <label for="role">Make user Admin</label>
          <form:checkbox path="role"  id="rolecheck" value="admin"/>
      </div>
    
      <form:button type="submit" class="btn btn-primary">addUser</form:button>
    </form:form>
            </div>
            <div id="findUser" class="container tab-pane fade">
                <h3>find  User</h3>
                <form:form action="getUser" method="get" modelAttribute="user">
           <div class="form-group">
            <label for="user Name">user Name</label>  
              <form:input type="text" class="form-control" path="userName" placeHolder="enter user Name"/>
              </div>
              <div class="form-group">
               <label for="firstName"> first Name</label>  
                <form:input type="text"  class="form-control" path="firstName"/>
                </div>
                <div class="form-group">
                 <label for="lastName">last Name</label>  
                <form:input type="text" class="form-control" path="lastName"/>
                </div>
                <div class="form-group">
                 <label for="email">emailId</label>  
                <form:input type="text" class="form-control" path="email"/>
                </div>
                 <form:button type="submit" class="btn btn-primary">find User</form:button>
                </form:form>
                
            </div>
            
            <div id="fetch-flagged" class="container tab-pane fade">
               <h3> Loading Flagged posts </h3>
               
            </div>
        </div>
    </div>
    
<script>
    $(document).ready(function() {
        // Get the tab from the query parameter
        var urlParams = new URLSearchParams(window.location.search);
        var tab = urlParams.get('tab');
        if (tab) {
            // Show the tab specified in the query parameter
            $('.nav-link[href="#' + tab + '"]').tab('show');
        if(tab=='fetch-flagged'){
        	fetchFlaggedPosts();
        }
       
        }
    });
        function fetchFlaggedPosts() {
          
            $.ajax({
                url: '/skillapp/admin/fetch-flagged', // Use the correct relative URL
                method: 'GET',
                success: function(response) {
                    // Handle success
                    console.log('Loaded posts successfully.');
                    $('#fetch-flagged').html(response);
                },
                error: function(xhr, status, error) {
                    // Handle error
                    console.error('Error loading posts:', error);
                }
            });
        }
      
      

</script>

    <!-- Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

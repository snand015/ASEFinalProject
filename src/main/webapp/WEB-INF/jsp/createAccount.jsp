<%--  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css" type="text/css"/>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>

    <h2>User Registration</h2>
    <form:form action="addUser" method="post" modelAttribute="user">

      

        <br/><br/>
<label>Username</label> <form:input path="userName" type="text" /><form:errors path="userName" cssClass="error"/><br/>
    			<label>Password</label> <form:input path="password" type="password" /><form:errors path="password" cssClass="error"/><br/>
    			<label>First Name</label> <form:input path="firstName" type="text" /><form:errors path="firstName" cssClass="error"/><br/>
    			<label>Last Name</label> <form:input path="lastName" type="text" /><form:errors path="lastName" cssClass="error"/><br/>
       

        <label for="email">Email:</label>
        <form:input  path="email" type="email" /><from:errors path="email" cssClass="error"/></br>

        <br/><br/>

        <br/><br/>

       <label for="address">Address</label>
       <form:textarea path="address" rows="4" cols="50"/>

            <label for="housePictures">House Pictures:</label>
           <form:input path="images" type="file" multiple="true"/>
        </div>

        <br/><br/>

        <input type="submit" value="addUser">
</form:form>


</body>
</html>
 --%>
 <!DOCTYPE html>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bootstrap Form</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-4">
    <h2>Registration Form</h2>
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
      <form:button type="submit" class="btn btn-primary">addUser</form:button>
    </form:form>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
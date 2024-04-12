


<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <!-- Bootstrap CSS -->
       <link rel="stylesheet" href="/css/style.css" type="text/css"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 
</head>
<body>

 <div class="container">
  <img src="/images/icon.jpeg" alt="Icon" class="icon">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card-local login-container">
        <span class=" "> ${errorMessage} </span>
          <div class="card-header">
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#normal-login">Normal Login</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#admin-login">Admin Login</a>
              </li>
            </ul>
          </div>
          <div class="card-body">
            <div class="tab-content">
              <div id="normal-login" class="tab-pane fade show active">
               <span class="success">${dataSaved}</span>
          <span class=""> ${passwordSuccess} </span>
            <form:form action="skillapp/login" method="post" modelAttribute="login">
              <div class="form-group">
                <label for="email">User Name</label>
                <form:input type="text" class="form-control"  path="userName" placeholder="Enter email"/>
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <form:input type="password" class="form-control"  path="password" placeholder="Password"/>
              </div>
              <form:button type="submit" class="btn btn-primary btn-block">Login</form:button>
            </form:form>
            <div class="nav-link">
        <a href="user/forgot-password">Forgot Password?</a> |
        <a href="user/register">New User? Register</a>
                
              </div>
              </div>
              <div id="admin-login" class="tab-pane fade">
                <form:form action="skillapp/admin/login" method="post" modelAttribute="login">
                  <div class="form-group">
                <label for="email">User Name</label>
                <form:input type="text" class="form-control"  path="userName" placeholder="Enter email"/>
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <form:input type="password" class="form-control"  path="password" placeholder="Password"/>
              </div>
              <input type="hidden" name="role" value="admin">
              <form:button type="submit" class="btn btn-primary btn-block">Login</form:button>
                </form:form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


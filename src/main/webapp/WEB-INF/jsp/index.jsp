<%-- 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
</head>
<body>
    
    <div class=signup>
    <span class="success">${dataSaved}</span>
    <form:form action="login" method="post" modelAttribute="login">
        <label for="username">Username:</label>
        <form:input type="text" id="username" path="userName" /><br/><br/>

        <label for="password">Password:</label>
        <form:input type="password" id="password" path="password" /><br/><br/>

        <form:button type="submit">Login</form:button><br/><br/>
    </form:form>

    <div class="nav-link">
        <a href="/forgot-password">Forgot Password?</a> |
        <a href="/register">New User? Register</a>
    </div>
    </div>
</body>
</html> --%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .login-container {
      margin-top: 100px;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card login-container">
          <div class="card-header">
            <h3 class="text-center">Login</h3>
          </div>
          <div class="card-body">
          <span class="success">${dataSaved}</span>
            <form:form action="login" method="post" modelAttribute="login">
              <div class="form-group">
                <label for="email">User Name</label>
                <form:input type="text" class="form-control" path="userName" placeholder="Enter email"/>
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <form:input type="password" class="form-control" path="password" placeholder="Password"/>
              </div>
              <form:button type="submit" class="btn btn-primary btn-block">Login</form:button>
            </form:form>
            <div class="nav-link">
        <a href="/forgot-password">Forgot Password?</a> |
        <a href="/register">New User? Register</a>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <img src="/src/resources/static/images/icon.jpeg" alt="Icon" class="icon">
 <div class="card-body">

<form:form action="validate-otp-and-reset" method="post" modelAttribute="pass">
        <input type="hidden" name="userName" value="${userName}"/>
       
       <div class="from-group">
        <label for="otp">Enter OTP:</label>
        <form:input type="text" id="otp" path="verificationCode"  calss="form-control-local" required="true" />
</div>
<div class="from-group">
        <label for="newPassword">New Password:</label>
        <form:input type="password" id="newPassword" path="password" required="true" />
</div>
<div class="from-group">
        <label for="confirmPassword">Confirm Password:</label>
        <form:input type="password" id="confirmPassword" path="confirmPassword" calss="form-control-local" required="true" />
</div>
        <form:button type="submit" class="btn btn-primary">Reset Password</form:button>
    
    </form:form>
        </div>
    <div>
        <a href="/skillapp" class="btn btn-primary">Back to Login</a>
    </div>
    </div>
</body>
</html>
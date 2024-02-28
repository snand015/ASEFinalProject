<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
</head>
<body>
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
        <div class="card">
          <div class="card-body">
            <div class=" align-items-center">
    <h2>Forgot Password</h2>
    <div id="confirmationblock">
<span class="form-control"> ${failure} </span>


</div>
    <form:form action="send-otp-and-reset" method="post">
          <span class="failure">${failure}</span>
        <label for="email">Enter UserID:</label>
        <input type="text" id="email" name="userName" required />

        <button type="submit">Send OTP</button>
    </form:form>

    <div>
        <a href="/skillapp" class="btn btn-primary" >Back to Login</a>
    </div>
    </div></div></div></div></div></div></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
</head>
<body>
    <h2>Forgot Password</h2>
    <form:form action="/send-otp-and-reset" method="post">
          <span class="failure">${failure}</span>
        <label for="email">Enter UserID:</label>
        <input type="text" id="email" name="userName" required />

        <button type="submit">Send OTP</button>
    </form:form>

    <div>
        <a href="/login">Back to Login</a>
    </div>
</body>
</html>
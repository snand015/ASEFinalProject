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
        <label for="email">Registered Email:</label>
        <form:input type="email" id="email" name="email" required/>

        <form:button type="submit">Send OTP</form:button>
    </form:form>

    <div>
        <a href="/login">Back to Login</a>
    </div>
</body>
</html>
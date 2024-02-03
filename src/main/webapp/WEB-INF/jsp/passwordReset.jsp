<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="/validate-otp-and-reset" method="post">
        <from:input type="hidden" name="email" value="${param.email}"/>
        <label for="otp">Enter OTP:</label>
        <from:input type="text" id="otp" path="otp" required/>

        <label for="newPassword">New Password:</label>
        <from:input type="password" id="newPassword" path="newPassword" required/>

        <label for="confirmPassword">Confirm Password:</label>
        <from:input type="password" id="confirmPassword" path="confirmPassword" required/>

        <from:button type="submit">Reset Password</from:button>
    </form:form>
    <div>
        <a href="/login">Back to Login</a>
    </div>
</body>
</html>
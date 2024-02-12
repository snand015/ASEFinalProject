<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="card-body">

<form:form action="validate-otp-and-reset" method="post" modelAttribute="pass">
        <input type="hidden" name="userName" value="${userName}"/>
        <label for="otp">Enter OTP:</label>
        <form:input type="text" id="otp" path="verificationCode" required="true" />

        <label for="newPassword">New Password:</label>
        <form:input type="password" id="newPassword" path="password" required="true" />

        <label for="confirmPassword">Confirm Password:</label>
        <form:input type="password" id="confirmPassword" path="confirmPassword" required="true" />

        <form:button type="submit">Reset Password</form:button>
    
    </form:form>
        </div>
    <div>
        <a href="/skillapp">Back to Login</a>
    </div>
</body>
</html>
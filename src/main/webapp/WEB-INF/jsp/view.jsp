<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Message Response</title>
</head>
<body>

<c:if test="${not empty talentRequest}">
    <h2>talentRequest Content:</h2>
    <p>${talentRequest.content}</p>

    <h2>Replied Skill:</h2>
    <p>${talentRequest.skillName}</p>
    
 <h2>From:</h2>
    <p>${talentRequest.receiverUserName}</p>
    <!-- Add additional information as needed -->

    <!-- Example: Displaying reply form -->
    <form:form action="${talentRequest.id}/respond" method="post" modelAttribute="response">
      
       
        <label for="response">Your Reply:</label>
        <form:textarea path="content" id="response" rows="10" cols="50"></form:textarea>
         <label for="status">Update Status of request:</label>
        <form:radiobutton  path="status" value="ACK" /> Accept
       <form:radiobutton  path="status" value="REJ"/> Reject
        <form:button type="submit">Send Reply</form:button>
    </form:form>
</c:if>
<button type="button" onclick="window.location.href='/skillapp/home'">gotoHome</button>
</body>
</html>

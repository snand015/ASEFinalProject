<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
    <h2>Skill Details</h2>
    <p>Skill ID: ${profile.skillSet.skill}</p>
    <p>Skill Description: ${profile.skillSet.description}</p>
    <p>Provider: ${profile.firstName} ${profile.lastName}</p>
    <p>Address of Provider: ${profile.address}</p>
    <c:forEach var="image" items="${profile.skillSet.imagePaths}">
        <c:if test="${image != null}">
            <img src="${image}" width="100" height="100" />
        </c:if>
    </c:forEach>
</div>

<form:form action="/skillapp/sendRequest" method="post" modelAttribute="message">
    <label for="content">Reply/Feedback:</label>
    <form:textarea type="text" path="content" cols="50" rows="5" />
    <form:input type="hidden" path="receiverUserName" value="${profile.userName}" />
    <form:input type="hidden" path="skillName" value="${profile.skillSet.skill}" />
    <form:button id="actionButton2" type="submit">Send Request</form:button>
</form:form>

   
    
    <form:form action="/skillapp/logcomplaint" method="post" modelAttribute="skill">
     <label for="irrelevant">Flag This Skill as Inappropriate:</label>
    <form:checkbox path="irrelaventFlg"  id="irrelevantCheckbox"/>
    <form:input type="hidden" path="userName" value="${profile.userName}"/>
     <form:input type="hidden" path="skill" value="${profile.skillSet.skill}"/>
     <form:input type="hidden" path="id" value="${profile.skillSet.id}"/>
  
 <div id="feedbackSection" style="display: none;">
        <!-- Textarea for feedback -->
        <label for="feedback">Feedback:</label>
        <form:textarea id="feedbackTextarea" path="complaint" cols="50" rows="5" /><br/><form:errors path="complaint" cssClass="error"/><br/>
        
        <!-- Button for filing complaint -->
        <form:button type="submit" id="actionButton2">File Complaint</form:button>
    </div>
    
</form:form>

<button type="button" onclick="window.location.href='/skillapp/home'">Back</button>
<script>
    document.getElementById("irrelevantCheckbox").addEventListener("change", function() {
        var feedbackSection = document.getElementById("feedbackSection");
        if (this.checked) {
            feedbackSection.style.display = "block";
        } else {
            feedbackSection.style.display = "none";
        }
    });

    
   
</script>
</body>
</html>
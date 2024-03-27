<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  <img src="/images/icon.jpeg" alt="Icon" class="icon">
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
        <div class="card-local">
          <div class="card-body">
            <div class=" align-items-center">
    <h2>Skill Details</h2>
     <div class="skill-info">
        <span class="skill-label">Skill ID:</span>
        <span class="skill-value">${profile.skillSet[0].skill}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Skill Description:</span>
        <span class="skill-value">${profile.skillSet[0].description}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Provider:</span>
        <span class="skill-value">${profile.firstName} ${profile.lastName}</span>
    </div>
      <div class="skill-info">
        <span class="skill-label">Address of Provider:</span>
        <span class="skill-value">${profile.address}</span>
    </div>

   <div>
    <c:forEach var="image" items="${profile.skillSet[0].imagePaths}">
        <c:if test="${image != null}">
            <img src="/${image}" class="img" />
        </c:if>
    </c:forEach>
    </div>
</div>


<c:if test="${sessionScope.role==null || sessionScope.role!='admin'}">
<form:form action="/skillapp/sendRequest" method="post" modelAttribute="message" class="mt-3">
    <div class="form-outline col-md-12 col-lg-10 col-xl-8">
                <label class="form-label" for="content">Reply/Feedback:</label>
        <form:textarea class="form-control textarea" id="content" path="content" rows="5"></form:textarea>

    </div>
    <input type="hidden" class="form-control" id="receiverUserName" name="receiverUserName" value="${profile.userName}" />
    <input type="hidden" class="form-control" id="skillName" name="skillName" value="${profile.skillSet[0].skill}" />
    <button id="actionButton2" type="submit" class="btn btn-primary">Send Message</button>
</form:form>
</c:if>
<c:if test="${sessionScope.role == 'admin'}">

    <form:form action="/skillapp/admin/removePost" method="post" modelAttribute="message">
    <div class="form-outline col-md-12 col-lg-10 col-xl-8">
    <label for="content">Reply/Feedback:</label>
    <form:textarea type="text" class="form-control textarea" path="content" cols="50" rows="5" />
    </div>
    <form:input type="hidden" class="form-control" path="receiverUserName" value="${profile.userName}" />
    <form:input type="hidden" class="form-control" path="skillName" value="${profile.skillSet[0].skill}" />
         <form:input type="hidden" path="skillId" value="${profile.skillSet[0].id}"/>

    <form:button id="actionButton2"  class="btn btn-primary" type="submit">remove Post</form:button>
</form:form>
<button type="button" onclick="window.location.href='/skillapp/admin/AdminHome'">Back</button>
</c:if>
  
   <c:if test="${sessionScope.role==null || sessionScope.role!='admin'}"> 
    <form:form action="/skillapp/logcomplaint" method="post" modelAttribute="skill">
    <div class="form-group">
     <label for="irrelevant">Flag This Skill as Inappropriate:</label>
    <form:checkbox path="irrelaventFlg" class="form-check-label" id="irrelevantCheckbox"/>
    </div>
    <form:input type="hidden" class="form-control" path="userName" value="${profile.userName}"/>
     <form:input type="hidden" class="form-control" path="Skill" value="${profile.skillSet[0].skill}"/>
     <form:input type="hidden" class="form-control" path="Id" value="${profile.skillSet[0].id}"/>
  
 <div id="feedbackSection" style="display: none;">
    <div class="form-outline col-md-12 col-lg-10 col-xl-8">
        <!-- Textarea for feedback -->
        <label class="form-label" for="feedback">Feedback:</label>
        <form:textarea id="feedbackTextarea" class="form-control textarea" path="complaint" cols="50" rows="5" /><br/><form:errors path="complaint" cssClass="error"/><br/>
        </div>
        <!-- Button for filing complaint -->
        <form:button type="submit" id="actionButton2"  class="btn btn-primary" >File Complaint</form:button>
    </div>
    
</form:form>

<button type="button"  class="btn btn-primary"  onclick="window.location.href='/skillapp/home'">Back</button>
</c:if>
</div>
</div></div></div></div></div></div>
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
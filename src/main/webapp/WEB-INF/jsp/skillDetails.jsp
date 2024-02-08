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
    <p>Skill Description:${profile.skillSet.description}</p>
    <p>Provider: ${profile.firstName}</p>
    <p>Provider: ${profile.lastName}</p>
    <p>address of Provider:${profile.address}</p>
 <c:forEach var="image" items="${profile.skillSet.imagePaths}">
 <c:if test="${image!=null}">
        <img src="${image}" width="100" height="100" />
      </c:if>
    </c:forEach>
 
    <!-- Add more details as needed -->
</div>
<form:form action="/sendRequest" method="post"  modelAttribute="message">
<form:input type="text" path="content"/>
    <form:input type="hidden" path="receiverUserName" value="${profile.userName}"/>
     <form:input type="hidden" path="skillName" value="${profile.skillSet.skill}"/>
   
 
       

    <!-- Add any additional fields for the request -->

<form:button type="submit">Send Request</form:button>
  
</form:form>
  <button type="button" onclick="window.location.href='/home'">Back</button>
</body>
</html>
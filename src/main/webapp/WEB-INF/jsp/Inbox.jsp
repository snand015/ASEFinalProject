<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inbox</title>
    <!-- Add any necessary CSS or script imports here -->
      <link rel="stylesheet" href="/css/style.css" type="text/css"/>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
        <div class="card">
          <div class="card-body">
            <div class=" align-items-center">
<h2>Inbox</h2>

<c:if test="${not empty talentRequests}">
    <div class="inbox">
   

               
       
        <ul class="messages">
            <c:forEach var="talentRequest" items="${talentRequests}">
               
                                   <li class="message_item">
                  <div class="header"> 
                  <span class="from" >from: ${talentRequest.senderUserName}</span>
                  </div>
                  <div class="title">
                 sub: ${talentRequest.skillName}
                  </div>
                  <div class="description">
                  
                    ${talentRequest.content}
                    </div>
                     <div class="actions">
                        <a href="<c:url value='talent-requests/${talentRequest.id}'/>">View</a>
                        <form action="<c:url value='talent-requests/${talentRequest.id}/delete'/>" method="post" style="display: inline;">
                            <button type="submit">Delete</button>
                        </form>
                    </div>
              </li>
               
            </c:forEach>
             </ul>
     
    </div>
</c:if>

<c:if test="${empty talentRequests}">
    <p>No talent requests in the inbox.</p>
</c:if>
<button type="button" class="btn btn-primary" onclick="window.location.href='home'">Back</button>
</div></div></div></div></div></div></div>
</body>
</html>

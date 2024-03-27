<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Message Response</title>
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
<c:if test="${not empty talentRequest}">
 <div class="timeline-body">
                              <div class="timeline-header">
                                 <span class="userimage"><img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt=""></span>
                                 <span class="username"><a href="javascript:;">${talentRequest.receiverUserName }</a> <small></small></span>
                                 <span class="pull-right text-muted"> Talent:${talentRequest.skillName}</span>
                              </div>
                              <div class="timeline-content">
                                 <p>
                                   ${talentRequest.content}.
                                 </p>
                              </div>
                             
    
    <!-- Example: Displaying reply form -->
    <form:form action="${talentRequest.id}/respond" method="post" modelAttribute="response">
       <div class="timeline-footer">
        <form:radiobutton  path="status" value="ACK" /> Accept
       <form:radiobutton  path="status" value="REJ"/> Reject</div>
         <div class="timeline-comment-box">
                                 <div class="user"><img src="https://bootdey.com/img/Content/avatar/avatar3.png">
                                 </div>
                                 <div class="input">
                                    
                                       <div class="input-group">
                                          <form:textarea path="content" id="response" rows="10" cols="50" class="form-control textarea" placeholder="Write a Reply..."></form:textarea>

                                         
                                        
                                       </div>
                                         <form:button type="submit"  class="btn btn-primary f-s-12 rounded-corner" >Send Reply</form:button>
                                   
                                 </div>
                              </div>
                           
        
        
        
       
     
    </form:form>
     </div>
</c:if>
<button type="button" class="btn btn-primary f-s-12 rounded-corner" onclick="window.location.href='/skillapp/home'">gotoHome</button>
</div></div></div></div></div></div></div></div>
<script>
    function validateForm() {
        var status = document.querySelector('input[name="status"]:checked');
        if (!status) {
            alert("Please select a status (Accept or Reject).");
            return;
        }
        document.getElementById("responseForm").submit();
    }
</script>
</body>
</html>

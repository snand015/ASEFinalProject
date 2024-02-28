<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Flagged Posts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
     <link rel="stylesheet" href="/css/style.css" type="text/css"/>
</head>
<body>
   <div class="container">
        
       
      
            <c:forEach var="skill" items="${flaggedSkills}">
                <div class="col-sm-10">
                    <div class="media">
                     <div class="media-body">
                    <div class="tile" onclick="window.location.href='/skillapp/admin/viewPost?skill=${skill.id}'">
                       <div class="media-heading"> ${skill.userName} </div>
                        <span>${skill.skill}</span>
                        <p><strong>Flag Reason:</strong> ${skill.complaint}</p>
                        
                        </div>
                    </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    
</body>
</html>

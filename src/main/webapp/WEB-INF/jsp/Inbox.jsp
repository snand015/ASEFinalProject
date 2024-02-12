<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inbox</title>
    <!-- Add any necessary CSS or script imports here -->
</head>
<body>

<h2>Inbox</h2>

<c:if test="${not empty talentRequests}">
    <table border="1">
        <thead>
            <tr>

                <th>Requester</th>
                <th>Message</th>
                <th>Requested For</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="talentRequest" items="${talentRequests}">
                <tr>
                    <td>${talentRequest.senderUserName}</td>
                     <c:set var="shortDesc" value="${fn:substring(talentRequest.content, 0, 10)}" />
                    <td>${shortDesc}</td>
                    <td>${talentRequest.skillName}</td>
                    <td>
                        <a href="<c:url value='talent-requests/${talentRequest.id}'/>">View</a>
                        <form action="<c:url value='talent-requests/${talentRequest.id}/delete'/>" method="post" style="display: inline;">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty talentRequests}">
    <p>No talent requests in the inbox.</p>
</c:if>
<button type="button" onclick="window.location.href='home'">Back</button>
</body>
</html>

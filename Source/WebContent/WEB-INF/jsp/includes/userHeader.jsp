<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="userMetaData">
	<div class="userMetaDataWrapper"></div>
	
	<c:catch var="exception">
		<img src="${Images.get(0).getContent()}" />
	</c:catch>
	<c:if test="${ exception != null }">
		"/>
	</c:if>
	
	<div class="username">
		${UserName}
		
		<div class="mainPoints">
			<span class="number" id="<c:choose><c:when test="${UserPoints < 0}">negative</c:when><c:when test="${UserPoints > 0}">positive</c:when><c:otherwise>neutral</c:otherwise></c:choose>">${UserPoints}</span> points <span class="number" id="blue">${UserFollowers} </span> followers
		</div>
		
		<form action="<c:url value='/follow' />" method="POST">
			<input type="hidden" value="${UserId}" name="followee" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="submit" id="followButton">
				<c:choose> 
				  	<c:when test="${isFollowing == true}">
				    	Unfollow
				  	</c:when>
				  	<c:otherwise>
				    	Follow
				  	</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div>
	<div class="metaDataWrapper">
	
		<div class="metaItem" id="imagesButton" style="background-color: #222;">
			<span class="number">${ImageNumber }</span> images
		</div>

		<div class="metaItem" id="commentsButton">
			<span class="number">${CommentNumber }</span> comments
		</div>
	</div>
	
	
</div>
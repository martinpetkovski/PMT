<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>${UserName}'s profile</title>
	<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/favicon.ico">
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/user.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/script/user.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->


	<div class="mainContent">
		<jsp:include page="includes/userHeader.jsp" />

		<div class="mainContentWrapper" id="user">
			<div class="images">
				<div class="contentTitle">
					Showing submitted images
				</div>
				<c:forEach var="image" items="${Images}" varStatus="loop">
	
					<div class="image">
						<a href="${pageContext.request.contextPath}/image/${image.getAddress()}/${loop.index}">
						<div class="content"><img src="${image.getContent()}">
						</div></a>
		
						<div class="title">
							<div class="points">
								<span class="numberOfPoints" id="<c:choose><c:when test="${image.getPoints() < 0}">negative</c:when><c:when test="${image.getPoints() > 0}">positive</c:when><c:otherwise>neutral</c:otherwise></c:choose>">${image.getPoints()}</span> points 
							</div>
		
		
							<div class="titleMeta">
								<form action="<c:url value='/image_vote' />" method="POST">
									<input type="hidden" name="voteType" value="true">
									<input type="hidden" name="imageId" value="${image.getIdimage() }">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<span class="submit" id="upvote">upvote</span>
								</form>
								<form action="<c:url value='/image_vote' />" method="POST">
									<input type="hidden" name="voteType" value="false">
									<input type="hidden" name="imageId" value="${image.getIdimage() }">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<span class="submit" id="downvote">downvote</span>
								</form>
								 - ${image.getCreateTimeAsString()}
							</div>
							${image.getTitle()}
						</div>
					</div>
					
				</c:forEach>
			</div>
			
			<div class="comments">
				<div class="contentTitle">
					Showing submitted comments
				</div>
	
				<c:forEach var="comment" items="${Comments}">
	
					<div class="image">
						<a href="${pageContext.request.contextPath}/image/${comment.getImg().getAddress()}">
						<div class="content"><img src="${comment.getImg().getContent()}">
						</div></a>
		
						<div class="title">
							<div class="points">
								<span class="numberOfPoints" id="<c:choose><c:when test="${comment.getPoints() < 0}">negative</c:when><c:when test="${comment.getPoints() > 0}">positive</c:when><c:otherwise>neutral</c:otherwise></c:choose>">${comment.getPoints() }</span> points
							</div>
		
		
							<div class="titleMeta">
								<form action="<c:url value='/comment_vote' />" method="POST">
									<input type="hidden" name="voteType" value="true">
									<input type="hidden" name="commentId" value="${comment.getIdcomment() }">
									<input type="hidden" name="imageId" value="${comment.getImg().getIdimage()}">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<span class="submit" id="upvote">upvote</span>
								</form>
								<form action="<c:url value='/comment_vote' />" method="POST">
									<input type="hidden" name="voteType" value="false">
									<input type="hidden" name="commentId" value="${comment.getIdcomment() }">
									<input type="hidden" name="imageId" value="${comment.getImg().getIdimage()}">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<span class="submit" id="downvote">downvote</span>
								</form>
								 - ${comment.getCreateTimeAsString()}
							</div>
							${comment.getContent()}
						</div>
					</div>
					
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- MAIN CONTENT END -->
	<!-- FOOTER START -->


	<footer>
	</footer>
	<!-- FOOTER END -->
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="activeComments" value="true" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>WTO Front Page</title>
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/user.css" rel="stylesheet">
	<script src=
	"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->


	<div class="mainContent">
		
		<jsp:include page="includes/userHeader.jsp" />

		<div class="mainContentWrapper" id="user">
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
							<span class="numberOfPoints">${comment.getPoints()}</span> points
						</div>
	
	
						<div class="titleMeta">
							<span id="upvote">upvote</span> <span id="downvote">downvote</span> - ${comment.getCreateTimeAsString()}
						</div>
						<span class="titleSiz">&gt;&gt;</span> ${comment.getContent()}
					</div>
				</div>
				
			</c:forEach>
		</div>
	</div>
	<!-- MAIN CONTENT END -->
	<!-- FOOTER START -->


	<footer>
	</footer>
	<!-- FOOTER END -->
</body>
</html>
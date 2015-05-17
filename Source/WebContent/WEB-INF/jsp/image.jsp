<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>${ImageTitle }</title>
	<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/favicon.ico">
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/image.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script>var ctxPath = "${pageContext.request.contextPath}"; var prevImage = "${PrevImage}"; var nextImage = "${NextImage}"; var imageIndex = ${sessionScope.imageIndex};</script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/script/image.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->


	<div class="mainContent">
		


		<div class="image">
			<div class="imageMeta">
				<div class="votes">
					<a href="${pageContext.request.contextPath}/image/${PrevImage}/${sessionScope.imageIndex - 1}"><span id="prev">« previous</span></a> 
					<form action="<c:url value='/image_vote' />" method="POST">
						<input type="hidden" name="voteType" value="true">
						<input type="hidden" name="imageId" value="${ImageId }">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<span class="submit" id="upvote">upvote</span>
					</form>
					<form action="<c:url value='/image_vote' />" method="POST">
						<input type="hidden" name="voteType" value="false">
						<input type="hidden" name="imageId" value="${ImageId }">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<span class="submit" id="downvote">downvote</span>
					</form>
					<a href="${pageContext.request.contextPath}/image/${NextImage}/${sessionScope.imageIndex + 1}"><span id="next">next »</span></a>
				</div>
				
				<div class="user">
					submitted by <a href="${pageContext.request.contextPath}/user/${ImageUser }">${ImageUser }</a>
				</div>
			</div>
			<img src="${ImageLocation }">
		</div>


		<div class="commentArea">
			<div class="commentAreaWrapper">
				<div class="imageTitle">
					<div class="imagePoints">
						${ImagePoints }
					</div>


					<div>
						${ImageTitle }
					</div>
					
					<div class="imageMetadata">
						<div class="tags">
							<c:forEach var="Tag" items="${Tags}">
								<a href="${pageContext.request.contextPath}/search/tag:${Tag.getContent()}">#${Tag.getContent() }</a>
							</c:forEach>
							&nbsp;
						</div>
						<div class="dateCreated">${ImageCreateTime }</div>
					</div>
				</div>


				<div class="comment">
					<div class="body">
						<form action="<c:url value='/comment' />" method="POST">
							<input name="content" placeholder="Write a comment ..." type="text" autocomplete="off">
							<input type="hidden" name="imageId" value="${ImageId}"/>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</div>

					<hr>
				</div>

				<c:forEach var="Comment" items="${Comments}">
					<div class="comment" id="comment${Comment.getIdcomment() }">
						<div class="body">
							${Comment.getContent() }
						</div>
	
	
						<div class="votes">
							<form action="<c:url value='/comment_vote' />" method="POST">
								<input type="hidden" name="voteType" value="true">
								<input type="hidden" name="commentId" value="${Comment.getIdcomment() }">
								<input type="hidden" name="imageId" value="${ImageId}">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<span class="submit" id="upvote">upvote</span>
							</form>
							<form action="<c:url value='/comment_vote' />" method="POST">
								<input type="hidden" name="voteType" value="false">
								<input type="hidden" name="commentId" value="${Comment.getIdcomment() }">
								<input type="hidden" name="imageId" value="${ImageId}">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<span class="submit" id="downvote">downvote</span>
							</form>
						</div>
	
	
						<div class="meta">
							<span id="username"><a href="${pageContext.request.contextPath}/user/${Comment.getUser().getUsername()}">${Comment.getUser().getUsername() }</a></span> <span class="siz">//</span> <span id="date">date submitted: ${Comment.getCreateTimeAsString() }</span> <span class="siz">//</span> <span id="points">${Comment.getPoints() } points</span>
						</div>
	
	
						<div class="voteBar">
							<div class="upvotes" style="width:${Comment.getPoints() / MostPoints * 100}%">
							</div>
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
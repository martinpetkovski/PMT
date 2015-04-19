<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>WTO Front Page</title>
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/image.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script>var ctxPath = "${pageContext.request.contextPath}"; var imageId = parseInt(${ImageId});</script>
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
					<a href="${ImageId - 1}"><span id="prev">« previous</span></a> <a href="#"><span id="upvote">upvote</span></a> <a href="#"><span id="downvote">downvote</span></a> <a href="${ImageId + 1}"><span id="next">next »</span></a>
				</div>
				
				<div class="user">
					submitted by <a href="${pageContext.request.contextPath}/user/${ImageUser }/images">${ImageUser }</a>
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
						<input placeholder="Write a comment" type="text"><button class="button">-></button>
					</div>

					<hr>
				</div>

				<c:forEach var="Comment" items="${Comments}">
					<div class="comment">
						<div class="body">
							${Comment.getContent() }
						</div>
	
	
						<div class="votes">
							<span id="upvote">upvote</span> <span id="downvote">downvote</span>
						</div>
	
	
						<div class="meta">
							<span id="username"><a href="${pageContext.request.contextPath}/user/${Comment.getUser().getUsername()}/comments">${Comment.getUser().getUsername() }</a></span> <span class="siz">//</span> <span id="date">date submitted: ${Comment.getCreateTimeAsString() }</span> <span class="siz">//</span> <span id="points">${Comment.getPoints() } points</span>
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
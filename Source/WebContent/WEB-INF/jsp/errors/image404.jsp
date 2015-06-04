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
		<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="../includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->


	<div class="mainContent">
		


		<div class="image">
			<img src="${pageContext.request.contextPath}/resources/errors/image404.png">
		</div>


		<div class="commentArea">
			<div class="commentAreaWrapper">
				<div class="imageTitle">
					<div>
						Oops! Here is a picture of Area 51!
					</div>
				</div>
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
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>WTO Front Page</title>
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/index.css" rel="stylesheet">
	<script src=
	"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/script/index.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->

	<div class="mainContent">
		<div class="mainContentWrapper">
			<div class="dropMenu" id="register">
				<div class="dropMenuWrapper">
					<form action="<c:url value='/upload' />" method="POST" enctype="multipart/form-data">
						<div class="title">
							Upload image
						</div>
						<input placeholder="file location..." name="file" type="file" /><br/>
						<input placeholder="title" name="title" type="text" /><br/>
						<input placeholder="tags" name="tags" type="text" id="last"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button class="button" id="submit">-></button>
					</form>
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
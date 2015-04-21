<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>WTO Front Page</title>
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/index.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/login.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
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
					<img src="${pageContext.request.contextPath}/resources/style/phineas.png">
					<div class="title">
						Register
					</div>
					<input id="username" placeholder="username" type="text" autocomplete="off"><br/>
					<input placeholder="email" type="email" autocomplete="off"><br/>
					<input placeholder="password" type="password" id="password"><br/>
					<input placeholder="repeat password" type="password" id="last"><button class="button" id="submit">-&gt;</button>
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
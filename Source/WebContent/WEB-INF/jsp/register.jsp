<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>Register</title>
	<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/favicon.ico">
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
					<form action="<c:url value='/register' />" method="post">
						<input name="username" placeholder="username" id="username" type="text" autocomplete="off" maxlength="20"><br/>
						<input name="email" placeholder="email" type="email" autocomplete="off"><br/>
						<input name="password" placeholder="password" type="password" id="password"><br/>
						<input name="password2" placeholder="repeat password" type="password" id="last"><button class="button" id="submit">-&gt;</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
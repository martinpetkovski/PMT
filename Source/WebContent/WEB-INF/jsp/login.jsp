<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
	<meta charset="utf8">

	<title>WTO Front Page</title>
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/index.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/login.css" rel="stylesheet">
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
		<div class="mainContentWrapper">
			<div class="dropMenu" id="login">
				<div class="dropMenuWrapper">
					<div class="title">
						Login
					</div>
					<form name="f" th:action="@{/login}" method="post">
						<input id="username" placeholder="username" name="username" type="text" autocomplete="off"><br>
						<input placeholder="password" name="password" type="password"><button class="button" id="submit">-&gt;</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div th:if="${param.error}" class="alert" id="error">
	                    	Invalid username and password.
		                </div>
		                <div th:if="${param.logout}" class="alert" id="success"> 
		                    You have been logged out.
		                </div>
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
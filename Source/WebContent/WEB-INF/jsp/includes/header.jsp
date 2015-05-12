<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<header>
		<div class="headerContainer">
			<a href="${pageContext.request.contextPath}">
				<div class="logo">
					<div class="wto">
						WTO
					</div>
					<div class="imager">
						imager
					</div>
				</div>
			</a>
			
			<a href="${pageContext.request.contextPath}/image/random">
				<div class="logoButton" id="random">
					<img src="${pageContext.request.contextPath}/resources/style/random.png" />
				</div>
			</a>
			
			<a href="${pageContext.request.contextPath}/help">
				<div class="logoButton" id="help">
					<img src="${pageContext.request.contextPath}/resources/style/help.png" />
				</div>
			</a>
			
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a href="${pageContext.request.contextPath}/upload">
					<div class="logoButton" id="upload">
						<img src="${pageContext.request.contextPath}/resources/style/upload.png" />
					</div>
				</a>
			</c:if>
			
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<a href="${pageContext.request.contextPath}/login">
					<div class="logoButton" id="login">
						<img src="${pageContext.request.contextPath}/resources/style/login.png" />
					</div>
				</a>


				<a href="${pageContext.request.contextPath}/register">
					<div class="logoButton" id="register">
						<img src="${pageContext.request.contextPath}/resources/style/register.png" />
					</div>
				</a>
			</c:if>
			
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a href="${pageContext.request.contextPath}/user/${pageContext.request.userPrincipal.name}">
					<div class="logoButton" id="settings">
							<img src="${pageContext.request.contextPath}/resources/style/settings.png">
					</div>
				</a>
				
				<c:url value="/logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post">
					<div class="logoButton" id="logout">
						<input type="image" src="${pageContext.request.contextPath}/resources/style/logout.png" ></input>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</c:if>

			<div class="buttonArea">
				<div class="search">
				  	<form:form method="GET" action="${pageContext.request.contextPath}/search" >
						<input placeholder="Search..." type="text" name="query" autocomplete="off"/>
					</form:form>
				</div>
				
				<div class="syntax">
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">tag:</span> shows only images of a tag</div>
						<div class="helpDescription">(usage - tag:StarTrek)</div>
					</div>
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">title:</span> search images only by their title</div>
						<div class="helpDescription">(usage - title:Bender Futurama)</div>
					</div>
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">user:</span> displays users page</div>
						<div class="helpDescription">(usage - user:najjak)</div>
					</div>
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">Enter</span></div>
						<div class="helpDescription">(will execute the query)</div>
					</div>
					<div class="syntaxTitle">search syntax</div>
				</div>

				
			</div>
		</div>
	</header>


	
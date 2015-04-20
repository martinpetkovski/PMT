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

				<c:choose>
				  <c:when test="${pageContext.request.userPrincipal.name}">Show something</c:when>
				  <c:otherwise>Show something else</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/login">
					<div class="button" id="login">
						Login
					</div>
				</a>


				<a href="${pageContext.request.contextPath}/register">
					<div class="button" id="register">
						Register
					</div>
				</a>
			</div>
		</div>
	</header>


	
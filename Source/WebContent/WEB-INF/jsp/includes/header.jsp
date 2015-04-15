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
			</div></a>

			<div class="buttonArea">
				<div class="search">
				  	<form:form method="GET" action="${pageContext.request.contextPath}/search">
						<input placeholder="Search..." type="text" name="query"/>
					</form:form>
				</div>


				<div class="button" id="login">
					Login
				</div>


				<div class="button" id="register">
					Register
				</div>
			</div>
		</div>
	</header>


	<div class="dropMenu" id="login">
		<div class="dropMenuWrapper">
			<div class="title">
				Login
			</div>
			<input placeholder="username or email" type="text"><br>
			<input placeholder="password" type="password"><button class=
			"button" id="submit">-&gt;</button>
		</div>
	</div>


	<div class="dropMenu" id="register">
		<div class="dropMenuWrapper">
			<div class="title">
				Register
			</div>
			<input placeholder="username" type="text"><br>
			<input placeholder="email" type="email"><br>
			<input placeholder="password" type="password"><button class=
			"button" id="submit">-&gt;</button>
		</div>
	</div>
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
						<div class="helpDescription">(usage - title:Some Title)</div>
					</div>
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">user:</span> displays users page</div>
						<div class="helpDescription">(usage - user:Username)</div>
					</div>
					<div class="helpItemWrapper">
						<div class="helpItem"><span class="white">F2</span></div>
						<div class="helpDescription">(pressing F2 will focus the search bar)</div>
					</div>
					<div class="syntaxTitle">search syntax</div>
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
			<input id="username" placeholder="username or email" type="text"><br>
			<input placeholder="password" type="password"><button class="button" id="submit">-&gt;</button>
		</div>
	</div>


	<div class="dropMenu" id="register">
		<div class="dropMenuWrapper">
			<div class="title">
				Register
			</div>
			<input id="username" placeholder="username" type="text"><br>
			<input placeholder="email" type="email"><br>
			<input placeholder="password" type="password"><button class=
			"button" id="submit">-&gt;</button>
		</div>
	</div>
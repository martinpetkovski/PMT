<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf8">

	<title>Help</title>
	<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/favicon.ico">
	<link href="${pageContext.request.contextPath}/resources/style/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/index.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/style/help.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/script/main.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/script/index.js" type="text/javascript"></script>
</head>

<body>
	<!-- HEADER START -->
		<jsp:include page="includes/header.jsp" />
	<!-- HEADER END -->
	<!-- MAIN CONTENT START -->


	<div class="mainContent">
		<div class="helpTitle">
			Help
		</div>
		<div class="mainContentWrapper">
			<div class="section">
		
				<div class="sectionTitle">Keyboard shortcuts</div>
				<div class="sectionBody">
					<table>
						<tr><td>KEY</td> <td>FUNCTION</td><td>AVAILABILITY</td></tr> 
						<tr><td>Enter/Return</td> <td>submits form</td><td>sitewide</td></tr> 
						<tr><td>F2</td> <td>focuses the search bar</td><td>sitewide</td></tr>
						<tr><td>Left arrow</td> <td>goes to previous image</td><td>images page</td></tr>
						<tr><td>Right arrow</td> <td>goes to next image</td><td>images page</td></tr>
					</table>
				</div>
				
				<div class="sectionTitle">Icons</div>
				<div class="sectionBody">
					<table>
						<tr><td>ICON</td> <td>FUNCTION</td> <td>AVAILABILITY</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/random.png" /></td> <td>Chooses and shows a random image from all images</td> <td>all users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/help.png" /></td> <td>Redirects to this page</td> <td>all users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/login.png" /></td> <td>Shows the page where the user can log in</td> <td>non-authenticated users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/register.png" /></td> <td>Shows the user registration page</td> <td>non-authenticated users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/upload.png" /></td> <td>Redirects to the image upload page</td>  <td>authenticated users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/settings.png" /></td> <td>Shows the user page</td>  <td>authenticated users</td></tr> 
						<tr><td><img src="${pageContext.request.contextPath}/resources/style/logout.png" /></td> <td>Logs the user out</td>  <td>authenticated users</td></tr> 
					</table>
				</div>
				
				<div class="sectionTitle">Search</div>
				<div class="sectionBody">
					<table>
						<tr><td>PREFIX</td> <td>FUNCTION</td> <td>EXAMPLE USAGE</td></tr> 
						<tr><td></td> <td>Searches images by all criteria</td> <td>Tigers Jaw</td></tr> 
						<tr><td>tag:</td> <td>Searches images only by their tag</td> <td>tag:Star Trek</td></tr> 
						<tr><td>title:</td> <td>Searches images only by their title</td> <td>title:Adventure Time</td></tr> 
						<tr><td>user:</td> <td>Shows user page if user exists</td> <td>user:Martin</td></tr> 
					</table>
				</div>
				
				<div class="sectionTitle">Sorting</div>
				<div class="sectionBody">
					<table>
						<tr><td>CRITERIA</td> <td>FUNCTION</td> <td>AVAILABILITY</td></tr> 
						<tr><td>by highest scoring</td> <td>Sorts the images from highest to lowest scoring</td> <td>index, search, image page(!)</td></tr> 
						<tr><td>by newest first</td> <td>Sorts the images from the newest to oldest by upload time</td> <td>index, search, image page</td></tr> 
						<tr><td>by random</td> <td>Sorts the images by random order</td> <td>index, search, image page(!)</td></tr> 
						<tr><td>default</td> <td>The default sort is by newest first</td> <td>index, search, image page</td></tr>
						<tr><td colspan="3">(!) - there is a bug with some sorting algorithms on the image page that is not yet resolved.</td></tr>
					</table>
				</div>
				
				<div class="sectionTitle">Image uploads</div>
				<div class="sectionBody">
					Maximum upload size is 1048576 bytes or ~1 Megabyte.<br/>
					Permitted formats are all image formats.
				</div>
				
				<div class="sectionTitle">About WTO imager</div>
				<div class="sectionBody">
					WTO imager is an image service that combines functionalities from both <a href="http://imgur.com">Imgur</a>, <a href="http://instagram.com">Instagram</a> and other similar services.
					The abbreviation WTO stands for Workshop Team One. This project was made as a part of workshops created by the college we're studying at - the Faculty of Informatics and Computer Technologies in Bitola, Macedonia
					and the company Seavus. The project is open source and available at GitHub. All the code is written by Martin Petkovski.
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
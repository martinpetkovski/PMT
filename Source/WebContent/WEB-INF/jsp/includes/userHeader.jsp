<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="userMetaData">
			<div class="username">
				${UserName}
			</div>
			<div class="metaDataWrapper">
	 	
				<div class="metaItem" id="imagesButton" style="background-color: #06A;">
					<span class="number">${ImageNumber }</span> images
				</div>

				<div class="metaItem" id="commentsButton">
					<span class="number">${CommentNumber }</span> comments
				</div>

				<div class="metaItem" id="pointsButton">
					<span class="number">${UserPoints}</span> points
				</div>
			</div>
			
			
		</div>
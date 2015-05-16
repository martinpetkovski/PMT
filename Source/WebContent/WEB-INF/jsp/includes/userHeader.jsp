<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="userMetaData">
			<div class="username">
				${UserName}
				
				<div class="mainPoints">
					<span class="number">${UserPoints}</span> points <span class="number" id="blue">X</span> followers
				</div>
				
				<div class="followButton">
					Follow
				</div>
			</div>
			<div class="metaDataWrapper">
	 	
				<div class="metaItem" id="imagesButton" style="background-color: #222;">
					<span class="number">${ImageNumber }</span> images
				</div>

				<div class="metaItem" id="commentsButton">
					<span class="number">${CommentNumber }</span> comments
				</div>
			</div>
			
			
		</div>
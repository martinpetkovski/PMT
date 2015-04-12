<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="userMetaData">
			<div class="username">
				${UserName}
			</div>
			<div class="metaDataWrapper">
				<a href="${pageContext.request.contextPath}/user/${UserName}/images">
				 	
					<div class="metaItem" id="${activeImages ? 'active' : ''}">
						<span class="number">${ImageNumber }</span> images
					</div>
				</a>
				
				<span class="siz">//</span>

				<a href="${pageContext.request.contextPath}/user/${UserName}/comments">
					<div class="metaItem" id="${activeComments ? 'active' : ''}">
						<span class="number">${CommentNumber }</span> comments
					</div>
				</a>
				<span class="siz">//</span>

				<div class="metaItem" id="${activeAll ? 'active' : ''}">
					<span class="number">${UserPoints}</span> points
				</div>
			</div>
			
			
		</div>
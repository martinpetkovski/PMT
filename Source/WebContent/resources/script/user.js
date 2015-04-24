$(document).ready(function(){
	$("#imagesButton").click(function(){
		$("#imagesButton").attr("style", "background-color: #06A;");
		$("#commentsButton").attr("style", "");
		$(".comments").hide();
		$(".images").show();
	});
	
	$("#commentsButton").click(function(){
		$("#imagesButton").attr("style", "");
		$("#commentsButton").attr("style", "background-color: #06A;");
		$(".images").hide(0);
		$(".comments").show(0);
	});
});
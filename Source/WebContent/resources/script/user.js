function submitParentForm() {
	$(".submit").click(function(event){
		$(this).parent().submit();
	})
}

function imagesButtonClick() {
	$("#imagesButton").click(function(){
		$("#imagesButton").attr("style", "background-color: #222;");
		$("#commentsButton").attr("style", "");
		$(".comments").hide();
		$(".images").show();
	});
}

function commentsButtonClick() {
	$("#commentsButton").click(function(){
		$("#imagesButton").attr("style", "");
		$("#commentsButton").attr("style", "background-color: #222;");
		$(".images").hide();
		$(".comments").show();
	});
}

$(document).ready(function(){
	imagesButtonClick();
	commentsButtonClick();
	submitParentForm();
});

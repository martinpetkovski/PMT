function submitParentForm() {
	$(".submit").click(function(event){
		$(this).parent().submit();
	})
}

function imagesButtonClick() {
	$("#imagesButton").click(function(){
		$("#imagesButton").attr("style", "background-color: #06A;");
		$("#commentsButton").attr("style", "");
		$(".comments").hide();
		$(".images").show();
	});
}

function commentsButtonClick() {
	$("#commentsButton").click(function(){
		$("#imagesButton").attr("style", "");
		$("#commentsButton").attr("style", "background-color: #06A;");
		$(".images").hide(0);
		$(".comments").show(0);
	});
}

$(document).ready(function(){
	imagesButtonClick();
	commentsButtonClick();
	submitParentForm();
});

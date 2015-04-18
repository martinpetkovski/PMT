function changeImagesOnKeypress() {
	$('body').bind('keydown', function(event){
		if(event.keyCode  == 37) {
			var prevImage = imageId - 1;
			window.location.href = ctxPath + "/image/" + prevImage;
		}
		else if(event.keyCode == 39) {
			var nextImage = imageId + 1;
			window.location.href = ctxPath + "/image/" + nextImage;
		}
	});
}

function displayCommentButtonOnClick() {
	mouseDownHappened = false;

	$('.comment input').focus(function(){
		$('.comment .button').css('display','block');
	});

	$('.comment button').mousedown(function() {
	    mouseDownHappened = true;
	});

	$('.comment input').blur(function(event){
		if(!mouseDownHappened)
			$('.comment .button').css('display', 'none');
	});

	$('.comment button').mouseup(function() {
	    mouseDownHappened = false;
	    $('.comment .button').css('display', 'none');
	});
}

function imageHover() {
	$(".image img").mouseenter(function(){
		$(".imageMeta").stop().fadeIn(200);
	});
	
	$(".image").mouseleave(function(){
		$(".imageMeta").stop().delay(400).fadeOut(200);
	});
}

$(document).ready(function(){
	imageHover();
	changeImagesOnKeypress();
	displayCommentButtonOnClick();
});
$(document).ready(function(){

	$('.mainContentWrapper .image, .mainContentWrapper .title').mouseover(function(event){
		$(this).find('.title').stop().fadeIn(200);
	});

	$('.mainContentWrapper .image').mouseout(function(event){
		$(this).find('.title').stop().fadeOut(50);
	});

});
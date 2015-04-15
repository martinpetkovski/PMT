function focusSearchBarOnKeypress() {
	$('body').bind('keydown', function(event){
		if(event.keyCode  == 113) {
			$('.search input').focus();
		}
	});
}

function loginRegisterClick() {
	$('.button#login').click(function(){
		$('.dropMenu#login').slideToggle(500);
		if($('.dropMenu#register').is(':visible')){
			$('.dropMenu#register').slideUp(500);
		}
	});

	$('.button#register').click(function(){
		$('.dropMenu#register').slideToggle(500);
		if($('.dropMenu#login').is(':visible')){
			$('.dropMenu#login').slideUp(500);
		}
	});
}

function searchBarClick() {
	$('.search input').focus(function(){
		$('.search input').animate({width: 500}, 500, function() {
			$('.buttonArea .syntax').fadeIn(500);
		});
	});
	$('.search input').blur(function(){
		$('.search input').animate({width: 120}, 400);
		$('.buttonArea .syntax').fadeOut(200);
	});
}


$(document).ready(function(){

	loginRegisterClick();
	searchBarClick();
	focusSearchBarOnKeypress();
});
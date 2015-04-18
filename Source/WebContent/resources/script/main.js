function focusSearchBarOnKeypress() {
	$('body').bind('keydown', function(event){
		if(event.keyCode  == 113) {
			$('.search input').focus();
		}
	});
}

function loginRegisterClick() {
		$('.dropMenu#login').slideDown(500);
		$('.dropMenu#login input#username').focus();

		$('.dropMenu#register').slideDown(500);
		$('.dropMenu#register input#username').focus();
}

function searchBarClick() {
	$('.search input').focus(function(){
		$('.search input').animate({width: 500}, 500, function() {
			$('.buttonArea .syntax').fadeIn(500);
		});
	});
	$('.search input').blur(function(){
		$('.search input').animate({width: 150}, 400);
		$('.buttonArea .syntax').fadeOut(100);
	});
}


$(document).ready(function(){

	loginRegisterClick();
	searchBarClick();
	focusSearchBarOnKeypress();
});
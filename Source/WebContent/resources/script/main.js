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
		$('.search input').animate({width: 600}, 300, function() {
			$('.buttonArea .syntax').fadeIn(300);
		});
	});
	$('.search input').blur(function(){
		$('.search input').animate({width: 300}, 200);
		$('.buttonArea .syntax').fadeOut(100);
	});
}

function logoButtonHover() {
	
	$(".logoButton").mouseover(function(e){
		$(".buttonDescription").css("left", $(this).css("left"));
		$(".buttonDescription").html($(this).attr("alt"));
		$(".buttonDescription").show();
	});
	
	$(".logoButton").mouseout(function(e){
		$(".buttonDescription").hide();
	});
}


$(document).ready(function(){

	logoButtonHover();
	searchBarClick();
	focusSearchBarOnKeypress();
});
$(document).ready(function(){

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

});
$(document).ready(function(){

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

});
function submitParentForm() {
	$(".submit").click(function(event){
		$(this).parent().submit();
	})
}

$(document).ready(function(){

	$('.mainContentWrapper .image, .mainContentWrapper .metaBox').mouseover(function(event){
		$(this).find('.metaBox').stop().fadeIn(200);
	});

	$('.mainContentWrapper .image').mouseout(function(event){
		$(this).find('.metaBox').stop().fadeOut(50);
	});

	submitParentForm();
});
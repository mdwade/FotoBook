$(document).on("click", ".albumName", function (e){			
	let id = $(e.target).closest("a").data("id");
	$.get("/FotoBook/get_album1?id="+id).done(function(data) {		
		$('#albumBloc').html(data);
	});
});

function fazerPost(){
	document.getElementById("formposts").method = "post";
	document.getElementById("formposts").action = "SvAbrirCadastroPost";
	document.getElementById("formposts").submit();
}

$(document).on('click', '.posts', function() {
    let id = $(this).find("input").val();
    document.getElementById("id_post").value = id;
    document.getElementById("formposts").submit();
});
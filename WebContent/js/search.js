$(document).on('click', '.livros', function() {
    let id = $(this).find("input").val();
    document.getElementById("id_livro").value = id;
    document.getElementById("formlivros").submit();
});

$(document).on('click', '.grupos', function() {
    let id = $(this).find("input").val();
    document.getElementById("id_grupo").value = id;
    document.getElementById("formgrupos").submit();
});

$(document).on('click', '.users', function() {
    let id = $(this).find("input").val();
    document.getElementById("id_user").value = id;
    document.getElementById("formusers").submit();
});

function searchAutor2(){
	document.getElementById("formlivros").method = "post";
	document.getElementById("formlivros").action = "SvSearchAutor";
	document.getElementById("formlivros").submit();
}
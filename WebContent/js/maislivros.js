$(document).on('click', '.morebooks', function() {

    let id = $(this).find("input").val();
    document.getElementById("id_livro").value = id;
    document.getElementById("formlivros").submit();
    
});

function searchAutor2(){
	document.getElementById("formlivros").method = "post";
	document.getElementById("formlivros").action = "SvSearchAutor";
	document.getElementById("formlivros").submit();
}
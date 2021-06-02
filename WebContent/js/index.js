function searchAutor(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvSearchAutor";
	document.getElementById("formulario").submit();
}

function cadastrarLivro(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvRCadastroLivro";
	document.getElementById("formulario").submit();
}

function cadastrarAutor(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "jspcadastrarautor.jsp";
	document.getElementById("formulario").submit();
}

function addLista(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvAddLista";
	document.getElementById("formulario").submit();
}

function searchBook(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvSearchBook";
	document.getElementById("formulario").submit();
}

function cadastroResenha(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvFazerResenha";
	document.getElementById("formulario").submit();
}

function abrirResenhas(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvReviews";
	document.getElementById("formulario").submit();
}
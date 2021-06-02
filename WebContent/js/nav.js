function logout(){
    	document.getElementById("formularionav").method = "post";
    	document.getElementById("formularionav").action = "SvLogout";
    	document.getElementById("formularionav").submit();
    }

function abrirPerfil(){
	document.getElementById("formularionav").method = "post";
	document.getElementById("formularionav").action = "SvPerfil";
	document.getElementById("formularionav").submit();
}

function abrirCadastroGrupos(){
	document.getElementById("formularionav").method = "post";
	document.getElementById("formularionav").action = "SvAbrirCadastroGrupo";
	document.getElementById("formularionav").submit();
}
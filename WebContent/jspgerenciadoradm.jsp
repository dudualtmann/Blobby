<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Meu Css -->
<link rel="stylesheet" type="text/css" href="css/styleposts.css">

<title>Reviews</title>
</head>
<body>
	<header class="header-index">

		<a href="jspgerenciadoradm.jsp"> <img src="./images/blobby header logo.png"
			alt="" class="header-img">
		</a>






	</header class="header-index">




	<form class="form" action="" method="post" id="formulario">

		<div class="card">
			<div class="card-top">
				<h2 class="title">Gerenciador de Administrador</h2>
				<p>
					<strong>Admin Logado: </strong>
					<%=session.getAttribute("admin")%></p>
				<div class="post">Use esse gerenciador para: Banimentos de
					Users, Exclusão de Grupos, Criar Logins ADM</div>

			</div>

			<%
				if (request.getAttribute("msg") != null) {
			%>

			<div class="card-group">
				<label><%=request.getAttribute("msg")%></label>
			</div>
			<%
				}
			%>

			<div class="h2-comment">
				<h2>Banir Usuários</h2>
			</div>
			<hr>

			<div class="card-group btt" style="margin-left: 10%;">
				<button type="button"
					onclick="window.location.href = 'jspbanuser.jsp'">Abrir</button>
				<p
					style="padding-left: 15%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px; margin-top: 10px;">
					Obs: É necessário um motivo plausível para o banimento.</p>
			</div>

			<div class="h2-comment" style="margin-top: 100px;">
				<h2>Excluir Grupos</h2>
			</div>
			<hr>

			<div class="card-group btt" style="margin-left: 10%;">
				<button type="button"
					onclick="window.location.href = 'jspdelgroup.jsp'">Abrir</button>
				<p
					style="padding-left: 15%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px; margin-top: 10px;">
					Obs: É necessário um motivo plausível para excluir um grupo.</p>
			</div>

			<div class="h2-comment" style="margin-top: 100px;">
				<h2>Criar Novo Login ADM</h2>
			</div>
			<hr>

			<div class="card-group btt" style="margin-top: 10px;">
				<label>E-Mail</label> <input type="text" name="email"
					required="required">

			</div>

			<div class="card-group btt">
				<label>Username</label> <input type="text" name="username"
					required="required">

			</div>
			<div class="card-group btt">
				<label>Senha</label> <input type="password" name="senha"
					required="required">

			</div>

			<div class="card-group btt" style="margin-left: 20%;">
				<button type="button" onclick="registerAdm()">Criar</button>

			</div>
			
			<div class="h2-comment" style="margin-top: 100px;">
				<h2>Fazer Logout</h2>
			</div>
			<hr>

			<div class="card-group btt" style="margin-left: 10%;margin-top: 10px;">
				<button type="button"
					onclick="logout()">Logout</button>
			</div>


		</div>
	</form>





	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>


</body>
<script>
	function registerAdm() {
		document.getElementById("formulario").method = "post";
		document.getElementById("formulario").action = "SvRegisterAdm";
		document.getElementById("formulario").submit();
	}

	function logout(){
    	document.getElementById("formulario").method = "post";
    	document.getElementById("formulario").action = "SvLogout";
    	document.getElementById("formulario").submit();
    }
</script>
</html>
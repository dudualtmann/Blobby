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
<link rel="stylesheet" type="text/css" href="css/stylegrupoperfil.css">
<script src="js/grupos.js"></script>
 <script src="js/nav.js"></script>


<title>Perfil do Grupo</title>
</head>
<body>
	<%@ page import="java.util.*, control.Grupo"%>
	<%
		Grupo g = (Grupo) session.getAttribute("objgrupo");
	String hasJoined = (String) session.getAttribute("hasJoined");
	%>
	<header class="header-index">

		<a href="home.jsp"> <img src="./images/blobby header logo.png"
			alt="" class="header-img">
		</a>

		<div class="search-container">
			<form action="SvSearch" method="POST">
				<input type="search" placeholder="Search.." name="search">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>




	</header class="header-index">

	<nav id="barranav">
        <form action="" method="post" id="formularionav">
            <a href="home.jsp" id="home">Home</a>
            <a href="#"onclick="abrirPerfil()">Perfil</a>
            <a href="#" onclick="abrirCadastroGrupos()">Criar Grupo</a>
            <a href="#" onclick="logout()">Logout</a>
        </form>

        </nav>


	<section class="main">

		<div class="grouptitle">
			<label>Grupo: <%=g.getName()%></label>
		</div>

		<section class="leftcolumn">
			<form action="SvJoinGroup" method="post" id="formgroups">
				<div class="cardimage">
					<img src="<%=g.getFotoGrupo()%>" alt="<%=g.getName()%>">
				</div>


				<%
					if (hasJoined.equalsIgnoreCase("yes")) {
				%>
				<div class="card-group">
					<div class="borda"></div>
					<div class="card-button">
						<button type="submit" disabled="disabled"
							style="backbackground-color: gray;">Já Participa</button>
					</div>
				</div>
				<div class="card-group leavegroup">
					<label onclick="leaveGroup()"
						style="text-align: center; margin-top: 5px; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 12px;">
						Sair do Grupo</label>
				</div>

				<%
					} else {
				%>
				<div class="card-group">
					<div class="borda"></div>

					<div class="card-button">
						<button type="submit">Entrar no Grupo</button>
					</div>
				</div>
				<%
					}
				%>



				<div class="card-group">
					<div class="card-item">
						<label>Informações</label>
						<div class="borda"></div>

						<p class="subtitle">
							Nome: <span class="info"><%=g.getName()%></span>
						</p>
						<p class="subtitle">
							Criador: <span class="info"><%=g.getOwner().getUsername()%></span>
						</p>
						<p class="subtitle">
							Categoria: <span class="info"><%=g.getCategory()%></span>
						</p>
						<p class="subtitle">
							Ano de Criação: <span class="info"><%=g.getYear()%></span>
						</p>
					</div>
				</div>

				<div class="card-group">
					<div class="card-item">
						<label>Estatísticas</label>
						<div class="borda"></div>
						<p class="subtitle">
							Membros: <span class="info"><%=session.getAttribute("group-members")%></span>
						</p>
					</div>
				</div>




			</form>
		</section>

		<div class="rightcolumn">

			<%
				if (request.getAttribute("msg") != null) {
			%>
			<p style="text-align: center"><%=request.getAttribute("msg")%></p>
			<%
				}
			%>


			<span class="h2title">
				<h2>Descrição do Grupo</h2>
			</span>
			<div class="borda1"></div>

			<span class="desc"><%=g.getDescription()%></span> <span
				class="h2title">
				<h2>Regras do Grupo</h2>
			</span>
			<div class="borda1"></div>
			<span class="desc"><%=g.getRules()%> </span> <span class="h2title">


				<div class="reviewsdiv">
					<form action="SvAbrirDiscussoes" method="post">
						<span class="h2title">
							<h2>Discussões</h2>
						</span>
						<div class="borda1"></div>
						<span class="reviewspan">
							<h2><%=g.getName()%></h2>
							<button type="submit">Ver Discussões do Grupo</button>
							<p>*Cuidado, pois as discussões podem conter Spoilers ou
								Conteúdo Inapropriado!</p>
						</span>
					</form>
				</div>
		</div>





	</section>


	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>


</body>
<style>
.leavegroup {
    width: 100%;
	margin-left: 30%;
	
}

.leavegroup:hover {
	cursor: pointer;
}
</style>
</html>
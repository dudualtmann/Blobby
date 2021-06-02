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
<link rel="stylesheet" type="text/css" href="css/stylecadlivro.css">
<script src="js/index.js"></script>
 <script src="js/nav.js"></script>
<title>Procurar Autor</title>
</head>
<body>

	<%@ page import="java.util.*, control.Autor"%>
	<%
		List<Autor> lista = (List<Autor>) session.getAttribute("listautor");
	%>
	<header class="header-index">

		<a href="home.jsp"> <img src="./images/blobby header logo.png"
			alt="" class="header-img">
		</a>

		<div class="search-container">
			<form action="SvSearch">
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


	<form class="form" method="post" id="formulario">

		<div class="card">
			<div class="card-top">
				<h2 class="title">Procurar Autor</h2>
				<p>Digite o nome do Autor do livro que deseja cadastrar.</p>


			</div>
			
			<%if(request.getAttribute("autorMsg")!=null){ %>
			
			<div class="card-group">
                      <label><%= request.getAttribute("autorMsg") %></label>
            </div>
            <% } %>

			<div class="card-group">
				<label>Nome do Autor</label> 
				<input type="text" name="autorsearch" id="autorsearch" placeholder="Procure o nome do Autor">
			</div>
			<div class="card-group">
			<button type="button" onclick="searchAutor()">Procurar</button>
			</div>
			<%
				if (lista != null) {
			%>
			<div class="card-group">
			<label>Selecione o Autor Que Procura:</label>
			<select name="autorselect">
				<%
					for (int i = 0; i < lista.size(); i++) {
				%>
				<option value="<%=lista.get(i).getId()%>">
					<%=lista.get(i).getNome()%></option>
				<%
					}
				%>
			</select>
			</div>
			<div class="card-group">
				<button type="submit" onclick="cadastrarLivro()">Continuar Cadastro</button>
			</div>
			<%
				}
			%>
			<div class="card-group" style="background-color: lightgreen;padding: 10px; border-radius: 10px;">
				<label>Não encontrou o Autor que desejava? Ajude a comunidade e faça o cadastro do autor!</label>
				<button type="submit" onclick="cadastrarAutor()">Cadastrar Autor</button>
			</div>
			
		</div>
	</form>






	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>



</body>
</html>
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
<title>Criar Resenha</title>
</head>
<body>


	<%@ page import="java.util.*, control.Book"%>
	<%
		List<Book> lista = (List<Book>) request.getAttribute("listbooks");
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


	<form class="form" action="SvSearchBook" method="post" id="formulario">

		<div class="card">
			<div class="card-top">
				<h2 class="title">Procurar Livro</h2>
				<p>Digite o nome do livro que deseja fazer uma review.</p>


			</div>
			
			<%if(request.getAttribute("bookMsg")!=null){ %>
			
			<div class="card-group">
                      <label><%= request.getAttribute("bookMsg") %></label>
            </div>
            <% } %>

			<div class="card-group">
				<label>Nome do Livro</label> 
				<input type="text" name="booksearch" placeholder="Procure o nome do Livro">
			</div>
			<div class="card-group">
			<button type="submit">Procurar</button>
			</div>
			<%
				if (lista != null) {
			%>
			<div class="card-group">
			<label>Selecione o Livro Que Procura:</label>
			<select name="bookselect">
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
				<button type="submit" formaction="SvAbrirResenha">Fazer Resenha</button>
			</div>
			<%
				}
			%>
			<div class="card-group" style="background-color: lightgreen;padding: 10px; border-radius: 10px;">
				<label>Não encontrou o Livro que desejava? Ajude a comunidade e 
				<a href="jspsearchautor.jsp">Faça o cadastro do Livro!</a></label>
			</div>
			
		</div>
	</form>






	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>



</body>
</html>
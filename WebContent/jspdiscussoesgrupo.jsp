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
<link rel="stylesheet" type="text/css" href="css/styledisc.css">
<script src="js/posts.js"></script>
 <script src="js/nav.js"></script>


<title>Discussões do Grupo</title>
</head>
<body>
	<%@ page import="java.util.*, control.Post"%>
	<%
		List<Post> listPosts = (List<Post>) request.getAttribute("listPosts");
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

	<main class="corpo">
		<nav id="barranav">
        <form action="" method="post" id="formularionav">
            <a href="home.jsp" id="home">Home</a>
            <a href="#"onclick="abrirPerfil()">Perfil</a>
            <a href="#" onclick="abrirCadastroGrupos()">Criar Grupo</a>
            <a href="#" onclick="logout()">Logout</a>
        </form>

        </nav>

		<section class="secteste">
			<form action="SvPost" method="POST" id="formposts" name="formposts">
			<input type="hidden" id="id_post" name="id_post" value="">

				<div class="my-3 p-3 bg-white rounded box-shadow">
				<% if(hasJoined.equalsIgnoreCase("yes")) {%>
					<h6 style="float: right;">
						<a href="#" onclick="fazerPost()">Fazer um Post</a>
					</h6>
					<% } %>
					<h6 class="border-bottom border-gray pb-2 mb-0">Discussões
						Recentes</h6>

					<%
						if (listPosts == null || listPosts.size() < 1) {
					%>

					<div class="media text-muted pt-3">
						<p
							class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
							<strong class="d-block text-gray-dark">Não Há nenhuma
								discussão aberta nesse grupo</strong>
						</p>
					</div>

					<%
						} else {
						for (int i = (listPosts.size() - 1); i > -1; i--) {
					%>
					<div class="media text-muted pt-3">
						<p
							class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray posts">
							<strong class="d-block text-gray-dark"><%=listPosts.get(i).getTitle()%></strong>
							Autor:
							<%=listPosts.get(i).getUser().getUsername()%>
							<input type="hidden" value="<%=listPosts.get(i).getId()%>">
						</p>
						
					</div>

					<%
						}
					}
					%>

					<!--<small class="d-block text-right mt-3">
      <a href="#">Ver Mais</a>
    </small>
  -->
				</div>

			</form>
		</section>

	</main>
	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>
</body>
<style>
.posts:hover {
	cursor: pointer;
}
</style>
</html>
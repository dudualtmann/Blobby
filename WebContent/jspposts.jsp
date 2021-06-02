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
 <script src="js/nav.js"></script>

<title>Posts</title>
</head>
<body>
	<%@ page import="java.util.*, control.Post, control.Comment"%>
	<%
		Post p = (Post) session.getAttribute("objpost");
	String hasJoined = (String) session.getAttribute("hasJoined");
	List<Comment> listComments = (List<Comment>) session.getAttribute("listComments");
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


	<form class="form" action="SvFazerComentario" method="post">

		<div class="card">
			<%
				if (request.getAttribute("msg") != null) {
			%>
			<p><%=request.getAttribute("msg")%></p>
			<%
				}
			%>
			<div class="card-top">
				<h2 class="title"><%=p.getTitle()%></h2>
				<p>
					<strong>Autor: </strong>
					<%=p.getUser().getUsername()%></p>
				<div class="post">
					<%=p.getContent()%>

				</div>

			</div>

			<div class="h2-comment">
				<h2>Comentários</h2>
			</div>
			<hr>

			
			<%
				if (hasJoined.equalsIgnoreCase("yes")) {
			%>

			<div class="card-group write-comment">
				<label>Fazer Comentário: </label>
				<textarea name="content" cols="30" rows="5"
					placeholder="Comentário..." required="required"></textarea>
			</div>

			<div class="card-group btt">
				<button type="submit">Enviar</button>
				<p
					style="padding-left: 15%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px; margin-top: 10px;">
					Obs: É necessário fazer parte do grupo para escrever um comentário
					em um Post.</p>
			</div>

			<%
				} else {
			%>
			<div class="card-group btt">
				
				<p
					style="padding-left: 15%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px; margin-top: 10px;">
					Obs: É necessário fazer parte do grupo para escrever um comentário
					em um Post.</p>
			</div>
			<%
				}
			if (listComments == null || listComments.size() < 1) {
			%>

			<%
				} else {

				for (int i = 0; i < listComments.size(); i++) {
			%>
			<div class="review">

				<div class="card-group username">
					<label><%=listComments.get(i).getUser().getUsername()%></label>
				</div>
				<div class="card-group comment">
					<%=listComments.get(i).getContent()%>
				</div>

			</div>
			<%
				}
			}
			%>


		</div>
	</form>





	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>


</body>
</html>
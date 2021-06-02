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
<title>Banir Usuário</title>
</head>
<body>

	<%@ page import="java.util.*"%>
	
	<header class="header-index">

		<a href="jspgerenciadoradm.jsp"> <img src="./images/blobby header logo.png"
			alt="" class="header-img">
		</a>




	</header class="header-index">


	<form class="form" method="post" id="formulario" style="height: 800px">

		<div class="card">
			<div class="card-top">
				<h2 class="title">Banir Usuário</h2>
				<p>Digite o nome do User que deseja banir. Cuidado para não digitar errado, uma vez banido, impossível recuperar.</p>


			</div>
			
			<%if(request.getAttribute("msg")!=null){ %>
			
			<div class="card-group">
                      <label><%= request.getAttribute("msg") %></label>
            </div>
            <% } %>

			<div class="card-group">
				<label>Nome do Usuário (username)</label> 
				<input type="text" name="username" placeholder="Digite o nome do Usuário">
			</div>
			<div class="card-group">
			<button type="button" onclick="banUser()">Banir</button>
			</div>
			
			
		</div>
	</form>






	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>



</body>
<script type="text/javascript">
function banUser(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvBanUser";
	document.getElementById("formulario").submit();
}
</script>
</html>
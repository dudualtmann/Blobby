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
<title>Excluir Grupo</title>
</head>
<body>

	<%@ page import="java.util.*, control.Grupo"%>
	<%
		List<Grupo> lista = (List<Grupo>) session.getAttribute("listgrupos");
	%>
	
	<header class="header-index">

		<a href="jspgerenciadoradm.jsp"> <img src="./images/blobby header logo.png"
			alt="" class="header-img">
		</a>




	</header class="header-index">


	<form class="form" method="post" id="formulario" style="height: 800px">

		<div class="card">
			<div class="card-top">
				<h2 class="title">Excluir Grupo</h2>
				<p>Digite o nome do Grupo que deseja Excluir. Cuidado para não excluir errado, uma vez excluído, impossível recuperar.</p>


			</div>
			
			<%if(request.getAttribute("msg")!=null){ %>
			
			<div class="card-group">
                      <label><%= request.getAttribute("msg") %></label>
            </div>
            <% } %>

			<div class="card-group">
				<label>Nome do Grupo</label> 
				<input type="text" name="groupsearch" placeholder="Procure o nome do Grupo">
			</div>
			<div class="card-group">
			<button type="button" onclick="searchGroups()">Procurar</button>
			</div>
			<%
				if (lista != null) {
			%>
			<div class="card-group">
			<label>Selecione o Grupo Que Deseja Banir:</label>
			<select name="groupselect">
				<%
					for (int i = 0; i < lista.size(); i++) {
				%>
				<option value="<%=lista.get(i).getId()%>">
					<%=lista.get(i).getName()%> / Criador: <%= lista.get(i).getOwner().getUsername() %></option>
				<%
					}
				%>
			</select>
			</div>
			<div class="card-group">
				<button type="submit" onclick="delGroup()">Excluir Grupo</button>
			</div>
			<%
				}
			%>
			
			
		</div>
	</form>






	<footer>
		<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
	</footer>



</body>
<script type="text/javascript">
function delGroup(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvDelGroup";
	document.getElementById("formulario").submit();
}

function searchGroups(){
	document.getElementById("formulario").method = "post";
	document.getElementById("formulario").action = "SvSearchGroup";
	document.getElementById("formulario").submit();
}
</script>
</html>
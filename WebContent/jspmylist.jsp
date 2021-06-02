<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blobby - Minha Lista</title>
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
<link rel="stylesheet" href="css/stylemylist.css">
<script src="js/index.js"></script>
<script src="js/search.js"></script>
<script src="js/openbooks.js"></script>
 <script src="js/nav.js"></script>
<title>Blobby - Perfil</title>
</head>
<body>
	<%@ page import="java.util.*, control.BookListaPessoal, control.Grupo"%>
	<%
		BookListaPessoal bl = new BookListaPessoal();
	List<BookListaPessoal> myList = (List<BookListaPessoal>) request.getAttribute("myList");
	Grupo g = new Grupo();
	List<Grupo> myGroupList = (List<Grupo>) request.getAttribute("myGroupList");
	%>

	<div class="conteiner-fluid">

		<nav id="barranav">
        <form action="" method="post" id="formularionav">
            <a href="home.jsp" id="home">Home</a>
            <a href="#"onclick="abrirPerfil()">Perfil</a>
            <a href="#" onclick="abrirCadastroGrupos()">Criar Grupo</a>
            <a href="#" onclick="logout()">Logout</a>
        </form>

        </nav>

		<header class="header-index">
			<div class="row">

				<div class="col-md-6">

					<a href="home.jsp"> <img src="images/blobby header logo.png"
						alt="" class="header-img">
					</a>

				</div>

				<div class="col-md-6">

					<div class="search-container">
						<form action="SvSearch">
							<input type="search" placeholder="Search.." name="search">
							<button type="submit">
								<i class="fa fa-search"></i>
							</button>
						</form>
					</div>
				</div>

			</div>
		</header>

		<section id="section">
			<form action="SvPerfilLivro" method="post" id="formlivros">

				<input type="hidden" value="vazio" id="id_livro" name="id_livro">

				<div>
					<h2 class="home-section">PERFIL</h2>

					<p class="top-livros">Username</p>

					<hr class="hr">

					<p
						style="padding-left: 5%; font-weight: bold; padding-bottom: 50px;"><%=request.getAttribute("user")%></p>

					<p class="top-livros">
						lista do(a)
						<%=request.getAttribute("user")%>
					</p>

					<hr class="hr">

					<p
						style="padding-left: 5%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px;">Obs:
						As Notas Que Aparecem São as Notas Dadas Pelo Usuário, Não o
						Rating Geral do Livro</p>

					<div class="row imagens" id="div-toplivros">

						<%
							if (myList == null) {
						%>
						<p
							style="padding: 5%; line-height: 2.0em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 18px;">O
							Usuário ainda adicionou nenhum livro à lista pessoal.</p>

						<%
							} else {
							for (int i = 0; i < myList.size(); i++) {
						%>
						<div class="col-md-3 espaço card-group">
							<p
								style="padding-left: 5%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px; width: 100px">
								<strong>Nota:</strong>
								<%=myList.get(i).getRating()%></p>
							<p
								style="padding-left: 5%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px;">
								<strong>Status:</strong>
								<%=myList.get(i).getStatus()%></p>
							<p
								style="padding-left: 5%; line-height: 1.5em; color: #7a0a0a; font-family: Avenir, lucida grande, tahoma, verdana, arial, sans-serif; font-size: 14px;">
								<strong>Pág. Lidas:</strong>
								<%=myList.get(i).getPagLidas()%></p>
							<img src="<%=myList.get(i).getBook().getCapa()%>" class="img"
								alt="<%=myList.get(i).getBook().getNome()%>">

							<p class="nomeLivro"><%=myList.get(i).getBook().getNome()%></p>
							<input type="hidden" value="<%=myList.get(i).getBook().getId()%>">
						</div>
						<%
							}
						}
						%>



					</div>
				</div>



			</form>


		</section>

		<aside id="aside">
			<form action="SvPerfilGrupo" method="post" id="formgrupos">
				<input type="hidden" value="vazio" id="id_grupo" name="id_grupo">

				<div class="home-section"></div>
				<h4 id="h4-aside">Meus Grupos</h4>

				<hr class="hr">

				<div>

					<%
						if (myGroupList != null) {
						for (int i = 0; i < myGroupList.size(); i++) {
					%>
					<div class="centraliza">
						<img src="<%=myGroupList.get(i).getFotoGrupo()%>" class="img"
							alt="<%=myGroupList.get(i).getName()%>">

						<p class="nomeLivro"><%=myGroupList.get(i).getName()%></p>
						<input type="hidden" value="<%=myGroupList.get(i).getId()%>">
					</div>
					<%
						}
					}
					%>



				</div>
			</form>
		</aside>

		<footer id="footer">
			<div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
		</footer>

	</div>
</body>

</body>
<script type="text/javascript">
	$(document).on('click', '.centraliza', function() {
		let id = $(this).find("input").val();
		document.getElementById("id_grupo").value = id;
		document.getElementById("formgrupos").submit();
	});
</script>
<style>
.centraliza:hover {
	cursor: pointer;
}
</style>
</html>
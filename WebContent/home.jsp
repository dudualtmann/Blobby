<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
        content="width=device-width, initial-scale=1.0">
    <title>Blobby - home page</title>
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
    <link rel="stylesheet" href="css/estilo.css">
    <script src="js/index.js"></script>
    <script src="js/search.js"></script>
    <script src="js/openbooks.js"></script>
    <script src="js/nav.js"></script>
<title>Blobby - Homepage</title>
</head>
<body>

<%@ page import="java.util.*, control.Book, control.Grupo" %>
<%
Book b = new Book();
List<Book> topBooks = b.getTopBooks(); 
List<Book> uAdd = b.ultimosAdicionados();
Grupo g = new Grupo();
List<Grupo> uGrupo = g.ultimosAdicionados();
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

                    <a href="home.jsp">
                        <img src="images/blobby header logo.png" alt=""
                            class="header-img">
                    </a>

                </div>

                <div class="col-md-6">

                    <div class="search-container">
                        <form action="SvSearch">
                            <input type="search"
                                placeholder="Search.."
                                name="search">
                            <button
                                type="submit"><i
                                    class="fa fa-search"></i></button>
                        </form>
                    </div>
                </div>

            </div>
        </header>

        <section id="section">
        <form action="SvPerfilLivro" method="post" id="formlivros">
        
        <input type="hidden" value="vazio" id="id_livro" name="id_livro">

            <div>
            <% if(request.getAttribute("msg") != null) {%>
            <p style="text-align: center"><%= request.getAttribute("msg") %></p>
            <% } %>
                <h2 class="home-section">HOME</h2>

                <p class="top-livros"> Top Livros
                </p>

                <hr class="hr">

                <div class="row imagens">

                    <div class="col-md-1">

                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= topBooks.get(0).getCapa() %>"
                            class="img" alt="<%= topBooks.get(0).getNome() %>">

                        <p class="nomeLivro"><%= topBooks.get(0).getNome() %></p>
                        <input type="hidden" value="<%=topBooks.get(0).getId()%>">
                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= topBooks.get(1).getCapa() %>"
                            class="img" alt="<%= topBooks.get(1).getNome() %>">

                        <p class="nomeLivro"><%= topBooks.get(1).getNome() %></p>
                        <input type="hidden" value="<%=topBooks.get(1).getId()%>">
                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= topBooks.get(2).getCapa() %>"
                            class="img" alt="<%= topBooks.get(2).getNome() %>">
                        <p class="nomeLivro"><%= topBooks.get(2).getNome() %></p>
                        <input type="hidden" value="<%=topBooks.get(2).getId()%>">
                    </div>
                </div>
            </div>

            <div class="separa-topicos">

                <p class="top-livros"> Ultimos
                    adicionados
                </p>

                <hr class="hr">

                <div class="row imagens">

                    <div class="col-md-1">

                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= uAdd.get(0).getCapa() %>"
                            class="img" alt="<%= uAdd.get(0).getNome() %>">

                        <p class="nomeLivro"><%= uAdd.get(0).getNome() %></p>
                        <input type="hidden" value="<%=uAdd.get(0).getId()%>">
                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= uAdd.get(1).getCapa() %>"
                            class="img" alt="<%= uAdd.get(1).getNome() %>">

                        <p class="nomeLivro"><%= uAdd.get(1).getNome() %></p>
                        <input type="hidden" value="<%=uAdd.get(1).getId()%>">
                    </div>

                    <div class="col-md-3 espaço card-group">
                        <img src="<%= uAdd.get(2).getCapa() %>"
                            class="img" alt="<%= uAdd.get(2).getNome() %>">
                        <p class="nomeLivro"><%= uAdd.get(2).getNome() %></p>
                        <input type="hidden" value="<%=uAdd.get(2).getId()%>">
                    </div>
                </div>
            </div>
            
            

            </form>
        </section>

        <aside id="aside">
        <form action="SvPerfilGrupo" method="post" id="formgrupos">
        <input type="hidden" value="vazio" id="id_grupo" name="id_grupo">

            <div class="home-section">

            </div>
            <h4 id="h4-aside">Novos Grupos</h4>

            <hr class="hr">

            <div>

                <div class="centraliza">
                    <img src="<%= uGrupo.get(0).getFotoGrupo() %>"
                            class="img" alt="<%= uGrupo.get(0).getName() %>">

                    <p class="nomeLivro"><%= uGrupo.get(0).getName() %></p>
                        <input type="hidden" value="<%=uGrupo.get(0).getId()%>">
                </div>

                <div class="centraliza">
                    <img src="<%= uGrupo.get(1).getFotoGrupo() %>"
                        class="img" alt="">

                    <p class="nomeLivro"><%= uGrupo.get(1).getName() %></p>
                        <input type="hidden" value="<%=uGrupo.get(1).getId()%>">
                </div>

                <div class="centraliza">
                    <img src="<%= uGrupo.get(2).getFotoGrupo() %>"
                        class="img" alt="">

                    <p class="nomeLivro"><%= uGrupo.get(2).getName() %></p>
                        <input type="hidden" value="<%=uGrupo.get(2).getId()%>">
                </div>

            </div>
            </form>
        </aside>

        <footer id="footer">
            <div>&copy; 2020 - Blobby - Todos os
                Direitos Reservados</div>
        </footer>

    </div>

</body>
<script type="text/javascript">
$(document).on('click', '.centraliza', function() {
	    let id = $(this).find("input").val();
	    document.getElementById("id_grupo").value = id;
	    document.getElementById("formgrupos").submit();
	});</script>
	<style>
	.centraliza:hover{
	cursor:pointer;
	}
	</style>
</html>
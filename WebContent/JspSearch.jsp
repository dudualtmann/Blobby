<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Meu Css -->
<link rel="stylesheet" type="text/css" href="css/stylesearch.css">

<!-- Meus JS -->
<script src="js/search.js"></script>
 <script src="js/nav.js"></script>

<title>Blobby - Pesquisar</title>
</head>
<body>
    <header class="header-index">
            
        <a href="home.jsp">
        <img src="./images/blobby header logo.png" alt="" class ="header-img">
        </a>

        <div class="search-container">
            <form action="SvSearch">
              <input type="search" placeholder="Search.." name="search">
              <button type="submit"><i class="fa fa-search"></i></button>
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

     
                <section class="slivros">
                    <form id="formlivros" action="SvPerfilLivro" method="post">
                        <input type="hidden" value="vazio" id="id_livro" name="id_livro">

                  <div class="card">
                    <div class="card-top">
                      <h2 class="title">Livros</h2>
                      <p>Não Encontrou o Livro Que Procurava? <a href="jspsearchautor.jsp" onclick="">Requisitar Cadastro de um Novo Livro</a></p>

                    </div>
                    <%@ page import="java.util.*, control.Book, control.Grupo, control.User" %>
                    <%! ArrayList<Book> searchLivros;
                    ArrayList<User> searchUsers;
                    List<Grupo> searchGrupos;
                    %>
                    <% 
                    if(request.getAttribute("searchLivros")!=null){
                     searchLivros = (ArrayList<Book>) request.getAttribute("searchLivros");
                      if(searchLivros.size() > 0){
                         for(int i = 0; i < searchLivros.size();i++) {%>
                            <div class="card-group livros">
                              <label><%= searchLivros.get(i).getNome() %></label>
                              <img src="<%=searchLivros.get(i).getCapa() %>" alt="<%=searchLivros.get(i).getNome() %>" class="capas">
                              <label>Autor(a): <%= searchLivros.get(i).getAutor().getNome() %></label>
                              <label>Editora: <%= searchLivros.get(i).getEditora() %></label>
                              <input type="hidden" value="<%=searchLivros.get(i).getId()%>">
                              <div class="borda"></div>
                            </div>

                          <% } }
                    }
                    else{
                    	out.println("<label>"+request.getAttribute("bError")+"</label>");
                    }
                    %>

                

                  </div>
                </form>
                </section>

                <section class="sgrupos">
                <form id="formgrupos" action="SvPerfilGrupo" method="post">
                        <input type="hidden" value="vazio" id="id_grupo" name="id_grupo">

                    <div class="card">
                      <div class="card-top">
                        <h2 class="title">Grupos</h2>
  
                      </div>
                      
                      <%
                      if(request.getAttribute("searchGrupos")!=null){
                     	 searchGrupos = (List<Grupo>) request.getAttribute("searchGrupos");
                     	 if(searchGrupos.size() > 0){
                            for(int i = 0; i < searchGrupos.size();i++) {%>
                              <div class="card-group grupos">
                                <label><%= searchGrupos.get(i).getName() %></label>
                                <img src="<%= searchGrupos.get(i).getFotoGrupo() %>" alt="<%= searchGrupos.get(i).getName() %>" class="capas">
                                <label>Criador: <%= searchGrupos.get(i).getOwner().getUsername() %></label>
                                <p>Categoria: <%= searchGrupos.get(i).getCategory() %></p>
                                <input type="hidden" value="<%=searchGrupos.get(i).getId()%>">
                                <div class="borda"></div>
                              </div>
                             <% 
                             } 
                            }
                          }
                         else{
                         	out.println("<label>"+request.getAttribute("gError")+"</label>");
                         } 
                      %>
                      
                    
                      
  
                    </div>
                    </form>
                  </section>

                 
  
  
      <footer>
         <div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
      </footer>


</body>
</html>
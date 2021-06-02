<%@page import="com.sun.xml.internal.txw2.Document"%>
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
<link rel="stylesheet" type="text/css" href="css/stylelivroperfil.css">
<link rel="stylesheet" type="text/css" href="css/morebooks.css">
<script src="js/index.js"></script>
<script src="js/maislivros.js"></script>
 <script src="js/nav.js"></script>
<%@ page import = "java.util.*, control.Book, control.Autor, control.BookListaPessoal" %>
<% 

   
   Book b = new Book();
   b = (Book) session.getAttribute("sessionlivro");
   List<Book> maislivros = (List<Book>) session.getAttribute("maislivros");
   

%>
<title>Perfil Livro</title>
</head>
<body>


<header class="header-index">
            
        <a href="home.jsp">
        <img src="./images/blobby header logo.png" alt="" class ="header-img">
        </a>

        <div class="search-container">
            <form action="SvSearch" method="POST">
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


       <section class="main">
      
                 <div class="booktitle">
                    <label><%= b.getNome() %></label>
                 </div>
     
            <section class="leftcolumn">
                  <div class="cardimage">
                      <img src="<%= b.getCapa() %>" alt="as" >
                  </div>
                  
                  <form method="post" id="formulario">
                  
                  <div class="card-group">
                    <label>Editar Status</label>
                    <div class="borda"></div>

                    <div class="card-item">
                        <label>Status: </label>
                        
                        <select name="status-select1">
                            <option value=""></option>
                            <option value="Lendo">Lendo</option>
                            <option value="Completado">Completado</option>
                            <option value="Desistiu">Desistiu</option>
                            <option value="Pretende ler">Pretende Ler</option>
                        </select>
                    
                    </div>
                    <div class="card-item">
                        <label>Páginas Lidas: </label>
                    
                        <input type="text" class="pagslidas" name="pag_lidas">   / <%= b.getPages() %>   
                    
                    </div>
                    <div class="card-item">
                        <label>Sua Nota: </label>
                        
                        <select name="status-select2">
                            <option value="0">Selecione</option>
                            <option value="10">(10) Obra-Prima</option>
                            <option value="9">(9) Ótimo</option>
                            <option value="8">(8) Muito Bom</option>
                            <option value="7">(7) Bom</option>
                            <option value="6">(6) OK</option>
                            <option value="5">(5) Mediano</option>
                            <option value="4">(4) Ruim</option>
                            <option value="3">(3) Muito Ruim</option>
                            <option value="2">(2) Horrível</option>
                            <option value="1">(1) Terrível</option>
                        </select>
                    
                    </div>
                    <div class="card-item">
                        <button type="button" onclick="addLista()">Adicionar à Minha Lista</button>
                    </div>

                </div>
                </form>
                <div class="card-group">
                    <div class="card-item">
                    <label>Informações</label>
                    <div class="borda"></div>
                    
                    <p class="subtitle">Título: <span class="info"><%= b.getNome() %></span></p>
                    <p class="subtitle">Autor: <span class="info"><%= b.getAutor().getNome() %></span></p>
                    <p class="subtitle">Gênero(s): <span class="info"><%= b.getGenero1() %></span></p>
                    <p class="subtitle">Ano: <span class="info"><%= b.getAno() %></span></p>
                </div>
                </div>

                <div class="card-group">
                    <div class="card-item">
                    <label>Estatísticas</label>
                    <div class="borda"></div>
                    
                    <p class="subtitle">Nota: <span class="info"><%= session.getAttribute("rating") %></span></p>
                    <p class="subtitle">Classificação: <span class="info">#<%= session.getAttribute("classificacao") %></span></p>
                    <p class="subtitle">Membros: <span class="info"><%= session.getAttribute("members") %></span></p>
                </div>
                </div>


                  
            </section>
                
            <div class="rightcolumn">

                <div class="quadro">

                    <span class="quadro-nota">
                      <!--  <div class="scorediv">NOTA</div>-->
                        <p data-title = "NOTA" data-user = "<%=session.getAttribute("members")%> users"><%= session.getAttribute("rating") %></p>   
                        
                    </span>
                    <div class="division"></div>

                    <div class="infos-quadro">
                        <p class="ranking">Classificação <span class="ranking-span">#<%= session.getAttribute("classificacao") %></span></p>
                        <p class="ranking">Membros <span class="ranking-span"><%= session.getAttribute("members") %></span></p>
                    </div>
                </div>
                <span class="h2title">
                <h2>Sinopse</h2>
                </span>
                <div class="borda1"></div>

                <span class="sinopse"><%= b.getSinopse() %></span>
                    <span class="sinopse"></span>
                    

                    <span class="h2title">
                        <h2>Autor(a)</h2>
                        </span>
                        <div class="borda1"></div>
                    <div class="autorimg">
                        <h3><%= b.getAutor().getNome() %></h3>
                        <img src="<%= b.getAutor().getFoto() %>" alt="">
                    </div>
                    <span class="autortext"><%= b.getAutor().getBiografia() %></span>
                          
                          <span class="h2title">
                <h2>Mais Livros de <%= b.getAutor().getNome() %></h2>
                </span>
                <div class="borda1"></div>
                <form action="SvPerfilLivro" method="post" id="formlivros">
                 <input type="hidden" value="vazio" id="id_livro" name="id_livro">

                            <table id="maislivrosautor" cellpadding>
                                <tbody>
                                    <tr>
                                    <%if(maislivros != null || maislivros.isEmpty()){
                                    	if(maislivros.size() < 4){
                                    for(int i = 0; i<maislivros.size(); i++){
                                    	%>
										
										
                                    	 <td>
                                            <span class="spanmaislivros morebooks">
                                               <img src="<%= maislivros.get(i).getCapa() %>" alt="">
                                               <p><%= maislivros.get(i).getNome() %></p>
                                               <input type="hidden" value="<%=maislivros.get(i).getId()%>">
                                            </span>
                                        </td>
                                        <td>
                                        
                                    	<% 
                                    }
                                    	}else { 
                                    		for(int i = 0; i<3; i++){ %>
                                    		
                                    		<td>
                                            <span class="spanmaislivros morebooks">
                                               <img src="<%= maislivros.get(i).getCapa() %>" alt="">
                                               <p><%= maislivros.get(i).getNome() %></p>
                                               <input type="hidden" value="<%=maislivros.get(i).getId()%>">
                                            </span>
                                        </td>
                                        <td>
                                    		
                                    <% }
                                    }
                                    } else{ %>
                                    <p>O Autor Não tem mais Livros.</p>
                                   <%  }%>
                                        
                                    </tr>
                                </tbody>
                            </table>
                            </form>

                            <div class="reviewsdiv">
                                <form name="formulario" action ="SvReviews"method="post">
                                <span class="h2title">
                                    <h2>Resenhas</h2>
                                    </span>
                                    <div class="borda1"></div>
                                     <span class="reviewspan">
                                         <h2><%= b.getNome() %></h2>
                                         <% session.setAttribute("id_livro", b.getId()+""); %>
                                    <button type="submit">Ver Resenhas Sobre O Livro</button>
                                    <p>*Cuidado, pois as resenhas podem conter Spoilers</p>
                                     </span>
                                    </form>
                            </div>
                        


            </div>

            

           
                
        </section>

  
      <footer>
         <div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
      </footer>

</body>
</html>
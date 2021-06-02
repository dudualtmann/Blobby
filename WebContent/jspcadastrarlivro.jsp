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
<link rel="stylesheet" type="text/css" href="css/stylecadlivro.css">
<script src="js/index.js"></script>
 <script src="js/nav.js"></script>

<title>Cadastro Livro</title>
</head>
<body>
<%@ page import = "java.util.*, control.Autor" %>
<% Autor a = (Autor) session.getAttribute("objautor");
  String msg = (String)request.getAttribute("msg");
%>
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

     
                <form class="form" action="SvCadastrarLivro" method="post" id="formulario">

                  <div class="card">
                    <div class="card-top">
                      <h2 class="title">Registro de Livro</h2>
                      <p>Não Encontrou o Livro Que Procurava?</p>
                      <p>Preencha este Formulário e Ajude a Sempre nos Manter Atualizados!</p>

                    </div>
                    
                    <%if(msg != null) {%>
                    
                    <div class="card-group">
                      <label><%= msg %></label>
                    </div>
                    
                    <% } %>

                    <div class="card-group">
                      <label>Nome do Livro</label>
                      <input type="text" name="nomelivro" placeholder="Digite o nome do livro">
                    </div>
                    
                    <div class="card-group">
                      <label>Nome do Autor</label>
                      <input type="text" name="autor" value="<%= a.getNome()%>" disabled="disabled">
                    </div>          
                   

                    <div class="card-group">
                      <label>Sinopse: </label>
                      <textarea name="sinopse" cols="30" rows="5" placeholder="Sinopse do livro..."></textarea>
                    </div>

                    <div class="card-group">
                      <label>Editora</label>
                      <input type="text" name="editora" placeholder="Digite o nome da editora">
                    </div>

                    <div class="card-group">
                      <label>Número de Páginas: </label>
                      <input type="number" name="npages" placeholder="Digite o número de páginas">
                    </div>

                    <div class="card-group">
                      <label>Gêneros: (Separados por vírgula) </label>
                      <input type="text" name="gprincipal" placeholder="Aventura, Ação, Romance Adolescente">
                    </div>
                    
                    <div class="card-group">
                      <label>Ano de Publicação </label>
                      <input type="text" name="ano" placeholder="Aventura, Ação, Romance Adolescente">
                    </div>

                    <div class="card-group">
                      <label>Imagem da Capa do Livro </label>
                      <input type="file" name="fotocapa">
                    </div>

                    <div class="card-group">
                      <button type="submit">Enviar</button>
                    </div>

                    

                  </div>
                </form>


           
                
  
  
      <footer>
         <div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
      </footer>


</body>
</html>
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
 <script src="js/nav.js"></script>

<title>Criar Post</title>
</head>
<body>
<%@page import="control.Grupo" %>
<% Grupo g = (Grupo) session.getAttribute("objgrupo");%>
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

     
                <form class="form" action="SvCadastrarPost" method="post">

                  <div class="card">
                    <div class="card-top">
                      <h2 class="title">Fazer um Post</h2>

                    </div>


                    <div class="card-group">
                      <label>Título do Post</label>
                      <input type="text" name="title" placeholder="Digite o título do Post">
                    </div>

                    <div class="card-group">
                      <label>Digite o Conteúdo do Post </label>
                      <textarea name="content" cols="30" rows="5" placeholder="Conteúdo do Post"></textarea>
                    </div>

                    <div class="card-group">
                      <label>Nome do Grupo </label>
                      <input type="text" name="grupo" disabled="disabled" value="<%=g.getName()%>">
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
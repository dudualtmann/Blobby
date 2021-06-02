<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
<title>Blobby</title>
</head>
<body>

 <header class="header-index">
            
              <a href="index.html">
              <img src="./images/blobby header logo.png" alt="" class = "header-img">
              </a>

              <form action="SvLogin" method="post">
              
             

               <label for="luser">Username</label>
               <label for="lsenha">Senha</label>
               <input type="text" name="username" id="hduser">
               <input type="password" name="senha" id="hdsenha"> 
               <input type="submit" value="Entrar" id="entrar" class="entrada" >
               
               
               <% if(request.getAttribute("lmsg") != null ){
                
               
            	   out.println("<p>"+request.getAttribute("lmsg")+"</p>");
            	   }%>
               
               
              </form>
              
            
          </header>
    <section id="pagecontent" class="psection">
      
      <div id="fieldcadastro">
          <form action="SvCadastrar" method="post">
            
            <fieldset class="fscadastro" >
              <legend>Não tem uma Conta Blobby? <br> Cadastre-se Agora!</legend>
              <br>
              <label for="lcemail">E-mail</label><br>
              <input type="email" name="cemail"><br><br>
              <label for="lcuser">Username</label><br>
              <input type="text" name="cuser"><br><br>
              <label for="lcsenha">Senha</label><br>
              <input type="password" name="cpassword"><br><br>
              <input type="submit" value="Cadastrar" id="cadastrar" class="entrada"><br>
              
              
               <%! String cmsg; %>
               <% if(request.getAttribute("cmsg") != null ){
               
               
            	   out.println("<p style=color:red;>"+request.getAttribute("cmsg")+"</p>");
            	   }%>
 
            </fieldset>
            

          </form>
          
       </div>
      
      
    </section>


    <footer>
       <div>&copy; 2020 - Blobby - Todos os Direitos Reservados</div>
          </footer>
          
</body>
</html>
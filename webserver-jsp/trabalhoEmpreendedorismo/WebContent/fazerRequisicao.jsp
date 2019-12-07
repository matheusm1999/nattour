<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/passeioServlet" var="linkPasseioServlet"/>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<style>		
   .botao{
   padding:5x;
	margin-left: 500px; 
	margin : auto;
	border-radius: 15px;	
}
  </style>
  <title>FE</title>
  <meta http-equiv="Content-Type" content = "text/html;charset=utf-8">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body >

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Menu </div>
      <img src= "${user.fotoFacebook}">
      <div class="list-group list-group-flush">
		    <a href="fazerRequisicao.jsp" class="list-group-item list-group-item-action bg-light">Solicitar Tour</a>
		    <a href="historicoTours.jsp" class="list-group-item list-group-item-action bg-light">Histórico de Tours</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="virarGuia.jsp" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Esconder Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"></a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Dropdown
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
              	<a class="dropdown-item" href="#"></a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#"></a>
              </div>
            </li>
          </ul>
        </div>
      </nav>

      <div class="container-fluid">
        <form action ="${linkPasseioServlet}" method ="get">
        	<div class = "form-row">
	        	<div class ="col-md-12 col-lg-12" style = "text-align: center">  
	        		<h1 class="mt-4" >Solicitar Passeio</h1>
	        	</div>
	        	<div class = "col-md-2 col-lg-2"></div>
	        	<div class = "col-md-8 col-lg-8">
	        		<label style = "margin-top:15px;">Título do Passeio</label> 
	        		<input class ="form-control" name ="campoTitulo" placeholder="Ex: Passeio nas Cataratas">
	        	</div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-8 col-lg-8">
	        		<label style = "margin-top: 15px;">Descrição</label> 
	        	    <textarea class ="form-control" style="padding-bottom: 50px;" name ="campoDescricao"></textarea>
	        </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;"> 
	        		<label>Data Início</label>
	        	    <input class ="form-control" name ="campoDataInicio" type="date" style = "padding-right: 15px;" >
	        </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;">
	        	<label>Data Fim</label> 
	        	<input class ="form-control" name ="campoDataFim" type="date" style = "padding-right: 15px;">
	        </div>
	        <div class = " col-md-2 col-lg-2"></div> 
	        <div class = " col-md-2 col-lg-2"></div> 
	        <div class = "col-sm-4 col-md-2 col-lg-2" style="margin-top: 15px;">
	        	<label>País</label> 
	        	<select class ="form-control" name ="campoPais" >
		        	<option>Brasil</option>
		        	<option>Estados Unidos</option>
		        	<option>Canada</option>
	        	</select>
	        </div>
	        <div class = "col-sm-4 col-md-3 col-lg-3" style="margin-top: 15px;">
	        	<label>Estado</label> 
	        	<select class ="form-control" name ="campoEstado">
	        		<option>Parana</option>
	        		<option>Nova York</option>
	        		<option>Quebec</option>
	        	</select>
	        </div>
	        <div class = "col-sm-4 col-md-3 col-lg-3" style="margin-top: 15px;">
	        	<label>Cidade</label> 
	        	<select class="form-control" name ="campoCidade" > 
	        		<option value= "Foz do Iguacu">Foz do Iguacu</option>
	        		<option value = "Nova York">Nova York</option>
	        		<option value = "Quebec">Quebec</option>
	        	</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-md-2 col-lg-2"></div>
	         <div class = "col-sm-12 col-md-8 col-lg-8" style="margin-top: 15px;">
	        	    <label>Local de Encontro</label> 
	        	    <input class ="form-control" name ="campoComplemento" >
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <input type = "hidden" value = "${user.userID}" name = "idTuser">
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-sm-12 col-md-8 col-md-8" style= "text-align: center;" style="margin-top: 15px;">
	        	<h3>Tags</h3>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-sm-6 col-md-4" style="margin-top: 15px;"> 
	        	    <select class ="form-control">
						<option value="volvo">Um</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
					</select>
	        </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;"> 
	        	    <select class ="form-control">
						<option value="volvo">Dois</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
					</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-sm-12 col-md-8 col-lg-8"> 
	        	    <select class ="form-control" style="margin-top: 15px;">
						<option value="volvo">Tres</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
					</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <a href= "trabalhoEmpreendedorismo/index.jsp"><button class="button" type ="submit" style="margin-top: 20px; margin-left: 290px;"> Enviar </button></a>
	        <input type="hidden" name="acao" value ="cadastrarRequest">
	        
	        </div>
        	</form>		
        </div>
     </div>
        
	    </div>
    <!-- /#page-content-wrapper -->

  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/linkOfertaServlet" var="linkOfertaServlet"/>

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
	border-radius: 10px;	
}
  </style>
  <title>FE</title>

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
      <div class="list-group list-group-flush">
      	    <a href="#" class="list-group-item list-group-item-action bg-light">Tour Concluídos</a>
		    <a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
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
      <div class = "form-row">
		    	<div class = "form-group col-md-4" style = "margin: auto;">
		        	<h1 class="mt-4">Realizar um lance</h1>
		        		<div class="card center" style="width: 22rem; ">
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						       	<p class="card-text">${requisicao.description}</p>
						    	<a class="card-text">Data início: ${requisicao.startsAt}</a>
						    	<div class = "form-group cold-md-2"></div>
						    	<a  style= "margin-top: 5px;" >Data fim: ${requisicao.endsAt}</a>
					  		</div>
						</div>
						</div>
	  </div>
	</div>
	<form action="${linkHistoricoServlet}" method="get">
		<h3 class="mt-4" style="color: gray;text-align: center;">Preencha os dados para fazer uma oferta</h3>
		<div class="form-group col-md-4"></div>
		<div class="form-group col-md-4" style="margin: auto;">
			<label>Valor</label> 
			<input  class="form-control" name="campoValor">
		</div>

		<div class="form-group col-md-6"></div>
		<div class="form-group col-md-4" style="margin: auto;">
			<label>Observacao</label> 
			<input class="form-control" name="campoObservacao" style="padding-bottom: 100px;">
		</div>
		<div class = "form-grpup col-md-4"></div>
		<div class = "form-grpup col-md-4" style="margin: auto;">
			<button class="btn btn-primary center">Enviar</button>
		</div>
		<input type = hidden name = "campoId" value = "${requisicao.idRequest }">
		<input type = hidden name = "Acao" value = "enviarOferta">
	</form>

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
</div>
</body>

</html>

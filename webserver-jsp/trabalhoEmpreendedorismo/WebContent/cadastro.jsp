<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/loginServlet" var="linkLoginServlet"/>

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
  <meta http-equiv="Content-Type" content = "text/html;charset=utf-8">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body >

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->


      <div class="container-fluid">
        <h1 class="mt-4" style = "text-align: center">Criar Conta</h1>
        <form action ="${linkLoginServlet}" method ="get">
        	<div class = "form-row">
        		<div class = "form-group col-md-3"> </div>
	        	<div class = "form-group col-md-6">
	        		<label style = "margin-top:10px;">Email</label> 
	        		<input class ="form-control" name ="campoEmail">
	        	</div>
	        
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-6">
	        		<label>Senha</label> 
	        	    <input class ="form-control" name ="campoSenha">
	        </div>
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-6">
	        		<label>Repetir senha</label> 
	        	    <input class ="form-control" name ="campoRepetirSenha">
	        </div>
	       
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-6">
	        		<label>Nome Completo</label> 
	        	    <input class ="form-control" name ="campoNomeCompleto">
	        </div>
	        <div class = "form-group col-md-3"> </div>
	        <div class = "form-group col-md-3"> </div>
			<div class = "form-row">
		        <div class = "form-group col-md-2">
		        	<label>País</label> 
		        	<select class ="form-control" name ="campoPais"  style="width: auto;" >
			        	<option>Brasil</option>
			        	<option>Estados Unidos</option>
			        	<option>Canada</option>
		        	</select>
		        </div>
		        <div class = "form-group col-md-3"> </div>
		        <div class = "form-group col-md-2">
		        	<label >Estado</label> 
		        	<select class ="form-control" name ="campoEstado" style="width: auto;"  >
		        		<option>Parana</option>
		        		<option>Nova York</option>
		        		<option>Quebec</option>
		        	</select>
		        </div>
		        <div class = "form-group col-md-3"> </div>
		        <div class = "form-group col-md-2">
		        	<label >Cidade</label> 
		        	<select class="form-control" name ="campoCidade" style="width: auto;" > 
		        		<option value= "Foz do Iguacu">Foz do Iguacu</option>
		        		<option value = "Nova York">Nova York</option>
		        		<option value = "Quebec">Quebec</option>
		        	</select>
		        </div>
		        	
		    	<div class = "form-group col-md-4">
	        		<a href= "trabalhoEmpreendedorismo/index.jsp"><button class="btn btn-primary" type ="submit" > Registrar </button></a>
	        		<input type="hidden" name="Acao" value ="cadastrarUser">
	        	</div>
		    </div>      
	        </div>
        	</form>		
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

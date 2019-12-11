<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/virarGuiaServlet" var="linkVirarGuiaServlet"/>
<c:url value="/ofertaServlet" var="linkOfertaServlet"/>


<html lang="pt">

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
      <%@ include file = "menu.jsp" %>
	    <div id="page-content-wrapper">
	<%@ include file = "restoHeader.jsp" %>
	
      <div class="container-fluid">
      
      <!--<a style = "margin-left: 100px;"href="<c:url value='/ofertaServlet' />">Click here</a> -->	
      <div class = "form-group col-md-12 col-xs-12 col-sm-12 col-lg-12" style = "text-align: center;">
      	<h2 class="h2"style = "margin-bottom: 5%;">Tornar-se um guia</h2>
      </div>
      <form action="${linkVirarGuiaServlet}" method = get>
      <div class = "form-row">
      <div class = "col-12 col-sm-12 col-md-6 col-lg-6 col-xs-6">
		  <label >DDI</label>
		  <input style = "margin-bottom: 10px;" class = "form-control" name = "campoDDI"  type="number" required  min = "0" max = "99">
      </div>
      <div class = "col-12 col-sm-12 col-md-6 col-lg-6 col-xs-6">
		  <label>DDD</label>
		  <input style = "margin-bottom: 10px;" class = "form-control" name = "campoDDD"  type="number" required min = "0" max = "99" >	      	
      </div>
      <div class = "col-sm-12 col-12 col-md-6 col-lg-6 col-xs-6">
		  <label>Número de celular</label>
      	  <input style = "margin-bottom: 10px;" class = "form-control" name = "campoNumeroTelefone"  type="number" required min = "0" max = "999999999" placeholder = "(Apenas Números)">
      </div>
      <div class = "col-12 col-sm-12 col-md-6 col-lg-6 col-xs-6">
		  <label>Email do Paypal</label>
      	  <input class = "form-control" name = "campoEmailPaypal" type = "email" required minlength="6" maxlength = "35">
      </div>
      <div class = "col-md-12 col-xs-12 col-sm-12 col-lg-12" style = "text-align: center;">
	      <button type = "submit" class ="btn btn-primary" style=" margin-top: 10px;">Virar Guia</button>
	      <input type = "hidden" name = "Acao" value ="VirarGuia">
      </div>	
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

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/passeioServlet" var="linkPasseioServlet"/>
<c:url value = "/ofertaServlet" var = "linkOfertaServlet"/>

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

      <%@ include file = "menu.jsp" %>
	    <div id="page-content-wrapper">
	<%@ include file = "restoHeader.jsp" %>
                       
      <div class="container-fluid">
      <div class = "form-row">
	      	<div class = "form-group col-md-12 col-lg-12">
	      	<h1 class="mt-4" style = "text-align: center">Escolher oferta</h1>
	      	</div>
	      	<div class = "form-group col-md-2 col-lg-2"></div>
	      	<div class = "form-group col-md-8 col-lg-8" style = "text-align: center;">
		        		<div class="card">
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						    	<div class = "col-12 col-sm-12 col-md-12 col-lg-12">
						       		<p class="card-text">${requisicao.description}</p>
						       	</div>
						       	<div class = "form-row">
							       	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: right;  ">
							    		<a class="card-text" style = "font-weight: bold;">Data início:</a>
							    		${requisicao.startsAt}
							    	</div>
							    	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: left;">
							    		<a class="card-text" style= "margin-top: 5px; font-weight: bold;">Data fim: </a>
							    		${requisicao.endsAt}
							    	</div>
						    	</div>
						    	<div class = "col-md-12 col-lg-12">
							    	<c:forEach items = "${requisicao.tags}" var= "tag">
							    		<label style = "color: blue; margin-right: 3px;">#${tag.nome}</label>
							    	</c:forEach>
						    	</div>
					  		</div>
						</div>	       		
		        </div>
			<div class = "form-group col-md-2 col-lg-2"></div>	     
		    <div class = "form-group col-md-12 col-lg-12" style= "text-align: center;">
		     	<form action="${linkOfertaServlet}" method = "get">
		      		<button class = "btn btn-primary" type = "submit">Buscar ofertas</button>
		      		<input type = "hidden" name = "Acao" value = "buscarOfertas">
					<input name = "campoIdRequisicao" value="${requisicao.idRequest}" type = "hidden">			    	
		     	</form>
		     </div>
	     </div>
	    	<c:if test = "${empty ofertas}">
	     		<div class = "col-md-12 col-lg-12" style="text-align: center;">
		     		<div class="alert alert-info" role="alert">
	  					<p style = "text-align: center;">Não há nenhuma oferta feita no momento!</p>
					</div>
				</div>
	     	</c:if>
	     	<div class = "form-row">
	     	<c:forEach items="${ofertas}" var = "oferta">
	     	<div class ="form-group col-md-6 col-lg-6">
	     		<form action ="${linkOfertaServlet}" method ="get">
		        	    <div class="card">
					  	    <div class="card-body">
						  	    	<div class = "form-row">
						  	    	<div class = "col-lg-12">
							        	<h5 class="card-title" style="text-align: center;">R$ ${oferta.valor}</h5>
							       	</div>
							       	<div class = "col-md-12 col-lg-12" style="margin-bottom: 10px; ">
							       		<a class="card-text"  style="text-align:left; font-weight: bold;">Obs:</a>
							       		${oferta.description}
							    	</div>
							    	<div class = "col-lg-12" style = "background-color: #DCDCDC ">
							    	    <div class ="form-row">
								    		<div class = "col-2 col-sm-2 col-lg-2 col-md-2">
								    			<img src= "${oferta.user.fotoFacebook}" class ="rounded-circle" style="height: 70px; margin-top: 5px;">
								    		</div>
								    		<div class = "col-4 col-sm-4 col-md-4 col-lg-4">
							    				<p style ="margin-top: 25px">${oferta.user.name}</p>
							    			</div>
									    	<div class = "col-4 col-sm-4 col-md-4 col-lg-4" style = "margin-top: 25px; ">
									    		<a style= "font-weight: bold;">Avaliação:</a>
									    		5.0/5.0
									    	</div>	
							    		</div>
							    	</div>
							    	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: right;">
							    		<button class ="btn btn-primary" type = "submit" style = "margin-top: 10px;">Aceitar</button>
							    	</div>
							    	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: left;">
							    		<button class ="btn btn-danger" type = "submit" style = "margin-top: 10px;">Recusar</button>
							    	</div>
							    	<input type = "hidden" name = "Acao" value = "verOferta">
							    	<input name = "campoId" value="${oferta.idOferta}" type = "hidden">
					  			</div>
					  		</div>
						</div>
						</form>	
						</div>        	       	
		 	</c:forEach>
		 	</div>
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

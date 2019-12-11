<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/passeioServlet" var="linkPasseioServlet"/>
<c:url value = "/ofertaServlet" var = "linkOfertaServlet"/>

<html lang="en">

<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Ensures optimal rendering on mobile devices. -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge" /> <!-- Optimal Internet Explorer compatibility -->
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
	      	<div class = "form-group col-sm-12 col-md-12 col-lg-12">
	      	<c:if test = "${tour.isTerminado == 0}">
	      		<h1 class="mt-4" style = "text-align: center">Tour em Progresso</h1>
	      	</c:if>
	      	<c:if test = "${tour.isTerminado == 1}">
	      		<h1 class="mt-4" style = "text-align: center">Tour Finalizado!</h1>
	      	</c:if>
	      	</div>
	      	<div class = "form-group col-md-2 col-lg-2"></div>
	      	<div class = "form-group col-md-8 col-lg-8" style = "text-align: center;">
		        		<div class="card">
					  		<div class="card-body">
						    	<h5 class="card-title">${tour.requisicao.title}</h5>
						    	<div class = "col-12 col-sm-12 col-md-12 col-lg-12">
						       		<p class="card-text">${tour.requisicao.description}</p>
						       	</div>
						       	<div class = "form-row">
							       	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: right;  ">
							    		<a class="card-text" style = "font-weight: bold;">Data início:</a>
							    		${tour.requisicao.startsAt}
							    	</div>
							    	<div class = "col-6 col-sm-6 col-md-6 col-lg-6" style = "text-align: left;">
							    		<a class="card-text" style= "margin-top: 5px; font-weight: bold;">Data fim: </a>
							    		${tour.requisicao.endsAt}
							    	</div>
						    	</div>
						    	<div class = "col-md-12 col-lg-12">
							    	<c:forEach items = "${tour.requisicao.tags}" var= "tag">
							    		<label style = "color: blue; margin-right: 3px;">#${tag.nome}</label>
							    	</c:forEach>
						    	</div>
					  		</div>
						</div>	       		
		        </div>
			<div class = "form-group col-md-2 col-lg-2"></div>	     
		    <div class = "form-group col-md-12 col-lg-12" style= "text-align: center;">
		    <c:if test="${tour.isTerminado == 0}">
		      		<button onClick = "finalizarTour()" class = "btn btn-primary" type = "submit" id ="fim">Finalizar Tour</button>			    	
		     </c:if>
		     <c:if test="${tour.isTerminado == 1}">
		     	<c:if test="${usuarioLogado.isGuide == 0}">
		      		<button onClick = "finalizarTour()" class = "btn btn-primary" type = "submit" id ="fim">Avaliar Guia</button>			    	
		     	</c:if>
		     	<c:if test="${usuarioLogado.isGuide == 1}">
		      		<button onClick = "finalizarTour()" class = "btn btn-primary" type = "submit" id ="fim">Avaliar Turista</button>			    	
		     	</c:if>
		     </c:if>
		     </div>
	     </div>
	     	<div class = "form-row">
	     	<div class ="form-group col-md-12 col-lg-12">
		        	    <div class="card">
					  	    <div class="card-body">
					  	    		<div class = "form-row">
						  	    	<div class = "col-lg-12">
							        	<h5 class="card-title" style="text-align: center;" >R$ ${tour.oferta.valor}</h5>
							       		<input type="hidden" id = "campoValor" value = "${tour.oferta.valor}">
							       	</div>
							       	<div class = "col-md-12 col-lg-12" style="margin-bottom: 10px; ">
							       		<a class="card-text"  style="text-align:left; font-weight: bold;">Obs:</a>
							       		${tour.oferta.description}
							    	</div>
							    	<div class = "col-lg-12" style = "background-color: #DCDCDC ">
							    	    <div class ="form-row">
								    		<div class = "col-2 col-sm-2 col-lg-2 col-md-2">
								    			<img src= "${tour.oferta.user.fotoFacebook}" class ="rounded-circle" style="height: 70px; margin-top: 5px;">
								    		</div>
								    		<div class = "col-4 col-sm-4 col-md-3 col-lg-3">
							    				<p style ="margin-top: 25px">${tour.oferta.user.name}</p>
							    			</div>
									    	<div class = "col-4 col-sm-4 col-md-4 col-lg-4" style = "margin-top: 25px; ">
									    		<a style= "font-weight: bold;">Avaliação:</a>
									    		5.0/5.0
									    	</div>
									    	<!-- <i class="fa fa-whatsapp" aria-hidden="true"></i> -->
									    	<a href="${tour.guia.linkWhats}"><i class="fa fa-whatsapp fa-2x" style = "margin-top: 25px; font-size = 48px;"></i></a>	
							    		</div>
							    	</div>
					  			</div>
					  		</div>
						</div>
						</div>        	       	
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
  function finalizarTour(){
		var txt;
		var resultado = prompt("Avalie a pessoa com a qual você fez tour");
		if(resultado == null || resultado == "")
			resultado = finalizarTour();
		else{
			location.replace("https://26e94682.ngrok.io/trabalhoEmpreendedorismo/pagamentoServlet?Acao=finalizar");
		}
			
  }

  
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

 

</html>

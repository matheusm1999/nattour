<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/historicoServlet" var="linkHistoricoServlet"/>
<c:url value="/ofertaServlet" var="linkOfertaServlet"/>
<c:url value="/pagamentoServlet" var="linkPagametoServlet"/>

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
      
      <div class="container">
      <div class = "form-row">
      <!--<a style = "margin-left: 100px;"href="<c:url value='/ofertaServlet' />">Click here</a> -->	
      
      <div class ="form-group col-md-12 col-lg-12 col-xl-12">
      <h2 class="h2" style="text-align: center;">Verificar Ofertas Feitas</h2>
      </div>
      
      <div class ="form-group col-sm-12 col-md-12 col-lg-12" style = "text-align: center;">
	      	<form action="${linkHistoricoServlet}" method = get>
	      		<button type = "submit" class ="btn btn-primary" style="margin-bottom: 30px;">Atualizar histórico</button>
	      		<input type = "hidden" name = "Acao" value ="buscarOfertasFeitas">
	      	</form>
      	</div>
      	<c:forEach items="${requisicoes}" var = "requisicao">
      		<c:choose>
      			<c:when test = "${requisicao.isPago == 1}">
      				<div class = "form-group col-sm-12 col-md-6 col-lg-4" style = "text-align: center;">
		        	<form action ="${linkPagametoServlet}" method ="get">
		        		<div class="card" style = "background-color: #98FB98;">
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						       	<p class="card-text">${requisicao.description}</p>
						    	<a class="card-text">Data início: ${requisicao.startsAt}</a>
						    	<div class = "form-group cold-md-2 col-lg-2"></div>
						    	<a  style= "margin-top: 5px;" >Data fim: ${requisicao.endsAt}</a>
						    	<div class = "col-lg-12" style = "background-color: #DCDCDC ">
							    	    <div class ="form-row">
								    		<div class = "col-2 col-sm-2 col-lg-2 col-md-2">
								    			<img src= "${requisicao.user.fotoFacebook}" class ="rounded-circle" style="height: 70px; margin-top: 5px;">
								    		</div>
								    		<div class = "col-4 col-sm-4 col-md-4 col-lg-4">
							    				<p style ="margin-top: 25px">${requisicao.user.name}</p>
							    			</div>
									    	<div class = "col-4 col-sm-4 col-md-4 col-lg-4" style = "margin-top: 25px; ">
									    		<a style= "font-weight: bold;">Avaliação:</a>
									    		5.0/5.0
									    	</div>	
							    		</div>
							    	</div>
						    	<div class = "col-md-12 col-lg-12" >
							    	<c:forEach items = "${requisicao.tags}" var= "tag">
							    		<label style = "color: blue;">#${tag.nome}</label>
							    	</c:forEach>
						    	</div>
						    	<button class ="btn btn-link" type = "submit">Ver</button>
						    	<input type = "hidden" name = "Acao" value = "verTourEmProgresso">
						    	<input name = "campoIdRequisicao" value="${requisicao.idRequest}" type = "hidden">
						    	<!--<a href="/trabalhoEmpreendedorismo/fazerOferta.jsp">Ver</a>-->
						    	<!-- <input name = "campoId">${requisicao.idRequest} -->
					  		</div>
						</div>    
		        	</form>		
		        </div>
      			</c:when>
      			<c:otherwise>
      				<div class = "form-group col-sm-12 col-md-6 col-lg-4" style = "text-align: center;">
		        	<form action ="${linkOfertaServlet}" method ="get">
		        		<div class="card" >
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						       	<p class="card-text">${requisicao.description}</p>
						    	<a class="card-text">Data início: ${requisicao.startsAt}</a>
						    	<div class = "form-group cold-md-2 col-lg-2"></div>
						    	<a  style= "margin-top: 5px;" >Data fim: ${requisicao.endsAt}</a>
						    	<div class = "col-lg-12" style = "background-color: #DCDCDC ">
							    	    <div class ="form-row">
								    		<div class = "col-2 col-sm-2 col-lg-2 col-md-2">
								    			<img src= "${requisicao.user.fotoFacebook}" class ="rounded-circle" style="height: 70px; margin-top: 5px;">
								    		</div>
								    		<div class = "col-4 col-sm-4 col-md-4 col-lg-4">
							    				<p style ="margin-top: 25px">${requisicao.user.name}</p>
							    			</div>
									    	<div class = "col-4 col-sm-4 col-md-4 col-lg-4" style = "margin-top: 25px; ">
									    		<a style= "font-weight: bold;">Avaliação:</a>
									    		5.0/5.0
									    	</div>	
							    		</div>
							    	</div>
						    	<div class = "col-md-12 col-lg-12" >
							    	<c:forEach items = "${requisicao.tags}" var= "tag">
							    		<label style = "color: blue;">#${tag.nome}</label>
							    	</c:forEach>
						    	</div>
						    	<button class ="btn btn-link" type = "submit">Ver</button>
						    	<input type = "hidden" name = "Acao" value = "buscarOfertas">
						    	<input name = "campoIdRequisicao" value="${requisicao.idRequest}" type = "hidden">
						    	<!--<a href="/trabalhoEmpreendedorismo/fazerOferta.jsp">Ver</a>-->
						    	<!-- <input name = "campoId">${requisicao.idRequest} -->
					  		</div>
						</div>    
		        	</form>		
		        </div>
      			</c:otherwise>
		    </c:choose>	
		    	
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

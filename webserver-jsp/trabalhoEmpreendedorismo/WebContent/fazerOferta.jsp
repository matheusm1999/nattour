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
	<%@ include file = "menu.jsp" %>
	    <div id="page-content-wrapper">
	<%@ include file = "restoHeader.jsp" %>
    <!-- Page Content 

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
-->
      <div class="container-fluid">
      <div class = "form-row"> 
      	  <div class ="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" style = "text-align: center;">	
	          <h1 class="mt-4">Realizar um lance</h1>
	      </div>
	      <div class ="col-md-2 col-lg-2 col-xl-2"></div>	
	   	  <div class = "form-group col-md-8 col-lg-8 col-xl-8" style = "text-align: center;">
		        		<div class="card">
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						    	<div class = "col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						       		<p class="card-text">${requisicao.description}</p>
						       	</div>
						       	<div class = "form-row">
							       	<div class = "col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" style = "text-align: right;  ">
							    		<a class="card-text" style = "font-weight: bold;">Data início:</a>
							    		${requisicao.startsAt}
							    	</div>
							    	<div class = "col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6" style = "text-align: left;">
							    		<a class="card-text" style= "margin-top: 5px; font-weight: bold;">Data fim: </a>
							    		${requisicao.endsAt}
							    	</div>
						    	</div>
						    	<div class = "col-lg-12" style = "background-color: #DCDCDC ">
							    	<div class ="form-row">
							    		<div class = "col-2 col-sm-2 col-lg-2 col-md-3" style = "margin-bottom: 5px;">
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
						    	<div class = "col-md-12 col-lg-12">
							    	<c:forEach items = "${requisicao.tags}" var= "tag">
							    		<label style = "color: blue; margin-right: 3px;">#${tag.nome}</label>
							    	</c:forEach>
						    	</div>
					  		</div>
						</div>	       		
		        </div>
		        </div>
		        <div class ="col-md-2 col-lg-2 col-xl-2"></div>
			        <form action="${linkHistoricoServlet}" method="get">
			        	<div class ="form-row col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
							<div class ="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" style = "text-align: center;">			        
								<h3 class="mt-4" style="color: gray;">Preencha os dados para fazer uma oferta</h3>
							</div>
							<div class="col-md-2 col-lg-2 col-xl-2"></div>
							<div class="form-group col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
								<label>Valor</label> 
								<input  class="form-control" name="campoValor">
							</div>
							<div class="col-md-2 col-lg-2 col-xl-2"></div>
							<div class="form-group col-md-2 col-lg-2 col-xl-2"></div>
							<div class="form-group col-12 col-sm-12 col-md-8 col-lg-8">
								<label>Observacao</label> 
								<textarea class="form-control" name="campoObservacao" style="padding-bottom: 50px;"></textarea>
							</div>
							<div class="form-group col-md-2 col-lg-2"></div>
							<div class = "col-12 col-sm-12 col-md-12 col-lg-12" style = "text-align: center; margin-bottom: 10px;">
								<button class="btn btn-primary center">Enviar</button>
							</div>
							<input type = hidden name = "campoId" value = "${requisicao.idRequest }">
							<input type = hidden name = "Acao" value = "enviarOferta">
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

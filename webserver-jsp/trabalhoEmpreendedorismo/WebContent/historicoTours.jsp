<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/historicoServlet" var="linkHistoricoServlet"/>
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
    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Menu </div>
      <div class="list-group list-group-flush">
      	    <a href="Turista/fazerRequisicao.jsp" class="list-group-item list-group-item-action bg-light">Solicitar Tour</a>
      	    <a href="Turista/historicoTours.jsp" class="list-group-item list-group-item-action bg-light">Histórico de tours</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="Tuista/virarGuia.jsp" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
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
            <!--  
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
            -->
          </ul>
        </div>
      </nav>

      <div class="container-fluid">
      <div class = "form-row">
      <!--<a style = "margin-left: 100px;"href="<c:url value='/ofertaServlet' />">Click here</a> -->	
      
      <div class ="form-group col-md-4"></div>
      <h2 class="h2"style = "text-align: center">Verificar histórico de tours</h2>
      <div class ="form-group col-md-5"></div>
      <div style = "text-align: center; ">
      	<form action="${linkHistoricoServlet}" method = get>
      		<button type = "submit" class ="btn btn-primary" style=" margin-bottom: 30px;">Atualizar histórico</button>
      		<input type = "hidden" name = "Acao" value ="buscarHistoricoTour">
      	</form>
      	</div>
      	<div class = "form-group col-md-2"></div>
      	<c:forEach items="${requisicoes}" var = "requisicao">
		    	<div class = "form-group col-md-4">
		        	<form action ="${linkOfertaServlet}" method ="get">
		        		<div class="card" style="width: 18rem;margin-left:60px;">
					  		<div class="card-body">
						    	<h5 class="card-title">${requisicao.title}</h5>
						       	<p class="card-text">${requisicao.description}</p>
						    	<a class="card-text">Data início: ${requisicao.startsAt}</a>
						    	<div class = "form-group cold-md-2"></div>
						    	<a  style= "margin-top: 5px;" >Data fim: ${requisicao.endsAt}</a>
						    	<button class ="btn btn-link" type = "submit" style="margin-left: 90px;">Ver</button>
						    	<input type = "hidden" name = "Acao" value = "buscarOfertas">
						    	<input name = "campoIdRequisicao" value="${requisicao.idRequest}" type = "hidden">
						    	<!--<a href="/trabalhoEmpreendedorismo/fazerOferta.jsp">Ver</a>-->
						    	<!-- <input name = "campoId">${requisicao.idRequest} -->
					  		</div>
						</div>	
						<div class = "form-group col-md-4"></div>       
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

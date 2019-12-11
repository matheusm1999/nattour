<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value="/passeioServlet" var="linkPasseioServlet"/>

<html lang="pt-br">

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

    <%@ include file = "menu.jsp" %>
	    <div id="page-content-wrapper">
	<%@ include file = "restoHeader.jsp" %>
      <div class="container-fluid">
      <c:if test="${resultado == 0}">
      	<div class="alert alert-danger col-sm-12 col-md-12 col-lg-12 col-xl-12" role="alert">
			 Um erro no formulário ocorreu por um ou mais desses motivos:
			 <ul>
				 <li>Data de Início e Fim não estão no mesmo dia, mês e ano</li>
				 <li>Data de Início ou Fim são maiores ou menores que o dia atual</li>
				 <li>Horário de Início e ou Fim são menores que o horário atual </li>
				 <li>Passeio tem mais de 4 horas de duração</li>
				 <li>Tour já foi solicitado</li>
			</ul>
		</div>
      </c:if>
        <form action ="${linkPasseioServlet}" method ="get">
        	<div class = "form-row">
	        	<div class ="col-md-12 col-lg-12" style = "text-align: center">  
	        		<h1 class="mt-4" >Solicitar Passeio</h1>
	        	</div>
	        	<div class = "col-md-2 col-lg-2"></div>
	        	<div class = "col-md-8 col-lg-8">
	        		<label style = "margin-top:15px;">Título do Passeio</label> 
	        		<input class ="form-control" name ="campoTitulo" placeholder="Ex: Passeio nas Cataratas" required minlength="6" maxlength="40" type = "text">
	        	</div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-8 col-lg-8">
	        		<label style = "margin-top: 15px;">Descrição</label> 
	        	    <textarea class ="form-control" style="padding-bottom: 50px;" name ="campoDescricao" required  minlength="6" maxlength="200" required></textarea>
	        </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;"> 
	        		<label>Data Início</label>
	        	    <input class ="form-control" name ="campoDataInicio" type="date" style = "padding-right: 15px;" required>
	        </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;">
	        	<label>Data Fim</label> 
	        	<input class ="form-control" name ="campoDataFim" type="date" style = "padding-right: 15px;" required>
	        </div>
	        <div class = " col-md-2 col-lg-2"></div> 
	        <div class = " col-md-2 col-lg-2"></div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;"> 
	        		<label>Horário Início</label>
	        	    <input input type = "time" class ="form-control" name ="campoHorarioInicio" type="date" style = "padding-right: 15px;" required>
	        </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;">
	        	<label>Horário Fim</label> 
	        	<input type = "time" class ="form-control" name ="campoHorarioFim" type="date" style = "padding-right: 15px;" required>
	        </div>
	        <div class = "col-md-2 col-lg-2"> </div>
	        <div class = "col-md-2 col-lg-2"> </div> 
	        <div class = "col-sm-4 col-md-2 col-lg-2" style="margin-top: 15px;">
	        	<label>País</label> 
	        	<select class ="form-control" name ="campoPais" >
		        	<option value = "Brasil">Brasil</option>
	        	</select>
	        </div>
	        <div class = "col-sm-4 col-md-3 col-lg-3" style="margin-top: 15px;">
	        	<label>Estado</label> 
	        	<select class ="form-control" name ="campoEstado">
	        		<option value = "Parana">Paraná</option>
	        	</select>
	        </div>
	        <div class = "col-sm-4 col-md-3 col-lg-3" style="margin-top: 15px;">
	        	<label>Cidade</label> 
	        	<select class="form-control" name ="campoCidade" > 
	        		<option value= "Foz do Iguacu">Foz do Iguaçu</option>
	        		<option value = "Curitiba">Curitiba</option>
	        		<option value = "Pato Branco">Pato Branco</option>
	        		<option value = "Londrina">Londrina</option>
	        	</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-md-2 col-lg-2"></div>
	         <div class = "col-sm-12 col-md-8 col-lg-8" style="margin-top: 15px;">
	        	    <label>Local de Encontro</label> 
	        	    <input class ="form-control" name ="campoComplemento" placeholder = "Ex: Rua A, Hotel XYZ" required>
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
	        	    <select name = "tag1" class ="form-control">
						<c:forEach items="${tags}" var = "tag">
							<option value="${tag.id}">${tag.nome}</option>
						</c:forEach>
					</select>
	        </div>
	        <div class = "col-sm-6 col-md-4 col-lg-4" style="margin-top: 15px;"> 
	        	    <select name = "tag2"class ="form-control">
						<c:forEach items="${tags}" var = "tag">
							<option value="${tag.id}">${tag.nome}</option>
						</c:forEach>
					</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-sm-12 col-md-8 col-lg-8"> 
	        	    <select name = "tag3" class ="form-control" style="margin-top: 15px;">
						<c:forEach items="${tags}" var = "tag">
							<option value="${tag.id}">${tag.nome}</option>
						</c:forEach>
					</select>
	        </div>
	        <div class = "col-md-2 col-lg-2"></div>
	        <div class = "col-sm-12 col-md-12 col-lg-12" style = "text-align: center;">
		        <a href= "trabalhoEmpreendedorismo/index.jsp"><button class="btn btn-primary" type ="submit" style="margin-top: 20px;"> Enviar </button></a>
		        <input type="hidden" name="acao" value ="cadastrarRequest">
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

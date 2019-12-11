<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="java.net.URLEncoder" %>

<c:url value="/PostServlet" var="linkLoginServlet"/>

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

<body style = "background-image: url(https://images.unsplash.com/photo-1511576661531-b34d7da5d0bb?ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80)"> 
<!-- style = "background-image: url(https://images.unsplash.com/photo-1541343672885-9be56236302a?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60.png); height: 100%; background-repeat: no-repeat; background-position: center; background-size : cover;"> -->


  <div class="d-flex" id="wrapper">
    <!-- Sidebar 
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Menu </div>
      <div class="list-group list-group-flush">
		    <a href="#" class="list-group-item list-group-item-action bg-light">Tours concluídos</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </div>
    </div>
    -->
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

<!--  
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
	--->
	
      <div class="container-fluid">
      
		<div class = "col-lg-12 col-md-12 col-sm-12" style = "text-align: center;">      
        	<h1 class="mt-4">Nattour</h1>
        </div>
         
	<br><br>
	<div id="status">
	</div>
        	 <img src=" " class = "img-fluid"> 
        		<div class = "form-group col-sm-12 col-md-12 col-lg-12" style = "text-align: center;"> 
	        		<button id = "custom-login-button" class = "btn btn-dark" style = "margin-top: 80px;">Login com Facebook</button>
	        	</div>
	        		<input type = "hidden" class ="form-control" id = "campoLogin" name ="campoLogin">	 
	        	    <input type = "hidden" id = "campoSenha" class ="form-control" name ="campoSenha">
	        		<input id = "campoFoto" name = "campoFoto" type = "hidden">
	        		<input id = "campoEmail" name = "campoSenha" type = "hidden">
	        </div>
	        </div>
	        <!-- <fb:login-button size="large" scope="public_profile,email" returnscopes="true" onlogin="getLoginState();">Login to Facebook</fb:login-button> -->
	        	        	  
	        
	        <input type = hidden name = "Acao" value = "logar">
	        </div> 		
    <!-- /#page-content-wrapper -->

  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script>	

/*
function logIn() {
	FB.login(function (response) {
		//console.log(response);
		if(response.status == "connected"){
			person.userID = response.authResponse.userID;
		    person.acessToken = response.authResponse.acessToken;	
			FB.api('https://79eb783e.ngrok.io/trabalhoEmpreendedorismo/login.jsp', function(userData){
					console.log(userData);
				});
			}
	}, {scope: 'public_profile,email'})
  }

*/
window.fbAsyncInit = function() {
    FB.init({
      appId      : '606447636762500',
      cookie     : true,
      xfbml      : true,
      version    : 'v5.0'
    });

    /*//Descomentar essas linhas
    FB.getLoginStatus(function (response) {
    	statusChangeCallback(response);
	});
	*/  
    //FB.AppEvents.logPageView();   
        
  }

function statusChangeCallback(response){
	//alert("MEnsagem");
	console.log('Verificando o status...');
	console.log(response);

	//Se a pessoa logar
	if(response.status === 'connected'){
		console.log("conectado");
		document.getElementById('status').innerHTML = 'connected';
		document.getElementById('campoLogin').setAttribute('value',response.authResponse.userID);
		//document.getElementById('campoEmail').setAttribute('value',response.authResponse.email);
				
		//location.replace("https://11462cd0.ngrok.io/trabalhoEmpreendedorismo/");
								
		//Mostro os dados dessa pessoa
		
		FB.api('/me',{fields: 'id,email,name,picture'},function(response){
				console.log('Login com sucesso para: ' + response.name);
				document.getElementById('status').innerHTML = 'Obrigrado por logar, ' + response.name + '!';
				document.getElementById('campoSenha').value = response.name;
				var email = document.getElementById('campoEmail').value;
				var id = document.getElementById('campoLogin').value;
				var image = "http://graph.facebook.com/" + response.id + "/picture?type=normal"
				document.getElementById('campoFoto').value = image;
				//document.getElementById('status').innerHTML = "<img src= " + image + ">";
				location.replace("https://66691ce5.ngrok.io/trabalhoEmpreendedorismo/loginServlet?Acao=loginFB&campoNome="+response.name+"&campoID=" + id + "&campoFoto=" + image+"&campoEmail=" + response.email);
				//document.getElementById('status').innerHTML = response.authResponse.userID;
				});	
		//var nome = document.getElementById('campoSenha').value;
		//console.log(nome);
		
		//FB.api('/me/picture','GET',{},function(response) {
				//document.getElementById('status').innerHTML = "<img src= " + response.data.url + ">";	
		//});
	}else {
		document.getElementById('status').innerHTML = 'Por favor, faça o login!';
		/*
		FB.login(function (response){
			console.log(response);
		}, {
				scope: 'email',
				return_scopes: true			
			});
		*/
	}	
}



var el = document.getElementById('custom-login-button');
if(el){
  //el.addEventListener('click', swapper, false);
  el.addEventListener('click', function(){
	  console.log("Botao apertado");
	   FB.login(function(response){
				console.log("login customizado Iniciado");
				//console.log(response);
				//statusChangeCallback(response);
				
				FB.login(function(response){
					//alert("MEnsagem");
					console.log('Verificando o status...');
					console.log(response);

					//Se a pessoa logar
					if(response.status === 'connected'){
						
						document.getElementById('status').innerHTML = 'connected';
						document.getElementById('campoLogin').setAttribute('value',response.authResponse.userID);
						//document.getElementById('campoEmail').setAttribute('value',response.authResponse.email);
								
						//location.replace("https://11462cd0.ngrok.io/trabalhoEmpreendedorismo/");
												
						//Mostro os dados dessa pessoa
						
						FB.api('/me',{fields: 'id,email,name,picture'},function(response){
								console.log('Login com sucesso para: ' + response.name);
								document.getElementById('status').innerHTML = 'Obrigrado por logar, ' + response.name + '!';
								document.getElementById('campoSenha').value = response.name;
								var email = document.getElementById('campoEmail').value;
								var id = document.getElementById('campoLogin').value;
								var image = "http://graph.facebook.com/" + response.id + "/picture?type=normal"
								document.getElementById('campoFoto').value = image;
								document.getElementById('status').innerHTML = "<img src= " + image + ">";
								location.replace("https://26e94682.ngrok.io/trabalhoEmpreendedorismo/loginServlet?Acao=loginFB&campoNome="+response.name+"&campoID=" + id + "&campoFoto=" + image+"&campoEmail=" + response.email);
								//document.getElementById('status').innerHTML = response.authResponse.userID;
								});	
						//var nome = document.getElementById('campoSenha').value;
						//console.log(nome);
						
						//FB.api('/me/picture','GET',{},function(response) {
								//document.getElementById('status').innerHTML = "<img src= " + response.data.url + ">";	
						//});
					}else 
						document.getElementById('status').innerHTML = 'Por favor, faça o login!';
						/*
						FB.login(function (response){
							console.log(response);
						}, {
								scope: 'email',
								return_scopes: true			
							});
						
					}	
								},{scope: 'public_profile,email'}); 
  });
  */
 
			/*
				FB.api('/me','get',{fields: 'id,name,email,picture'}, function(response){
					console.log("resposta");
					console.log(response);
	          });
		  },
		    {
				scope: 'email,picture',
				return_scopes: true
		  });
	});
	*/			  
});
	});
	});
	}

  
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) return;
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));


</script> 
</body>
</html>

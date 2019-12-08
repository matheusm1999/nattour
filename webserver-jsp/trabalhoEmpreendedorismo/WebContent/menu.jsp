<!-- Sidebar -->
<c:if test="${usuarioLogado == null}">
	<c:redirect url="/login.jsp"/>
</c:if> 	
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Menu </div>
      <img src= "${usuarioLogado.fotoFacebook}">
      <div class="list-group list-group-flush">
      <c:if test="${usuarioLogado.isTourist == 1}">
      	    <a href="passeioServlet?acao=inicio" class="list-group-item list-group-item-action bg-light">Solicitar Tour</a>
      	    <a href="historicoTours.jsp" class="list-group-item list-group-item-action bg-light">Hist�rico de tours</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="virarGuia.jsp" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
            <a href="login.jsp" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </c:if>
	  <c:if test="${usuarioLogado.isGuide == 1}">
      	    <a href="#" class="list-group-item list-group-item-action bg-light">Tour Conclu�dos</a>
      	    <a href="selecionarRequisicao.jsp" class="list-group-item list-group-item-action bg-light">Buscar Requisi��es</a>
		    <a href="#" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
			<a href="#" class="list-group-item list-group-item-action bg-light">Chat</a>
            <a href="login.jsp" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </c:if>
	  </div>
    </div>
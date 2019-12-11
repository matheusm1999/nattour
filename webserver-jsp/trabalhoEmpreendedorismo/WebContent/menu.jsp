<!-- Sidebar -->
<c:if test="${usuarioLogado == null}">
	<c:redirect url="/login.jsp"/>
</c:if> 	
    <div class="bg-light border-right" id="sidebar-wrapper">
      <img src= "${usuarioLogado.fotoFacebook}" class ="rounded-circle">
      ${usuarioLogado.name}
      <div class="sidebar-heading">Menu </div>
      <div class="list-group list-group-flush">
      <c:if test="${usuarioLogado.isTourist == 1}">
      	    <a href="passeioServlet?acao=inicio" class="list-group-item list-group-item-action bg-light">Solicitar Tour</a>
      	    <a href="historicoServlet?Acao=buscarHistoricoTour" class="list-group-item list-group-item-action bg-light">Histórico de tours</a>
			<a href="pagamentoServlet?Acao=verTourEmProgresso" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="virarGuia.jsp" class="list-group-item list-group-item-action bg-light">Torne-se um guia</a>
            <a href="login.jsp" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </c:if>
	  <c:if test="${usuarioLogado.isGuide == 1}">
      	    <a href="#" class="list-group-item list-group-item-action bg-light">Tour Concluídos</a>
      	    <a href="selecionarRequisicao.jsp" class="list-group-item list-group-item-action bg-light">Buscar Requisições</a>
		    <a href="ofertasFeitas.jsp" class="list-group-item list-group-item-action bg-light">Ofertas Feitas</a>
		    <a href="pagamentoServlet?Acao=verTourEmProgresso" class="list-group-item list-group-item-action bg-light">Tour em progresso</a>
            <a href="login.jsp" class="list-group-item list-group-item-action bg-light">Sair</a>
	  </c:if>
	  </div>
    </div>

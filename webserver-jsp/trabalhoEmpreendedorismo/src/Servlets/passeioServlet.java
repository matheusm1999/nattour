package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.Cidade;
import BO.Requisicao;
import BO.User;
import Servicos.RequisicaoServicos;
import Servicos.tagServicos;
import BO.Tag;

/**
 * Servlet implementation class passeioServlet
 */
@WebServlet("/passeioServlet")
public class passeioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public passeioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("acao");
		System.out.println("ParamAcao  "  + paramAcao);
		if(paramAcao.equals("cadastrarRequest")){
			System.out.println("Cadastrando request...");
			
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			String title = request.getParameter("campoTitulo");
			String descricao = request.getParameter("campoDescricao");
			String dataInicio = request.getParameter("campoDataInicio");
			String dataFim = request.getParameter("campoDataFim");
			String complemento = request.getParameter("campoComplemento");
			String nomeCidade = request.getParameter("campoCidade");
			//int idTUser = Integer.parseInt(request.getParameter("idTuser"));
			int idTUser = user.getUserID();
			
			Cidade objCidade = new Cidade(nomeCidade);
			Requisicao requisicao = new Requisicao(title,descricao,dataInicio,dataFim,complemento,objCidade,idTUser);
			
			RequisicaoServicos rc = new RequisicaoServicos();
			int chave = rc.adicionarRequisicao(requisicao);
			
			requisicao.setIdRequest(chave);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ofertas.jsp"); //para qual jsp vou enviar meu request
    		request.setAttribute("requisicao", requisicao); //coloco o atributo (nome da empresa) na requisição
    		rd.forward(request, response);  //encaminho para o jsp
			//response.sendRedirect("/trabalhoEmpreendedorismo/ofertas.jsp");
			
		}
		if(paramAcao.equals("inicio")){
			System.out.println("Buscando tags e cidades...");
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			tagServicos ts = new tagServicos();
			ArrayList<Tag> tags = ts.buscarTags();
		
			RequestDispatcher rd = request.getRequestDispatcher("/fazerRequisicao.jsp"); //para qual jsp vou enviar meu request
    		request.setAttribute("tags", tags); //coloco o atributo (nome da empresa) na requisição
    		rd.forward(request, response);  //encaminho para o jsp
		}
	}

}

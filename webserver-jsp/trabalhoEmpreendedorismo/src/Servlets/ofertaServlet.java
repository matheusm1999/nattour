package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BO.Oferta;
import BO.Requisicao;
import BO.User;
import Servicos.RequisicaoServicos;
import Servicos.ofertaServicos;

/**
 * Servlet implementation class ofertaServlet
 */
@WebServlet("/ofertaServlet")
public class ofertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ofertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("Acao");
		//System.out.println("ParamAcaaaaaaoo: " + paramAcao);
		System.out.println("OfertaServlet chamado");
		//Essa sessão foi criado no servlet de login
		HttpSession sessao = request.getSession(false); 
		User user = (User) sessao.getAttribute("usuarioLogado");
		
		if(paramAcao.equals("buscarRequisicoes")){
			System.out.println("Buscando requisiçoes");
			//ofertaServicos os = new ofertaServicos();
			RequisicaoServicos rs = new RequisicaoServicos();
			
			ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
			requisicoes = rs.recuperarRequisicao();
			
			RequestDispatcher rd = request.getRequestDispatcher("selecionarRequisicao.jsp"); //para qual jsp vou enviar meu request
			request.setAttribute("requisicoes", requisicoes); //coloco o atributo (nome da empresa) na requisição
			request.setAttribute("usuarioLogado", user);
			rd.forward(request, response);  //encaminho para o jsp
			
			//System.out.println(requisicoes.get(0)); //para ver se está funcionando
			
			}
		else if(paramAcao.equals("selecionarRequisicao")){
			System.out.println("Selecionado a Oferta...");
			RequisicaoServicos rs = new RequisicaoServicos();
			Requisicao requisicao = new Requisicao();
			
			int id = Integer.parseInt(request.getParameter("campoId"));
			requisicao = rs.recuprerRequisicaoId(id);
			System.out.println("Oferta será feita para: " + id);
			//System.out.println("NOME DO USUARIO: " + requisicao.getUser().getName());
			RequestDispatcher rd = request.getRequestDispatcher("fazerOferta.jsp"); //para qual jsp vou enviar meu request
			request.setAttribute("requisicao", requisicao); //coloco o atributo (nome da empresa) na requisição
			request.setAttribute("usuarioLogado", user);
			rd.forward(request, response);  //encaminho para o jsp
			
		}
		else if(paramAcao.equals("enviarOferta")){
			System.out.println("Enviando Oferta...");
			float campoValor = Float.parseFloat(request.getParameter("campoValor"));	
			String campoObservacao = request.getParameter("campoObservacao");
			int idRequest = Integer.parseInt(request.getParameter("campoId"));
			
			//System.out.println("IDREQUEST:" + idRequest);
			
			int idGUser = user.getUserID();
			Oferta oferta = new Oferta(campoValor,campoObservacao,idRequest);
			oferta.setIdGUser(idGUser);
			ofertaServicos os = new ofertaServicos();
			os.enviarOferta(oferta);
			RequestDispatcher rd = request.getRequestDispatcher("/ofertaServlet?Acao=buscarOfertas&campoIdRequisicao=" + idRequest); //para qual jsp vou enviar meu request
			request.setAttribute("usuarioLogado", user);
    		rd.forward(request, response);  //encaminho para o jsp


		}
		else if(paramAcao.equals("buscarOfertas")){
			System.out.println("Buscando ofertas...");
			ArrayList<Oferta> ofertas;
			Requisicao requisicao;
			int idRequisicao = Integer.parseInt(request.getParameter("campoIdRequisicao"));
			
			ofertaServicos os = new ofertaServicos();
			RequisicaoServicos rs = new RequisicaoServicos();
			
			ofertas = os.buscarOferta(idRequisicao);
			requisicao = rs.recuprerRequisicaoId(idRequisicao);
			System.out.println(ofertas);
			//System.out.println("TAGSON: " + requisicao.getTags().get(0));
			//System.out.println();
			RequestDispatcher rd = request.getRequestDispatcher("ofertas.jsp"); //para qual jsp vou enviar meu request
			request.setAttribute("ofertas", ofertas); //coloco o atributo (nome da empresa) na requisição
			request.setAttribute("requisicao", requisicao);
			request.setAttribute("usuarioLogado", user);
			rd.forward(request, response);  //encaminho para o jsp
			
		}
		else if(paramAcao.equals("verOferta")){
			ofertaServicos os = new ofertaServicos();
			int idOferta = Integer.parseInt(request.getParameter("campoId"));
			Oferta oferta = os.buscarOfertaID(idOferta);
			System.out.println(oferta);
			
			RequestDispatcher rd = request.getRequestDispatcher("verOferta.jsp"); //para qual jsp vou enviar meu request
			request.setAttribute("usuarioLogado", user);
			request.setAttribute("oferta", oferta); //coloco o atributo (nome da empresa) na requisição
			rd.forward(request, response);  //encaminho para o jsp
			/*
			request.getAttribute(arg0)
			request.setAttribute("requisicao", requisicao);
			*/
		}
	}

}

package Servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
import BO.tourEmProgresso;
import Servicos.RequisicaoServicos;
import Servicos.guiaServicos;
import Servicos.ofertaServicos;
import Servicos.tourServicos;
import Servicos.userServicos;

/**
 * Servlet implementation class pagamentoServlet
 */
@WebServlet("/pagamentoServlet")
public class pagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pagamentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("Acao");
		HttpSession sessao = request.getSession(false);
		
		System.out.println("Pagamento Servlet");
		if(paramAcao.equals("pagamentoConcluido")){
			System.out.println("Efetivando pagamento...");
			int idOferta = Integer.parseInt(request.getParameter("campoIdOferta"));
			int idTUser = Integer.parseInt(request.getParameter("campoIdTUser"));
			int idGUser = Integer.parseInt(request.getParameter("campoIdGUser"));
			int idRequest = Integer.parseInt(request.getParameter("campoIdRequest"));
			String campoValor = request.getParameter("idValor");
			Float campoValorFloat = Float.parseFloat(campoValor);
			
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			
			System.out.println("IdOferta: " + idOferta);
			System.out.println("idTUser: " + idTUser);
			System.out.println("idGUser: " + idGUser);
			System.out.println("idRequest: " + idRequest);
			System.out.println("campoValor: " + campoValor);
			
			
			RequisicaoServicos rs = new RequisicaoServicos();		
			userServicos us = new userServicos();
			ofertaServicos os = new ofertaServicos();
			
			Requisicao req = rs.recuprerRequisicaoId(idRequest);
			User guia = us.recuperarUsuarioId(idTUser);
			Oferta oferta = os.buscarOfertaID(idOferta);
			oferta.setValor(campoValorFloat);
			guia.setLinkWhats();
			
			tourEmProgresso tour = new tourEmProgresso(user,guia,req,oferta);
			tourServicos ts = new tourServicos();
			ts.insereTour(tour);
			
			//sessao.setAttribute("usuarioLogado", user);
			RequestDispatcher rd = request.getRequestDispatcher("tourEmProgresso.jsp"); //para qual jsp vou enviar meu request	
			sessao.setAttribute("usuarioLogado", user);
	    	//sessao.setAttribute("guia", guia);
			sessao.setAttribute("tour", tour);
			rd.forward(request, response);  //encaminho para o jsp
		}
		if(paramAcao.equals("verTourEmProgresso")){
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			
			System.out.println("Verificando tour em progresso");
			tourServicos ts = new tourServicos();
			System.out.println("Buscando o id: " + user.getUserID());
			tourEmProgresso tour = ts.buscaTourID(user.getUserID(),user.getIsGuide());
			
			tour.getGuia().setLinkWhats();
			System.out.println("JÁ FOI FINALIZADO? : " + tour.getIsTerminado());
			
			RequestDispatcher rd = request.getRequestDispatcher("tourEmProgresso.jsp"); //para qual jsp vou enviar meu request	
			sessao.setAttribute("usuarioLogado", user);
	    	sessao.setAttribute("tour", tour);
			//request.setAttribute("guia", guia);
	    	rd.forward(request, response);  //encaminho para o jsp
		}
		if(paramAcao.equals("finalizar")){
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			tourServicos ts = new tourServicos();
			ts.finalizaTour(user.getUserID(), user.getIsGuide());
			
			RequestDispatcher rd = request.getRequestDispatcher("historicoServlet?Acao=buscarHistoricoTour"); //para qual jsp vou enviar meu request
			sessao.setAttribute("usuarioLogado", user);
	    	rd.forward(request, response);  //encaminho para o jsp
		}
	}

}


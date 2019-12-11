package Servlets;

import java.io.IOException;

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
import Servicos.ofertaServicos;
import Servicos.tourServicos;
import Servicos.userServicos;

/**
 * Servlet implementation class avaliacaoServlet
 */
@WebServlet("/avaliacaoServlet")
public class avaliacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public avaliacaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("Acao");
		HttpSession sessao = request.getSession(false);
		
		System.out.println("Avaliação Servlet");
		if(paramAcao.equals("Avaliando Turista")){
			System.out.println("Efetivando pagamento...");
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			tourServicos ts = new tourServicos();
			tourEmProgresso tour = ts.buscaTourID(user.getUserID(), user.getIsGuide());
			
			
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
			
			RequestDispatcher rd = request.getRequestDispatcher("tourEmProgresso.jsp"); //para qual jsp vou enviar meu request	
			sessao.setAttribute("usuarioLogado", user);
	    	sessao.setAttribute("tour", tour);
			//request.setAttribute("guia", guia);
	    	rd.forward(request, response);  //encaminho para o jsp
		}
	}

}

package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BO.Requisicao;
import BO.User;
import Servicos.RequisicaoServicos;

/**
 * Servlet implementation class historicoServlet
 */
@WebServlet("/historicoServlet")
public class historicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public historicoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Historico Servlet");
		String paramAcao = request.getParameter("Acao");
		HttpSession sessao = request.getSession(false); 
		User user = (User) sessao.getAttribute("usuarioLogado");

		if (paramAcao.equals("buscarHistoricoTour")){
			System.out.println("Buscando histórico");
			
			user = (User) request.getSession(false).getAttribute("usuarioLogado");
			RequisicaoServicos rs = new RequisicaoServicos();
			ArrayList<Requisicao> requisicoes = new ArrayList<>();
			System.out.println(user);
			
			try{
				requisicoes = rs.recuperarHistoricoRequisicao(user.getUserID());
				//System.out.println(requisicoes.get(0));
				
				RequestDispatcher rd = request.getRequestDispatcher("historicoTours.jsp"); //para qual jsp vou enviar meu request
		    	request.setAttribute("requisicoes", requisicoes); //coloco o atributo na requisição
		    	request.setAttribute("usuarioLogado", user);
		    	rd.forward(request, response);  //encaminho para o jsp
			}catch(NullPointerException e ){
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
				//System.out.println(user);
				//System.out.println(requisicoes.get(0));
		}
		else if (paramAcao.equals("buscarOfertasFeitas")){
			System.out.println("Buscando ofertas feitas...");
			
			user = (User) request.getSession(false).getAttribute("usuarioLogado");
			RequisicaoServicos rs = new RequisicaoServicos();
			ArrayList<Requisicao> requisicoes = new ArrayList<>();
			System.out.println(user);
			
			try{
				requisicoes = rs.recuperarRequiscaoOfertaFeitaGuia(user.getUserID());
				//System.out.println(requisicoes.get(0));
				
				RequestDispatcher rd = request.getRequestDispatcher("ofertasFeitas.jsp"); //para qual jsp vou enviar meu request
		    	request.setAttribute("requisicoes", requisicoes); //coloco o atributo na requisição
		    	request.setAttribute("usuarioLogado", user);
		    	rd.forward(request, response);  //encaminho para o jsp
			}catch(NullPointerException e ){
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		
		
	}
	}
}

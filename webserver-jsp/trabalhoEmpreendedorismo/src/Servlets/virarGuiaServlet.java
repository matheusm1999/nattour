package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BO.User;
import Servicos.guiaServicos;

/**
 * Servlet implementation class virarGuiaServlet
 */
@WebServlet("/virarGuiaServlet")
public class virarGuiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public virarGuiaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("Acao");
		System.out.println("Virar Guia Servlet");
		if(paramAcao.equals("VirarGuia")){
			System.out.println("Cadastrando novo guia...");
			int ddd = Integer.parseInt(request.getParameter("campoDDD"));
			int ddi = Integer.parseInt(request.getParameter("campoDDI"));
			int numeroTelefone = Integer.parseInt(request.getParameter("campoNumeroTelefone"));
			String emailPaypal = request.getParameter("campoEmailPaypal");
			
			User user = (User) request.getSession(false).getAttribute("usuarioLogado");
			user.setDdd(ddd);
			user.setDdi(ddi);
			user.setNumeroTelefone(numeroTelefone);
			user.setEmailPaypal(emailPaypal);
			
			guiaServicos gs = new guiaServicos();
			gs.virarGuia(user);
			
			HttpSession sessao = request.getSession(false);
			//sessao.setAttribute("usuarioLogado", user);
			RequestDispatcher rd = request.getRequestDispatcher("selecionarRequisicao.jsp"); //para qual jsp vou enviar meu request	
			sessao.setAttribute("usuarioLogado", user);
	    	rd.forward(request, response);  //encaminho para o jsp
		}
	}
}

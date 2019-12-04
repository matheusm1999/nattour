package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BO.Cidade;
import BO.User;
import Servicos.loginServicos;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("Acao");
		System.out.println("Servlet Login");
		System.out.println(paramAcao);
		
		if(paramAcao.equals("loginFB")){
			System.out.println("Login pelo Facebook feito!");
			HttpSession sessao = request.getSession(true); //crio uma sessão, caso não exista
			
			String nomeUsuario = request.getParameter("campoNome");
			String idFacebook = request.getParameter("campoID");
			String fotoFacebook = request.getParameter("campoFoto");
			String emailFacebook = request.getParameter("campoEmail");
			
			User user = new User(idFacebook,fotoFacebook,nomeUsuario,emailFacebook);
			loginServicos ls = new loginServicos();
			user = ls.userLogarFacebook(user);
			
			System.out.println("Nome: " + nomeUsuario);
			System.out.println("IdFacebook: " + idFacebook);
			System.out.println("Foto: " + fotoFacebook);
		
			request.setAttribute("user", user); //coloco o atributo na requisição
	    	sessao.setAttribute("usuarioLogado", user);
	    	RequestDispatcher rd = null;
	    	if(user.getIsGuide() == 1){ //Se for guia
	    		System.out.println("O usuário é um guia!");
	    		rd = request.getRequestDispatcher("selecionarRequisicao.jsp"); //para qual jsp vou enviar meu request
	    	}
	    		 else if(user.getIsGuide() == 0){
	    			 System.out.println("O usuário é um turista!");
	    			 rd = request.getRequestDispatcher("fazerRequisicao.jsp"); //para qual jsp vou enviar meu request
	    		 }
	    	rd.forward(request, response);  //encaminho para o jsp
			
		}
		
		if(paramAcao.equals("logar")){
			loginServicos ls = new loginServicos();
			ls.loginFacebook();
		}
		
		if (paramAcao.equals("cadastrarUser")){
			String senha = request.getParameter("campoSenha");
			String email = request.getParameter("campoEmail");
			String nomeCompleto = request.getParameter("campoNomeCompleto");
			String nomeCidade = request.getParameter("campoCidade");
			
			Cidade cidade = new Cidade(nomeCidade);
			User user = new User(email,nomeCompleto,senha,cidade);
			
			loginServicos ls = new loginServicos();
			ls.registrarConta(user);
			
		//	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp"); //para qual jsp vou enviar meu request
    	//	request.setAttribute("User", user); //coloco o atributo (objeto usuário) na requisição
    	//	rd.forward(request, response);  //encaminho para o jsp
			
		}
	}

}

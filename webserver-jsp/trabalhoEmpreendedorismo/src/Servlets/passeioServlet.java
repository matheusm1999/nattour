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
import BO.Validar;
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
		User user = (User) request.getSession(false).getAttribute("usuarioLogado");
		if(paramAcao.equals("cadastrarRequest")){
			System.out.println("Cadastrando request...");
			
			String title = request.getParameter("campoTitulo");
			String descricao = request.getParameter("campoDescricao");
			String dataInicio = request.getParameter("campoDataInicio");
			String dataFim = request.getParameter("campoDataFim");
			String horarioInicio = request.getParameter("campoHorarioInicio");
			String horarioFim = request.getParameter("campoHorarioFim");
			String complemento = request.getParameter("campoComplemento");
			String nomeCidade = request.getParameter("campoCidade");
			
			Validar valida = new Validar();
			boolean campoDataValidado = valida.ValidaData(dataInicio, dataFim);
			boolean campoHorarioValidado = valida.ValidaHora(horarioInicio, horarioFim);
			if(campoDataValidado == true && campoHorarioValidado == true){
				System.out.println("Não teve erro de validação");
				dataInicio = dataInicio + " " + horarioInicio;
				dataFim = dataFim + " " + horarioFim;
				System.out.println("O CAMPO DT FIM: " + dataFim);
				
				int tag1 = Integer.parseInt(request.getParameter("tag1"));
				int tag2 = Integer.parseInt(request.getParameter("tag2"));
				int tag3 = Integer.parseInt(request.getParameter("tag3"));
				
				System.out.println("TAAAAG: " + tag1);
				System.out.println("TAAAAG: " + tag2);
				System.out.println("TAAAAG: " + tag3);
				
				Tag tagUm = new Tag(tag1);
				Tag tagDois = new Tag(tag2);
				Tag tagTres = new Tag(tag3);
				
				ArrayList<Tag> tags = new ArrayList<>();
				tags.add(tagUm);
				tags.add(tagDois);
				tags.add(tagTres);
				//int idTUser = Integer.parseInt(request.getParameter("idTuser"));
				int idTUser = user.getUserID();
				
				Cidade objCidade = new Cidade(nomeCidade);
				Requisicao requisicao = new Requisicao(title,descricao,dataInicio,dataFim,complemento,objCidade,idTUser,tags);
				
				RequisicaoServicos rc = new RequisicaoServicos();
				int chave = rc.adicionarRequisicao(requisicao,idTUser);
				if(chave == 0){
					RequestDispatcher rd = request.getRequestDispatcher("passeioServlet?acao=inicio&resultado=0"); //para qual jsp vou enviar meu request
					request.setAttribute("usuarioLogado", user);
		    		//request.setAttribute("resultado", 0);
					rd.forward(request, response);  //encaminho para o jsp
				}
				else{
					System.out.println("Não existe nenhuma requisição feita, prosseguindo...");
					requisicao.setIdRequest(chave);
					
					RequestDispatcher rd = request.getRequestDispatcher("ofertaServlet?Acao=buscarOfertas&campoIdRequisicao="+ chave); //para qual jsp vou enviar meu request
					request.setAttribute("requisicao", requisicao);
					request.setAttribute("usuarioLogado", user);
		    		rd.forward(request, response);  //encaminho para o jsp
					//response.sendRedirect("/trabalhoEmpreendedorismo/ofertas.jsp");
				}
				
			}else{
				System.out.println("estou no else");
				RequestDispatcher rd = request.getRequestDispatcher("passeioServlet?acao=inicio&resultado=0"); //para qual jsp vou enviar meu request
				request.setAttribute("usuarioLogado", user);
	    		//request.setAttribute("resultado", 0);
				rd.forward(request, response);  //encaminho para o jsp
			//response.sendRedirect("/trabalhoEmpreendedorismo/ofertas.jsp");
			}
		}
		if(paramAcao.equals("inicio")){
			System.out.println("Buscando tags e cidades...");
			user = (User) request.getSession(false).getAttribute("usuarioLogado");
			tagServicos ts = new tagServicos();
			ArrayList<Tag> tags = ts.buscarTags();
			System.out.println(tags.get(0));
			
			try{
				System.out.println("estou verificando se o resultado é nulo");
				int resultado = Integer.parseInt(request.getParameter("resultado"));
				RequestDispatcher rd = request.getRequestDispatcher("/fazerRequisicao.jsp");
				request.setAttribute("usuarioLogado", user);
				request.setAttribute("resultado", resultado);
				System.out.println("RESULTADOOOOO:" + resultado);
				request.setAttribute("tags", tags);
	    		rd.forward(request, response);  //encaminho para o jsp
			}catch (Exception e){
				System.out.println("O RESULTADO É NULO (N TEVE ERRO), ENTÃO CONTINUO");
				RequestDispatcher rd = request.getRequestDispatcher("/fazerRequisicao.jsp"); //para qual jsp vou enviar meu request
	    		request.setAttribute("tags", tags); //coloco o atributo (nome da empresa) na requisição
	    		request.setCharacterEncoding("UTF-8");
	    		response.setCharacterEncoding("UTF-8");
	    		request.setAttribute("usuarioLogado", user);
	    		rd.forward(request, response);  //encaminho para o jsp
			}
			
		}
	}

}

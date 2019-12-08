package Servicos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Conexao;
import BO.Oferta;
import BO.User;
import DAO.geralDAO;
import DAO.ofertaDAO;
import DAO.userDAO;

public class ofertaServicos {
	
	//Dado o id da requisicao, retorna as ofertas relacionados a ela
	/*
	public Oferta retornaOfertas(int idRequisicao){
		Connection connection = Conexao.getConnection();
		geralDAO gd = new geralDAO(connection);
		int chave = gd.selectRetornaInt("SELECT id FROM city WHERE id = " + idRequisicao); 
		
		return ;
	}
	*/

	//Envia a oferta criada pelo turista na requisição selecionada
	//Entrada: Oferta a ser feita para o turista
	//Saída: Nenhuma
	//Pré condição: Nenhuma
	//Pós condição: Oferta inserida no banco de dados na entidade "Offer"
	public void enviarOferta(Oferta oferta){
		Connection connection = Conexao.getConnection();
		ofertaDAO od = new ofertaDAO(connection);
		try {
			od.insertOferta(oferta);
		} catch (SQLException e) {
			System.out.println("Erro ao enviar a oferta!");
			e.printStackTrace();
		}
	}
	
	//Busca todas as ofertas de requisições no banco de dados
	public ArrayList<Oferta> buscarOferta(int idRequisicao){
		Connection connection = Conexao.getConnection();
		
		ofertaDAO od = new ofertaDAO(connection);
		userDAO ud = new userDAO(connection);
		
		User user;
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		try {
			ofertas = od.buscarOferta(idRequisicao);
			for(int i = 0; i <ofertas.size();i++){
				user = ud.selectUserID(ofertas.get(i).getIdGUser());
				ofertas.get(i).setUser(user);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar a Requisição!");
			e.printStackTrace();
		}
		return ofertas;
	}

	//Busca uma oferta no banco de dados, na tabela "Offer", por meio do seu ID
	public Oferta buscarOfertaID(int idOferta){
		Connection connection = Conexao.getConnection();
		Oferta oferta = null;
		User user = null;
		ofertaDAO od = new ofertaDAO(connection);
		userDAO us = new userDAO(connection);
		try {
			oferta = od.buscarOfertaId(idOferta);
			user = us.selectUserID(oferta.getIdGUser());
			oferta.setUser(user);
		} catch (SQLException e) {
			System.out.println("Erro ao Buscar a oferta!");
			e.printStackTrace();
		}
		catch(NullPointerException n){
			System.out.println("Oferta não existe no banco de dados!");
			n.printStackTrace();
		}
		return oferta;
	}
}

package Servicos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Conexao;
import BO.Oferta;
import DAO.geralDAO;
import DAO.ofertaDAO;

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
	
	public ArrayList<Oferta> buscarOferta(int idRequisicao){
		Connection connection = Conexao.getConnection();
		ofertaDAO od = new ofertaDAO(connection);
		
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		try {
			ofertas = od.buscarOferta(idRequisicao);
		} catch (SQLException e) {
			System.out.println("Erro ao buscar a Requisição!");
			e.printStackTrace();
		}
		return ofertas;
	}
}

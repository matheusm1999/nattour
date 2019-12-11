package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BO.Oferta;

public class ofertaDAO {
	private Connection connection;
	String SQLInsereOferta = "INSERT INTO offer (description,price,idGUser,idRequest) VALUES (?,?,?,?)";
	String SQLSelectOfertaIdRequest = "SELECT * FROM offer WHERE idRequest = ?";
	String SQLSelectOfertaId = "SELECT * FROM offer WHERE id = ?";
	String SQLUpdateIsPagoOffer = "UPDATE offer SET isPago = 1 where id = ?";
	
	public ofertaDAO(Connection connection){
		this.connection = connection;
	}
	
		public void updateOfertaIsPago(int idOferta) throws SQLException{
			PreparedStatement ps = connection.prepareStatement(SQLUpdateIsPagoOffer);
			ps.setInt(1, idOferta);
			ps.execute();
		}
	
		//Retorna a chave primária por meio da consulta especificada nos parâmetros
		//Pré condição: A consulta tem que ser um SELECT e precisa ser do tipo que retorna uma chave primária
		//Pós condição: Nenhuma
		public int selectRetornaChave(String query) throws SQLException{
			PreparedStatement ps = connection.prepareStatement(query);
					
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int chave = rs.getInt(1);
			System.out.println("chaveee: " + chave);
			return chave;
			
		}
		
		//Insere a oferta na entidade "offer" no banco de dados
		//Entrada: Objeto oferta a ser inserido na entidade Offer no banco de dados
		//Saída: Nenhuma
		//Pré condição: Nenhuma
		//Pós condição: Oferta inserido na entidade Offer no banco de dados
		public void insertOferta(Oferta oferta) throws SQLException{
			float valor = oferta.getValor();
			String observacao = oferta.getDescription();
			int idGUser = oferta.getIdGUser();
			int idRequest = oferta.getIdRequest();
			//System.out.println("IDIDIDI"+ idRequest);
			
			PreparedStatement ps = connection.prepareStatement(SQLInsereOferta);
			ps.setString(1, observacao);
			ps.setFloat(2, valor);
			ps.setInt(3, idGUser); 
			ps.setInt(4, idRequest);

			ps.execute();
		}

		//Busca as ofertas feita para uma determinada requisição por meio do parâmetro idRequisicao
		//Entrada: Id da requisição a ser utilizada para buscar no banco de dados uma oferta feita
		//Saída: Ofertas feitas para aquela requição que possúi o id específicado nos parâmetros
		public ArrayList<Oferta> buscarOferta(int idRequisicao) throws SQLException{
			ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
			
			PreparedStatement ps = connection.prepareStatement(SQLSelectOfertaIdRequest);
			ps.setInt(1, idRequisicao);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int idGUser = rs.getInt("idGUser");
				int idRequest = rs.getInt("idRequest");
				
				Oferta oferta = new Oferta(id,idGUser,price,description,idRequest);
				ofertas.add(oferta);
			}
			return ofertas;
		}

		//Busca a oferta no banco de dados por meio do id da oferta
		//Entrada: Id da oferta a ser buscada
		//Saída: Objeto oferta populado de acordo com o seu id no banco de dados
		//Pré condição: Nenhuma
		//Pós condição: Nenhuma
		public Oferta buscarOfertaId (int idOferta) throws SQLException{
			Oferta oferta = null;
			
			PreparedStatement ps = connection.prepareStatement(SQLSelectOfertaId);
			ps.setInt(1, idOferta);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int id = rs.getInt("id");
			String description = rs.getString("description");
			float price = rs.getFloat("price");
			int idGUser = rs.getInt("idGUser");
			int idRequest = rs.getInt("idRequest");
				
			oferta = new Oferta(id,idGUser,price,description,idRequest);
			
			return oferta;
		}
}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BO.Oferta;
import BO.Requisicao;
import BO.User;
import BO.tourEmProgresso;

public class tourEmProgressoDAO {
	Connection connection;
	private String SQLInsertTourProgresso = "INSERT INTO tour (idRequest,idOffer,idGUser,idTUser,valor) VALUES (?,?,?,?,?)";
	private String SQLSelectTour = "SELECT * FROM tour WHERE idTUser = ?";
	private String SQLSelectTourGuia = "SELECT * FROM tour WHERE idGUser = ?";
	private String SQLUpdateIsTerminado = "UPDATE tour SET isTerminado = 1 where idTUser = ?";
	private String SQLUpdateIsTerminadoGuia = "UPDATE tour SET isTerminado = 1 where idGUser = ?";
	
	
	public tourEmProgressoDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	public void insertTourProgresso(tourEmProgresso tour) throws SQLException{
		int idRequest = tour.getRequisicao().getIdRequest();
		int idTUser = tour.getUsuario().getUserID();
		int idGuia = tour.getGuia().getUserID();
		int idOferta = tour.getOferta().getIdOferta();
		float valor = tour.getOferta().getValor();
		
		PreparedStatement ps = connection.prepareStatement(SQLInsertTourProgresso,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, idRequest);
		ps.setInt(2, idOferta);
		ps.setInt(3, idGuia);
		ps.setInt(4, idTUser);
		ps.setFloat(5, valor);
		ps.execute();
	}
	
	public void updateTourIsTerminado(int idUser) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLUpdateIsTerminado);
		ps.setInt(1, idUser);
		ps.execute();
	}

	public tourEmProgresso selectTourID(int idUser) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLSelectTour);
		ps.setInt(1, idUser);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		//int id = rs.getInt("id");
		//float price = rs.getFloat("valor");
		int idGUser = rs.getInt("idGUser");
		int idRequest = rs.getInt("idRequest");
		int idOffer = rs.getInt("idOffer");	
		int isTerminado = rs.getInt("isTerminado");
		System.out.println("DAO, ACABOU? + " + isTerminado);
		
		User user = new User();
		User guia = new User();
		Oferta offer = new Oferta();
		Requisicao requisicao = new Requisicao();
		
		System.out.println("Setando o valor :" + idGUser);
		
		user.setUserID(idUser);
		guia.setUserID(idGUser);
		offer.setIdOferta(idOffer);
		requisicao.setIdRequest(idRequest);
		
		tourEmProgresso tour = new tourEmProgresso(user,guia,requisicao,offer);
		tour.setIsTerminado(isTerminado);
		System.out.println("TOUR, KBO? :" + tour.getIsTerminado());
		
		return tour;

	}
	
	public tourEmProgresso selectTourIDGuia(int idGuia) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLSelectTourGuia);
		ps.setInt(1, idGuia);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		//int id = rs.getInt("id");
		//float price = rs.getFloat("valor");
		int idGUser = rs.getInt("idGUser");
		int idRequest = rs.getInt("idRequest");
		int idOffer = rs.getInt("idOffer");	
		int isTerminado = rs.getInt("isTerminado");
		System.out.println("DAO, ACABOU? + " + isTerminado);
		
		User user = new User();
		User guia = new User();
		Oferta offer = new Oferta();
		Requisicao requisicao = new Requisicao();
		
		System.out.println("Setando o valor :" + idGUser);
		
		user.setUserID(idGuia);
		guia.setUserID(idGUser);
		offer.setIdOferta(idOffer);
		requisicao.setIdRequest(idRequest);
		
		tourEmProgresso tour = new tourEmProgresso(user,guia,requisicao,offer);
		tour.setIsTerminado(isTerminado);
		
		return tour;

	}

	public void updateTourIsTerminadoGuia(int idGuia) throws SQLException{
		System.out.println("atualizandooooooo");
		PreparedStatement ps = connection.prepareStatement(SQLUpdateIsTerminadoGuia);
		ps.setInt(1, idGuia);
		ps.execute();
	}
	
	public void updateTourIsTerminadoTurista(int idTurista) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLUpdateIsTerminado);
		ps.setInt(1, idTurista);
		ps.execute();
	}
}

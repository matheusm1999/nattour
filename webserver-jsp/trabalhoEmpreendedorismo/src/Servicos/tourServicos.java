package Servicos;

import java.sql.Connection;
import java.sql.SQLException;

import BD.Conexao;
import BO.Oferta;
import BO.Requisicao;
import BO.User;
import BO.tourEmProgresso;
import DAO.ofertaDAO;
import DAO.requestDAO;
import DAO.tourEmProgressoDAO;

public class tourServicos {

	//busca o tour no banco de dados por meio do id do usuário
	public tourEmProgresso buscaTourID(int idUsuario,int isGuide){
		Connection connection = Conexao.getConnection();
		tourEmProgressoDAO td = new tourEmProgressoDAO(connection);
		
		RequisicaoServicos rs = new RequisicaoServicos();
		ofertaServicos os = new ofertaServicos();
		userServicos us = new userServicos();
		
		tourEmProgresso tour = null;
		
		try {
			if(isGuide == 1)
				tour = td.selectTourIDGuia(idUsuario);
			else
				tour = td.selectTourID(idUsuario);
				System.out.println("KBO MESMO: " + tour.getIsTerminado());
			Requisicao requisicao = rs.recuprerRequisicaoId(tour.getRequisicao().getIdRequest());
			Oferta oferta = os.buscarOfertaID(tour.getOferta().getIdOferta());
			System.out.println("Buscando pelo Guia: " + tour.getGuia().getUserID());
			User guia = us.recuperarUsuarioId(tour.getGuia().getUserID()); 
			//System.out.println("ddd: " + guia.getDdd());
			//System.out.println("ddd: " + guia.getDdi());
			//System.out.println("ddd: " + guia.getNumeroTelefone());
			User user = us.recuperarUsuarioId(idUsuario);
			
			//tour = new tourEmProgresso(user,guia,requisicao,oferta);
			tour.setUsuario(user);
			tour.setGuia(guia);
			tour.setRequisicao(requisicao);
			tour.setOferta(oferta);
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o tour");
			e.printStackTrace();
		}
		return tour;
	}

	public void insereTour(tourEmProgresso tour){
		Connection connection = Conexao.getConnection();
		tourEmProgressoDAO td = new tourEmProgressoDAO(connection);
		requestDAO rd = new requestDAO(connection);
		ofertaDAO od = new ofertaDAO(connection); 
		try {
			td.insertTourProgresso(tour);
			rd.updateRequisicaoIsPago(tour.getRequisicao().getIdRequest());
			od.updateOfertaIsPago(tour.getOferta().getIdOferta());
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o tour!");
			e.printStackTrace();
		}
	}
	
	public void finalizaTour(int idUsuario,int isGuide){
		Connection connection = Conexao.getConnection();
		tourEmProgressoDAO td = new tourEmProgressoDAO(connection);
		requestDAO rd = new requestDAO(connection);
		ofertaDAO od = new ofertaDAO(connection);
		
		System.out.println("finalizando o tour");
		tourEmProgresso tour = buscaTourID(idUsuario,isGuide);
			try {
				if(isGuide == 1){
					td.updateTourIsTerminadoGuia(idUsuario);
					System.out.println();
				}
					else{
						System.out.println("é um turista!!");
						td.updateTourIsTerminadoTurista(idUsuario);
					}
					rd.updateRequisicaoIsTerminado(tour.getRequisicao().getIdRequest());
				od.updateOfertaIsTerminado(tour.getOferta().getIdOferta());
			} catch (SQLException e) {
				System.out.println("erro ao finalizar o tour!");
				e.printStackTrace();
			}
		}
}


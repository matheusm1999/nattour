package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BO.Oferta;
import BO.Tag;

public class tagDAO {
	private Connection connection;
	private String SQLSelectTags = "SELECT * FROM TAG";
	private String SQLSelectTagNome = "SELECT * FROM tag WHERE VALUE = ?";
	private String SQLSelectTagId = "SELECT * FROM TAG WHERE id = ?";
	private String SQLSelectRequestTagIdRequest = "SELECT * FROM requesttag WHERE idRequest = ?";
	
	public tagDAO(Connection connection){
		this.connection = connection;
	}
	
	//Busca todas as tags no banco de dados, na entidade "tags"
	//Entrada: Nenhuma
	//Saída: Todas as tags existentes na entidade "tags" no banco de dados
	public ArrayList<Tag> selectTags() throws SQLException{
		ArrayList<Tag> tags = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(SQLSelectTags);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			int id = rs.getInt("id");
			String nome = rs.getString("value");
			
			Tag tag = new Tag(id,nome);
			tags.add(tag);
		}
		return tags;
	}
	
	//Busca a Tag pelo nome no banco de dados, na entidade "tags"
	//Entrada: Nome da tag a ser buscada
	//Saída: Obejto tag com nome e id vindo da entidade tags no banco de dados
	public Tag selectTagNome(String nome) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLSelectTagNome);
		ps.setString(1, nome);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		int id = rs.getInt("id");
		String name = rs.getString("value");
		
		Tag tag = new Tag(id,nome);
		
		return tag;
	}

	public Tag selectTagID(int idTag) throws SQLException{
		//ArrayList<Tag> tags = new ArrayList<>();
		
		PreparedStatement ps = connection.prepareStatement(SQLSelectTagId);
		ps.setInt(1, idTag);
		ResultSet rs = ps.executeQuery();
		
		//while(rs.next()){
		rs.next();
		int id = rs.getInt("id");
		String name = rs.getString("value");
		
		Tag tag = new Tag(id,name);
		//tags.add(tag);
		//}
		return tag;	
	}

	//Busca as tags no banco de dados na entidade RequestTag por meio do id da Requisicao
	//Ou seja, busco todas as tags associadas a uma requisição por meio do id dessa requisição
	//Entrada: ID da requisição a ser utilizado para buscar as tags associadas a ela na entidade "requesttag"
	//Saída: Todas as tags associadas aquela requisição
	public ArrayList<Tag> selectRequestTagIdRequest(int idRequisicao) throws SQLException{
		ArrayList<Tag> tags = new ArrayList<>();
		
		PreparedStatement ps = connection.prepareStatement(SQLSelectRequestTagIdRequest);
		ps.setInt(1, idRequisicao);
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next()){
			int idTag = rs.getInt("idTag");
			
			//chamar a consulta buscatag por id
			Tag tag = selectTagID(idTag);
			tags.add(tag);
		}
		return tags;	

	}
}


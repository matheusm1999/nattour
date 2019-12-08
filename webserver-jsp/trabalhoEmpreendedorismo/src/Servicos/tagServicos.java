package Servicos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Conexao;
import BO.Tag;
import DAO.tagDAO;

public class tagServicos {
	public tagServicos(){
		
	}
	
	//Busca todas as tags no banco de dados
	//Saída: Todas as tas existentes na entidade "tag" no banco de dados
	public ArrayList<Tag> buscarTags(){
		Connection connection = Conexao.getConnection();
		ArrayList<Tag> tags = new ArrayList<>();
		tagDAO td = new tagDAO(connection);
		
		try {
			tags = td.selectTags();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar as tags!");
			e.printStackTrace();
		}
		return tags;
	}
	
	//Busca uma tag no banco de dados por meio do seu nome
	//Entrada: Nome da tag a ser buscada no banco de dados
	//Saída: Tag associada ao nome especificado nos parâmetros
	public Tag buscaTagNome(String nome){
		Connection connection = Conexao.getConnection();
		Tag tag = new Tag();
		tagDAO td = new tagDAO(connection);
		
		try {
			tag = td.selectTagNome(nome);
		} catch (SQLException e) {
			System.out.println("Erro ao buscar a tag!");
			e.printStackTrace();
		}
		return tag;
	}
}

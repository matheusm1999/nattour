package Servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BD.Conexao;
import BO.Cidade;
import BO.Pais;
import BO.Requisicao;
import BO.Tag;
import BO.Uf;
import BO.User;
import DAO.enderecoDAO;
import DAO.ofertaDAO;
import DAO.requestDAO;
import DAO.tagDAO;
import DAO.userDAO;

public class RequisicaoServicos {
	
	public RequisicaoServicos(){
		
	}
	
	//Adiciona a requisi��o passado no par�metro no banco de dados
	//Entrada: Requisi��o a ser adicionada no banco de dados
	//Sa�da: Chave gerado ap�s o registro ter sido inserido
	//Pr� condi��o: Nenhuma
	//P�s condi��o: Requisi��o adicionada no banco de dados
	public int adicionarRequisicao(Requisicao requisicao,int idTurista){
		Connection connection = Conexao.getConnection();
		
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		tagDAO td = new tagDAO(connection);
		
		ArrayList<Requisicao> reqs = recuperarHistoricoRequisicao(idTurista);
		for(int j = 0 ;j < reqs.size(); j++){
			if(reqs.get(j).getIsPago() == 0){
				System.out.println("N�o pode cadastrar requisi��o pq j� existe uma req feita!!");
				return 0;
			}
				
		}
		
		
		int chave = 0;
		System.out.println("DENTRO DO ADICIONAR: DT FINAL = " + requisicao.getEndsAt());
		try {
			//Recuperando a cidade no banco de dados
			System.out.println("Cidade: " + requisicao.getCidade().getNome());
			int idCidade = ed.selectRetornaChave("SELECT id FROM city WHERE name = '" + requisicao.getCidade().getNome() + "'");
			requisicao.getCidade().setIdCidade(idCidade);
						
			//Recuperando o id e nome da uf no banco de dados, associado ao id da cidade
			int idUf = ed.selectRetornaChave("SELECT idState FROM city WHERE name = '" + requisicao.getCidade().getNome() + "'");
			requisicao.getCidade().getUf().setIdUf(idUf);
			System.out.println("idUff: " + idUf);
			String nomeUf = ed.selectRetornaString("SELECT name FROM state where id = " + idUf);
			requisicao.getCidade().getUf().setNomeUf(nomeUf);
			//System.out.println("NullPointer??");
			
			//Recuperando o id e nome do pais no banco de dados, associado ao id da uf (que est� associado ao id da cidade)
			int idPais = ed.selectRetornaChave("SELECT id FROM country WHERE id = " + idUf);
			requisicao.getCidade().getUf().getPais().setIdPais(idPais); //adicona o id no pais
			//System.out.println("Null Pointer??");
			String nomePais = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idPais);
			requisicao.getCidade().getUf().getPais().setNomePais(nomePais);
			
			chave = rd.insertRequest(requisicao);
			requisicao.setIdRequest(chave);
			
			rd.insertTagRequest(requisicao);
			
			
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel realizar a consulta");
			//System.out.println(e.toString());
			e.printStackTrace();
		}
		return chave;
	}

	//Busca todas as requsi��es feitas no banco de dados
	//Sa�da: Todas as requisi��es no banco de dados, na entidade "request"
	public ArrayList<Requisicao> recuperarRequisicao(){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		userDAO ud = new userDAO(connection);
		tagDAO td = new tagDAO(connection);
		
		User user = new User();
		
		ArrayList<Tag> tags = new ArrayList<Tag>();
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		try {
			requisicoes = rd.selectRequisicoes();
			for(int i = 0; i < requisicoes.size();i++){
				String nomeCidade = ed.selectRetornaString("SELECT name FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				requisicoes.get(i).getCidade().setNome(nomeCidade);
				
				int idState = ed.selectRetornaChave("SELECT idState FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				String stateName = ed.selectRetornaString("SELECT name FROM state WHERE id = " + idState); 
				Uf uf = new Uf(stateName,".",idState);
				
				int idCountry = ed.selectRetornaChave("SELECT idCountry FROM state WHERE id = " + idState);
				String nameCountry = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idCountry);
				Pais pais = new Pais(nameCountry,".",idCountry);
				uf.setPais(pais);
				
				requisicoes.get(i).getCidade().setUf(uf);
				user = ud.selectUserID(requisicoes.get(i).getIdTuser());
				requisicoes.get(i).setUser(user);
				
				tags = td.selectRequestTagIdRequest(requisicoes.get(i).getIdRequest());
				requisicoes.get(i).setTags(tags);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return requisicoes;
	}

	//Seleciona a requisi��o por meio de seu ID
	public Requisicao recuprerRequisicaoId(int id){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		tagDAO td = new tagDAO(connection);
		userDAO ud = new userDAO(connection);
		
		User user = new User();
		
		Requisicao req = new Requisicao(); //Objeto vazio que ser� populado ap�s as opera��es abaixo
		ArrayList<Tag> tags = new ArrayList<Tag>();
		try {
			//Busco as requisi��es associadas ao seu respectivo id no bd
			req = rd.selectRequisicaoId(id);
			
			//Pega as tags daquela requisi��o e as seta para o objeto request
			tags = td.selectRequestTagIdRequest(id);
			req.setTags(tags);
			
			//Pega o usu�rio associado com aquela requisi��o
			user = ud.selectUserID(req.getIdTuser());
			req.setUser(user);
			
			String nomeCidade = ed.selectRetornaString("SELECT name FROM city WHERE id = " + req.getCidade().getIdCidade());
			req.getCidade().setNome(nomeCidade);
			
			int idState = ed.selectRetornaChave("SELECT idState FROM city WHERE id = " + req.getCidade().getIdCidade());
			String stateName = ed.selectRetornaString("SELECT name FROM state WHERE id = " + idState); 
			Uf uf = new Uf(stateName,".",idState);
				
			int idCountry = ed.selectRetornaChave("SELECT idCountry FROM state WHERE id = " + idState);
			String nameCountry = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idCountry);
			Pais pais = new Pais(nameCountry,".",idCountry);
			uf.setPais(pais);
				
			req.getCidade().setUf(uf);
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return req;
	}
 
	
	//Busca todas as requisi��es feitas por um turista por meio do seu id do facebook
	//Entrada: id do turista
	//Sa�da: Todas as requisi��es feitas pelo turista
	//Pr� condi��o: id tem que ser o do Facebook
	//P�s condi��o: Nenhuma
	public ArrayList<Requisicao> recuperarHistoricoRequisicao(int idTurista){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		tagDAO td = new tagDAO(connection);
		
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		try {
			requisicoes = rd.selectHistoricoRequisicaoTurista(idTurista);
			for(int i = 0; i < requisicoes.size();i++){
				//Pega as tags daquela requisi��o e as seta para o objeto request
				tags = td.selectRequestTagIdRequest(requisicoes.get(i).getIdRequest());
				requisicoes.get(i).setTags(tags);
				
				String nomeCidade = ed.selectRetornaString("SELECT name FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				requisicoes.get(i).getCidade().setNome(nomeCidade);
				
				int idState = ed.selectRetornaChave("SELECT idState FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				String stateName = ed.selectRetornaString("SELECT name FROM state WHERE id = " + idState); 
				Uf uf = new Uf(stateName,".",idState);
				
				int idCountry = ed.selectRetornaChave("SELECT idCountry FROM state WHERE id = " + idState);
				String nameCountry = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idCountry);
				Pais pais = new Pais(nameCountry,".",idCountry);
				uf.setPais(pais);
				
				requisicoes.get(i).getCidade().setUf(uf);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return requisicoes;
	}


	//Recupera todas as requisi��es nas quais o guia fez uma requisi��o
	public ArrayList<Requisicao> recuperarRequiscaoOfertaFeitaGuia(int idGuia){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		tagDAO td = new tagDAO(connection);
		userDAO ud = new userDAO(connection);
		
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		try {
			requisicoes = rd.buscarOfertasIDGuia(idGuia);
			for(int i = 0; i < requisicoes.size();i++){
				//Pega as tags daquela requisi��o e as seta para o objeto request
				tags = td.selectRequestTagIdRequest(requisicoes.get(i).getIdRequest());
				requisicoes.get(i).setTags(tags);
				
				String nomeCidade = ed.selectRetornaString("SELECT name FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				requisicoes.get(i).getCidade().setNome(nomeCidade);
				
				int idState = ed.selectRetornaChave("SELECT idState FROM city WHERE id = " + requisicoes.get(i).getCidade().getIdCidade());
				String stateName = ed.selectRetornaString("SELECT name FROM state WHERE id = " + idState); 
				Uf uf = new Uf(stateName,".",idState);
				
				int idCountry = ed.selectRetornaChave("SELECT idCountry FROM state WHERE id = " + idState);
				String nameCountry = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idCountry);
				Pais pais = new Pais(nameCountry,".",idCountry);
				uf.setPais(pais);
				
				requisicoes.get(i).getCidade().setUf(uf);
				User user = ud.selectUserID(requisicoes.get(i).getIdTuser());
				requisicoes.get(i).setUser(user);
			}
			//Deixo apenas as requisi��es que n�o tem o mesmo id
			requisicoes = requisicoes.get(0).comparaIDReequisicoes(requisicoes); //tanto faz de qual requisi��o esse m�todo vem
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return requisicoes;

	}
}


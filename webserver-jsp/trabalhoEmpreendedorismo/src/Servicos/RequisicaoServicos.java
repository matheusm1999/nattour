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
import BO.Uf;
import DAO.enderecoDAO;
import DAO.requestDAO;

public class RequisicaoServicos {
	
	public RequisicaoServicos(){
		
	}
	
	//Adiciona a requisição passado no parâmetro no banco de dados
	//Entrada: Requisição a ser adicionada no banco de dados
	//Saída: Chave gerado após o registro ter sido inserido
	//Pré condição: Nenhuma
	//Pós condição: Requisição adicionada no banco de dados
	public int adicionarRequisicao(Requisicao requisicao){
		Connection connection = Conexao.getConnection();
		
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		
		int chave = 0;
		
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
			
			//Recuperando o id e nome do pais no banco de dados, associado ao id da uf (que está associado ao id da cidade)
			int idPais = ed.selectRetornaChave("SELECT id FROM country WHERE id = " + idUf);
			requisicao.getCidade().getUf().getPais().setIdPais(idPais); //adicona o id no pais
			//System.out.println("Null Pointer??");
			String nomePais = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idPais);
			requisicao.getCidade().getUf().getPais().setNomePais(nomePais);
			
			chave = rd.insertRequest(requisicao);
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a consulta");
			//System.out.println(e.toString());
			e.printStackTrace();
		}
		return chave;
	}

	//Busca todas as requsições feitas no banco de dados
	public ArrayList<Requisicao> recuperarRequisicao(){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		
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
			}
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return requisicoes;
	}

	//Seleciona a requisição por meio de seu ID
	public Requisicao recuprerRequisicaoId(int id){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);

		Requisicao req = new Requisicao(); //Objeto vazio que será populado após as operações abaixo
		try {
			req = rd.selectRequisicaoId(id);
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

	//Busca todas as requisições feitas por um turista por meio do seu id do facebook
	//Entrada: id do turista
	//Saída: Todas as requisições feitas pelo turista
	//Pré condição: id tem que ser o do Facebook
	//Pós condição: Nenhuma
	public ArrayList<Requisicao> recuperarHistoricoRequisicao(int idTurista){
		Connection connection = Conexao.getConnection();
		requestDAO rd = new requestDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		try {
			requisicoes = rd.selectHistoricoRequisicaoTurista(idTurista);
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
			}
			
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}
		return requisicoes;
	}
}


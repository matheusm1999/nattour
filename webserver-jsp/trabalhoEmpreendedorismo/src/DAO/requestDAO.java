package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BO.Cidade;
import BO.Oferta;
import BO.Requisicao;
import BO.Tag;

public class requestDAO {
	private String SQLInsertRequest = "INSERT INTO request (title,description,startsAt,endsAt,minPrice,maxPrice,idTuser,idCity,complement,isPago) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String SQLSelectHistoricoRequisicao = "SELECT * FROM request WHERE idTUser = ?";
	//private String SQLSelectOfferIdGuiaa = "SELECT * FROM offer WHERE idGUser = ?";
	private String SQLInsertTagRequest = "INSERT INTO requesttag (idRequest,idTag) VALUES (?,?)";
	String SQLUpdateIsPagoRequest  ="UPDATE request SET isPago = 1 where id = ?";
	String SQLUpdateIsTerminadoRequest  ="UPDATE request SET isTerminado = 1 where id = ?";

	
	private Connection connection;
	
	public requestDAO(Connection connection){
		this.connection = connection;
	}
	
	public void updateRequisicaoIsTerminado(int idRequisicao) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLUpdateIsTerminadoRequest);
		ps.setInt(1, idRequisicao);
		ps.execute();
	}
	
	public void updateRequisicaoIsPago(int idRequisicao) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLUpdateIsPagoRequest);
		ps.setInt(1, idRequisicao);
		ps.execute();
	}
	
	//Busca todas as requisi��es na qual o guia fez uma oferta.
	//Entrada: ID do guia
	//Sa�da: Todas as requisi��es na qual o guia fez oferta.
	public ArrayList<Requisicao> buscarOfertasIDGuia(int idGuia) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM offer WHERE idGUser = ?");
		ps.setInt(1, idGuia);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		while(rs.next()){
			//Pegando os valores dos atributos
			int idRequest = rs.getInt("idRequest");
			Requisicao req = selectRequisicaoId(idRequest);	
			requisicoes.add(req);
		}
		return requisicoes;
	}
	
	//Insere o request na entidade request no banco de dados
	public int insertRequest(Requisicao requisicao) throws SQLException{
		String title = requisicao.getTitle();
		String description = requisicao.getDescription();
		String startAt = requisicao.getStartsAt();
		String endsAt = requisicao.getEndsAt();
		float minPrice = requisicao.getMinPrice();
		float maxPrice = requisicao.getMaxPrice();
		int idTuser = requisicao.getIdTuser();
		int idCity = requisicao.getCidade().getIdCidade();
		String complement = requisicao.getComplement();
		
		PreparedStatement ps = connection.prepareStatement(SQLInsertRequest,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, title);
		ps.setString(2, description);
		ps.setString(3, startAt);
		ps.setString(4, endsAt);
		ps.setFloat(5, minPrice);
		ps.setFloat(6, maxPrice);
		ps.setInt(7, idTuser);
		ps.setInt(8, idCity);
		ps.setString(9, complement);
		ps.setInt(10, 0); //IsPago
		ps.execute();
		
		ResultSet keys = ps.getGeneratedKeys();
		keys.first();
		int key = keys.getInt(1);
		
		return key;
	}

	public ArrayList<Requisicao> selectRequisicoes() throws SQLException{
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM request");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			//Pegando os valores dos atributos
			int id = rs.getInt("id");
			System.out.println("idd: " + id);
			String title = rs.getString("title");
			System.out.println(title);
			String description = rs.getString("description");
			String startsAt = rs.getString("startsAt");
			String endsAt = rs.getString("endsAt");
			String minPrice = rs.getString("minPrice");
			String maxPrice = rs.getString("minPrice");
			int idTUser = rs.getInt("idTUser");
			int idCity = rs.getInt("idCity");
			String complement = rs.getString("complement");
			int isPago = rs.getInt("isPago");
			
			PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
			pss.setString(1, startsAt);
			ResultSet rss = pss.executeQuery();
			rss.next();
			startsAt = rss.getString(1);
			System.out.println("COME�A EMmmmmmm : " + startsAt);
			
			PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
			psss.setString(1, endsAt);
			ResultSet rsss = psss.executeQuery();
			rsss.next();
			endsAt = rsss.getString(1);
			System.out.println("COME�A EMmmmmmm : " + endsAt);
			//Criando os os objetos
			Cidade cidade = new Cidade(idCity);
			Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
			
			if(isPago == 0)
				requisicoes.add(req);
		}
		return requisicoes;
	}

	//Seleciona uma requici��o no banco de dados por meio da sua id
	//Entrada: Id da requisi��o a ser buscada na entidadde "Request" no banco de dados
	//Sa�da: Objeto requisi��o com os dados preenchidos, menos as informa��es referentes ao endere�o
	public Requisicao selectRequisicaoId(int id) throws SQLException{
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM request WHERE id = " + id);
		ResultSet rs = ps.executeQuery();
		
		//while(rs.next()){
		rs.next();
		//Pegando os valores dos atributos
		//int id = rs.getInt("id");
		//System.out.println("idd: " + id);
		String title = rs.getString("title");
		System.out.println(title);
		String description = rs.getString("description");
		String startsAt = rs.getString("startsAt");
		String endsAt = rs.getString("endsAt");
		String minPrice = rs.getString("minPrice");
		String maxPrice = rs.getString("minPrice");
		int idTUser = rs.getInt("idTUser");
		int idCity = rs.getInt("idCity");
		String complement = rs.getString("complement");
		int isPago = rs.getInt("isPago");
		
		PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
		pss.setString(1, startsAt);
		ResultSet rss = pss.executeQuery();
		rss.next();
		startsAt = rss.getString(1);
		System.out.println("COME�A EMmmmmmm : " + startsAt);
		
		PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
		psss.setString(1, endsAt);
		ResultSet rsss = psss.executeQuery();
		rsss.next();
		endsAt = rsss.getString(1);
		System.out.println("COME�A EMmmmmmm : " + endsAt);
			
		//Criando os os objetos
		Cidade cidade = new Cidade(idCity);
		Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
		req.setIsPago(isPago);
		
		return req;
	}

	//Seleciona todas as requisi��es associadas a um turista por meio do seu id
	//Entrada: Id do turista
	//Sa�da: Todas as requisi��es do turistas associadas com o seu id
	public ArrayList<Requisicao> selectHistoricoRequisicaoTurista(int idTurista) throws SQLException{
		ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
		PreparedStatement ps = connection.prepareStatement(SQLSelectHistoricoRequisicao);
		ps.setInt(1, idTurista);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
				//Pegando os valores dos atributos
				int id = rs.getInt("id");
				//System.out.println("idd: " + id);
				String title = rs.getString("title");
				System.out.println(title);
				String description = rs.getString("description");
				String startsAt = rs.getString("startsAt");
				String endsAt = rs.getString("endsAt");
				System.out.println("endsAT: " + endsAt);
				String minPrice = rs.getString("minPrice");
				String maxPrice = rs.getString("minPrice");
				int idTUser = rs.getInt("idTUser");
				int idCity = rs.getInt("idCity");
				String complement = rs.getString("complement");
				int isPago = rs.getInt("isPago");
				
				PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
				pss.setString(1, startsAt);
				ResultSet rss = pss.executeQuery();
				rss.next();
				startsAt = rss.getString(1);
				System.out.println("COME�A EM : " + startsAt);
				
				PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
				psss.setString(1, endsAt);
				System.out.println("setando :" + endsAt);
				ResultSet rsss = psss.executeQuery();
				rsss.next();
				endsAt = rsss.getString(1);
				System.out.println("TERMINA EM : " + endsAt);
					
				//Criando os os objetos
				Cidade cidade = new Cidade(idCity);
				Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
				req.setIsPago(isPago);
				requisicoes.add(req);
		}		
		return requisicoes;
	}
	
	//Insere as tags associadas 
	public void insertTagRequest(Requisicao req) throws SQLException{
		System.out.println("fun��o de inserir tags chamada");
		PreparedStatement ps = connection.prepareStatement(SQLInsertTagRequest,Statement.RETURN_GENERATED_KEYS);
		ArrayList<Tag> tags = new ArrayList<>();
		tags.add(req.getTags().get(0));
		System.out.println("Tamanho das tags: " + tags.size());
		int contador = 0;
		
		
		//Verifica se as tags n�o s�o repetidas. Insiro no bd apenas as que n�o s�o
		for(int a = 1;a < req.getTags().size();a++){
			int id = req.getTags().get(a).getId();
			System.out.println("ID DA TAG :" + id);
			
			
			for(int j = 0; j< tags.size(); j++){
				System.out.println("J:" +  j);
			
				if(id == tags.get(j).getId())
					contador++;
			}
				if(contador == 0 && id!=9){
					System.out.println("tag adicionada");
					tags.add(req.getTags().get(a));	
				}
			contador = 0;
		}

		//pego o Id de cada para inserir na entidade requestTag
		for(int i = 0;i <tags.size();i++){
			ps.setInt(1, req.getIdRequest());
			ps.setInt(2, tags.get(i).getId());
			ps.execute();
		}	
	}
}


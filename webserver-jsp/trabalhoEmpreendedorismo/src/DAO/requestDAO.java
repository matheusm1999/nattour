package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BO.Cidade;
import BO.Requisicao;

public class requestDAO {
	private String SQLInsertRequest = "INSERT INTO request (title,description,startsAt,endsAt,minPrice,maxPrice,idTuser,idCity,complement) VALUES (?,?,?,?,?,?,?,?,?)";
	private String SQLSelectHistoricoRequisicao = "SELECT * FROM request WHERE idTUser = ?";
	private String SQLInsertTagRequest = "INSERT INTO requesttag (idRequest,idTag) VALUES (?,?)";
	//private String SQLSelectOfferIdGuia =
	
	private Connection connection;
	
	public requestDAO(Connection connection){
		this.connection = connection;
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
			
			PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
			pss.setString(1, startsAt);
			ResultSet rss = pss.executeQuery();
			rss.next();
			startsAt = rss.getString(1);
			System.out.println("COMEÇA EMmmmmmm : " + startsAt);
			
			PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
			pss.setString(1, startsAt);
			ResultSet rsss = pss.executeQuery();
			rsss.next();
			endsAt = rsss.getString(1);
			System.out.println("COMEÇA EMmmmmmm : " + endsAt);
			//Criando os os objetos
			Cidade cidade = new Cidade(idCity);
			Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
			
			requisicoes.add(req);
		}
		return requisicoes;
	}

	//Seleciona uma requicição no banco de dados por meio da sua id
	//Entrada: Id da requisição a ser buscada na entidadde "Request" no banco de dados
	//Saída: Objeto requisição com os dados preenchidos, menos as informações referentes ao endereço
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
		
		PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
		pss.setString(1, startsAt);
		ResultSet rss = pss.executeQuery();
		rss.next();
		startsAt = rss.getString(1);
		System.out.println("COMEÇA EMmmmmmm : " + startsAt);
		
		PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
		pss.setString(1, startsAt);
		ResultSet rsss = pss.executeQuery();
		rsss.next();
		endsAt = rsss.getString(1);
		System.out.println("COMEÇA EMmmmmmm : " + endsAt);
			
		//Criando os os objetos
		Cidade cidade = new Cidade(idCity);
		Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
		
		return req;
	}

	//Seleciona todas as requisições associadas a um turista por meio do seu id
	//Entrada: Id do turista
	//Saída: Todas as requisições do turistas associadas com o seu id
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
				String minPrice = rs.getString("minPrice");
				String maxPrice = rs.getString("minPrice");
				int idTUser = rs.getInt("idTUser");
				int idCity = rs.getInt("idCity");
				String complement = rs.getString("complement");
				
				PreparedStatement pss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
				pss.setString(1, startsAt);
				ResultSet rss = pss.executeQuery();
				rss.next();
				startsAt = rss.getString(1);
				System.out.println("COMEÇA EMmmmmmm : " + startsAt);
				
				PreparedStatement psss = connection.prepareStatement("SELECT DATE_FORMAT(?, '%Y/%m/%d %H:%i')");
				pss.setString(1, startsAt);
				ResultSet rsss = pss.executeQuery();
				rsss.next();
				endsAt = rsss.getString(1);
				System.out.println("COMEÇA EMmmmmmm : " + endsAt);
					
				//Criando os os objetos
				Cidade cidade = new Cidade(idCity);
				Requisicao req = new Requisicao(id,title,description,startsAt,endsAt,complement,cidade,idTUser);
				requisicoes.add(req);
		}		
		return requisicoes;
	}
	
	//Insere as tags associadas 
	public void insertTagRequest(Requisicao req) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLInsertTagRequest,Statement.RETURN_GENERATED_KEYS);
		
		//pego o Id de cada para inserir na entidade requestTag
		for(int i = 0;i <req.getTags().size();i++){
			ps.setInt(1, req.getIdRequest());
			ps.setInt(2, req.getTags().get(i).getId());
			ps.execute();
		}
		
	}
}

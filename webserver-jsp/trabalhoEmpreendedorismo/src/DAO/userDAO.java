package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BO.Cidade;
import BO.Conta;
import BO.Requisicao;
import BO.User;

public class userDAO {
	
	private String SQLInsertUser = "INSERT INTO USER (email,name,hash,salt,idTRating,idGRating,idCity,isGuide,isTourist,docValue,docType,idFacebook,fotoFacebook,ddi,ddd,numeroTelefone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String SQLSelectUserIDFacebook = "SELECT * FROM user where idFacebook = ?";
	private String SQLUpdateUserIsGuia = "UPDATE user SET isGuide = 1,isTourist = 0 ,ddi = ?, ddd = ?, numeroTelefone = ? , emailPaypal = ? WHERE id = ?";
	private String SQLSelectUserID = "SELECT * FROM user WHERE id = ?";
	private Connection connection;
	
	public userDAO(Connection connection){
		this.connection = connection;
	}
	
	//Insere o usuário no banco de dados na entidade "User"
	//Entrada: Usuáio a ser inserido no banco de dados
	//Saída: Chave gerada após a inserção
	//Pré condição: Nenhuma
	//Pós condição: Usuário inserido no banco de dados na entidade "User"
	public User insertUser(User user) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(SQLInsertUser,Statement.RETURN_GENERATED_KEYS);
		
		String email = user.getEmail();
		String name = user.getName();
		//String hash = user.getHash();
		//int idTRating = user.getIdRating();
		//int idCity = user.getCidade().getIdCidade();
		String idFacebook = user.getIdFacebook();
		String fotoFacebook = user.getFotoFacebook();
		
		ps.setString(1, email);
		ps.setString(2, name);
		ps.setString(3, "a");
		ps.setString(4, "a");
		ps.setInt(5, 1); //mudar o TRating depois para outro valor, atualmente é 0
		ps.setInt(6, 1);
		ps.setInt(7, 1); //Atualmente é 0, mas preciso alterar depois (idCity).
		ps.setInt(8,0);
		ps.setInt(9, 1);
		ps.setString(10, "a");
		ps.setString(11, "a");
		ps.setString(12, idFacebook);
		ps.setString(13, fotoFacebook);
		ps.setInt(14, 0);
		ps.setInt(15, 0);
		ps.setInt(16, 0);
		
		ps.execute();
		
		ResultSet keys = ps.getGeneratedKeys();
		keys.first(); //Verdadeiro caso uma chave tenha sido gerada
		int key = keys.getInt(1);
		user.setUserID(key);
		return user;
	}

	
	//Consulta um usuário no banco de dados por meio do seu id 
	//Entrada: id do usuário a ser consultado
	//Saída: usuário com o id do Facebook associado
	//Pré condição: Esse id deve vir após a conexão com o Facebook
	//Pós condiçao:Nenhuma
	public User selectUserIDFacebook(String idFacebook) throws SQLException{
		User user = null;
		PreparedStatement ps = connection.prepareStatement(SQLSelectUserIDFacebook);
		ps.setString(1, idFacebook);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			//Pegando os valores dos atributos
			//String idFacebook = rs.getString("idFacebook");
			int id = rs.getInt("id");
			//System.out.println("idd: " + id);
			String name = rs.getString("name");
			//System.out.println(title);
			int idCity = rs.getInt("idCity");
			String fotoFacebook = rs.getString("fotoFacebook");
			int isGuide = rs.getInt("isGuide");
			int isTourist = rs.getInt("isTourist");
			String emailPaypal = "";
			int ddi = 0;
			int ddd = 0;
			int numeroTelefone = 0;
			if(isGuide == 1){
				emailPaypal = rs.getString("emailPaypal");
				ddi = rs.getInt("ddi");
				ddd = rs.getInt("ddd");
				numeroTelefone = rs.getInt("numeroTelefone");
			}
			
			//Criando os os objetos
			Cidade cidade = new Cidade(idCity);
			user = new User(id,idFacebook,fotoFacebook,cidade,name);
			user.setIsGuide(isGuide);
			user.setIsTourist(isTourist);
			user.setEmailPaypal(emailPaypal);
			user.setDdi(ddi);
			user.setDdd(ddd);
			user.setNumeroTelefone(numeroTelefone);
		}
		return user;
	}

	//Faz com que um registro na tabela User vire um guia, fazendo com que "isGuide = 1"
	//Entrada: Id do usuário a ser atualizado
	//Saída: Nenhuma
	//Pré condição: Nenhuma
	//Pós condição: campo "isGuide" da entidade "user" atualizado para 1
	public void updateIsGuia(User user) throws SQLException{
	    PreparedStatement ps = connection.prepareStatement(SQLUpdateUserIsGuia);
		
	    int id = user.getUserID();
	    int ddi = user.getDdi();
	    int ddd = user.getDdd();
	    int numeroTelefone = user.getNumeroTelefone();
	    String emailPaypal = user.getEmailPaypal();
	    
		ps.setInt(1, ddi); 
		ps.setInt(2, ddd);
		ps.setInt(3, numeroTelefone); 
		ps.setString(4, emailPaypal);
		ps.setInt(5,id);
		
		ps.execute();
	}

	//Busca o usuário no banco de dados, na entidade "user" por meio do seu ID
	//Entrada: Id do usuarío a ser buscado no banco de dados
	//Saída: Usuário associado ao ID passado nos parâmetros
	public User selectUserID(int idUsuario) throws SQLException{
		User user = null;
		PreparedStatement ps = connection.prepareStatement(SQLSelectUserID);
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		//Pegando os valores dos atributos
		int id = rs.getInt("id");
		//System.out.println("idd: " + id);
		String name = rs.getString("name");
		int idCity = rs.getInt("idCity");
		String idFacebook = rs.getString("idFacebook");
		String fotoFacebook = rs.getString("fotoFacebook");
		int isGuide = rs.getInt("isGuide");
		int isTourist = rs.getInt("isTourist");
		String email = rs.getString("email");
		
		String emailPaypal = "";
		int ddi = 0;
		int ddd = 0;
		int numeroTelefone = 0;
		if(isGuide == 1){
			System.out.println("É um guia, pegando infos adicionais");
			emailPaypal = rs.getString("emailPaypal");
			ddi = rs.getInt("ddi");
			ddd = rs.getInt("ddd");
			numeroTelefone = rs.getInt("numeroTelefone");
			System.out.println(ddi);
			System.out.println(ddd);
			System.out.println(numeroTelefone);
		}
		
		//Criando os os objetos
		Cidade cidade = new Cidade(idCity);
		user = new User(id,idFacebook,fotoFacebook,cidade,name,email);
		user.setIsGuide(isGuide);
		user.setIsTourist(isTourist);
		user.setEmailPaypal(emailPaypal);
		user.setDdi(ddi);
		user.setDdd(ddd);
		user.setNumeroTelefone(numeroTelefone);
		
		//System.out.println(user);
		return user;
	}
}
 
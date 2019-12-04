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
	
	private String SQLInsertUser = "INSERT INTO USER (email,hash,name,salt,idTRating,idGRating,idCity,isGuide,isTourist,docValue,docType,idFacebook,fotoFacebook) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String SQLSelectUser = "SELECT * FROM user where idFacebook = ?";
	private String SQLUpdateUserIsGuia = "UPDATE user SET isGuide = 1, ddd = ?, ddi = ?, numeroTelefone = ? WHERE id = ?";
	private Connection connection;
	
	public userDAO(Connection connection){
		this.connection = connection;
	}
	
	//Insere o usu�rio no banco de dados na entidade "User"
	//Entrada: Usu�io a ser inserido no banco de dados
	//Sa�da: Chave gerada ap�s a inser��o
	//Pr� condi��o: Nenhuma
	//P�s condi��o: Usu�rio inserido no banco de dados na entidade "User"
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
		ps.setInt(5, 1); //mudar o TRating depois para outro valor, atualmente � 0
		ps.setInt(6, 1);
		ps.setInt(7, 1); //Atualmente � 0, mas preciso alterar depois (idCity).
		ps.setInt(8,0);
		ps.setInt(9, 1);
		ps.setString(10, "a");
		ps.setString(11, "a");
		ps.setString(12, idFacebook);
		ps.setString(13, fotoFacebook);
		
		ps.execute();
		
		ResultSet keys = ps.getGeneratedKeys();
		keys.first(); //Verdadeiro caso uma chave tenha sido gerada
		int key = keys.getInt(1);
		user.setUserID(key);
		return user;
	}

	
	//Consulta um usu�rio no banco de dados por meio do seu id 
	//Entrada: id do usu�rio a ser consultado
	//Sa�da: usu�rio com o id do Facebook associado
	//Pr� condi��o: Esse id deve vir ap�s a conex�o com o Facebook
	//P�s condi�ao:Nenhuma
	public User selectUserID(String idFacebook) throws SQLException{
		User user = null;
		PreparedStatement ps = connection.prepareStatement(SQLSelectUser);
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
			
			//Criando os os objetos
			Cidade cidade = new Cidade(idCity);
			user = new User(id,idFacebook,fotoFacebook,cidade,name);
			user.setIsGuide(isGuide);
			user.setIsTourist(isTourist);
		}
		return user;
	}

	//Faz com que um registro na tabela User vire um guia, fazendo com que "isGuide = 1"
	//Entrada: Id do usu�rio a ser atualizado
	//Sa�da: Nenhuma
	//Pr� condi��o: Nenhuma
	//P�s condi��o: campo "isGuide" da entidade "user" atualizado para 1
	public void updateIsGuia(User user) throws SQLException{
	    PreparedStatement ps = connection.prepareStatement(SQLUpdateUserIsGuia);
		
	    int id = user.getUserID();
	    int ddi = user.getDdi();
	    int ddd = user.getDdd();
	    int numeroTelefone = user.getNumeroTelefone();
		ps.setInt(1, ddi); //mudar o TRating depois para outro valor, atualmente � 0
		ps.setInt(2, ddd);
		ps.setInt(3, numeroTelefone); //Atualmente � 0, mas preciso alterar depois (idCity).
		ps.setInt(4,id);
		
		ps.execute();
	}
}
 
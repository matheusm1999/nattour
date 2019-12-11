package Servicos;

import java.sql.Connection;
import java.sql.SQLException;

import BD.Conexao;
import BO.User;
import DAO.userDAO;

public class userServicos {

	
	//Busca um usuáiro no banco de dados
	//Entrada = id do usuário a ser buscado no banco de dados
	//Saída = usuário associado aquela id
	public User recuperarUsuarioId(int idUsuario){
		Connection connection = Conexao.getConnection();
		User user = new User();
		userDAO ud = new userDAO(connection);
		try {
			user = ud.selectUserID(idUsuario);
			System.out.println("d: " + user.getDdd());
			System.out.println("dd: " + user.getDdi());
			System.out.println("ddd: " + user.getNumeroTelefone());
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o usuário pelo id");
			e.printStackTrace();
		}
		return user;
	}
}

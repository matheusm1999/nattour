package Servicos;

import java.sql.Connection;
import java.sql.SQLException;

import BD.Conexao;
import BO.User;
import DAO.userDAO;

public class guiaServicos {
	
	public void virarGuia(User user){
		Connection connection = Conexao.getConnection();
		userDAO ud = new userDAO(connection);
		try {
			ud.updateIsGuia(user);
		} catch (SQLException e) {
			System.out.println("Erro ao transformar o turista em guia!");
			e.printStackTrace();
		}
	}

}

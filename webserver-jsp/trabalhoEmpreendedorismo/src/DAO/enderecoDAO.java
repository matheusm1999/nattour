package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class enderecoDAO {
	private Connection connection;
	
	public enderecoDAO(Connection connection){
		this.connection = connection;
	}
	
	//Retorna a chave prim�ria por meio da consulta especificada nos par�metros
	//Pr� condi��o: A consulta tem que ser um SELECT e precisa ser do tipo que retorna uma chave prim�ria
	//P�s condi��o: Nenhuma
	public int selectRetornaChave(String query) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(query);
				
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int chave = rs.getInt(1);
		System.out.println("chaveee: " + chave);
		return chave;
		
	}
	
	//Retorna a chave prim�ria por meio da consulta especificada nos par�metros
	//Pr� condi��o: A consulta tem que ser um SELECT e precisa ser do tipo que retorna um VARCHAR
	//P�s condi��o: Nenhuma
	public String selectRetornaString(String query) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(query);
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		rs.next();
			
		String resultado = rs.getString(1);
		return resultado;		
	}
		
	
	public int insertRetornaChave(String query) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		ps.execute();
		
		ResultSet keys = ps.getGeneratedKeys();
		keys.first(); //Verdadeiro caso uma chave tenha sido gerada
		int key = keys.getInt(1);
		return key;	
	}
}

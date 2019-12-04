package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class geralDAO {
	private Connection connection;

	public geralDAO(Connection connection){
		this.connection = connection;
	}
		
		//Retorna a chave primária por meio da consulta especificada nos parâmetros
		//Pré condição: A consulta tem que ser um SELECT e precisa ser do tipo que retorna um VARCHAR
		//Pós condição: Nenhuma
		public String selectRetornaString(String query) throws SQLException{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			rs.next();
				
			String resultado = rs.getString(1);
			return resultado;		
		}
		
		//Retorna a chave primária por meio da consulta especificada nos parâmetros
		//Pré condição: A consulta tem que ser um SELECT e precisa ser do tipo que retorna uma chave primária
		//Pós condição: Nenhuma
		public int selectRetornaInt(String query) throws SQLException{
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int chave = rs.getInt(1);
			System.out.println("chaveee: " + chave);
			return chave;
			
		}
		
		//Retorna a chave primária por meio da consulta especificada nos parâmetros
		//Pré condição: A consulta tem que ser um SELECT e precisa ser do tipo que retorna um VARCHAR
		//Pós condição: Nenhuma
		public float selectRetornaFloat(String query) throws SQLException{
			PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			rs.next();
				
			float resultado = rs.getFloat(1);
			return resultado;		
		}

		public int insertQualquer(String query) throws SQLException{
				PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				ps.execute();
				
				ResultSet keys = ps.getGeneratedKeys();
				keys.first(); //Verdadeiro caso uma chave tenha sido gerada
				int key = keys.getInt(1);
				return key;	
			}
		}


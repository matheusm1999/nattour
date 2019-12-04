package DAO;

import java.sql.Connection;

import BO.User;

public class ratingDAO {
	Connection connection;
	
	public ratingDAO(Connection connection){
		this.connection = connection;
	}

}

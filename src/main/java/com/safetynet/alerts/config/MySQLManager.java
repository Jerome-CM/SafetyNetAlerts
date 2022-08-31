package com.safetynet.alerts.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {
	
	private static MySQLManager instance = null;
	 
	private Connection connection = null;
	private String url = "jdbc:mysql://localhost:3306/application";
	private String utilisateur = "root";
	private String motDePasse = "";
 
	public Connection getConnection() {
		return connection;
	}
	
	public static synchronized MySQLManager getInstance() {
		if (instance == null) {
			instance = new MySQLManager();
		}
		return instance;
	}
 
	private MySQLManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
 
			}
		}
	}

}

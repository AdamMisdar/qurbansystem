package com.qurban.connection;

import java.sql.*;

public class ConnectionPool {
	
	private ConnectionPool() {
		
	}
	
	// Connection instance..
	private Connection connection = getConnection();
	
	private static ConnectionPool instance = null;
	
	public static ConnectionPool getInstance(){
		if (instance==null)
			instance = new ConnectionPool();
		return instance;
	}
	
	/**
	 * Getting connection from connection pool.
	 *
	 * @see ConnectionPool
	 * @throws SQLException
	 */
	
	public Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String username = "kyayppqzhldtcq";
		String password = "1d059bfbd68536ca18008409a21d767cbfc2062e05a0e9386362d60ee26a7ed2";
		String dbUrl = "jdbc:postgresql://"
						+ "ec2-52-22-136-117.compute-1.amazonaws.com:5432"
						+ "/ddts7lotslrr09?sslmode=require"; // '?sslmode=require' : it's to connect to the database from our machine
		
		try {
			return DriverManager.getConnection(dbUrl, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
		}
}

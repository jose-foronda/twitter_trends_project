package com.protalento.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManager {
	private static final Logger logger = LogManager.getLogger();

	private Connection connection;
	private static ConnectionManager connectionManager;

	private ConnectionManager() {
		setConnection();
		logger.info("se crea la instancia de " + this);
	}

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public Connection getConnection() {
		return connection;
	}

	private void setConnection() { 
		final String DRIVER_DB = System.getenv("DRIVER_DB");
		final String URL_DB = System.getenv("URL_DB");
		final String USER_DB = System.getenv("USER_DB");
		final String PASSWORD_DB = System.getenv("PASSWORD_DB");
		
		try {
			Class.forName(DRIVER_DB);
			connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
		} catch (ClassNotFoundException e) { 
			logger.error(e);
		} catch (SQLException e) { 
			logger.error(e);
		}
	}
	
}

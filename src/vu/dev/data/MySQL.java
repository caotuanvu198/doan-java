package vu.dev.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import vu.dev.tools.DBConstant;

public class MySQL {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
	     String connectionURL = "jdbc:mysql://" + DBConstant.HOSTNAME + ":3306/" + DBConstant.DB_NAME;
	 
	     Connection conn = DriverManager.getConnection(connectionURL, DBConstant.DB_USER,
	    		 DBConstant.DB_PASS);
	     return conn;
	}
}

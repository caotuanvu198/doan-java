package binh.dev.data;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnect {	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MySQL.getConnection();
	}
	
}

package vu.dev.data.implement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import vu.dev.data.DatabaseConnect;
import vu.dev.data.model.Room;

public abstract class ModelDAO<T> {
	protected Connection conn;

	public ModelDAO() {
		try {
			conn = DatabaseConnect.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

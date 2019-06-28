package vu.dev.data.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vu.dev.data.model.Customer;
import vu.dev.data.model.Room;
import vu.dev.tools.DBConstant;

public class RoomDAO extends ModelDAO {
	public boolean save(Room room) {
		String sql = "insert into " + DBConstant.TABLE_ROOM + " values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, room.getId());
			preparedStmt.setString(2, room.getName());
			preparedStmt.setInt(3, room.getStatus());
			preparedStmt.setString(4, room.getDesc());
			preparedStmt.setInt(5, room.getPrice());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Room room) {
		String sql = "UPDATE `" + DBConstant.TABLE_ROOM + "` SET `name`=?,`status`=?,`desc`=?, `price`=? WHERE id=?";

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);

			preparedStmt.setString(1, room.getName());
			preparedStmt.setInt(2, room.getStatus());
			preparedStmt.setString(3, room.getDesc());

			preparedStmt.setInt(4, room.getId());
			preparedStmt.setInt(5, room.getPrice());
			preparedStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		String sql = "delete from " + DBConstant.TABLE_ROOM + " where id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<Room> findAll() {
		List<Room> rs = new ArrayList<>();
		String sql = "select * from " + DBConstant.TABLE_ROOM;
		try {
			Statement stats = conn.createStatement();
			ResultSet resultSet = stats.executeQuery(sql);

			while (resultSet.next()) {
				Room room = new Room(resultSet.getInt(DBConstant.ROOM_ID), resultSet.getString(DBConstant.ROOM_NAME),
						resultSet.getString(DBConstant.ROOM_DESC), resultSet.getInt(DBConstant.ROOM_STATUS),
						resultSet.getInt(DBConstant.ROOM_PRICE));
				rs.add(room);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public Room findByField(String field, String condition) {
		String sql = "select * from `" + DBConstant.TABLE_ROOM + "` where `" + field + "` = '" + condition + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet resultSet;

			resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Room room = new Room(resultSet.getInt(DBConstant.ROOM_ID), resultSet.getString(DBConstant.ROOM_NAME),
						resultSet.getString(DBConstant.ROOM_DESC), resultSet.getInt(DBConstant.ROOM_STATUS),
						resultSet.getInt(DBConstant.ROOM_PRICE));
				return room;
			}
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

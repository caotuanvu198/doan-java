package vu.dev.data.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vu.dev.data.model.HireInformation;
import vu.dev.tools.DBConstant;

public class HireInfoDAO extends ModelDAO {

	public boolean save(HireInformation hif) {
		// TODO Auto-generated method stub
		String sql = "insert into " + DBConstant.TABLE_HIRE_INFOR + " values(null,?,?,?,?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);

			preparedStmt.setInt(1, hif.getCustomer().getId());
			preparedStmt.setInt(2, hif.getRoom().getId());
			preparedStmt.setString(3, hif.getDateFrom());
			preparedStmt.setString(4, hif.getDateEnd());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean update(HireInformation hif) {
		String sql = "UPDATE `" + DBConstant.TABLE_HIRE_INFOR
				+ "` SET `customer_id`=?,`room_id`=?,`day_from`=?,`end_from`=? WHERE id=?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);

			preparedStmt.setInt(1, hif.getId());
			preparedStmt.setInt(2, hif.getCustomer().getId());
			preparedStmt.setInt(3, hif.getRoom().getId());
			preparedStmt.setString(4, hif.getDateFrom());
			preparedStmt.setString(5, hif.getDateEnd());
			preparedStmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		String sql = "delete from " + DBConstant.TABLE_HIRE_INFOR + " where id = ?";
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

	private String report() {
		// TODO Auto-generated method stub
		String sql = "SELECT hr.id as id, r.price as price, c.name as name_cus, r.name as name_room, hr.day_from as date_start, hr.end_from as date_end, if(r.status = 1, \"Active\",\"Inactive\") as status FROM`hire_info` as hr, room as r, customer as c WHERE hr.customer_id = c.id and hr.room_id = r.id";
		return sql;
	}

	public List<HireInformation> statistics() {
		List<HireInformation> rs = new ArrayList<>();

		try {
			Statement stats = conn.createStatement();
			ResultSet resultSet = stats.executeQuery(report());

			while (resultSet.next()) {
				HireInformation listHired = new HireInformation(resultSet.getInt(DBConstant.TABLE_HIRE_ID),
						resultSet.getString(DBConstant.HIRE_CUS_NAME), resultSet.getString(DBConstant.HIRE_ROOM_NAME),
						resultSet.getString(DBConstant.HIRE_DAY_FROM), resultSet.getString(DBConstant.HIRE_DAY_END),
						resultSet.getString(DBConstant.HIRE_STATUS), resultSet.getInt(DBConstant.ROOM_PRICE));
				rs.add(listHired);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

}

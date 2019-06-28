package vu.dev.data.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vu.dev.data.model.Customer;
import vu.dev.tools.DBConstant;

public class CustomerDAO extends ModelDAO {

	public boolean save(Customer cus) {
		String sql = "insert into " + DBConstant.TABLE_CUSTOMER + " values(?,?,?,?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, cus.getId());
			preparedStmt.setString(2, cus.getName());
			preparedStmt.setInt(3, cus.getOld());
			preparedStmt.setString(4, cus.getSex());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Customer cus) {
		String sql = "update " + DBConstant.TABLE_CUSTOMER + " set name=?, old=?, sex=? where id = ?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, cus.getName());
			preparedStmt.setInt(2, cus.getOld());
			preparedStmt.setString(3, cus.getSex());
			preparedStmt.setInt(4, cus.getId());
			preparedStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		String sql = "delete from " + DBConstant.TABLE_CUSTOMER + " where id = ?";
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

	public List<Customer> findAll() {
		List<Customer> rs = new ArrayList<>();
		String sql = "select * from " + DBConstant.TABLE_CUSTOMER;
		try {
			Statement stats = conn.createStatement();
			ResultSet resultSet = stats.executeQuery(sql);

			while (resultSet.next()) {
				Customer cus = new Customer(resultSet.getInt(DBConstant.CUSTOMER_ID),
						resultSet.getString(DBConstant.CUSTOMER_NAME), resultSet.getString(DBConstant.CUSTOMER_SEX),
						resultSet.getInt(DBConstant.CUSTOMER_OLD));
				cus.setId(resultSet.getInt(DBConstant.CUSTOMER_ID));
				rs.add(cus);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public Customer findByField(String field, String condition) {
		String sql = "select * from `" + DBConstant.TABLE_CUSTOMER + "` where `" + field + "` = '" + condition + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer cus = new Customer(rs.getInt(DBConstant.CUSTOMER_ID), rs.getString(DBConstant.CUSTOMER_NAME),
						rs.getString(DBConstant.CUSTOMER_SEX), rs.getInt(DBConstant.CUSTOMER_OLD));
				return cus;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

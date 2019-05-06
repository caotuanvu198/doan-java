package binh.dev.data.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import binh.dev.data.DatabaseConnect;
import binh.dev.data.model.Customer;
import binh.dev.tools.DBConstant;

public class CustomerDAO {
	private Connection conn;
	
	public CustomerDAO() {
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
	
	public boolean save(Customer cus) {
		String sql = "insert into " + DBConstant.TABLE_CUSTOMER + " values(null,?,?,?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, cus.getName());
			preparedStmt.setInt(2, cus.getOld());
			preparedStmt.setString(3, cus.getSex());
			return preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int update(Customer cus) {
		String sql = "update " + DBConstant.TABLE_CUSTOMER + " set name=?, old=?, sex=? where id = ?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, cus.getName());
			preparedStmt.setInt(2, cus.getOld());
			preparedStmt.setString(3, cus.getSex());
			preparedStmt.setInt(4, cus.getId());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public boolean delete(int id) {
		String sql = "delete from " + DBConstant.TABLE_CUSTOMER + " where id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			return preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Customer> findAll(){
		List<Customer> rs = new ArrayList<>();
		String sql = "select * from " + DBConstant.TABLE_CUSTOMER;
		try {
			Statement stats = conn.createStatement();
			ResultSet resultSet = stats.executeQuery(sql);
			
			while(resultSet.next()) {
				Customer cus = new Customer(resultSet.getString(DBConstant.CUSTOMER_NAME), 
						resultSet.getInt(DBConstant.CUSTOMER_OLD), 
						resultSet.getString(DBConstant.CUSTOMER_SEX));
				cus.setId(resultSet.getInt(DBConstant.CUSTOMER_ID));
				rs.add(cus);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public Customer findById(int id) {
		String sql = "select * from " + DBConstant.TABLE_CUSTOMER + " where id = ?";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStmt.executeQuery(sql);
			
			while(resultSet.next()) {
				Customer cus = new Customer(resultSet.getString(DBConstant.CUSTOMER_NAME), 
						resultSet.getInt(DBConstant.CUSTOMER_OLD), 
						resultSet.getString(DBConstant.CUSTOMER_SEX));
				cus.setId(resultSet.getInt(DBConstant.CUSTOMER_ID));
				return cus;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

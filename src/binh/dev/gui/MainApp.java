package binh.dev.gui;

import java.util.List;

import binh.dev.data.implement.CustomerDAO;
import binh.dev.data.model.Customer;

public class MainApp {
	public static void main(String[] args) {
		//Insert
		CustomerDAO customerDAO = new CustomerDAO();
		Customer cus = new Customer("Binh", 10, "Male");
		customerDAO.save(cus);
		
		//Read
		List<Customer> listCustomer = customerDAO.findAll();
		for (Customer customer : listCustomer) {
			System.out.println(String.format("Id:%d - Name: %s - Old:%d - Sex: %s", 
					customer.getId(), customer.getName(), 
					customer.getOld(), customer.getSex()));
		}
		
		//Update
		if(listCustomer.size() > 0) {
			Customer cusChange = listCustomer.get(0);
			cusChange.setName("Change name");
			int rs = customerDAO.update(cusChange);
			System.out.println(rs);
		}
		
		//Delete
		customerDAO.delete(2);
	}
}

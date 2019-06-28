package vu.dev.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import vu.dev.data.implement.CustomerDAO;
import vu.dev.data.model.Customer;
import vu.dev.tools.ConvertData;
import vu.dev.tools.ModelDialog;
import vu.dev.tools.Slang;

public class CustomerView {
	public CustomerView() {
		// TODO Auto-generated constructor stub

		// Start ModelDAO
		CustomerDAO ctDAO = new CustomerDAO();

		// create JFrame and JTable
		JFrame frame = new JFrame();
		frame.setTitle("Manager Customer !");
		JTable table = new JTable();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Id", "Name", "Sex", "Old" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);

		// create JLable

		JLabel lId = new JLabel("Id: ");
		JLabel lName = new JLabel("Name: ");
		JLabel lSex = new JLabel("Sex: ");
		JLabel lOld = new JLabel("Old: ");

		// create JTextFields
		JTextField textId = new JTextField();
		JTextField textFname = new JTextField();
		JTextField textOld = new JTextField();
		JTextField textSex = new JTextField();

		// create JButtons
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");

		// paramers setBounds(x, y, width, height);
		lId.setBounds(10, 220, 50, 25);
		lName.setBounds(10, 250, 50, 25);
		lSex.setBounds(10, 280, 50, 25);
		lOld.setBounds(10, 310, 50, 25);

		textId.setBounds(70, 220, 100, 25);
		textFname.setBounds(70, 250, 100, 25);
		textSex.setBounds(70, 280, 100, 25);
		textOld.setBounds(70, 310, 100, 25);

		btnAdd.setBounds(200, 220, 100, 25);
		btnUpdate.setBounds(200, 265, 100, 25);
		btnDelete.setBounds(200, 310, 100, 25);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1100, 200);

		frame.setLayout(null);

		frame.add(pane);

		// add JLableFields to the jframe
		frame.add(lId);
		frame.add(lName);
		frame.add(lOld);
		frame.add(lSex);

		// add JTextFields to the jframe
		frame.add(textId);
		frame.add(textFname);
		frame.add(textOld);
		frame.add(textSex);

		// add JButtons to the jframe
		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnUpdate);

		// create an array of objects to set the row data
		Object[] row = new Object[4];

		// take data in table
		List<Customer> listCustomer = ctDAO.findAll();

		for (Customer customer : listCustomer) {
			row[0] = customer.getId();
			row[1] = customer.getName();
			row[2] = customer.getSex();
			row[3] = customer.getOld();

			model.addRow(row);
		}

		// button add row
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// get data is archived database
				int id = ConvertData.toInt(textId.getText());
				String name = textFname.getText();
				String sex = textSex.getText();
				int old = ConvertData.toInt(textOld.getText());

				// save
				Customer c = new Customer(id, name, sex, old);
				Boolean flag = ctDAO.save(c);

				if (flag) {
					ModelDialog.alert(Slang.success_created);
					// add row to the model

					row[0] = ConvertData.toInt(textId.getText());
					row[1] = textFname.getText();
					row[2] = textSex.getText();
					row[3] = textOld.getText();

					model.addRow(row);

				} else {
					ModelDialog.alert(Slang.error);
				}

			}
		});

		// button remove row
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();

				if (i >= 0) {
					// remove a row from jtable
					int id = 0;
					try {
						id = (int) model.getValueAt(i, 0);
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println(e2.getMessage());
					} finally {
						id = (int) model.getValueAt(i, 0);
					}

					Boolean flag = ctDAO.delete(id);
					if (flag) {
						ModelDialog.alert(Slang.success_deleted);
						model.removeRow(i);
					} else {
						ModelDialog.alert(Slang.error);
						System.exit(0);
					}
				} else {
					ModelDialog.alert(Slang.not_choose);
				}
			}
		});

		// get selected row data From table to textfields
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();

				textId.setText(model.getValueAt(i, 0).toString());
				textFname.setText(model.getValueAt(i, 1).toString());
				textSex.setText(model.getValueAt(i, 2).toString());
				textOld.setText(model.getValueAt(i, 3).toString());
			}
		});

		// button update row
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();

				if (i >= 0) {
					// get data is archived database
					int id = ConvertData.toInt(textId.getText());
					String name = textFname.getText();
					String sex = textSex.getText();
					int old = ConvertData.toInt(textOld.getText());

					Customer cus = new Customer(id, name, sex, old);
					Boolean flag = ctDAO.update(cus);

					if (flag) {
						model.setValueAt(textId.getText(), i, 0);
						model.setValueAt(textFname.getText(), i, 1);
						model.setValueAt(textSex.getText(), i, 2);
						model.setValueAt(textOld.getText(), i, 3);

						ModelDialog.alert(Slang.success_updated);
					} else {
						ModelDialog.alert(Slang.error);
					}
				}
			}
		});

		frame.setSize(1100, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

}

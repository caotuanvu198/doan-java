package vu.dev.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import vu.dev.data.implement.CustomerDAO;
import vu.dev.data.implement.RoomDAO;
import vu.dev.data.model.Customer;
import vu.dev.data.model.Room;
import vu.dev.tools.ConvertData;
import vu.dev.tools.ModelDialog;
import vu.dev.tools.Slang;

public class RoomView {
	public RoomView() {
		// TODO Auto-generated constructor stub

		// Start ModelDAO
		RoomDAO rDAO = new RoomDAO();

		// create JFrame and JTable
		JFrame frame = new JFrame();
		frame.setTitle("Manager Room !");
		JTable table = new JTable();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Id", "Name", "Status", "Desc", "Price" };
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
		JLabel lStatus = new JLabel("Status: ");
		JLabel lDesc = new JLabel("Desc: ");
		JLabel lPrice = new JLabel("Price: ");

		// create JTextFields
		JTextField textId = new JTextField();
		JTextField textFname = new JTextField();
		JTextField textStatus = new JTextField();
		JTextArea textDesc = new JTextArea();
		JTextField textPrice = new JTextField();

		// create JButtons
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");

		// paramers setBounds(x, y, width, height);
		lId.setBounds(10, 220, 50, 25);
		lName.setBounds(10, 250, 50, 25);
		lStatus.setBounds(10, 280, 50, 25);
		lDesc.setBounds(10, 310, 50, 25);
		lPrice.setBounds(10, 340, 50, 25);

		textId.setBounds(70, 220, 300, 25);
		textFname.setBounds(70, 250, 300, 25);
		textStatus.setBounds(70, 280, 300, 25);
		textDesc.setBounds(70, 310, 300, 30);
		textPrice.setBounds(70, 340, 300, 30);

		btnAdd.setBounds(400, 220, 100, 25);
		btnUpdate.setBounds(400, 265, 100, 25);
		btnDelete.setBounds(400, 310, 100, 25);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1100, 200);

		frame.setLayout(null);

		frame.add(pane);

		// add JLableFields to the jframe
		frame.add(lId);
		frame.add(lName);
		frame.add(lStatus);
		frame.add(lDesc);
		frame.add(lPrice);

		// add JTextFields to the jframe
		frame.add(textId);
		frame.add(textFname);
		frame.add(textStatus);
		frame.add(textDesc);
		frame.add(textPrice);

		// add JButtons to the jframe
		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnUpdate);

		// create an array of objects to set the row data
		Object[] row = new Object[5];

		// take data in table
		List<Room> listRoom = rDAO.findAll();

		for (Room room : listRoom) {
			row[0] = room.getId();
			row[1] = room.getName();
			row[2] = room.getStatus();
			row[3] = room.getDesc();
			row[4] = room.getPrice();

			model.addRow(row);
		}

		// button add row
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get data is archived database
				int id = ConvertData.toInt(textId.getText());
				String name = textFname.getText();
				String desc = textDesc.getText();
				int status = ConvertData.toInt(textStatus.getText());
				int price = ConvertData.toInt(textPrice.getText());

				// save
				Room room = new Room(id, name, desc, status, price);
				Boolean flag = rDAO.save(room);

				if (flag) {
					ModelDialog.alert(Slang.success_created);
					// add row to the model

					row[0] = ConvertData.toInt(textId.getText());
					row[1] = textFname.getText();
					row[2] = textStatus.getText();
					row[3] = textDesc.getText();
					row[4] = textPrice.getText();

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

					Boolean flag = rDAO.delete(id);
					if (flag) {
						ModelDialog.alert(Slang.success_deleted);
						model.removeRow(i);
					} else {
						ModelDialog.alert(Slang.error);
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
				textStatus.setText(model.getValueAt(i, 2).toString());
				textDesc.setText(model.getValueAt(i, 3).toString());
				textPrice.setText(model.getValueAt(i, 4).toString());
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
					int id, status, price;
					try {
						id = ConvertData.toInt(textId.getText());
						status = ConvertData.toInt(textStatus.getText());
						price = ConvertData.toInt(textPrice.getText());
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println(e2.getMessage());
					} finally {
						id = ConvertData.toInt(textId.getText());
						status = ConvertData.toInt(textStatus.getText());
						price = ConvertData.toInt(textPrice.getText());
					}

					String name = textFname.getText();
					String desc = textDesc.getText();

					Room room = new Room(id, name, desc, status, price);
					Boolean flag = rDAO.update(room);

					if (flag) {
						int id_model = ConvertData.toInt(textId.getText());
						model.setValueAt(id_model, i, 0);
						model.setValueAt(textFname.getText(), i, 1);
						model.setValueAt(textStatus.getText(), i, 2);
						model.setValueAt(textDesc.getText(), i, 3);
						model.setValueAt(textPrice.getText(), i, 4);

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

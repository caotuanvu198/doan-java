package vu.dev.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import vu.dev.data.implement.CustomerDAO;
import vu.dev.data.implement.HireInfoDAO;
import vu.dev.data.implement.RoomDAO;
import vu.dev.data.model.Customer;
import vu.dev.data.model.HireInformation;
import vu.dev.data.model.Room;
import vu.dev.tools.ConvertData;
import vu.dev.tools.ModelDialog;
import vu.dev.tools.Slang;

public class StatisticalView {
	static int idItem = 0;

	// TODO Auto-generated constructor stub
	public StatisticalView() {
		// Start ModelDAO
		HireInfoDAO hired = new HireInfoDAO();
		RoomDAO rDAO = new RoomDAO();
		CustomerDAO cusDAO = new CustomerDAO();

		// create JFrame and JTable
		JFrame frame = new JFrame();
		frame.setTitle("Statistical !");
		JTable table = new JTable();

		Object[] columns = { "Id", "Customer", "Room", "Date Start", "Date End", "Status", "Price Room" };
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

		// ==============================START ROOMS
		// =====================================//
		// create JLable
		JLabel listRoom = new JLabel("List the rooms: ");
		JLabel lId = new JLabel("Id: ");
		JLabel lName = new JLabel("Name: ");
		JLabel lStatus = new JLabel("Status: ");
		JLabel lDesc = new JLabel("Desc: ");
		JLabel lPrice = new JLabel("Price: ");

		// create JTextFields list rooms
		JTextField textId = new JTextField();
		JComboBox jComboName = new JComboBox();
		JTextField textStatus = new JTextField();
		JTextArea textDesc = new JTextArea();
		JTextField textPrice = new JTextField();

		// paramers setBounds(x, y, width, height); set rooms
		listRoom.setBounds(10, 200, 150, 25);
		lId.setBounds(10, 220, 50, 25);
		lName.setBounds(10, 250, 50, 25);
		lStatus.setBounds(10, 280, 50, 25);
		lDesc.setBounds(10, 310, 50, 25);
		lPrice.setBounds(10, 330, 50, 25);

		textId.setBounds(70, 220, 300, 25);
		jComboName.setBounds(70, 250, 300, 25);
		textStatus.setBounds(70, 280, 300, 25);
		textDesc.setBounds(70, 310, 300, 30);
		textPrice.setBounds(70, 340, 300, 30);
		// ==============================END ROOMS
		// =====================================//

		// ====================== BTN=======================//

		// ==============================START CUSTOMERS
		// =====================================//
		// create JLable Customer
		JLabel listCus = new JLabel("List the customers: ");
		JLabel CId = new JLabel("Id: ");
		JLabel CName = new JLabel("Name: ");
		JLabel COld = new JLabel("Old: ");
		JLabel CSex = new JLabel("Sex: ");

		// create JTextFields list rooms
		JTextField CtextId = new JTextField();
		JComboBox CjComboName = new JComboBox();
		JTextField CtextOld = new JTextField();
		JTextField CtextSex = new JTextField();

		listCus.setBounds(400, 200, 150, 25);
		CId.setBounds(400, 220, 50, 25);
		CName.setBounds(400, 250, 50, 25);
		COld.setBounds(400, 280, 50, 25);
		CSex.setBounds(400, 310, 50, 25);

		CtextId.setBounds(550, 220, 300, 25);
		CjComboName.setBounds(550, 250, 300, 25);
		CtextOld.setBounds(550, 280, 300, 25);
		CtextSex.setBounds(550, 310, 300, 25);

		// ==============================END CUSTOMERS
		// =====================================//

		// create JButtons
		JButton btnAdd = new JButton("Hiring room");
		JButton btnDelete = new JButton("Delete");

		btnAdd.setBounds(530, 350, 150, 25);
		btnDelete.setBounds(530, 380, 150, 25);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1100, 200);

		frame.setLayout(null);

		frame.add(pane);

		// ==============================START ROOMS

		frame.add(listRoom);
		frame.add(lId);
		frame.add(lName);
		frame.add(lStatus);
		frame.add(lDesc);
		frame.add(lPrice);

		// add JTextFields to the jframe
		frame.add(textId);
		frame.add(jComboName);
		frame.add(textStatus);
		frame.add(textDesc);
		frame.add(textPrice);

		// ==============================END ROOMS=====================

		// ==============================START CUS========================
		// add JLableFields to the jframe
		frame.add(listCus);
		frame.add(CId);
		frame.add(CName);
		frame.add(COld);
		frame.add(CSex);

		// add JTextFields to the jframe
		frame.add(CtextId);
		frame.add(CjComboName);
		frame.add(CtextOld);
		frame.add(CtextSex);

		frame.add(btnAdd);
		frame.add(btnDelete);

		// =====================LIST ROOMS
		List<Room> listRoomValue = rDAO.findAll();

		for (Room r : listRoomValue) {
			jComboName.addItem(r.getName());
		}
		// add listener while change
		jComboName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object cmboitem = jComboName.getSelectedItem();

				Room r = rDAO.findByField("name", (String) cmboitem);

				textId.setText(ConvertData.toString(r.getId()));
				textStatus.setText(ConvertData.toString(r.getStatus()));
				textDesc.setText(r.getDesc());
				textPrice.setText(ConvertData.toString(r.getPrice()));
			}
		});

		// =====================LIST CUS
		// take data in table
		List<Customer> listCusValue = cusDAO.findAll();

		for (Customer cus : listCusValue) {
			CjComboName.addItem(cus.getName());
		}

		CjComboName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object cusName = CjComboName.getSelectedItem();

				Customer cus = cusDAO.findByField("name", (String) cusName);
				CtextId.setText(ConvertData.toString(cus.getId()));
				CtextOld.setText(ConvertData.toString(cus.getOld()));
				CtextSex.setText(cus.getSex());
			}
		});

		// create an array of objects to set the row data
		Object[] row = new Object[7];

		// take data in table
		List<HireInformation> listHired = hired.statistics();

		for (HireInformation hireInformation : listHired) {
			row[0] = hireInformation.getId();
			row[1] = hireInformation.getCusName();
			row[2] = hireInformation.getRoomName();
			row[3] = hireInformation.getDateFrom();
			row[4] = hireInformation.getDateEnd();
			row[5] = hireInformation.getStatus();
			row[6] = hireInformation.getPriceRoom();

			model.addRow(row);
			idItem = hireInformation.getId();
		}

		// button add row
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textId.getText().length() != 0) {
					// get data is archived input || ROOM
					int id = ConvertData.toInt(textId.getText());
					String name = (String) jComboName.getSelectedItem();
					String desc = textDesc.getText();
					int status = ConvertData.toInt(textStatus.getText());
					int price = ConvertData.toInt(textPrice.getText());

					Room room = new Room(id, name, desc, status, price);

					// get data is archived input || CUS
					int Cid = ConvertData.toInt(CtextId.getText());
					String Cname = (String) CjComboName.getSelectedItem();
					String Csex = CtextSex.getText();
					int Cold = ConvertData.toInt(CtextOld.getText());
					Customer customer = new Customer(Cid, Cname, Csex, Cold);

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					String dateFrom = dtf.format(now);

					Boolean flag = hired.save(new HireInformation(Cid, customer, room, dateFrom, null));
					
					if (flag) {
						ModelDialog.alert(Slang.success_created);
						// add row to the model

						row[0] = idItem += 1;
						row[1] = customer.getName();
						row[2] = room.getName();
						row[3] = dateFrom;
						row[4] = null;
						row[5] = (room.getStatus() == Room.ACTIVE) ? "active" : "inActive";
						row[6] = room.getPrice();

						model.addRow(row);

					} else {
						ModelDialog.alert(Slang.error);
					}
				} else {
					ModelDialog.alert(Slang.not_choose);
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
					int id = (int) model.getValueAt(i, 0);

					Boolean flag = hired.delete(id);
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

		frame.setSize(1100, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

}

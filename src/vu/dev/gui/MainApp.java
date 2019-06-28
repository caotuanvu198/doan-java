package vu.dev.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import vu.dev.data.implement.CustomerDAO;
import vu.dev.data.model.Customer;
import vu.dev.gui.CustomerView;
import vu.dev.tools.ModelDialog;
import vu.dev.tools.Slang;

public class MainApp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 400, 100);
		setTitle("Managerment Rooms !");
		// all item in Jpanel
		contentPane = new JPanel();

		// set panel
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(255, 0, 255), null));
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel center = new JPanel();

		// group btn
		JButton btn_cus = createJButton("Customer");
		JButton btn_room = createJButton("Room");
		JButton btn_statistic = createJButton("Statistical");
		JButton btnLogOut = createJButton("LogOut");

		center.add(btn_cus);
		center.add(btn_room);
		center.add(btn_statistic);
		center.add(btnLogOut);

		contentPane.add(center, BorderLayout.CENTER);
	}

	private JButton createJButton(String buttonName) {
		JButton btn = new JButton(buttonName);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Customer") {
					CustomerView ctv = new CustomerView();
				} else if (e.getActionCommand() == "Room") {
					RoomView rv = new RoomView();
				} else if (e.getActionCommand() == "Statistical") {
					StatisticalView statiscal = new StatisticalView();
				} else {
					java.awt.Window win[] = java.awt.Window.getWindows();
					for (int i = 0; i < win.length; i++) {
						win[i].dispose();
					}
					ModelDialog.alert(Slang.done_logout);
					Login lg = new Login();
					lg.handlingEvents();
				}
			}
		});
		return btn;
	}

}

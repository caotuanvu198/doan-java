package vu.dev.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vu.dev.tools.ModelDialog;
import vu.dev.tools.Slang;

public class Login extends JFrame {
	JLabel l1, l2, l3, l4;
	JTextField uname;
	JButton btn1;
	JPasswordField pass;

	Login() {
		JFrame frame = new JFrame("Login Form");

		l1 = new JLabel("Login Form");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));

		l2 = new JLabel("Username");
		l3 = new JLabel("Password");

		uname = new JTextField();
		pass = new JPasswordField();
		btn1 = new JButton("Login");

		uname.setText("management@vu.hueic.vn");
		pass.setText("123456");

		l1.setBounds(100, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);

		uname.setBounds(300, 70, 200, 30);
		pass.setBounds(300, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);

		frame.add(l1);
		frame.add(l2);
		frame.add(uname);
		frame.add(l3);
		frame.add(pass);
		frame.add(btn1);

		frame.setLayout(null);
		frame.setBounds(310, 200, 700, 300);
		frame.setVisible(true);
	}

	public void handlingEvents() {
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = uname.getText();
				String password = pass.getText();

				if (username.equals("management@vu.hueic.vn") && password.equals("123456")) {

					MainApp main = new MainApp();
					main.setVisible(true);
					main.setTitle("Welcome:" + username);
				} else {
					ModelDialog.alert(Slang.login_failed);
				}
			}
		});
	};

	public static void main(String[] args) {
		Login login = new Login();
		login.handlingEvents();
	}
}
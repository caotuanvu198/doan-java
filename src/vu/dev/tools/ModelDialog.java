package vu.dev.tools;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModelDialog {
	public static void alert(String messages) {
		// TODO Auto-generated method stub
		JFrame parent = new JFrame();
		JOptionPane.showMessageDialog(parent, messages);
	}
}

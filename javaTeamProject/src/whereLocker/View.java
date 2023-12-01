package whereLocker;

import java.awt.EventQueue; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JList;

public class View extends JFrame {
	Model model = new Model();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	JTextArea textArea;

	
	public View() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 594);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 920, 557);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("굴림", Font.PLAIN, 21));
		txtSearch.setText("ex) 천안");
		txtSearch.setBounds(121, 10, 214, 41);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("역 검색");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 21));
		lblNewLabel.setBounds(12, 10, 84, 41);
		panel.add(lblNewLabel);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setToolTipText("");
		btnSearch.setFont(new Font("굴림", Font.BOLD, 21));
		btnSearch.setBounds(351, 10, 91, 41);
		panel.add(btnSearch);
		
		
		
		
		textArea = new JTextArea();
		textArea.setBounds(12, 71, 430, 435);
		panel.add(textArea);
	}
	public String getSearch() {
		return txtSearch.getText();
	}
}

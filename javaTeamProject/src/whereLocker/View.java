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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class View extends JFrame {
	Model model = new Model();
	private Controller controller ;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	JTextArea textAreaLocker;
	JTextArea textAreaToil;
	JTextArea textAreaAtm;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	

	
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
		panel.setBounds(0, 0, 920, 557);
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(0, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(121, 10, 214, 41);
		txtSearch.setFont(new Font("굴림", Font.PLAIN, 21));
		txtSearch.setText("ex) 천안");
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("역 검색");
		lblNewLabel.setBounds(12, 10, 84, 41);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 21));
		panel.add(lblNewLabel);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(351, 10, 91, 41);
		btnSearch.setToolTipText("");
		btnSearch.setFont(new Font("굴림", Font.BOLD, 21));
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String searchText = getSearch();
				try {
					controller.handleSearchButtonClick(searchText);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 90, 430, 117);
		panel.add(scrollPane);
		
		
		
		textAreaLocker = new JTextArea();
		scrollPane.setViewportView(textAreaLocker);
		textAreaLocker.setEditable(false);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 238, 430, 117);
		panel.add(scrollPane_1);
		
		textAreaToil = new JTextArea();
		scrollPane_1.setViewportView(textAreaToil);
		textAreaToil.setEditable(false);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 391, 430, 117);
		panel.add(scrollPane_2);
		
		textAreaAtm = new JTextArea();
		scrollPane_2.setViewportView(textAreaAtm);
		textAreaAtm.setEditable(false);
	}
	public String getSearch() {
		return txtSearch.getText();
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public void setTextArea(JTextArea area ,String info) {
	      area.append(info);
	    }
	public void clearTextArea(JTextArea area) {
		area.setText("");
	}
	

}


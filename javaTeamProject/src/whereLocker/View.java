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

import javax.swing.ImageIcon;
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
	private JLabel imageicon;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	

	
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
		
		JLabel label1 = new JLabel("역 검색");
		label1.setBounds(12, 10, 84, 41);
		label1.setFont(new Font("굴림", Font.BOLD, 21));
		panel.add(label1);
		
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
		
		imageicon = new JLabel("");
		imageicon.setBounds(500, 100, 386, 408);
		imageicon.setFont(new Font("굴림", Font.BOLD, 16));
		panel.add(imageicon);
		
		label2 = new JLabel("      지도");
		label2.setFont(new Font("굴림", Font.BOLD, 16));
		label2.setBounds(636, 43, 84, 32);
		panel.add(label2);
		
		label3 = new JLabel("물품보관함 정보");
		label3.setBounds(12, 69, 91, 24);
		panel.add(label3);
		
		label4 = new JLabel("화장실 정보");
		label4.setBounds(12, 217, 84, 24);
		panel.add(label4);
		
		label5 = new JLabel("ATM 정보");
		label5.setBounds(12, 365, 84, 24);
		panel.add(label5);
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
	public void setIcon(ImageIcon image) {
		imageicon.setIcon(image);
	}

}


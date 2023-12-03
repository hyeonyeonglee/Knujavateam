package whereLocker;

import java.awt.EventQueue; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
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
		setTitle("WhereLocker");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 594);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon programIcon = new ImageIcon("C:\\Users\\gusdu\\eclipse-workspace\\javaTeamProject\\Icon.png");
		setIconImage(programIcon.getImage());

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
		textAreaLocker.setFont(new Font("굴림", Font.PLAIN, 16));
		scrollPane.setViewportView(textAreaLocker);
		textAreaLocker.setEditable(false);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 238, 430, 117);
		panel.add(scrollPane_1);
		
		textAreaToil = new JTextArea();
		textAreaToil.setFont(new Font("굴림", Font.PLAIN, 16));
		scrollPane_1.setViewportView(textAreaToil);
		textAreaToil.setEditable(false);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 391, 430, 117);
		panel.add(scrollPane_2);
		
		textAreaAtm = new JTextArea();
		textAreaAtm.setFont(new Font("굴림", Font.PLAIN, 16));
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
		label3.setFont(new Font("굴림", Font.PLAIN, 14));
		label3.setBounds(12, 69, 152, 24);
		panel.add(label3);
		
		label4 = new JLabel("화장실 정보");
		label4.setFont(new Font("굴림", Font.PLAIN, 16));
		label4.setBounds(12, 217, 152, 24);
		panel.add(label4);
		
		label5 = new JLabel("ATM 정보");
		label5.setFont(new Font("굴림", Font.PLAIN, 16));
		label5.setBounds(12, 365, 198, 24);
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
class SearchList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Model model;
	private JList<String> stinNmList;
	private DefaultListModel<String> listModel;
	private Controller controller;
	private JScrollPane scrollPane;

	
	public SearchList() {
		model = new Model();
		listModel = new DefaultListModel<>();
		
		setBounds(100, 100, 501, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		
		stinNmList = new JList<>(listModel);
		stinNmList.setFont(new Font("굴림", Font.PLAIN, 15));
		stinNmList.setBackground(new Color(255, 255, 255));
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 475, 546);
		scrollPane.setViewportView(stinNmList);
		contentPane.add(scrollPane);
		scrollPane.revalidate();
		scrollPane.repaint();
		
		stinNmList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					try {
						controller.handleListClick(stinNmList.getSelectedIndex());
						dispose();
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		
	}
	public void refreshList(ArrayList<String> stinNm,ArrayList<String> lnNm) {
		  SwingUtilities.invokeLater(() -> {
		        listModel.clear();
		        ArrayList<String> stinNmData = stinNm;
		        ArrayList<String> lnNmData = lnNm;
		        for (int i = 0; i < stinNmData.size(); i++) {
		            String item = stinNmData.get(i) + " " + lnNmData.get(i);
		            listModel.addElement(item);
		        }
		    });
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

}



package whereLocker;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ViewList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Model model;
	private JList<String> stinNmList;
	private DefaultListModel<String> listModel;
	private Controller controller;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public ViewList() {
		model = new Model();
		listModel = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 475, 546);
		contentPane.add(panel);
		panel.setLayout(null);
		
		stinNmList = new JList<>(listModel);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 475, 546);
		scrollPane.setViewportView(stinNmList);
		contentPane.add(scrollPane);
		
		stinNmList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
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

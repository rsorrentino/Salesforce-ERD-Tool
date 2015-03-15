import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;


import com.sforce.ws.ConnectorConfig;


public class selectWindow extends JFrame {
	
	private JPanel contentPane;

	public static void main(String[] args, ConnectorConfig token) {
		
		final ConnectorConfig token2 = token;
				
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
			
				try {
					selectWindow frame = new selectWindow(token2);
					frame.setVisible(true);
				} 
				
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public selectWindow(ConnectorConfig token) {
		
		setTitle("salesforce.com ERD Generator");
		
		final ConnectorConfig token2 = token;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalesforceLogo = new JLabel("");
		lblSalesforceLogo.setIcon(new ImageIcon("/Users/michael/Documents/workspace/Org Describe/resources/Salesforce_Logo_RGB_8_13_14_1.png"));
		lblSalesforceLogo.setBounds(384, 6, 60, 47);
		contentPane.add(lblSalesforceLogo);
		
		JLabel lblTitle = new JLabel("Select Objets");
		lblTitle.setBounds(6, 21, 230, 16);
		contentPane.add(lblTitle);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(6, 309, 117, 29);
		contentPane.add(btnGenerate);
		
		ArrayList<String> allSobjects = fetchSObjects.fetchAllSobjects(token);
		DefaultListModel sObjectListModel = new DefaultListModel();
		
		for (int i = 0; i < allSobjects.size(); i++) {
			
			sObjectListModel.addElement(allSobjects.get(i));
			
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		final JList lstSObjects = new JList(sObjectListModel);
		lstSObjects.setBounds(6, 61, 376, 249);
		scrollPane.setViewportView(lstSObjects);
		scrollPane.setBounds(6, 61, 376, 249);
		contentPane.add(scrollPane);
		
		btnGenerate.addActionListener(new ActionListener() {
			
	    	public void actionPerformed(ActionEvent e) {
	    			    		
	    		List<String> selFromList = lstSObjects.getSelectedValuesList();
	    		
	    		ArrayList<String> selectedSObjects = new ArrayList<String>();

	    		for (int i = 0; i < selFromList.size(); i++) {
	    			
	    			selectedSObjects.add(selFromList.get(i));
	    			
	    		}
	    		
	    	    
	    	    System.out.println(selectedSObjects);
	    	    
	    	    String output = fetchSObjects.describeSObjects(token2,selectedSObjects);
	    	    System.out.println(output);
	    	    File file = new File("file.txt");

	            PrintWriter printWriter = null;

	            try
	            {
	                printWriter = new PrintWriter(file);
	                printWriter.println(output);
	            }
	            catch (FileNotFoundException fe)
	            {
	                fe.printStackTrace();
	            }
	            finally
	            {
	                if ( printWriter != null ) 
	                {
	                    printWriter.close();
	                }
	            }

	    		}
	      });
		
	}

}

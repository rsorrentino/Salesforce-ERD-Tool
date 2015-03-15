import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Toolkit;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sforce.ws.ConnectorConfig;


public class mainWindow {

	private JFrame frmSalesforcecomErdGenerator;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdToken;
	private JLabel lblPassword;
	private JLabel lblToken;
	private JLabel lblInstance;
	private JLabel lblStatus;
	
	public ConnectorConfig token = null;
	
	private String[] instances = new String[] {"Production", "Sandbox", "Pre-Release"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frmSalesforcecomErdGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainWindow() {
		initialize();
	}

	private ConnectorConfig initialize() {
		
		//final ConnectorConfig token;
		
		frmSalesforcecomErdGenerator = new JFrame();
		frmSalesforcecomErdGenerator.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/michael/Documents/workspace/Org Describe/resources/Salesforce_Logo_RGB_8_13_14_1.png"));
		frmSalesforcecomErdGenerator.setTitle("salesforce.com ERD Generator");
		frmSalesforcecomErdGenerator.setBounds(100, 100, 450, 366);
		frmSalesforcecomErdGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesforcecomErdGenerator.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("salesforce.com ERD Generator");
		lblTitle.setBounds(6, 21, 230, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblTitle);
		
		JLabel lblSalesforceLogo = new JLabel("");
		lblSalesforceLogo.setIcon(new ImageIcon("/Users/michael/Documents/workspace/Org Describe/resources/Salesforce_Logo_RGB_8_13_14_1.png"));
		lblSalesforceLogo.setBounds(384, 6, 60, 47);
		frmSalesforcecomErdGenerator.getContentPane().add(lblSalesforceLogo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(16, 60, 67, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(95, 54, 200, 28);
		frmSalesforcecomErdGenerator.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(95, 94, 200, 28);
		frmSalesforcecomErdGenerator.getContentPane().add(pwdPassword);
		
		pwdToken = new JPasswordField();
		pwdToken.setBounds(95, 134, 200, 28);
		frmSalesforcecomErdGenerator.getContentPane().add(pwdToken);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(16, 100, 74, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblPassword);
		
		lblToken = new JLabel("Token:");
		lblToken.setBounds(16, 140, 61, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblToken);
		
		final JComboBox cmbInstance = new JComboBox();
		cmbInstance.setModel(new DefaultComboBoxModel(instances));
		cmbInstance.setBounds(95, 174, 200, 28);
		frmSalesforcecomErdGenerator.getContentPane().add(cmbInstance);
		
		lblInstance = new JLabel("Instance:");
		lblInstance.setBounds(16, 179, 61, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblInstance);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(95, 268, 117, 29);
		frmSalesforcecomErdGenerator.getContentPane().add(btnLogin);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(6, 320, 438, 16);
		frmSalesforcecomErdGenerator.getContentPane().add(lblStatus);
		
		
		btnLogin.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String pwd = new String(pwdPassword.getPassword());
	    		String tkn = new String(pwdToken.getPassword());
	    		String ins = cmbInstance.getSelectedItem().toString();
	    		token = SalesforceLoginSOAP.doLogin(txtUsername.getText(), pwd, tkn, ins);
	    		if (token.getSessionId() != null) {
	    			lblStatus.setText("Successfully Logged In");
	    			selectWindow.main(null, token);
	    		}
	    		if (token.getSessionId() == null) {
	    			lblStatus.setText("Login Failed");
	    		}
	      }
    });
		
		return token;

	}
}

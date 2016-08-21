import com.sforce.ws.ConnectorConfig;
import com.sforce.soap.metadata.*;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public HashMap sfdcInstance = new HashMap(); 
	
	public ConnectorConfig globalToken = null;
	
	public static void main(String[] args) { 
		
		//sfcdInstance.put("Production", )
		
		
		//mainWindow.main(args);
			
		
		
		
	//	ArrayList<String> allSobjects = fetchSObjects.fetchAllSobjects(token);
		
	//	String output = fetchSObjects.describeSObjects(token,allSobjects);
		
	//	System.out.println(output);
	
	
	
	 CustomField nf = new CustomField();
	 
     nf.setType(FieldType.Text);
     nf.setDescription("The custom object identifier on page layouts, related lists etc");
     nf.setLabel("Field");
     nf.setFullName("Account." + "test_field__c");
     nf.setLabel("customField");
     nf.setType(FieldType.Text);
     nf.setExternalId(false);
     nf.setLength(18);
     
     try {
 	
     MetadataConnection mc = SalesforceLoginSOAP.login("username", "password", "token", "Production");

     mc.createMetadata(new Metadata[] {nf});
     
     }
     
     catch (ConnectionException e) {
         System.out.println("An error occured while connecting to the org.");
         System.out.println(e);
     }
 
             
 	
	
	
	 
	}
	 
	 
	 
	 
}
	 

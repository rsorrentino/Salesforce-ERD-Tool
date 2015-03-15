import java.util.ArrayList;

import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeGlobalSObjectResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;


public class fetchSObjects {
	
	 public static ArrayList<String> fetchAllSobjects(ConnectorConfig config) {

		 try {
			
			ArrayList<String> sobjects = new ArrayList<String>();
        	
			PartnerConnection pc = new PartnerConnection(config);
        	
			DescribeGlobalResult gr = pc.describeGlobal();        	
        	
			DescribeGlobalSObjectResult[] sobjectResults = gr.getSobjects();

        	for (int i = 0; i < sobjectResults.length; i++) {
             
        		sobjects.add(sobjectResults[i].getName());
        		
              }
        	
    		return sobjects;
    		
        }
		 
        catch (Exception e) {
        	
        	e.printStackTrace();
        	
        	return null;
        	
        }
		 
	 }
	 

	 
	 public static String describeSObjects(ConnectorConfig token, ArrayList<String> sobjects) {
		 
		 String output = "";  
		 
		 try {
		    	PartnerConnection pc = new PartnerConnection(token);      	
		    	
	        	for (int i = 0; i < sobjects.size(); i++) {
	        		
	        		DescribeSObjectResult describeSObjectResult = pc.describeSObject(sobjects.get(i));
		 		        
	        			if (describeSObjectResult != null) {
		        						        				
			 		        Field[] fields = describeSObjectResult.getFields();
			 		        
		 		        	int counter = 1;
			 		          			 		        			 		        
			 		        for (int j = 0; j < fields.length; j++) {
			 		        	
			 		               
			 		          	Field field = fields[j];
	 		          	
			 		          	if (field.getType().equals(FieldType.id)) {
			 		          		
			 		          		output += "postgresql;ELSA;Salesforce;" + "\"" + describeSObjectResult.getName() + " (" + describeSObjectResult.getName() + ")" + "\";" + "\"" + field.getLabel() + " (" + field.getName() + ")\";" + counter  + ";" + "\"" + String.valueOf(field.getType()).toUpperCase() + "\";" + field.getLength() + ";\"PRIMARY KEY\";";
			 		          		
			 		          		output += "\r\n";
	 		          	
			 		          	}
	 		          		
			 		          	if (field.getType().equals(FieldType.reference)) {
			 		          		
			 		          		counter++;
			 		          		
			 		          		String[] referenceTos = field.getReferenceTo();  
 		          							 		          		
			 		          		if (referenceTos.length != 0) {
 		          				
		 		          				for (int k = 0; k < referenceTos.length; k++) {
		 		          					
		 		          					System.out.println(counter);
			 		          								 		          					
		 		          					output += "postgresql;ELSA;Salesforce;" + "\"" + describeSObjectResult.getName() + " (" + describeSObjectResult.getName() + ")" + "\";" + "\"" + field.getLabel() + " (" + field.getName() + ")\";" + counter  + ";\"" + String.valueOf(field.getType()).toUpperCase() + "\";" + field.getLength() +";\"FOREIGN KEY\";\"Salesforce\";\"" + referenceTos[k] + " (" + referenceTos[k] + ")\";\"" + referenceTos[k] + " ID (Id)\";";  
			 		          					
		 		          					output += "\r\n";
		 		          					
		 		          					
		 		          				}
			 		          		}     	
			 		          	}
			 		        }	        			
	        			}
	        		}
	        	
        		return output;
	        	
        	}
	        	
		    catch (ConnectionException ce) {
		    	
			    ce.printStackTrace();
			    
			    return null;
		    
		    }
		
	 	}

	}

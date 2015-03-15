import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;


import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/* Author: M.Wheeler - Feb 2015 
 * Desc: This class performs a login to salesforce using the salesforce SOAP API, and returns a ConnectorConfig Object
 * Params: Username (String), Password (String), Token (String), Instance Name (String)
 */

public class SalesforceLoginSOAP {
	
	public static ConnectorConfig doLogin(String userName, String password, String token, String instance) {

	String instanceURL = null;

	ConnectorConfig config = new ConnectorConfig();
	
	// could potentially pass the URL as part of the class instantiation, rather than store in here.
	// did it this way because the login URLs are unlikely to change.
	
	if (instance.equalsIgnoreCase("Production")) {
		instanceURL = "https://login.salesforce.com/services/Soap/u/32.0";
	}
	if (instance.equalsIgnoreCase("Sandbox")) {
		instanceURL = "https://test.salesforce.com/services/Soap/u/32.0";
	}
	if (instance.equalsIgnoreCase("Pre-Release")) {
		instanceURL = "https://pre.salesforce.com/services/Soap/u/32.0";
	}

	
	config.setAuthEndpoint(instanceURL);
	
    config.setUsername(userName);
    
    config.setPassword(password + token);
    
    
    /* Flags to enable debugging */
    
    //config.setTraceMessage(true);

    try {
    	
    	PartnerConnection connection;
    	connection = Connector.newConnection(config);
      
    	/* Additional debugging statements */
    	
    	// System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
    	// System.out.println("Service EndPoint: "+config.getServiceEndpoint());
    	// System.out.println("Username: "+config.getUsername());
    	// System.out.println("SessionId: "+config.getSessionId());
    	
    	return config;

    } 
    
    	catch (ConnectionException e1) {
    		//TODO: Improve error handling
    		e1.printStackTrace();
        
    		return null;
    	}      	
	}
	
	
	public static MetadataConnection login(String userName, String password, String token, String instance) throws ConnectionException {
		// This is only a sample. Hard coding passwords in source files is a bad practice.

		String instanceURL = null;

		if (instance.equalsIgnoreCase("Production")) {
			instanceURL = "https://login.salesforce.com/services/Soap/u/32.0";
		}
		if (instance.equalsIgnoreCase("Sandbox")) {
			instanceURL = "https://test.salesforce.com/services/Soap/u/32.0";
		}
		if (instance.equalsIgnoreCase("Pre-Release")) {
			instanceURL = "https://pre.salesforce.com/services/Soap/u/32.0";
		}

		
        final LoginResult loginResult = loginToSalesforce(userName, password+token, instanceURL);
        return createMetadataConnection(loginResult);
    }

    private static MetadataConnection createMetadataConnection(
            final LoginResult loginResult) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setServiceEndpoint(loginResult.getMetadataServerUrl());
        config.setSessionId(loginResult.getSessionId());
        return new MetadataConnection(config);
    }

    private static LoginResult loginToSalesforce(
            final String username,
            final String password,
            final String loginUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setAuthEndpoint(loginUrl);
        config.setServiceEndpoint(loginUrl);
        config.setManualLogin(true);
        return (new PartnerConnection(config)).login(username, password);
    }
	
	
	
	
	
	
	
	
	
	
	
	
}

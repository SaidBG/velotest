package persistence.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCManagerSolo {

    private static JDBCManagerSolo INSTANCE = null;
    private static Connection connection;
    
    private JDBCManagerSolo(){
    	
    }
    
    public static JDBCManagerSolo getInstance(){  
    	if(INSTANCE == null) {
    		INSTANCE = new JDBCManagerSolo();
    	}
    	return INSTANCE;
    }	    
    
    public Connection openConection() throws Exception {
    	Properties properties =	CfgManagerSolo.getInstance().getCfg();
		Class.forName(properties.getProperty("driverName"));		
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		connection =  DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	
    public void closeConnection() throws SQLException {
			connection.close();
	}
}

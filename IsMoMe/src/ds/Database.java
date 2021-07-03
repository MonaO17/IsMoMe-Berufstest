package ds;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource; 

/**The Database class creates a connection to the Oracle database of the HWR-Berlin
 */
public class Database { 
	//variables
	private static Database dss = null; 
	private static OracleDataSource ds = null; 
	private Connection con;

	//constructor
	private Database(){ 
		try { 
			ds = new OracleDataSource(); 
			ds.setDataSourceName("OracleDataSource"); 
			ds.setURL("jdbc:oracle:xxx"); 
			ds.setUser("user"); 
			ds.setPassword("password"); 
		} catch (SQLException e) {} 
	}

	public static Database getInstance() { 
		if (dss == null) 
			dss = new Database(); 
		return dss; 
	} 
	
	public Connection getConnection() throws SQLException { 
		con = null; 
		con = ds.getConnection(); 
		return con; 
	}
}
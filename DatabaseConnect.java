/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author codeGeek
 */
/**
 * class to establish database connection
**/
public class DatabaseConnect {
    Connection conn=null;
    /**
     * variable for database crecedentials declaration
     **/
   
    private final String password="hr";
    private final String username="HR";
    private final String url="jdbc:oracle:thin:@localhost:1521/orclpdb";
    
    /*
    *
    MySQL	com.mysql.jdbc.Driver	        jdbc:mysql://hostname/ databaseName
    ORACLE	oracle.jdbc.driver.OracleDriver	jdbc:oracle:thin:@hostname:port Number:databaseName
    DB2	        COM.ibm.db2.jdbc.net.DB2Driver	        jdbc:db2:hostname:port Number/databaseName
    Sybase	com.sybase.jdbc.SybDriver	jdbc:sybase:Tds:hostname: port Number/databaseName
    Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    
    */
   // jdbc:mysql://localhost:3306/;
    
   public Connection dbConnect() throws ClassNotFoundException, SQLException{
        
          //  Class.forName(drivername);
            conn=DriverManager.getConnection(url, username, password);        
       
       return conn;
        
    }
   ///
    //first get connection
//  Connection conn=DriverManger.getConnection();
}

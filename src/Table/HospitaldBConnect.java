/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author codeGeek
 */
public class HospitaldBConnect {
    static Connection conn=null;
  //static String Drivername="org.sqlite.JDBC";
   private static final String drivername="com.mysql.jdbc.Driver";
    private static final String password="";
    private static final String username="root";
    private static final String url="jdbc:mysql://localhost/hospital_queue";
    
   public static Connection connect(){
        try { 
            Class.forName(drivername);
          //  conn=DriverManager.getConnection("jdbc:sqlite:hospital_queue.sqlite");
            conn=DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection Not Available");
            return null;
        }
       
     return conn;  
   }
}

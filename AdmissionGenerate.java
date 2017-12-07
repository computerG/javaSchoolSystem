/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author codeGeek
 */
public class  AdmissionGenerate {
String admissionno;
;Connection conn;
StudentSearch search;
PreparedStatement pst;
ResultSet rs;
DatabaseConnect dbconn;
String getLast;
  
 
    public AdmissionGenerate() {    
    try {
        dbconn=new DatabaseConnect();
       conn=dbconn.dbConnect();
    } catch (Exception ex) {
        Logger.getLogger(AdmissionGenerate.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }
//asumes that no student that has been registered
    public String getAdmissionno() {
    try {
        getLast="select student_adm from student";
        pst=conn.prepareStatement(getLast);
        rs=pst.executeQuery();
        if(rs.next()){
         rs.last(); 
         admissionno=rs.getString("student_adm");
        return admissionno;
        }
        else return null;
            
    } catch (Exception ex) {
        Logger.getLogger(AdmissionGenerate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return admissionno;
    }

    public void setAdmissionno(String admissionno) {
        this.admissionno = admissionno;
    }

   
    
    
}

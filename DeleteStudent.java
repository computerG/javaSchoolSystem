/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author codeGeek
 */
public class DeleteStudent extends javax.swing.JDialog {
    private final String stude_name;
    private final String stude_admno;
    DatabaseConnect dbconn;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    /**
     * Creates new form DeleteStudent
     */
    public DeleteStudent(String admno,String name ,JFrame frame,boolean modal) {
        super(frame,modal);
         initComponents();
         btn_exit.setToolTipText("Click to cancel and exit");
            stude_name=name;
            stude_admno=admno;
            txt_stude_admno.setText(admno);
            txt_stude_name.setText(name);
           
            try{
            dbconn=new DatabaseConnect();
            conn=dbconn.dbConnect();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteFee(String admno){
        try {
            String deletefee="DELETE FROM fee where student_adm='"+admno.trim()+"'";
            pst=conn.prepareStatement(deletefee);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

    public void deleteGuardian(String admno){
        try {
            String deletefee="DELETE FROM guardian where student_adm='"+admno+"'";
            pst=conn.prepareStatement(deletefee);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteMarks(String admno){
        try {
            String deletefee="DELETE FROM marks where admno='"+admno+"'";
            pst=conn.prepareStatement(deletefee);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteEducation(String admno){
       try {
            String deletefee="DELETE FROM education where student_adm='"+admno+"'";
            pst=conn.prepareStatement(deletefee);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void deletestudent(String admno){
         try {
            String deletefee="DELETE FROM student where student_adm='"+admno+"'";
            pst=conn.prepareStatement(deletefee);
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Student removed from the system");
            }
            else{
              JOptionPane.showMessageDialog(null, "Student not removed");  
            }
            ;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStudent.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_stude_admno = new javax.swing.JTextField();
        btn_delete = new javax.swing.JButton();
        txt_stude_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete student");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete the following student"));

        txt_stude_admno.setEditable(false);

        btn_delete.setText("Delete Student");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        txt_stude_name.setEditable(false);

        jLabel2.setText("Student Name");

        btn_exit.setText("Cancel");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        jLabel1.setText("Student admission no");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_exit))
                    .addComponent(txt_stude_name)
                    .addComponent(txt_stude_admno, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_stude_admno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_stude_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete)
                    .addComponent(btn_exit))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
         int confirm= JOptionPane.showConfirmDialog(null, "Do you want to remove student", "Remove student", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
   if(confirm==JOptionPane.OK_OPTION){
       deleteGuardian(stude_admno);
       deleteEducation(stude_admno);
       deleteMarks(stude_admno);
       deleteFee(stude_admno);
       deletestudent(stude_admno);
       JOptionPane.showMessageDialog(null, " student deleted");  
       WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
         
   }
   else   JOptionPane.showMessageDialog(null, "You cancelled student removal");  
                             
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
     WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
     Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
    }//GEN-LAST:event_btn_exitActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_stude_admno;
    private javax.swing.JTextField txt_stude_name;
    // End of variables declaration//GEN-END:variables
}

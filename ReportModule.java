/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Toolkit;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author codeGeek
 */
public class ReportModule extends javax.swing.JDialog {
Connection conn;
PreparedStatement pst;
ResultSet rs;
DatabaseConnect dbConn;
    private int i;
    /**
     * Creates ne==w form ReportModule
     */
    public ReportModule() {
    try {
        initComponents();
        dbConn=new DatabaseConnect();
        conn= dbConn.dbConnect();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ReportModule.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        
        Logger.getLogger(ReportModule.class.getName()).log(Level.SEVERE, null, ex);
    }
   
     populateComboSession();    
     populateComboStream();
     populateAdmission();
     if(report_combo_admission.getItemCount()<0){
         btn_generate_report.setEnabled(false);
         report_combo_admission.setEnabled(false);
     }
    }
    public void populateComboSession(){
    String session="select session_name from session";
    try{
            pst=conn.prepareStatement(session);
            rs=pst.executeQuery();
            while(rs.next()){
                 report_combo_term.addItem(rs.getString("session_name"));
            }
    }
    catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
    }
    finally{
    try{
        pst.close();
        rs.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
    }
}
}
    public void  populateAdmission(){
        
         report_combo_stream.addItemListener(new ItemListener() {
             @Override
             public void itemStateChanged(ItemEvent e) {
                  populateAdmission();
                 if(report_combo_admission.getItemCount()==0){
                   btn_generate_report.setEnabled(false);
                   report_combo_admission.setEnabled(false);
                 }
                 else{
                   btn_generate_report.setEnabled(true);
                   report_combo_admission.setEnabled(true);
            }
                  //To change body of generated methods, choose Tools | Templates.
             }
             
         });
        report_combo_admission.removeAllItems();
        String admission="select student_adm from student where"
                + " stream='"+ report_combo_stream.getSelectedItem()+"' "
                + "and year='"+report_combo_year.getSelectedItem()+"'";
           try {
        pst=conn.prepareStatement(admission);
         rs=pst.executeQuery();
            while(rs.next()){
                report_combo_admission.addItem(rs.getString("student_adm"));
            }
            if(report_combo_admission.getItemCount()==0){
                   btn_generate_report.setEnabled(false);
                   report_combo_admission.setEnabled(false);
            }
            else{
                   btn_generate_report.setEnabled(true);
                   report_combo_admission.setEnabled(true);
            }
                  //To change body of generated methods, choose Tools | Templates.
             
            
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        
    }
public void populateComboStream(){
    String stream="select stream from stream";
    try{ pst=conn.prepareStatement(stream);
            rs=pst.executeQuery();
            while(rs.next()){
                 report_combo_stream.addItem(rs.getString("stream"));
            }
    }
    catch(Exception ex){
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
    try{
        pst.close();
        rs.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
    }
    
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
        report_combo_admission = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        report_combo_term = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        report_combo_year = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_generate_report = new javax.swing.JButton();
        report_combo_stream = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student report module");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Report Module"));

        jLabel3.setText("Stream");

        jLabel1.setText("Term");

        report_combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        report_combo_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                report_combo_yearItemStateChanged(evt);
            }
        });

        jLabel4.setText("Admission");

        jLabel2.setText("Year");

        btn_generate_report.setText("Generate Report");
        btn_generate_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generate_reportActionPerformed(evt);
            }
        });

        report_combo_stream.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                report_combo_streamItemStateChanged(evt);
            }
        });
        report_combo_stream.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                report_combo_streamPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(report_combo_year, 0, 71, Short.MAX_VALUE)
                    .addComponent(report_combo_term, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(report_combo_admission, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(report_combo_stream, 0, 85, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_generate_report)
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(report_combo_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(report_combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_combo_admission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(report_combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addComponent(btn_generate_report)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_generate_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generate_reportActionPerformed
     
    try {
        String query="SELECT student.firstname AS First , student.surname AS Surname, student.year AS Year, marks.admno AS Admno, marks.grade AS GRADE, marks.marks AS marks_marks, marks.subject AS SUBJECT, marks.term AS TERM, marks.cat1 AS CAT1, marks.cat2 AS CAT2, marks.marks AS marks_exam, marks.stream "
                + "AS STREAM FROM marks marks INNER JOIN student ON marks.admno = student.student_adm "
                + "WHERE marks.admno='"+report_combo_admission.getSelectedItem().toString()+
                "' AND marks.stream='"+report_combo_stream.getSelectedItem().toString()+""
                + "'and student.year='"+report_combo_year.getSelectedItem().toString()+"'"
                + " and marks.term='"+report_combo_term.getSelectedItem().toString()+"'";
                
        JasperDesign jd= JRXmlLoader.load("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\reports\\student_report.jrxml");
        JRDesignQuery newQuery= new JRDesignQuery();
        newQuery.setText(query);
        jd.setQuery(newQuery);
        JasperReport jr= JasperCompileManager.compileReport(jd);
        JasperPrint jp= JasperFillManager.fillReport(jr, null, conn);
        JasperViewer viewer=new JasperViewer(jp,false);
        viewer.setTitle("Student Academic  Details");
        //viewer.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\images\\report.jpg"));
        JDialog dialog = new JDialog(this);//the owner
        dialog.setContentPane(viewer.getContentPane());
        dialog.setSize(viewer.getSize());
        dialog.setTitle("student Academic Details");
        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\images\\report.jpg") );
        dialog.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(ReportModule.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_btn_generate_reportActionPerformed

    private void report_combo_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_report_combo_yearItemStateChanged
      populateAdmission();
       btn_generate_report.setEnabled(true);
       if(report_combo_admission.getItemCount()==0){
         btn_generate_report.setEnabled(false);}
        
     
    }//GEN-LAST:event_report_combo_yearItemStateChanged

    private void report_combo_streamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_report_combo_streamItemStateChanged
      
    }//GEN-LAST:event_report_combo_streamItemStateChanged

    private void report_combo_streamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_report_combo_streamPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_report_combo_streamPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generate_report;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox report_combo_admission;
    private javax.swing.JComboBox report_combo_stream;
    private javax.swing.JComboBox report_combo_term;
    private javax.swing.JComboBox report_combo_year;
    // End of variables declaration//GEN-END:variables
}
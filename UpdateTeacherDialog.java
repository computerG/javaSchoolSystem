/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author codeGeek
 */
public class UpdateTeacherDialog extends JDialog
{
DatabaseConnect dbcon=null;
    Connection conn=null;
    PreparedStatement pst=null;
     private ResultSet rs; 
     DefaultListModel listModel;
    private String idno;
    private TeacherList teacherList;
    /**
     * Creates new form UpdateTeacherDialog
     */
    
    UpdateTeacherDialog(String firstname, String lastname, String surname, String idno, String mobileno,String email,JDialog frame,boolean modal) {
      super(frame, modal);
    try {
        initComponents();
         
        dbcon=new DatabaseConnect();
        conn= dbcon.dbConnect();
        teacher_reg_email.setText(email);
        teacher_reg_fname.setText(firstname);
        teacher_reg_lname.setText(lastname);
        teacher_reg_surname.setText(surname);
        teacher_reg_mobile.setValue(mobileno);
        teacher_reg_idno.setText(idno);
        teacher_reg_idno.setEditable(false);
        
       teacher_reg_mobile.setInputVerifier(new InputVerifier() {

    @Override
    public boolean verify(JComponent input) {
        JFormattedTextField source=(JFormattedTextField) input;
      if(source.getValue().toString().length()>12) 
      {  JOptionPane.showMessageDialog(null, "Invalid mobile number ", "Mobile number check",JOptionPane.ERROR_MESSAGE);
      
          return false;
      }
      else{
          return true;
      }
//To change body of generated methods, choose Tools | Templates.
    }
});
//To change body of generated methods, choose Tools | Templates.
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(UpdateTeacherDialog.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(UpdateTeacherDialog.class.getName()).log(Level.SEVERE, null, ex);
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
        teacher_reg_idno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        teacher_reg_surname = new javax.swing.JTextField();
        teacher_reg_lname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        teacher_reg_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        teacher_reg_fname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_save_teacher = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        teacher_reg_mobile = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update teacher");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Teachers Detail"));

        teacher_reg_idno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_idnoKeyTyped(evt);
            }
        });

        jLabel2.setText("ID NO");

        jLabel4.setText("Firstname");

        jLabel7.setText("Mobile no");

        jLabel8.setText("Email");

        teacher_reg_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_emailKeyTyped(evt);
            }
        });

        jLabel5.setText("Lastname");

        jLabel3.setText("Surname");

        btn_save_teacher.setText("Update");
        btn_save_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_teacherActionPerformed(evt);
            }
        });

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            teacher_reg_mobile.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("07#-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        teacher_reg_mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_mobileKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(teacher_reg_email, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(32, 32, 32))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(teacher_reg_idno, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(teacher_reg_surname)
                                    .addComponent(teacher_reg_fname)
                                    .addComponent(teacher_reg_lname)
                                    .addComponent(teacher_reg_mobile))))
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_save_teacher)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacher_reg_idno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(teacher_reg_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(teacher_reg_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(teacher_reg_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(teacher_reg_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(teacher_reg_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btn_save_teacher))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void teacher_reg_idnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_idnoKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){
        }
        else{
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_teacher_reg_idnoKeyTyped

    private void btn_save_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_teacherActionPerformed
         int confirm=JOptionPane.showConfirmDialog(null, "Update Teacher?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){       
         if(teacher_reg_idno.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id no" );}
        else if(teacher_reg_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id surname" );
        teacher_reg_surname.grabFocus();}
        else if(teacher_reg_fname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id first name" );
        teacher_reg_fname.grabFocus();}
        else if(teacher_reg_lname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id last name" );
        teacher_reg_lname.grabFocus();}
        else if(teacher_reg_mobile.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter Mobile no" );
       teacher_reg_mobile.grabFocus(); }     
        else if(teacher_reg_email.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter email" );
        teacher_reg_email.grabFocus();}
        else{
            String insert ="update teacher set surname=?,firstname=?,lastname=?,mobile_no=?,email=? where id_no=? "
            ;
            try {
                pst=conn.prepareStatement(insert);                             
                pst.setString(1, teacher_reg_surname.getText());
                pst.setString(2, teacher_reg_fname.getText());
                pst.setString(3, teacher_reg_lname.getText());
                pst.setString(4, teacher_reg_mobile.getText());               
                pst.setString(5, teacher_reg_email.getText());  
                pst.setString(6, teacher_reg_idno.getText());
               if( pst.executeUpdate()>0){
                   JOptionPane.showMessageDialog(null, "Update Successful");
                   
               }
               else{
                  JOptionPane.showMessageDialog(null, "Update Not Successful");                   
               }

            } catch (SQLException ex) {
               Logger.getLogger(TeacherList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }else
            JOptionPane.showMessageDialog(null  , "No Updates Made to records");
        
    }//GEN-LAST:event_btn_save_teacherActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void teacher_reg_mobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_mobileKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_teacher_reg_mobileKeyTyped

    private void teacher_reg_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_emailKeyTyped
      teacher_reg_email.setBackground(Color.LIGHT_GRAY);
      teacher_reg_email.setInputVerifier(new InputVerifier() {

                @Override
                public boolean verify(JComponent input) {
                    JTextComponent source=(JTextComponent)input;
        String emailPattern= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$";
       Pattern p=Pattern.compile(emailPattern) ;
        Matcher match=p.matcher(source.getText());
            //To change body of generated methods, choose Tools | Templates
            if (match.matches()) {
                return true;
            }
            else{
                  JOptionPane.showMessageDialog(null, "Invalid Email", "Email check",JOptionPane.ERROR_MESSAGE);
                  source.setBackground(Color.red);
                return false;
            }
                    //To change body of generated methods, choose Tools | Templates.
                }
            });
        
    }//GEN-LAST:event_teacher_reg_emailKeyTyped

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_save_teacher;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField teacher_reg_email;
    private javax.swing.JTextField teacher_reg_fname;
    private javax.swing.JTextField teacher_reg_idno;
    private javax.swing.JTextField teacher_reg_lname;
    private javax.swing.JFormattedTextField teacher_reg_mobile;
    private javax.swing.JTextField teacher_reg_surname;
    // End of variables declaration//GEN-END:variables

    private void close() {
       
        WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);//To change body of generated methods, choose Tools | Templates.
    }
}

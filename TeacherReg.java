/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;


/**
 *
 * @author codeGeek
 */
public class TeacherReg extends javax.swing.JDialog {
    private long age;
    DatabaseConnect dbcon=null;
    Connection conn=null;
    PreparedStatement pst=null;
     private ResultSet rs; 
     DefaultListModel listModel;

    public TeacherReg(Dialog owner, boolean modal) {
        super(owner, modal);
        try {
          
            initComponents();
            dob.setDate(new Date());
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
            listModel=new DefaultListModel();
            dbcon=new DatabaseConnect();
            conn=dbcon.dbConnect();
             radio_female.setActionCommand("Female");
             radio_male.setActionCommand("Male");
            populateList();
            subject_list.setModel(listModel);
            //To change body of generated methods, choose Tools | Templates.
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates new form TeacherReg     */
     TeacherReg(JFrame aThis, boolean b) {
          super(aThis, b);
         try {
          
            initComponents();
             dob.setDate(new Date());
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
            listModel=new DefaultListModel();
            dbcon=new DatabaseConnect();
            conn=dbcon.dbConnect();
             radio_female.setActionCommand("Female");
             radio_male.setActionCommand("Male");
            populateList();
            subject_list.setModel(listModel);
            //To change body of generated methods, choose Tools | Templates.
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void populateList(){
        try {
            String subject="select subject_name from Subject ";
            pst=conn.prepareStatement(subject);
            rs=pst.executeQuery();
            while(rs.next()){
                listModel.addElement(rs.getString("subject_name"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
public boolean isValidateAge(){
        java.util.Date date = dob.getCalendar().getTime();
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy/dd/MM"); 
        Calendar calender=Calendar.getInstance();
        java.util.Date now=calender.getTime();
        String dat = calender.getTime().toString();
        try {
            java.util.Date dob =  fmt.parse(fmt.format(date));
            java.util.Date today=fmt.parse(fmt.format(now));
            age = today.getYear()-dob.getYear();           
        } catch (ParseException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        }
      System.out.println(age);
        if(age<20||age>=60){
            return false;
        }
        else return true;
         
      
}
public boolean isValidDob(){
        java.util.Date date = dob.getCalendar().getTime();  
        Calendar calender=Calendar.getInstance();      
        java.util.Date dat = calender.getTime();
        System.out.println(date); 
      if(date.after(dat)) {
          return false;
      } 
      else{
          return  true;
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

        btng_gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        teacher_reg_idno = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        subject_list = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        teacher_reg_tsno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        teacher_reg_surname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        teacher_reg_lname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dob = new com.toedter.calendar.JDateChooser();
        teacher_reg_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        teacher_reg_fname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_save_teacher = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        radio_male = new javax.swing.JRadioButton();
        radio_female = new javax.swing.JRadioButton();
        teacher_reg_mobile = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Teacher registration");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Teachers Detail"));

        teacher_reg_idno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_idnoKeyTyped(evt);
            }
        });

        jScrollPane1.setViewportView(subject_list);

        jLabel2.setText("ID NO");

        jLabel4.setText("Firstname");

        teacher_reg_tsno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_tsnoKeyTyped(evt);
            }
        });

        jLabel7.setText("Mobile no");

        jLabel6.setText("Subject");

        jLabel9.setText("Dob");

        jLabel8.setText("Email");

        jLabel1.setText("TS NO");

        teacher_reg_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                teacher_reg_emailKeyTyped(evt);
            }
        });

        jLabel5.setText("Lastname");

        jLabel3.setText("Surname");

        btn_save_teacher.setText("Save");
        btn_save_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_teacherActionPerformed(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Gender");

        btng_gender.add(radio_male);
        radio_male.setText("Male");
        radio_male.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_maleItemStateChanged(evt);
            }
        });

        btng_gender.add(radio_female);
        radio_female.setText("Female");
        radio_female.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_femaleItemStateChanged(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_save_teacher)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(teacher_reg_email, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel9))
                                            .addGap(32, 32, 32))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(teacher_reg_tsno)
                                            .addComponent(teacher_reg_idno)
                                            .addComponent(teacher_reg_surname)
                                            .addComponent(teacher_reg_fname)
                                            .addComponent(teacher_reg_lname)
                                            .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(radio_male)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(radio_female))
                                        .addComponent(teacher_reg_mobile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(43, 43, 43)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(56, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacher_reg_tsno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(teacher_reg_idno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(teacher_reg_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
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
                    .addComponent(jLabel9)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(teacher_reg_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(radio_male)
                    .addComponent(radio_female))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(teacher_reg_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btn_save_teacher))
                .addContainerGap(23, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_save_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_teacherActionPerformed
        if(teacher_reg_tsno.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter tsc no" );}
        else if(teacher_reg_idno.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id no" );}
        else if(teacher_reg_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id surname" );}
        else if(teacher_reg_fname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id first name" );}
        else if(teacher_reg_lname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id last name" );}
        else if(teacher_reg_mobile.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter Mobile no" );}
        else if(subject_list.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "Select a subject" );}
        else if(teacher_reg_email.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter email" );}
        else if(btng_gender.getSelection()==null){JOptionPane.showMessageDialog(null, "Select Gender" );}
        else if (!isValidDob()&&!isValidateAge()) {JOptionPane.showMessageDialog(null, "invalid dob" );}
        else{
           
            long date=dob.getDate().getTime();
            SimpleDateFormat fmt=new SimpleDateFormat("YYYY-MM-dd"); 
            String insert ="insert into teacher(tscNo,id_no,surname,firstname,lastname,subject,mobile_no,email,dob,gender) "
              + "values(?,?,?,?,?,?,?,?,?,?)"  ;
        try {
             pst=conn.prepareStatement(insert);
             pst.setString(1, teacher_reg_tsno.getText());
             pst.setString(2, teacher_reg_idno.getText());
             pst.setString(3, teacher_reg_surname.getText());
             pst.setString(4, teacher_reg_fname.getText());
             pst.setString(5, teacher_reg_lname.getText());
             pst.setString(7, teacher_reg_mobile.getValue().toString());
             pst.setString(6, subject_list.getSelectedValuesList().toString());
             pst.setString(8, teacher_reg_email.getText());
             pst.setString(9, fmt.format(date));            
             pst.setString(10, btng_gender.getSelection().getActionCommand());
             if(pst.executeUpdate()>0){
                 JOptionPane.showMessageDialog(null, "Teacher registered sucessfully");
                 clearall();
             }
             else{
                  JOptionPane.showMessageDialog(null, "Teacher not registered");
             }
             
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherReg.class.getName()).log(Level.SEVERE, null, ex);
        } 
         }
    }//GEN-LAST:event_btn_save_teacherActionPerformed

    private void teacher_reg_tsnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_tsnoKeyTyped
   if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
       if(teacher_reg_tsno.getText().length()>10){
           evt.consume();
           Toolkit.getDefaultToolkit().beep(); 
       }
       else{
           
       }
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_teacher_reg_tsnoKeyTyped

    private void teacher_reg_idnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_idnoKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
             if(teacher_reg_idno.getText().length()>10){
           evt.consume();
           Toolkit.getDefaultToolkit().beep(); 
       }
       else{
           
       }
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_teacher_reg_idnoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radio_maleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_maleItemStateChanged
        if (evt.getStateChange()==ItemEvent.SELECTED) {
            int confirm=JOptionPane.showConfirmDialog(null, "You selected "+ btng_gender.getSelection().getActionCommand().toString(),"Verify gender",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.OK_OPTION){
                
            }
            else{
                radio_female.setSelected(false);
                radio_male.setSelected(false);
            }
        }
    }//GEN-LAST:event_radio_maleItemStateChanged

    private void radio_femaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_femaleItemStateChanged
          if (evt.getStateChange()==ItemEvent.SELECTED) {
            int confirm=JOptionPane.showConfirmDialog(null, "You selected "+ btng_gender.getSelection().getActionCommand().toString());
            if(confirm==JOptionPane.OK_OPTION){
                
            }
            else{
                radio_female.setSelected(false);
                radio_male.setSelected(false);
            }
        }
    }//GEN-LAST:event_radio_femaleItemStateChanged

    private void teacher_reg_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_emailKeyTyped
     teacher_reg_email.setBackground(Color.lightGray);
    }//GEN-LAST:event_teacher_reg_emailKeyTyped

    private void teacher_reg_mobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacher_reg_mobileKeyTyped
      
        
    }//GEN-LAST:event_teacher_reg_mobileKeyTyped

    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_save_teacher;
    private javax.swing.ButtonGroup btng_gender;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radio_female;
    private javax.swing.JRadioButton radio_male;
    private javax.swing.JList subject_list;
    private javax.swing.JTextField teacher_reg_email;
    private javax.swing.JTextField teacher_reg_fname;
    private javax.swing.JTextField teacher_reg_idno;
    private javax.swing.JTextField teacher_reg_lname;
    private javax.swing.JFormattedTextField teacher_reg_mobile;
    private javax.swing.JTextField teacher_reg_surname;
    private javax.swing.JTextField teacher_reg_tsno;
    // End of variables declaration//GEN-END:variables

    private void close() {
      
        WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
                //To change body of generated methods, choose Tools | Templates.
     //To change body of generated methods, choose Tools | Templates.
    }

    private void clearall() {
  ;
     teacher_reg_email.setText("");
     teacher_reg_fname.setText("");
     teacher_reg_idno.setText("");
     teacher_reg_lname.setText(null);
     teacher_reg_mobile.setText(null);
     teacher_reg_surname.setText("");
    teacher_reg_tsno.setText("");//To change body of generated methods, choose Tools | Templates.
    }
}

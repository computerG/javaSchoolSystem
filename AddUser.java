/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
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
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author codeGeek
 */
public class AddUser extends javax.swing.JDialog { 
    private String fname;
    private String sname;
    private String id;
    private String box;
    private String postcode;
    private String email;
    private String town;
    private String accounttype;
    private String uname;
    private String password;
    private String department;
    private int age;
    private Date dob;

DatabaseConnect dbcon=null;
    Connection conn=null;
    PreparedStatement pst=null;
     private ResultSet rs; 
    private String roll_id;
    private String department_id;
    private String oldid;



    /**
     * Creates new form AddUser
     */
public void gatherDetails(){
   
 fname=txt_user_fname.getText();
 uname=txt_user_uname.getText();
 sname=txt_user_sname.getText();
 id=txt_user_id.getText();
 box=txt_user_pobox.getText();
 postcode=txt_user_postalcode.getText();
 email=txt_user_email.getText();
 town= txt_user_town.getText();
 accounttype=user_combo_type.getSelectedItem().toString()  ;
 department=combo_user_department.getSelectedItem().toString();
 password=txt_user_password.getText();
 
 dob=user_dob.getDate();
 
 
}
    public AddUser(JFrame aThis, boolean b) {
        super(aThis, b);
          initComponents();
        try {
            dbcon=new DatabaseConnect();
            conn=dbcon.dbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateDepartment();
        populateRoles();
        user_dob.setDate(new  Date());
        txt_user_id.setInputVerifier(new StrictInputVerifier(id));
        txt_user_email.setInputVerifier(new InputVerifier() {

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
    }
    public void populateDepartment(){
        try {
            combo_user_department.removeAllItems();
            String department="select department_name from  department";
            pst=conn.prepareStatement(department);
            rs=pst.executeQuery();
            while(rs.next()) {
                combo_user_department.addItem(rs.getString("department_name"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void populateRoles(){
        try {
           user_combo_type.removeAllItems();
            String department="select role_name from  roles";
            pst=conn.prepareStatement(department);
            rs=pst.executeQuery();
            while(rs.next()) {
               user_combo_type.addItem(rs.getString("role_name"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
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

        jDatePickerUtil1 = new net.sourceforge.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil2 = new net.sourceforge.jdatepicker.util.JDatePickerUtil();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        user_combo_type = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_user_uname = new javax.swing.JTextField();
        combo_user_department = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_user_password = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        txt_user_pobox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_user_town = new javax.swing.JTextField();
        txt_user_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_user_fname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_user_id = new javax.swing.JTextField();
        txt_user_sname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_user_postalcode = new javax.swing.JTextField();
        user_dob = new com.toedter.calendar.JDateChooser();
        btn_user_add = new javax.swing.JButton();
        btn_user_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add user");
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Account Details"));

        user_combo_type.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                user_combo_typePropertyChange(evt);
            }
        });

        jLabel9.setText("Role");

        jLabel11.setText("Password");

        jLabel10.setText("UserName");

        jLabel12.setText("Dapartment");

        txt_user_password.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_user_uname, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(combo_user_department, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user_combo_type, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_user_password))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(user_combo_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_user_uname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_user_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(combo_user_department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("UserDetails"));

        txt_user_pobox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_user_poboxKeyTyped(evt);
            }
        });

        jLabel4.setText("Id");

        txt_user_town.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_townActionPerformed(evt);
            }
        });

        txt_user_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_user_emailKeyTyped(evt);
            }
        });

        jLabel5.setText("P.O.BOX");

        jLabel1.setText("First name");

        jLabel7.setText("Email");

        jLabel3.setText("Dob");

        jLabel2.setText("Surname");

        jLabel8.setText("Town");

        txt_user_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_user_idKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_user_idKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_user_idKeyTyped(evt);
            }
        });

        txt_user_sname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_snameActionPerformed(evt);
            }
        });

        jLabel6.setText("postal Code");

        txt_user_postalcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_postalcodeActionPerformed(evt);
            }
        });
        txt_user_postalcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_user_postalcodeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_user_pobox, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(txt_user_id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_user_email)
                    .addComponent(txt_user_sname)
                    .addComponent(txt_user_town)
                    .addComponent(txt_user_fname)
                    .addComponent(txt_user_postalcode)
                    .addComponent(user_dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_user_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(txt_user_sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(user_dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_user_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_user_pobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_user_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(txt_user_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_user_town, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_user_add.setText("Add");
        btn_user_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_user_addActionPerformed(evt);
            }
        });

        btn_user_cancel.setText("Exit");
        btn_user_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_user_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btn_user_add)
                        .addGap(29, 29, 29)
                        .addComponent(btn_user_cancel))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_user_add)
                    .addComponent(btn_user_cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_user_townActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_townActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_townActionPerformed

    private void txt_user_snameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_snameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_snameActionPerformed

    private void btn_user_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_user_addActionPerformed
        gatherDetails();
        if(fname.isEmpty()||sname.isEmpty()||password.isEmpty()||uname.isEmpty()
                ||box.isEmpty()||postcode.isEmpty()||email.isEmpty()){
            JOptionPane.showMessageDialog(null, "Field are empty!Please fILL ALL Fields","Empty Fields",JOptionPane.ERROR_MESSAGE);
        }
        else{ 
            if(isValidDob()&&isValidateAge()){
              
                 getRoleId();
         getDepartmentId();
        try {
           
            String insert ="INSERT INTO users(username,password,role_id,firstname,surname,dob,idno,box,postalcode,town,department_id,email) VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
           pst=conn.prepareStatement(insert);
           pst.setString(1, uname);
           pst.setString(2, password);
           pst.setString(3, getRoll_id());
           pst.setString(4,fname );
           pst.setString(5, sname);
           pst.setString(6,new SimpleDateFormat("YYY-MM-dd").format(dob));
           pst.setString(7,id);
           pst.setString(8, box);
           pst.setString(9, postcode);
           pst.setString(10, town);
           pst.setString(11, getDepartment_id());
           pst.setString(12, email);
            if(pst.executeUpdate()>0){
                oldid=id;
                JOptionPane.showMessageDialog(null, "Sucessfull insertion");
            }
            else{
                JOptionPane.showMessageDialog(null, "Insertion not sucessful");
            }    } catch (SQLException ex) {
            try {
           
            String insert ="update users set username=?,password=?,role_id=?,firstname=?,surname,dob=?,idno=?,box=?,postalcode=?,town=?,department_id=?,email=? where idno=?";
           pst=conn.prepareStatement(insert);
           pst.setString(1, uname);
           pst.setString(2, password);
           pst.setString(3, getRoll_id());
           pst.setString(4,fname );
           pst.setString(5, sname);
           pst.setString(6,new SimpleDateFormat("YYY-MM-dd").format(dob));
           pst.setString(7,id);
           pst.setString(8, box);
           pst.setString(9, postcode);
           pst.setString(10, town);
           pst.setString(11, getDepartment_id());
           pst.setString(12, email);
           pst.setString(13, oldid);
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Details updated");
            }
            else{
                JOptionPane.showMessageDialog(null, "Details not updated");
            }    } 
            catch (SQLException e) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        }            }
            
            else{
                JOptionPane.showMessageDialog(null, "invalid date of birth!");
   user_dob.grabFocus();
   
            }
 }
             
    }//GEN-LAST:event_btn_user_addActionPerformed

    private void user_combo_typePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_user_combo_typePropertyChange
     
    }//GEN-LAST:event_user_combo_typePropertyChange

    private void txt_user_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_idKeyTyped
     if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'||
             evt.getKeyCode()==KeyEvent.VK_BACKSPACE||evt.getKeyCode()==KeyEvent.VK_DELETE)
     { 
         txt_user_id.setBackground(Color.white);
         if(txt_user_id.getText().length()>10){
           evt.consume();
           Toolkit.getDefaultToolkit().beep(); 
       }
       else{
           
       }
         
     }
     else{
         Toolkit.getDefaultToolkit().beep();
         evt.consume();
     }
    }//GEN-LAST:event_txt_user_idKeyTyped

    private void txt_user_poboxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_poboxKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'||
                evt.getKeyCode()==KeyEvent.VK_BACKSPACE||evt.getKeyCode()==KeyEvent.VK_DELETE)
     {
      
     }
     else{
         Toolkit.getDefaultToolkit().beep();
         evt.consume();
     }
    }//GEN-LAST:event_txt_user_poboxKeyTyped

    private void txt_user_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_idKeyReleased
      
    }//GEN-LAST:event_txt_user_idKeyReleased

    private void txt_user_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_idKeyPressed

    private void txt_user_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_emailKeyTyped
       txt_user_email.setBackground(Color.white);
    }//GEN-LAST:event_txt_user_emailKeyTyped

    private void btn_user_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_user_cancelActionPerformed
      WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
    }//GEN-LAST:event_btn_user_cancelActionPerformed

    private void txt_user_postalcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_user_postalcodeKeyTyped
         if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'||evt.getKeyCode()==KeyEvent.VK_BACKSPACE||evt.getKeyCode()==KeyEvent.VK_DELETE)
     {
      if(txt_user_postalcode.getText().length()>5){
           Toolkit.getDefaultToolkit().beep();
         evt.consume();
     }else{
         
     }
     }
     else{
         Toolkit.getDefaultToolkit().beep();
         evt.consume();
     }
    }//GEN-LAST:event_txt_user_postalcodeKeyTyped

    private void txt_user_postalcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_postalcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_postalcodeActionPerformed
public void getRoleId(){
     String department="select role_id from  roles where role_name='"+ user_combo_type.getSelectedItem().toString()+"'";
     try{
            pst=conn.prepareStatement(department);
            rs=pst.executeQuery();
           if (rs.next()) {
              setRoll_id(rs.getString("role_id"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void getDepartmentId(){
     String department="select department_id from  department where department_name='"+ combo_user_department.getSelectedItem().toString()+"'";
     try{
            pst=conn.prepareStatement(department);
            rs=pst.executeQuery();
           if (rs.next()) {
               setDepartment_id(rs.getString("department_id"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public String getRoll_id() {
        return roll_id;
    }

    public void setRoll_id(String roll_id) {
        this.roll_id = roll_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public boolean isValidateAge(){
        java.util.Date date = user_dob.getCalendar().getTime();
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
      
        if(age<20||age>=60){
            return false;
        }
        else return true;
         
      
}
   
public boolean isValidDob(){
        java.util.Date date = user_dob.getCalendar().getTime();  
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
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_user_add;
    private javax.swing.JButton btn_user_cancel;
    private javax.swing.JComboBox combo_user_department;
    private net.sourceforge.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private net.sourceforge.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txt_user_email;
    private javax.swing.JTextField txt_user_fname;
    private javax.swing.JTextField txt_user_id;
    private javax.swing.JPasswordField txt_user_password;
    private javax.swing.JTextField txt_user_pobox;
    private javax.swing.JTextField txt_user_postalcode;
    private javax.swing.JTextField txt_user_sname;
    private javax.swing.JTextField txt_user_town;
    private javax.swing.JTextField txt_user_uname;
    private javax.swing.JComboBox user_combo_type;
    private com.toedter.calendar.JDateChooser user_dob;
    // End of variables declaration//GEN-END:variables
}

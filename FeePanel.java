/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author codeGeek
 */
public class FeePanel extends javax.swing.JPanel {
String admno;
String paidamount;
String mode;
String receiptno;
String chequeno;
String term;
//sms sending variables

Connection conn;
DatabaseConnect dbconn;
PreparedStatement pst;
    private String transactionNo;
    private ResultSet rs;
    private String studentname;
    private JTabbedPane tabedPane;
    private String year;
    /**
     * Creates new form FeePanel
     */
    public FeePanel(String admnissionNo, JTabbedPane student_admission_tabbedpane_details, String year) {
    try {
        initComponents();
        tabedPane=student_admission_tabbedpane_details;
        admno=admnissionNo;
        this.year=year;
       txt_fee_no.setVisible(false);
        combo_year.setSelectedItem(this.year);
        txt_fee_module.setText(admno);    
        lbl_pay_no.setVisible(false);
        txt_fee_no.setVisible(false);
        radio_fee_module_cash.setActionCommand("Cash");
        radio_fee_module_cash.setSelected(true);
        radio_fee_module_cheque.setActionCommand("Cheque");
        radio_fee_module_receipt.setActionCommand("Receipt");
        dbconn=new DatabaseConnect();
        conn=dbconn.dbConnect();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(FeePanel.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(FeePanel.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    FeePanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public void getTextFieldValues(){    
    if (txt_fee_no.isVisible()) {
       transactionNo=txt_fee_no.getText();
    }
    admno=txt_fee_module.getText();     
    paidamount=txt_fee_module_amt_paid.getText();    
    receiptno=txt_fee_no.getText();
    mode=btng_mode.getSelection().getActionCommand();
    term=combo_fee_module_term.getSelectedItem().toString();
   
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_mode = new javax.swing.ButtonGroup();
        panel_fee_module = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_fee_module = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combo_fee_module_term = new javax.swing.JComboBox();
        radio_fee_module_cash = new javax.swing.JRadioButton();
        radio_fee_module_cheque = new javax.swing.JRadioButton();
        radio_fee_module_receipt = new javax.swing.JRadioButton();
        lbl_pay_no = new javax.swing.JLabel();
        txt_fee_no = new javax.swing.JTextField();
        btn_fee_module_save = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        combo_year = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_fee_module_amt_paid = new javax.swing.JFormattedTextField();
        btn_view_balance = new javax.swing.JButton();

        panel_fee_module.setBorder(javax.swing.BorderFactory.createTitledBorder("Fee Payment module"));

        jLabel1.setText("Admin no");

        txt_fee_module.setEditable(false);
        txt_fee_module.setEnabled(false);
        txt_fee_module.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fee_moduleKeyTyped(evt);
            }
        });

        jLabel2.setText("Amount paid");

        jLabel3.setText("Term");

        combo_fee_module_term.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TERM 1", "TERM 2", "TERM 3" }));

        btng_mode.add(radio_fee_module_cash);
        radio_fee_module_cash.setText("Cash");
        radio_fee_module_cash.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_fee_module_cashItemStateChanged(evt);
            }
        });

        btng_mode.add(radio_fee_module_cheque);
        radio_fee_module_cheque.setText("Cheque");
        radio_fee_module_cheque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_fee_module_chequeItemStateChanged(evt);
            }
        });
        radio_fee_module_cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_fee_module_chequeActionPerformed(evt);
            }
        });

        btng_mode.add(radio_fee_module_receipt);
        radio_fee_module_receipt.setText("Receipt");
        radio_fee_module_receipt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_fee_module_receiptItemStateChanged(evt);
            }
        });

        lbl_pay_no.setText("Enter Receipt number");

        txt_fee_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fee_noKeyTyped(evt);
            }
        });

        btn_fee_module_save.setText("Save ");
        btn_fee_module_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fee_module_saveActionPerformed(evt);
            }
        });

        jLabel5.setText("Year");

        combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        combo_year.setEnabled(false);

        jLabel6.setText("Mode of payment");

        txt_fee_module_amt_paid.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00;(¤#,##0.00)"))));
        txt_fee_module_amt_paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fee_module_amt_paidKeyTyped(evt);
            }
        });

        btn_view_balance.setText("Previous");
        btn_view_balance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_view_balanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_fee_moduleLayout = new javax.swing.GroupLayout(panel_fee_module);
        panel_fee_module.setLayout(panel_fee_moduleLayout);
        panel_fee_moduleLayout.setHorizontalGroup(
            panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_fee_moduleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_fee_moduleLayout.createSequentialGroup()
                        .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_fee_moduleLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(60, 60, 60)
                                .addComponent(radio_fee_module_receipt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radio_fee_module_cash, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radio_fee_module_cheque, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_fee_moduleLayout.createSequentialGroup()
                                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_pay_no, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(combo_fee_module_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_fee_module_amt_paid, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addComponent(txt_fee_module)
                                        .addComponent(combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_fee_no, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_fee_moduleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_view_balance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_fee_module_save)
                .addGap(119, 119, 119))
        );
        panel_fee_moduleLayout.setVerticalGroup(
            panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fee_moduleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fee_module, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_fee_module_amt_paid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(combo_fee_module_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_fee_module_receipt)
                    .addComponent(radio_fee_module_cash)
                    .addComponent(radio_fee_module_cheque)
                    .addComponent(jLabel6))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_pay_no)
                    .addComponent(txt_fee_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_fee_moduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_view_balance)
                    .addComponent(btn_fee_module_save))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_fee_module, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_fee_module, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_fee_moduleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fee_moduleKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){

        }
        else{
            if(evt.getKeyChar()==KeyEvent.VK_SLASH){

            }
            else{
                evt.consume();
                Toolkit.getDefaultToolkit().beep();}

        }        // TODO add your handling
    }//GEN-LAST:event_txt_fee_moduleKeyTyped

    private void radio_fee_module_cashItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_fee_module_cashItemStateChanged
        int change=evt.getStateChange();
        if(change==ItemEvent.SELECTED){
            transactionNo=String.valueOf(0);}
    }//GEN-LAST:event_radio_fee_module_cashItemStateChanged

    private void radio_fee_module_chequeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_fee_module_chequeItemStateChanged
        int change=evt.getStateChange();
        if(change==ItemEvent.SELECTED){
            lbl_pay_no.setVisible(true);
            txt_fee_no.setVisible(true);
            lbl_pay_no.setText("Enter Cheque No.");
        }
        else{
            lbl_pay_no.setVisible(false);
            lbl_pay_no.setText("Enter Cheque No.");
            txt_fee_no.setVisible(false);
        }
    }//GEN-LAST:event_radio_fee_module_chequeItemStateChanged

    private void radio_fee_module_chequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_fee_module_chequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_fee_module_chequeActionPerformed

    private void radio_fee_module_receiptItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_fee_module_receiptItemStateChanged
        int change=evt.getStateChange();
        if(change==ItemEvent.SELECTED){
            lbl_pay_no.setVisible( true);
            txt_fee_no.setVisible(true);}
        else{
            lbl_pay_no.setVisible( false);
            txt_fee_no.setVisible(false);
        }
    }//GEN-LAST:event_radio_fee_module_receiptItemStateChanged

    private void txt_fee_noKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fee_noKeyTyped
        if(evt.getKeyChar()<'0'||evt.getKeyChar()>'9'){
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_fee_noKeyTyped

    private void btn_fee_module_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fee_module_saveActionPerformed
       
        Calendar calender=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("YYY-MM-dd");
        String insert_fee="INSERT INTO fee (payment_mode,student_adm,Paid_amount,paid_date,term,transaction_no,year)VALUES(?,?,?,?,?,?,?)";
        getTextFieldValues();
        if(emptyFiedls())
        {
            JOptionPane.showMessageDialog(null, "Please fill in the Fields");
        }else
        if(!admnoValid(admno)){
            JOptionPane.showMessageDialog(null, "Invalid admission");
            //String phone=JOptionPane.showInputDialog(null,"")
        }
        else if(Double.parseDouble(paidamount.replace(",", ""))>20000){
            JOptionPane.showMessageDialog(null, "Invalid Fee entry");
        }
        else{
            try {
                pst=conn.prepareStatement(insert_fee);
                pst.setString(1, mode);
                pst.setString(2, admno);
                pst.setString(3, paidamount.replace(",", ""));
                pst.setString(4,  format.format(calender.getTime()));
                pst.setString(5, term);
                pst.setString(6, transactionNo);
                pst.setString(7, combo_year.getSelectedItem().toString());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Fee details Saved");
                tabedPane.setSelectedIndex(4);
                tabedPane.setEnabledAt(3, false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                txt_fee_module.grabFocus();
            }
        }
       
    }//GEN-LAST:event_btn_fee_module_saveActionPerformed

    private void txt_fee_module_amt_paidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fee_module_amt_paidKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){

        }
        else{
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        //code here:
    }//GEN-LAST:event_txt_fee_module_amt_paidKeyTyped

    private void btn_view_balanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_view_balanceActionPerformed
      tabedPane.setSelectedIndex(2);
    StudentAdmission studentAdmission = new StudentAdmission(true);
    }//GEN-LAST:event_btn_view_balanceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_fee_module_save;
    private javax.swing.JButton btn_view_balance;
    private javax.swing.ButtonGroup btng_mode;
    private javax.swing.JComboBox combo_fee_module_term;
    private javax.swing.JComboBox combo_year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_pay_no;
    private javax.swing.JPanel panel_fee_module;
    private javax.swing.JRadioButton radio_fee_module_cash;
    private javax.swing.JRadioButton radio_fee_module_cheque;
    private javax.swing.JRadioButton radio_fee_module_receipt;
    private javax.swing.JTextField txt_fee_module;
    private javax.swing.JFormattedTextField txt_fee_module_amt_paid;
    private javax.swing.JTextField txt_fee_no;
    // End of variables declaration//GEN-END:variables

       public JTextField getVisibleTextField(){
    
    if(txt_fee_no.isVisible()){
        return txt_fee_no;        
    }else
    return null;   
}
public String getParentPhoneNo(){
   String phonenumber = null;
    try {
        String parent ="select mobilenumber from guardian where student_adm='"+admno+"'";
        pst=conn.prepareStatement(parent);
        rs=pst.executeQuery();
        if(rs.next())
        {
     return rs.getString(1);
        }
        else
           return phonenumber;
    } catch (SQLException ex) {
        return  phonenumber;
        //Logger.getLogger(FeeModule.class.getName()).log(Level.SEVERE, null, ex);
    }
   
}
 private boolean emptyFiedls() {
        if(getVisibleTextField()==null){
        if (admno.isEmpty()|| 
            paidamount.isEmpty()
             ){
              return true;
        }
        else
            return false;
         //To change body of generated methods, choose Tools | Templates.
    }
    else{
    if (admno.isEmpty()|| getVisibleTextField().getText().isEmpty()||
            paidamount.isEmpty()
             ){
              return true;
        }
        else
            return false;
}
    }

   
public String getStudentName(){
   String phonenumber = null;
    try {
        String parent ="select surname,firstname from student where student_adm='"+admno+"'";
        pst=conn.prepareStatement(parent);
        rs=pst.executeQuery();
        while(rs.next())
        {
    String surname=rs.getString("surname");
    String firstname=rs.getString("firstname");
    studentname=surname+" "+firstname;
        }
        
    } catch (SQLException ex) {
       
        Logger.getLogger(FeeModule.class.getName()).log(Level.SEVERE, null, ex);
    }
   return studentname;
}
    private boolean admnoValid(String admno) {
   
    try {
        String parent ="select student_adm from student where student_adm='"+admno+"' and year='"+combo_year.getSelectedItem()+"'";
        pst=conn.prepareStatement(parent);
        rs=pst.executeQuery();
        if(rs.next())
        return true;
        else
            return false;  
        
        
    } catch (SQLException ex) {       
        Logger.getLogger(FeeModule.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    }
}
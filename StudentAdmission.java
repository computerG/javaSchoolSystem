/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

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

public class StudentAdmission extends javax.swing.JDialog {
    DatabaseConnect dbcon=null;
    Connection conn=null;
    PreparedStatement pst=null;
    private ResultSet rs;   
    private int age;
    private String admnissionNo;
    private int sequence;
    private String year;
    AdmissionGenerate adm;
    private String[] adm_sequence;
    boolean student_previous=false;
    boolean parent_previous=false;
    boolean education_previous=false;
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$";
    private EmailVerifier emailverifier;
    private FeeModuleDialog feemodule;
    private String username;
  
    
    public String getYear() {
        return year;
   
    }

    /**
     * Creates new form StudentAdmission
     */
    public void setYear(String year) {
        this.year = year;
    }

    public StudentAdmission(JFrame aThis, boolean b, String admno, String username) {
        super(aThis,true);
        initComponents(); 
        this.username=username;
        admnissionNo=admno;
        emailverifier=new EmailVerifier();
        student_admission_tabbedpane_details.setEnabledAt(0, true);
        student_admission_tabbedpane_details.setSelectedIndex(0);
        student_admission_tabbedpane_details.setEnabledAt(1, false);
        student_admission_tabbedpane_details.setEnabledAt(2, false);
        student_admission_tabbedpane_details.setEnabledAt(3, false);
        student_admission_tabbedpane_details.setEnabledAt(4, false);
        student_admission_student_txt_admission_no.setEditable(false);
        student_admission_student_combo_stream.setEnabled(false);
        student_admission_student_combo_year.setEnabled(false);
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        jDateChooser2.setEnabled(false);
        jDateChooser3.setDate(new Date());
        fee_panel.setLayout(new FlowLayout());
        fee_panel.add(new FeePanel(getAdmnissionNo(),student_admission_tabbedpane_details,student_admission_student_combo_year.getSelectedItem().toString()));
        dbcon=new DatabaseConnect();
        
        try {
            conn=dbcon.dbConnect();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "missing driver");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        populatestream();
        setparent(admnissionNo);
        setstudent(admnissionNo);
        seteducation(admnissionNo);
        setstudent(admnissionNo);
//    setparent(admno);
//    setstudent(admno); 
//    seteducation(admno);
        student_admission_txt_parent_email.setInputVerifier(emailverifier);
    }

    public StudentAdmission( boolean parent_previous) {
        education_previous=parent_previous;
    }

    StudentAdmission(JFrame aThis, boolean b) {
        super(aThis,true);
        initComponents();
        emailverifier=new EmailVerifier();
         student_admission_txt_parent_email.setInputVerifier(emailverifier);        
        adm=new AdmissionGenerate();
        admnissionNo=adm.getAdmissionno();
        if(admnissionNo==null)        {  
            admnissionNo =JOptionPane.showInputDialog(null,"enter the Intial admission no");
            Calendar calender=Calendar.getInstance();
            SimpleDateFormat format=new SimpleDateFormat("YY");
            String trailing=format.format(calender.getTime());
            admnissionNo.concat(trailing);
            adm_sequence=admnissionNo.split("/");
            sequence=Integer.parseInt(adm_sequence[0]);
            setAdmnissionNo(String.valueOf(sequence));
        }
        else{
          adm_sequence=admnissionNo.split("/");  
          sequence=Integer.parseInt(adm_sequence[0])+1;
          setAdmnissionNo(String.valueOf(sequence));}
         
         student_admission_student_txt_admission_no.setText(getAdmnissionNo());
         student_admission_tabbedpane_details.setEnabledAt(0, true);
         student_admission_tabbedpane_details.setSelectedIndex(0);
         student_admission_tabbedpane_details.setEnabledAt(1, false);
         student_admission_tabbedpane_details.setEnabledAt(2, false);
         student_admission_tabbedpane_details.setEnabledAt(3, false);
         student_admission_tabbedpane_details.setEnabledAt(4, false);
         student_admission_student_txt_admission_no.setEditable(false);
         jDateChooser1.setDate(new Date());         
         jDateChooser2.setDate(new Date());  
         jDateChooser2.setEnabled(false);
         jDateChooser3.setDate(new Date());         
         dbcon=new DatabaseConnect();
          fee_panel.setLayout(new FlowLayout());
         //fee_panel.add(new FeePanel(getAdmnissionNo(),student_admission_tabbedpane_details,student_admission_student_combo_year.getSelectedItem().toString()));
    try {
        conn=dbcon.dbConnect();
    } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "missing driver");
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,ex);
    }              
                
    populatestream();
      //To change body of generated methods, choose Tools | Templates.
    }
    //END OF CONSTRUCTOR
    
public void populatestream(){
    try {
        String stream="select stream from stream" ;
        pst=conn.prepareStatement(stream);
        rs=pst.executeQuery();
        while(rs.next()){
            student_admission_student_combo_stream.addItem(rs.getString("stream"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(StudentAdmission.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally{
        try {
            //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                }
   
}
    //GENERATE ADMISSION NUMBER
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroup_student = new javax.swing.ButtonGroup();
        parentbtn_group = new javax.swing.ButtonGroup();
        student_admission_tabbedpane_details = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        student_admission_student_txt_firstname = new javax.swing.JTextField();
        student_admission_student_combo_county = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        student_admission_student_txt_box = new javax.swing.JTextField();
        student_admission_student_txt_surname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        student_admission_student_radio_female = new javax.swing.JRadioButton();
        student_admission_student_txt_lastname = new javax.swing.JTextField();
        student_admission_student_txt_town = new javax.swing.JTextField();
        student_admission_student_radio_male = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnnext_student = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        student_admission_student_txt_admission_no = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        student_admission_student_combo_stream = new javax.swing.JComboBox();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        student_admission_student_combo_year = new javax.swing.JComboBox();
        student_admission_student_txt_postalcode = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        student_admission_combo_parent_county = new javax.swing.JComboBox();
        student_admission_txt_parent_surname = new javax.swing.JTextField();
        student_admission_txt_parent_firstname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        student_admission_radio_parent_male = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        student_admission_radio_parent_female = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        student_admission_txt_parent_town = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        student_admission_txt_parent_lastname = new javax.swing.JTextField();
        student_admission_txt_parent_box = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        student_admission_txt_parent_email = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        student_admission_txt_parent_id_no = new javax.swing.JTextField();
        parent_button_next = new javax.swing.JButton();
        parent_button_previous = new javax.swing.JButton();
        student_admission_txt_parent_postalcode = new javax.swing.JFormattedTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        student_admission_txt_parent_mobile_no = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        student_admission_txt_edu_school_name = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        student_admission_txt_edu_box = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btn_education_next = new javax.swing.JButton();
        education_btn_previous = new javax.swing.JButton();
        student_admission_txt_edu_postalcode = new javax.swing.JFormattedTextField();
        student_admission_txt_edu_marks = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        fee_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        button_admission_previous = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        student_admission_admission_txt_surname = new javax.swing.JTextField();
        student_admission_admission_txt_firstname = new javax.swing.JTextField();
        student_admission_admission_txt_lastname = new javax.swing.JTextField();
        student_admission_admission_txt_admission_no = new javax.swing.JTextField();
        student_admission_admission_txt_stream = new javax.swing.JTextField();
        student_admission_admission_txt_year = new javax.swing.JTextField();
        student_admission_admission_txt_admission_date = new javax.swing.JTextField();
        studentadmission_generate_report = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        student_admission_file_menu = new javax.swing.JMenu();
        student_admission_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student admission");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Personal Details"));

        student_admission_student_txt_firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_admission_student_txt_firstnameActionPerformed(evt);
            }
        });

        student_admission_student_combo_county.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nairobi", "Mombasa", "Kiambu", "Muranga", "Nyandarua", "Mandera", "Nandi", "UasinGishu", "Laikipia", "Machakos", "Makueni" }));

        jLabel4.setText("P.O. BOX");

        jLabel3.setText("Last Name");

        jLabel5.setText("Town/City");

        jLabel6.setText("Postal code");

        jLabel7.setText("County");

        student_admission_student_txt_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                student_admission_student_txt_boxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_student_txt_boxKeyTyped(evt);
            }
        });

        student_admission_student_txt_surname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_admission_student_txt_surnameActionPerformed(evt);
            }
        });

        jLabel2.setText("First Name");

        btngroup_student.add(student_admission_student_radio_female);
        student_admission_student_radio_female.setSelected(true);
        student_admission_student_radio_female.setText("Female");

        btngroup_student.add(student_admission_student_radio_male);
        student_admission_student_radio_male.setText("Male");

        jLabel8.setText("Gender");

        jLabel1.setText("Surname");

        jLabel21.setText("Date of birth");

        btnnext_student.setText("NEXT STEP");
        btnnext_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnext_studentActionPerformed(evt);
            }
        });

        jLabel31.setText("Admission date");

        jLabel36.setText("Year");

        jLabel38.setText("Admission no");

        jLabel39.setText("Stream");

        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        student_admission_student_combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        student_admission_student_txt_postalcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                student_admission_student_txt_postalcodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_student_txt_postalcodeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel36)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student_admission_student_txt_firstname)
                            .addComponent(student_admission_student_txt_surname)
                            .addComponent(student_admission_student_txt_town, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(student_admission_student_txt_box)
                            .addComponent(student_admission_student_txt_admission_no)
                            .addComponent(student_admission_student_txt_lastname)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student_admission_student_combo_county, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(student_admission_student_radio_female)
                                        .addGap(15, 15, 15)
                                        .addComponent(student_admission_student_radio_male)
                                        .addGap(142, 142, 142)
                                        .addComponent(btnnext_student))
                                    .addComponent(student_admission_student_combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student_admission_student_combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 41, Short.MAX_VALUE))
                            .addComponent(student_admission_student_txt_postalcode))))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(student_admission_student_txt_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(student_admission_student_txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(student_admission_student_txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(student_admission_student_txt_admission_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(student_admission_student_combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(student_admission_student_combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(student_admission_student_txt_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(student_admission_student_txt_town, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(student_admission_student_txt_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(student_admission_student_combo_county, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(student_admission_student_radio_female)
                        .addComponent(student_admission_student_radio_male)
                        .addComponent(btnnext_student)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        student_admission_tabbedpane_details.addTab("Student Personal Details", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parent Details"));

        jLabel19.setText("Gender");

        jLabel13.setText("Mobile No");

        student_admission_combo_parent_county.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nairobi", "Mombasa", "Kiambu", "Muranga", "Nyandarua", "Mandera", "Nandi", "UasinGishu", "Laikipia", "Machakos", "Makueni", " " }));

        jLabel10.setText("Firstname");

        jLabel16.setText("Email");

        parentbtn_group.add(student_admission_radio_parent_male);
        student_admission_radio_parent_male.setText("Male");

        jLabel12.setText("Date of Birth");

        jLabel20.setText("Id no");

        parentbtn_group.add(student_admission_radio_parent_female);
        student_admission_radio_parent_female.setSelected(true);
        student_admission_radio_parent_female.setText("Female");

        jLabel15.setText("Postal code");

        jLabel11.setText("Lastname");

        student_admission_txt_parent_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_parent_boxKeyTyped(evt);
            }
        });

        jLabel17.setText("Town/city/village");

        jLabel14.setText("P.O.BOX");

        student_admission_txt_parent_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                student_admission_txt_parent_emailFocusGained(evt);
            }
        });
        student_admission_txt_parent_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_parent_emailKeyTyped(evt);
            }
        });

        jLabel18.setText("County");

        jLabel9.setText("Surname");

        student_admission_txt_parent_id_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_parent_id_noKeyTyped(evt);
            }
        });

        parent_button_next.setText("Next");
        parent_button_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent_button_nextActionPerformed(evt);
            }
        });

        parent_button_previous.setText("Previous");
        parent_button_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent_button_previousActionPerformed(evt);
            }
        });

        try {
            student_admission_txt_parent_postalcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        student_admission_txt_parent_postalcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_parent_postalcodeKeyTyped(evt);
            }
        });

        jDateChooser3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooser3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooser3FocusLost(evt);
            }
        });
        jDateChooser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser3MouseClicked(evt);
            }
        });

        try {
            student_admission_txt_parent_mobile_no.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(student_admission_txt_parent_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student_admission_txt_parent_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(student_admission_txt_parent_mobile_no, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(student_admission_txt_parent_postalcode, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(student_admission_txt_parent_box, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(student_admission_txt_parent_id_no, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(student_admission_txt_parent_surname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(64, 64, 64)
                                .addComponent(student_admission_radio_parent_female)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(student_admission_radio_parent_male)))
                        .addContainerGap(249, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(parent_button_previous)
                        .addGap(18, 18, 18)
                        .addComponent(parent_button_next)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(student_admission_txt_parent_email, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(student_admission_combo_parent_county, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(student_admission_txt_parent_town)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(119, 119, 119))
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(student_admission_txt_parent_id_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(student_admission_txt_parent_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(student_admission_txt_parent_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(student_admission_txt_parent_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(student_admission_txt_parent_mobile_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(student_admission_txt_parent_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(student_admission_txt_parent_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(student_admission_txt_parent_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(student_admission_txt_parent_town, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(student_admission_combo_parent_county, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(student_admission_radio_parent_female)
                            .addComponent(student_admission_radio_parent_male)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel19)
                        .addGap(5, 5, 5)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parent_button_next)
                    .addComponent(parent_button_previous))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        student_admission_tabbedpane_details.addTab("Parent Details", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Education details"));

        jLabel27.setText("Marks");

        jLabel24.setText("Postal code");

        jLabel22.setText("Former school name");

        student_admission_txt_edu_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_edu_boxKeyTyped(evt);
            }
        });

        jLabel23.setText("P.O.BOX");

        btn_education_next.setText("Next Step");
        btn_education_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_education_nextActionPerformed(evt);
            }
        });

        education_btn_previous.setText("Previous");
        education_btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                education_btn_previousActionPerformed(evt);
            }
        });

        try {
            student_admission_txt_edu_postalcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        student_admission_txt_edu_postalcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_edu_postalcodeKeyTyped(evt);
            }
        });

        student_admission_txt_edu_marks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                student_admission_txt_edu_marksKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 243, Short.MAX_VALUE)
                        .addComponent(education_btn_previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_education_next)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(student_admission_txt_edu_box, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(student_admission_txt_edu_school_name, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(student_admission_txt_edu_postalcode)
                            .addComponent(student_admission_txt_edu_marks))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(student_admission_txt_edu_school_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(student_admission_txt_edu_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student_admission_txt_edu_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(student_admission_txt_edu_marks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(education_btn_previous)
                    .addComponent(btn_education_next))
                .addContainerGap(297, Short.MAX_VALUE))
        );

        student_admission_tabbedpane_details.addTab("Education Details", jPanel3);

        javax.swing.GroupLayout fee_panelLayout = new javax.swing.GroupLayout(fee_panel);
        fee_panel.setLayout(fee_panelLayout);
        fee_panelLayout.setHorizontalGroup(
            fee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );
        fee_panelLayout.setVerticalGroup(
            fee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(fee_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(fee_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        student_admission_tabbedpane_details.addTab("PayFee", jPanel5);

        button_admission_previous.setText("Previous step");
        button_admission_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_admission_previousActionPerformed(evt);
            }
        });

        jLabel28.setText("Surname");

        jLabel29.setText("First Name");

        jLabel30.setText("Last Name");

        jLabel32.setText("Admission no");

        jLabel33.setText("Stream");

        jLabel34.setText("Year");

        jLabel35.setText("Date of admission");

        student_admission_admission_txt_stream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_admission_admission_txt_streamActionPerformed(evt);
            }
        });

        studentadmission_generate_report.setText("Generate Report");
        studentadmission_generate_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentadmission_generate_reportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(59, 59, 59)
                                .addComponent(student_admission_admission_txt_admission_date, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(student_admission_admission_txt_stream, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addComponent(jLabel28)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(82, 82, 82)
                                            .addComponent(student_admission_admission_txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(student_admission_admission_txt_admission_no, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(student_admission_admission_txt_lastname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(student_admission_admission_txt_firstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(student_admission_admission_txt_surname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addContainerGap(167, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(studentadmission_generate_report)
                        .addGap(76, 76, 76)
                        .addComponent(button_admission_previous)
                        .addGap(22, 22, 22))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(student_admission_admission_txt_surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(student_admission_admission_txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(student_admission_admission_txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(student_admission_admission_txt_admission_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(student_admission_admission_txt_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(student_admission_admission_txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(student_admission_admission_txt_admission_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_admission_previous)
                    .addComponent(studentadmission_generate_report))
                .addGap(193, 193, 193))
        );

        student_admission_tabbedpane_details.addTab("Admission", jPanel4);

        student_admission_file_menu.setText("File");

        student_admission_exit.setText("Exit");
        student_admission_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_admission_exitActionPerformed(evt);
            }
        });
        student_admission_file_menu.add(student_admission_exit);

        jMenuBar1.add(student_admission_file_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(student_admission_tabbedpane_details, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(student_admission_tabbedpane_details)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void student_admission_student_txt_firstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_admission_student_txt_firstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_admission_student_txt_firstnameActionPerformed

    private void student_admission_admission_txt_streamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_admission_admission_txt_streamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_admission_admission_txt_streamActionPerformed
public boolean isValidDates(){
    if(isValidDob()&&isValidateAge()&&isValidAdmission()){             
      return  true;
    }
         else{
         return false;
         } 
}
    private void btnnext_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnext_studentActionPerformed
                           student_admission_txt_parent_town.setEnabled(false);
                            student_admission_txt_parent_postalcode.setEnabled(false);
                            student_admission_combo_parent_county.setEnabled(false);
                            student_admission_txt_parent_box.setEnabled(false);
                            SimpleDateFormat fmt=new SimpleDateFormat("YYYY-MM-dd"); 
                            String surname=student_admission_student_txt_lastname.getText();
                            String firstname=student_admission_student_txt_firstname.getText();
                            String lastname=student_admission_student_txt_lastname.getText();   
                            String year=student_admission_student_combo_year.getSelectedItem().toString();
                            setYear(year);
                            Date dob=jDateChooser1.getDateEditor().getDate();   
                            Date date_of_admission=jDateChooser2.getDateEditor().getDate();      
                            String pobox=student_admission_student_txt_box.getText();;
                            String town=student_admission_student_txt_town.getText();
                            String county=student_admission_student_combo_county.getSelectedItem().toString();
                            String postalcode=student_admission_student_txt_postalcode.getText();;
                            String stream=student_admission_student_combo_stream.getSelectedItem().toString();
                            String admissionno=student_admission_student_txt_admission_no.getText();  
                            student_admission_student_radio_male.setActionCommand("male");
                            student_admission_student_radio_female.setActionCommand("Female");    
                            String gender =btngroup_student.getSelection().getActionCommand();  
        
        if(student_previous){  
            student_previous=false;
             if(!isValidDob()&&!isValidateAge()){JOptionPane.showMessageDialog(null, "Enter valid dates" );
             }else if(student_admission_student_txt_firstname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );student_previous=true;
        }else if(student_admission_student_txt_lastname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter  last name" );student_previous=true;
        }else if(student_admission_student_txt_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter surname" );student_previous=true;
       // }else if(student_admission_admission_txt_admission_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "admno" );
        }else if(student_admission_student_txt_town.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter town" );student_previous=true; 
        }else if(student_admission_student_txt_postalcode.getText().isEmpty()&&student_admission_student_txt_postalcode.getText()==null){JOptionPane.showMessageDialog(null, "Enter postal code" ); student_previous=true;
        }else{   
             
            String update="update student set surname=?,firstname=?,lastname=?"
                    + ",dob=?,date_of_admission=?,stream=?,pobox=?,postalcode=?"
                    +",town=?,county=?,gender=?,year=? where student_adm='"+student_admission_student_txt_admission_no.getText()+"'";
             try {
       
        //actual insertion to database             
        //actual insertion to database
                            student_admission_txt_parent_town.setText(town);
                            student_admission_txt_parent_postalcode.setText(postalcode);
                            student_admission_combo_parent_county.setSelectedIndex(student_admission_student_combo_county.getSelectedIndex());
                            student_admission_txt_parent_box.setText(pobox);
        pst=conn.prepareStatement(update);                     
                      pst.setString(1,surname);
                      pst.setString(2, firstname);
                      pst.setString(3, lastname);
                      pst.setString(4, fmt.format(dob));
                      pst.setString(5, fmt.format(date_of_admission));
                      pst.setString(6, stream);
                      pst.setString(7, pobox);
                      pst.setString(8, postalcode);                      
                      pst.setString(9, town);
                      pst.setString(10, county);
                      pst.setString(11, gender);
                      pst.setString(12, year);
                    int success=  pst.executeUpdate();
                      if(success>=1){
                      JOptionPane.showMessageDialog(null, "Sucessful update");
                       student_admission_tabbedpane_details.setSelectedIndex(1);
                      student_admission_tabbedpane_details.setEnabledAt(0, false);                  
                      student_admission_tabbedpane_details.setEnabledAt(1, true);
                      }else { JOptionPane.showMessageDialog(null, " Update not successful");
                              student_admission_tabbedpane_details.setSelectedIndex(0);
                              student_admission_tabbedpane_details.setEnabledAt(0, true);
                              student_previous=true;
                      }
                      
                     
            } catch (SQLException ex) {
       Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
         student_admission_tabbedpane_details.setSelectedIndex(0);
         student_admission_tabbedpane_details.setEnabledAt(0,true);
         student_previous=true;
       
    }
    finally{
        try {
            //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
              student_admission_tabbedpane_details.setSelectedIndex(0);
              student_admission_tabbedpane_details.setEnabledAt(0,true);
              student_previous=true;
        }
               
                }  
        }
        }
        else{
        if(!isValidDates()){JOptionPane.showMessageDialog(null, "Enter valid dates" );
        }else if(student_admission_student_txt_firstname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        }else if(student_admission_student_txt_lastname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter  last name" );
        }else if(student_admission_student_txt_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter surname" );
       // }else if(student_admission_admission_txt_admission_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "admno" );
        }else if(student_admission_student_txt_town.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter town" ); 
        }else if(student_admission_student_txt_postalcode.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter postal code" ); 
        }else{ 
              
    //sql statement to insert to the database
    String sql_insert="insert into student(student_adm,surname,firstname,lastname,dob,date_of_admission,stream,pobox,postalcode,"
           + "town,county,gender,year) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
        //
        //actual insertion to database             
        //actual insertion to database
        pst=conn.prepareStatement(sql_insert);
                      pst.setString(1, admissionno);
                      pst.setString(2,surname);
                      pst.setString(3, firstname);
                      pst.setString(4, lastname);
                      pst.setString(5,fmt.format(dob));
                      pst.setString(6, fmt.format(date_of_admission));
                      pst.setString(7, stream);
                      pst.setString(8, pobox);
                      pst.setString(9, postalcode);
                      pst.setString(10, town);
                      pst.setString(11, county);
                      pst.setString(12, gender);
                      pst.setString(13, year);
                    int success=  pst.executeUpdate();
                      if(success>=1){
                      JOptionPane.showMessageDialog(null, "Sucessful Insertion");
                              student_admission_txt_parent_town.setText(town);
                              student_admission_txt_parent_postalcode.setText(postalcode);
                              student_admission_combo_parent_county.setSelectedIndex(student_admission_student_combo_county.getSelectedIndex());
                              student_admission_txt_parent_box.setText(pobox);
                      }else { JOptionPane.showMessageDialog(null, " Insertion not successful");
                              student_admission_tabbedpane_details.setSelectedIndex(0);
                              student_admission_tabbedpane_details.setEnabledAt(0, true);
                             
                      }
                      student_admission_tabbedpane_details.setSelectedIndex(1);
                      student_admission_tabbedpane_details.setEnabledAt(0, false);                  
                    student_admission_tabbedpane_details.setEnabledAt(1, true);                    
                    //student_admission_student_txt_admission_no.setText(getAdmnissionNo());
                      
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
         student_admission_tabbedpane_details.setSelectedIndex(0);
         student_admission_tabbedpane_details.setEnabledAt(0,true);
       
    }
    finally{
        try {
            //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            //Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
            student_admission_tabbedpane_details.setSelectedIndex(0);
             student_admission_tabbedpane_details.setEnabledAt(0,true);
            student_previous=true;
        }
               
                }  
         }
    
        } 
               
    }//GEN-LAST:event_btnnext_studentActionPerformed

    public String getAdmnissionNo() {
        return admnissionNo;
    }

    public void setAdmnissionNo(String admnissionNo) {
        Calendar calender=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("YY");
        String trailing=format.format(calender.getTime());
        this.admnissionNo = (admnissionNo+"/"+trailing).trim();
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
            
    private void parent_button_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parent_button_previousActionPerformed
            student_previous=true;            
            student_admission_student_txt_admission_no.setText(getAdmnissionNo());
            student_admission_tabbedpane_details.setEnabledAt(0, true);
             student_admission_tabbedpane_details.setSelectedIndex(0);
            student_admission_tabbedpane_details.setEnabledAt(1, false);
            student_admission_tabbedpane_details.setEnabledAt(2, false);
            student_admission_tabbedpane_details.setEnabledAt(3, false);
    }//GEN-LAST:event_parent_button_previousActionPerformed

    private void parent_button_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parent_button_nextActionPerformed
      if(!parent_previous){
        if (isInvalidParentDates()) {JOptionPane.showMessageDialog(null, "Enter valid date of birth" );           
        } else if(student_admission_txt_parent_id_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        } else if(student_admission_txt_parent_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        } else if(student_admission_txt_parent_firstname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        } else if(student_admission_txt_parent_lastname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        } else if(student_admission_txt_parent_mobile_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" ); 
        } else if(student_admission_txt_parent_box.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" ); 
        } else if(student_admission_txt_parent_postalcode.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" ); 
        } else if(student_admission_txt_parent_town.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        }else{     
                    jDateChooser3.setDateFormatString("YYYY-MM-dd");
                    int idno=Integer.parseInt(student_admission_txt_parent_id_no.getText());
                    String surname=student_admission_txt_parent_id_no.getText();
                    String firstname=student_admission_txt_parent_firstname.getText();
                    String lastname=student_admission_txt_parent_lastname.getText();     
                    String dob  = ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
                    //SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd");
                   // String dob=format.format(dateofbirth);
                    String pobox=student_admission_txt_parent_box.getText();
                    String town=student_admission_txt_parent_town.getText();
                    String county=student_admission_combo_parent_county.getSelectedItem().toString();
                    String postalcode=student_admission_txt_parent_postalcode.getText();;
                    String mobileno=student_admission_txt_parent_mobile_no.getText();
                    String email=student_admission_txt_parent_email.getText();
                    student_admission_radio_parent_male.setActionCommand("male");
                    student_admission_radio_parent_female.setActionCommand("Female");    
                    String gender =btngroup_student.getSelection().getActionCommand(); 
    
    //sql statement to insert to the database
    String sql_insert="insert into guardian(idno,surname,firstname,lastname,dob,mobilenumber,pobox,postalcode,email,"
           + "town,county,gender,student_adm) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";           
    try {
        //
        //actual insertion to database
        pst=conn.prepareStatement(sql_insert);
                      pst.setInt(1, idno);
                      pst.setString(2, surname);
                      pst.setString(3,firstname);
                      pst.setString(4, lastname);
                      pst.setString(5, dob);
                      pst.setString(6, mobileno);
                      pst.setString(7, pobox);
                      pst.setString(8, postalcode);
                      pst.setString(9, email);
                      pst.setString(10, town);
                      pst.setString(11, county);
                      pst.setString(12, gender);
                      pst.setString(13, student_admission_student_txt_admission_no.getText());
                      
                   pst.executeUpdate();
                  student_admission_tabbedpane_details.setSelectedIndex(2);
                  student_admission_tabbedpane_details.setEnabledAt(0, false);
                  student_admission_tabbedpane_details.setEnabledAt(1,false);
                  student_admission_tabbedpane_details.setEnabledAt(2, true);
                  student_admission_tabbedpane_details.setEnabledAt(3, false);
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       JOptionPane.showMessageDialog(null, "Error occured");
                  student_admission_tabbedpane_details.setSelectedIndex(1);
                  student_admission_tabbedpane_details.setEnabledAt(0, false);
                  student_admission_tabbedpane_details.setEnabledAt(1,true);
                  student_admission_tabbedpane_details.setEnabledAt(2, false);
                  student_admission_tabbedpane_details.setEnabledAt(3, false);
       
    }
    finally{
        try {
            //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
           Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                }
                  
         }
        
      }
      else{
          if (isInvalidParentDates()) {JOptionPane.showMessageDialog(null, "Invalid dob" );           
        } else if(student_admission_txt_parent_id_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter id no" );
        } else if(student_admission_txt_parent_surname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter surname" );
        } else if(student_admission_txt_parent_firstname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter firstname" );
        } else if(student_admission_txt_parent_lastname.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter lastname" );
        } else if(student_admission_txt_parent_mobile_no.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Mobile no" ); 
        } else if(student_admission_txt_parent_email.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter email" ); 
        } else if(student_admission_txt_parent_box.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter box" ); 
        } else if(student_admission_txt_parent_postalcode.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter postal code" ); 
        } else if(student_admission_txt_parent_town.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Enter town" );
        }else{     
                    jDateChooser3.setDateFormatString("YYYY-MM-dd");
                    int idno=Integer.parseInt(student_admission_txt_parent_id_no.getText());
                    String surname=student_admission_txt_parent_id_no.getText();
                    String firstname=student_admission_txt_parent_firstname.getText();
                    String lastname=student_admission_txt_parent_lastname.getText();     
                    String dob  = ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
                    //SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd");
                   // String dob=format.format(dateofbirth);
                    String pobox=student_admission_txt_parent_box.getText();
                    String town=student_admission_txt_parent_town.getText();
                    String county=student_admission_combo_parent_county.getSelectedItem().toString();
                    String postalcode=student_admission_txt_parent_postalcode.getText();;
                    String mobileno=student_admission_txt_parent_mobile_no.getText();
                    String email=student_admission_txt_parent_email.getText();
                    student_admission_radio_parent_male.setActionCommand("male");
                    student_admission_radio_parent_female.setActionCommand("Female");    
                    String gender =btngroup_student.getSelection().getActionCommand(); 
    
    //sql statement to insert to the database
    String sql_insert="update guardian set idno=?,surname=?,firstname=?,lastname=?,dob=?,mobilenumber=?,pobox=?,postalcode=?,email=?,"
           + "town=?,county=?,gender=? where student_adm=? ";           
    try {
        //
        //actual insertion to database
        pst=conn.prepareStatement(sql_insert);
                      pst.setInt(1, idno);
                      pst.setString(2, surname);
                      pst.setString(3,firstname);
                      pst.setString(4, lastname);
                      pst.setString(5, dob);
                      pst.setString(6, mobileno);
                      pst.setString(7, pobox);
                      pst.setString(8, postalcode);
                      pst.setString(9, email);
                      pst.setString(10, town);
                      pst.setString(11, county);
                      pst.setString(12, gender);
                      pst.setString(13, student_admission_student_txt_admission_no.getText());
                      
        if(pst.executeUpdate()>0){
          JOptionPane.showMessageDialog(null, "Details updated");
                  student_admission_tabbedpane_details.setSelectedIndex(2);
                  student_admission_tabbedpane_details.setEnabledAt(0, false);
                  student_admission_tabbedpane_details.setEnabledAt(1,false);
                  student_admission_tabbedpane_details.setEnabledAt(2, true);
                  student_admission_tabbedpane_details.setEnabledAt(3, false);
          
        }
        else{
          JOptionPane.showMessageDialog(null, "Details not updated");  
                  student_admission_tabbedpane_details.setSelectedIndex(1);
                  student_admission_tabbedpane_details.setEnabledAt(0, false);
                  student_admission_tabbedpane_details.setEnabledAt(1,true);
                  student_admission_tabbedpane_details.setEnabledAt(2, false);
                  student_admission_tabbedpane_details.setEnabledAt(3, false);
                  parent_previous=true;
        }
        
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Error occurred");
                  student_admission_tabbedpane_details.setSelectedIndex(1);
                  student_admission_tabbedpane_details.setEnabledAt(0, false);
                  student_admission_tabbedpane_details.setEnabledAt(1,true);
                  student_admission_tabbedpane_details.setEnabledAt(2, false);
                  student_admission_tabbedpane_details.setEnabledAt(3, false);
                  parent_previous=true;
       
    }
    finally{
        try {
            //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                }
                  
         }
      }
    }//GEN-LAST:event_parent_button_nextActionPerformed

    private void button_admission_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_admission_previousActionPerformed
         education_previous=true;
         student_admission_tabbedpane_details.setEnabledAt(3, true);
         student_admission_tabbedpane_details.setSelectedIndex(3);
    }//GEN-LAST:event_button_admission_previousActionPerformed

    private void studentadmission_generate_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentadmission_generate_reportActionPerformed
    try {
        String query="select surname as Surname,firstname as 'First Name' ,lastname as 'Last Name',student_adm As 'Admission No'"
                + ",date_of_admission as 'Admission Date',year as Year,stream as Stream,gender as Gender from student "
                + "where student_adm='"+student_admission_student_txt_admission_no.getText()+"'";
        JasperDesign jd= JRXmlLoader.load("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\reports\\admission.jrxml");
        JRDesignQuery newQuery= new JRDesignQuery();
        newQuery.setText(query);
        jd.setQuery(newQuery);
        JasperReport jr= JasperCompileManager.compileReport(jd);
        JasperPrint jp= JasperFillManager.fillReport(jr, null, conn);
        JasperViewer viewer=new JasperViewer(jp,false);
        viewer.setTitle("student Admission Details");
        //viewer.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\images\\report.jpg"));
         JDialog dialog = new JDialog(this);//the owner
         dialog.setContentPane(viewer.getContentPane());
         dialog.setSize(viewer.getSize());
         dialog.setTitle("student Admission Details");
         dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\images\\report.jpg") );
          dialog.setVisible(true);
        
           clearall(); 
          sequence=sequence+1;
          setAdmnissionNo(""+sequence);
           student_admission_student_txt_admission_no.setText(getAdmnissionNo());
           student_admission_tabbedpane_details.setSelectedIndex(0);
           student_admission_tabbedpane_details.setEnabledAt(0, true);
           student_admission_tabbedpane_details.setEnabledAt(3, false);
    } catch (JRException ex) {
         Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       JOptionPane.showMessageDialog(null, "Failed to generate report");
    }
         
    }//GEN-LAST:event_studentadmission_generate_reportActionPerformed

    private void student_admission_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_admission_exitActionPerformed
       int confirm=JOptionPane.showConfirmDialog(null, "Quit window?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
       if(confirm==JOptionPane.YES_OPTION){
        close();
        new StudentMainModule(username).setVisible(true);    
       }
       else{
           //clearall();
           student_admission_tabbedpane_details.setSelectedIndex(student_admission_tabbedpane_details.getSelectedIndex());
           student_admission_tabbedpane_details.setEnabledAt(student_admission_tabbedpane_details.getSelectedIndex(), true);
       }
        
    }//GEN-LAST:event_student_admission_exitActionPerformed

    private void student_admission_student_txt_surnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_admission_student_txt_surnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_admission_student_txt_surnameActionPerformed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
       
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
      
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void student_admission_student_txt_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_student_txt_boxKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){             
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_student_admission_student_txt_boxKeyTyped

    private void student_admission_txt_parent_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_parent_boxKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){             
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_student_admission_txt_parent_boxKeyTyped

    private void student_admission_txt_parent_id_noKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_parent_id_noKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           if(student_admission_txt_parent_id_no.getText().length()>=9){
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
    }//GEN-LAST:event_student_admission_txt_parent_id_noKeyTyped

    private void student_admission_student_txt_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_student_txt_boxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_admission_student_txt_boxKeyPressed

    private void student_admission_txt_parent_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_student_admission_txt_parent_emailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_student_admission_txt_parent_emailFocusGained

    private void student_admission_txt_parent_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_parent_emailKeyTyped
        // TODO add your handling code here:
          student_admission_txt_parent_email.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_student_admission_txt_parent_emailKeyTyped

    private void jDateChooser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser3MouseClicked
        // TODO add your handling code here: 
        jDateChooser3.getDateEditor().addPropertyChangeListener(
    new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            
        }

           
    });
    }//GEN-LAST:event_jDateChooser3MouseClicked

    private void jDateChooser3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser3FocusGained

    private void jDateChooser3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser3FocusLost

    private void education_btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_education_btn_previousActionPerformed
        student_admission_tabbedpane_details.setEnabledAt(1, true);
        student_admission_tabbedpane_details.setSelectedIndex(1);
        student_admission_tabbedpane_details.setEnabledAt(0, false);
        student_admission_tabbedpane_details.setEnabledAt(2, false);
        parent_previous=true;
    }//GEN-LAST:event_education_btn_previousActionPerformed

    private void btn_education_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_education_nextActionPerformed
     
        if(!education_previous){
            int mark=Integer.parseInt(student_admission_txt_edu_marks.getText());
            if(mark<0||mark>500){ JOptionPane.showMessageDialog(null, "Invalid mark");  }
            else
            if(student_admission_txt_edu_school_name.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }else if(student_admission_txt_edu_box.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }    else if(student_admission_txt_edu_postalcode.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }else if(student_admission_txt_edu_marks.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }
            else{

                try {
                    String schoolname=student_admission_txt_edu_school_name.getText();
                    String pobox=student_admission_txt_edu_box.getText();
                    String postalcode=student_admission_txt_edu_postalcode.getText();                   
                    String marks=student_admission_txt_edu_marks.getText().toString();
                    String sql="insert into education(schoolname,pobox,code,marks,student_adm) values(?,?,"
                    + "?,?,?)";

                    pst=conn.prepareStatement(sql);
                    pst.setString(1, schoolname);
                    pst.setString(2, pobox);
                    pst.setString(3, postalcode);                   
                    pst.setString(4, marks);
                    pst.setString(5, getAdmnissionNo());
                    pst.executeUpdate();
                    student_admission_tabbedpane_details.setEnabledAt(3, true);
                    student_admission_tabbedpane_details.setSelectedIndex(3);
                    student_admission_tabbedpane_details.setEnabledAt(0, false);
                    student_admission_tabbedpane_details.setEnabledAt(1, false);
                    student_admission_tabbedpane_details.setEnabledAt(2, false);
                     student_admission_tabbedpane_details.setEnabledAt(4, false);
                     fee_panel.removeAll();
                 fee_panel.add(new FeePanel(getAdmnissionNo(),student_admission_tabbedpane_details,getYear()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                finally{
                    try {
                        //conn.close();
                        rs.close();
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                //populate the next tabbed pane with data from database
                String query="select surname,firstname,lastname,student_adm,date_of_admission,year,stream from student  "
                + "where student_adm='"+student_admission_student_txt_admission_no.getText()+"'";
                try {
                    pst=conn.prepareStatement(query);
                    rs=pst.executeQuery();
                    rs.next();
                    student_admission_admission_txt_surname.setText(rs.getString("surname"));
                    student_admission_admission_txt_firstname.setText(rs.getString("firstname"));
                    student_admission_admission_txt_lastname.setText(rs.getString("lastname"));
                    student_admission_admission_txt_admission_no.setText(rs.getString("student_adm"));
                    student_admission_admission_txt_admission_date.setText(rs.getString("date_of_admission"));
                    student_admission_admission_txt_year.setText(rs.getString("year"));
                    student_admission_admission_txt_stream.setText(rs.getString("stream"));

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }finally{
                    try {
                        //conn.close();
                        rs.close();
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
        else{
            int mark=Integer.parseInt(student_admission_txt_edu_marks.getText());
            if(mark<0||mark>500){ JOptionPane.showMessageDialog(null, "Invalid mark");  }
            else
            if(student_admission_txt_edu_school_name.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }else if(student_admission_txt_edu_box.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }    else if(student_admission_txt_edu_postalcode.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }else if(student_admission_txt_edu_marks.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Enter firstname");
            }
            else{

                try {
                    String schoolname=student_admission_txt_edu_school_name.getText();
                    String pobox=student_admission_txt_edu_box.getText();
                    String postalcode=student_admission_txt_edu_postalcode.getText();                   
                    String marks=student_admission_txt_edu_marks.getText();
                    String sql="update education set schoolname=?,pobox=?,code=?,marks=? where student_adm=?";

                    pst=conn.prepareStatement(sql);
                    pst.setString(1, schoolname);
                    pst.setString(2, pobox);
                    pst.setString(3, postalcode);                   
                    pst.setString(4, marks);
                    pst.setString(5, getAdmnissionNo());
                    if( pst.executeUpdate()>0){
                        JOptionPane.showMessageDialog(null, "Details updated sucessfully","Detail update",JOptionPane.INFORMATION_MESSAGE);
                        student_admission_tabbedpane_details.setEnabledAt(3, true);
                        student_admission_tabbedpane_details.setSelectedIndex(3);
                        student_admission_tabbedpane_details.setEnabledAt(0, false);
                        student_admission_tabbedpane_details.setEnabledAt(1, false);
                        student_admission_tabbedpane_details.setEnabledAt(2, false);
                        fee_panel.removeAll();
                          fee_panel.add(new FeePanel(getAdmnissionNo(),student_admission_tabbedpane_details,getYear()));
                    }
                    else { JOptionPane.showMessageDialog(null, "update not successful","Detail update",JOptionPane.WARNING_MESSAGE);
//                    education_previous=true;
//                        student_admission_tabbedpane_details.setEnabledAt(2, true);
//                        student_admission_tabbedpane_details.setSelectedIndex(2);
//                        student_admission_tabbedpane_details.setEnabledAt(0, false);
//                        student_admission_tabbedpane_details.setEnabledAt(1, false);
//                        student_admission_tabbedpane_details.setEnabledAt(3, false);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    education_previous=true;
                     student_admission_tabbedpane_details.setEnabledAt(2, true);
                        student_admission_tabbedpane_details.setSelectedIndex(2);
                        student_admission_tabbedpane_details.setEnabledAt(0, false);
                        student_admission_tabbedpane_details.setEnabledAt(1, false);
                        student_admission_tabbedpane_details.setEnabledAt(3, false);
                }
                finally{
                    try {
                        //conn.close();
                        rs.close();
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                //populate the next tabbed pane with data from database
                String query="select surname,firstname,lastname,student_adm,date_of_admission,year,stream from student  "
                + "where student_adm='"+student_admission_student_txt_admission_no.getText()+"'";
                try {
                    pst=conn.prepareStatement(query);
                    rs=pst.executeQuery();
                    rs.next();
                    student_admission_admission_txt_surname.setText(rs.getString("surname"));
                    student_admission_admission_txt_firstname.setText(rs.getString("firstname"));
                    student_admission_admission_txt_lastname.setText(rs.getString("lastname"));
                    student_admission_admission_txt_admission_no.setText(rs.getString("student_adm"));
                    student_admission_admission_txt_admission_date.setText(rs.getString("date_of_admission"));
                    student_admission_admission_txt_year.setText(rs.getString("year"));
                    student_admission_admission_txt_stream.setText(rs.getString("stream"));

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }finally{
                    try {
                        //conn.close();
                        rs.close();
                        pst.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }//GEN-LAST:event_btn_education_nextActionPerformed

    private void student_admission_txt_edu_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_edu_boxKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_student_admission_txt_edu_boxKeyTyped

    private void student_admission_student_txt_postalcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_student_txt_postalcodeKeyPressed
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){             
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_student_admission_student_txt_postalcodeKeyPressed

    private void student_admission_student_txt_postalcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_student_txt_postalcodeKeyTyped
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           if(student_admission_student_txt_postalcode.getText().length()>5){
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
    }//GEN-LAST:event_student_admission_student_txt_postalcodeKeyTyped

    private void student_admission_txt_parent_postalcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_parent_postalcodeKeyTyped
     if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           if(student_admission_txt_parent_postalcode.getText().length()>5){
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
    }//GEN-LAST:event_student_admission_txt_parent_postalcodeKeyTyped

    private void student_admission_txt_edu_postalcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_edu_postalcodeKeyTyped
     if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           if(student_admission_txt_edu_postalcode.getText().length()>5){
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
    }//GEN-LAST:event_student_admission_txt_edu_postalcodeKeyTyped

    private void student_admission_txt_edu_marksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_student_admission_txt_edu_marksKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){  
           if(student_admission_txt_edu_marks.getText().length()>=3){
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
    }//GEN-LAST:event_student_admission_txt_edu_marksKeyTyped

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_education_next;
    private javax.swing.ButtonGroup btngroup_student;
    private javax.swing.JButton btnnext_student;
    private javax.swing.JButton button_admission_previous;
    private javax.swing.JButton education_btn_previous;
    private javax.swing.JPanel fee_panel;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton parent_button_next;
    private javax.swing.JButton parent_button_previous;
    private javax.swing.ButtonGroup parentbtn_group;
    private javax.swing.JTextField student_admission_admission_txt_admission_date;
    private javax.swing.JTextField student_admission_admission_txt_admission_no;
    private javax.swing.JTextField student_admission_admission_txt_firstname;
    private javax.swing.JTextField student_admission_admission_txt_lastname;
    private javax.swing.JTextField student_admission_admission_txt_stream;
    private javax.swing.JTextField student_admission_admission_txt_surname;
    private javax.swing.JTextField student_admission_admission_txt_year;
    private javax.swing.JComboBox student_admission_combo_parent_county;
    private javax.swing.JMenuItem student_admission_exit;
    private javax.swing.JMenu student_admission_file_menu;
    private javax.swing.JRadioButton student_admission_radio_parent_female;
    private javax.swing.JRadioButton student_admission_radio_parent_male;
    private javax.swing.JComboBox student_admission_student_combo_county;
    private javax.swing.JComboBox student_admission_student_combo_stream;
    private javax.swing.JComboBox student_admission_student_combo_year;
    private javax.swing.JRadioButton student_admission_student_radio_female;
    private javax.swing.JRadioButton student_admission_student_radio_male;
    private javax.swing.JTextField student_admission_student_txt_admission_no;
    private javax.swing.JTextField student_admission_student_txt_box;
    private javax.swing.JTextField student_admission_student_txt_firstname;
    private javax.swing.JTextField student_admission_student_txt_lastname;
    private javax.swing.JTextField student_admission_student_txt_postalcode;
    private javax.swing.JTextField student_admission_student_txt_surname;
    private javax.swing.JTextField student_admission_student_txt_town;
    private javax.swing.JTabbedPane student_admission_tabbedpane_details;
    private javax.swing.JTextField student_admission_txt_edu_box;
    private javax.swing.JFormattedTextField student_admission_txt_edu_marks;
    private javax.swing.JFormattedTextField student_admission_txt_edu_postalcode;
    private javax.swing.JTextField student_admission_txt_edu_school_name;
    private javax.swing.JTextField student_admission_txt_parent_box;
    private javax.swing.JTextField student_admission_txt_parent_email;
    private javax.swing.JTextField student_admission_txt_parent_firstname;
    private javax.swing.JTextField student_admission_txt_parent_id_no;
    private javax.swing.JTextField student_admission_txt_parent_lastname;
    private javax.swing.JFormattedTextField student_admission_txt_parent_mobile_no;
    private javax.swing.JFormattedTextField student_admission_txt_parent_postalcode;
    private javax.swing.JTextField student_admission_txt_parent_surname;
    private javax.swing.JTextField student_admission_txt_parent_town;
    private javax.swing.JButton studentadmission_generate_report;
    // End of variables declaration//GEN-END:variables
  
    private void close() {
        WindowEvent event =new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event); //To change body of generated methods, choose Tools | Templates.
    }

    private void clearall() {
     student_admission_admission_txt_admission_date.setText("");
     student_admission_admission_txt_admission_no.setText("");
    student_admission_admission_txt_firstname.setText("");
    student_admission_admission_txt_lastname.setText("");
    student_admission_admission_txt_stream.setText("");
    student_admission_admission_txt_surname.setText("");
    student_admission_admission_txt_year.setText("");    
    student_admission_student_txt_admission_no.setText("");
    student_admission_student_txt_box.setText("");
    student_admission_student_txt_firstname.setText("");
    student_admission_student_txt_lastname.setText("");
    student_admission_student_txt_postalcode.setText("");
    student_admission_student_txt_surname.setText("");
    student_admission_student_txt_town.setText("");
    
    student_admission_txt_edu_box.setText("");
    
    student_admission_txt_edu_marks.setText("");
    student_admission_txt_edu_postalcode.setText("");
    student_admission_txt_edu_school_name.setText("");
    student_admission_txt_parent_box.setText("");
    student_admission_txt_parent_email.setText("");
    student_admission_txt_parent_firstname.setText("");
    student_admission_txt_parent_id_no.setText("");
    student_admission_txt_parent_lastname.setText("");
    student_admission_txt_parent_mobile_no.setText("");
    student_admission_txt_parent_postalcode.setText("");
    student_admission_txt_parent_surname.setText("");
    student_admission_txt_parent_town.setText(""); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean isValidateAge(){
        java.util.Date date = jDateChooser1.getCalendar().getTime();       
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
      
        if(age<13){
            return false;
        }
        else return true;
         
      
}
    public boolean isValidAdmission(){
         java.util.Date date = jDateChooser2.getCalendar().getTime();       
         SimpleDateFormat fmt=new SimpleDateFormat("yyyy/dd/MM"); 
         Calendar calender=Calendar.getInstance();
         java.util.Date now=calender.getTime();
         
        if(fmt.format(date).compareTo(fmt.format(now))==0){
            return true;
            
        }
        else
            return false;
        
        
    }
public boolean isValidDob(){    
        java.util.Date date = jDateChooser1.getCalendar().getTime();        
        Calendar calender=Calendar.getInstance();        
        java.util.Date dat = calender.getTime();
         
      if(date.after(dat)) {
          return false;
      } 
      else{
          return  true;
      
        }
} 

   

    private void setparent(String ad) {
       
        try {
            String select_parent="select idno,surname,firstname,lastname,dob,"
                    + "mobilenumber,pobox,postalcode,town,county,gender,email from guardian where student_adm=?";
            //To change body of generated methods, choose Tools | Templates.
            pst=conn.prepareStatement(select_parent);
            pst.setString(1, ad);
            rs=pst.executeQuery();
            if(rs.next()){
                 parent_previous=true;
                 student_admission_txt_parent_id_no.setText(rs.getString("idno"));
                 student_admission_txt_parent_box.setText(rs.getString("pobox"));
                 student_admission_txt_parent_email.setText(rs.getString("email"));
                 student_admission_txt_parent_firstname.setText(rs.getString("firstname"));
                 student_admission_txt_parent_mobile_no.setText(rs.getString("mobilenumber"));
                 student_admission_txt_parent_lastname.setText(rs.getString("lastname"));
                 student_admission_txt_parent_town.setText(rs.getString("town"));
                 student_admission_txt_parent_surname.setText(rs.getString("surname"));
                 student_admission_txt_parent_postalcode.setText(rs.getString("postalcode"));
                 jDateChooser3.setDate(rs.getDate("dob"));
                 student_admission_combo_parent_county.setSelectedItem(rs.getString("county"));
                  if(rs.getString("gender")==student_admission_student_radio_female.getActionCommand()){
                          student_admission_student_radio_female.setSelected(true);
           
                  }
       else {
           student_admission_student_radio_male.setSelected(true);
        }
            }
            else{
                 parent_previous=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAdmission.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
        try {
           //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
       //To change body of generated methods, choose Tools | Templates.
    }
    }

    private void setstudent(String admno) {
         
       String populate="select student_adm,surname,firstname ,lastname,date_of_admission,stream,year, "
               + "dob,county,town,postalcode,pobox,gender from student where student_adm=?  ";
        try {           
        pst=conn.prepareStatement(populate);
        pst.setString(1, admno);
        rs=pst.executeQuery();
        if(rs.next()){
        student_previous=true;
        jDateChooser1.setDate(rs.getDate("dob"));
        student_admission_student_txt_admission_no.setText(rs.getString("student_adm"));
        student_admission_student_txt_firstname.setText(rs.getString("firstname"));
        student_admission_student_txt_lastname.setText(rs.getString("lastname"));
        student_admission_student_txt_surname.setText(rs.getString("surname"));          
        student_admission_student_combo_stream.setSelectedItem(rs.getString("stream"));
        student_admission_student_combo_year.setSelectedItem(rs.getString("year"));
        jDateChooser2.setDate(rs.getDate("date_of_admission"));
        student_admission_student_txt_box.setText(rs.getString("pobox"));      
        student_admission_student_txt_postalcode.setText(rs.getString("postalcode"));        
        student_admission_student_txt_town.setText("town");
        student_admission_student_combo_county.setSelectedItem(rs.getString("county"));
        if(rs.getString("gender")==student_admission_student_radio_female.getActionCommand()){
           student_admission_student_radio_female.setSelected(true);
           
       }
       else 
           student_admission_student_radio_male.setSelected(true);
        }
        else{
             student_previous=false;

        }
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       
    } 
         finally{
        try {
           //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
       //To change body of generated methods, choose Tools | Templates.
    }
    }
  

    private void seteducation(String admno) {
       
      String education="select schoolname,pobox,pobox,code,marks from education where student_adm=?";
          try {           
        pst=conn.prepareStatement(education);
        pst.setString(1, admno);
        rs=pst.executeQuery();
        if(rs.next()){ 
             education_previous=true;
           student_admission_txt_edu_school_name.setText(rs.getString("schoolname"));
           student_admission_txt_edu_box.setText(rs.getString("pobox"));
           student_admission_txt_edu_postalcode.setText(rs.getString("code"));          
           student_admission_txt_edu_marks.setText(rs.getString("marks"));
          
        }
        else
             education_previous=false;
        
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       
    } 
         finally{
        try {
           //conn.close();
            rs.close();
             pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }    
        }        
    }                  

 public boolean validateEmail(String email){
  
      String emailPattern= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    Pattern p=Pattern.compile(emailPattern) ;
    Matcher match=p.matcher(email);
    return match.matches();
 }                                              
 private boolean isValidateparentAge() {
       java.util.Date date = jDateChooser3.getCalendar().getTime();       
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
      
        if(age>=18){
            return true;
        }
        else return false;
         
    }
    private boolean isValidParentDob() {
       java.util.Date date = jDateChooser3.getCalendar().getTime();        
        Calendar calender=Calendar.getInstance();        
        java.util.Date dat = calender.getTime();
         
      if(date.after(dat)) {
          return false;
      } 
      else{
          return  true;
      
        }
    }
private boolean isInvalidParentDates(){
   // System.out.println(is);
    if(isValidParentDob()&&isValidateparentAge()){
        return false;
    }
    else return true;
}
    
    
    private  class EmailVerifier extends InputVerifier {

        public EmailVerifier() {
        }

        @Override
        public boolean verify(JComponent input) {
            JTextComponent source=(JTextComponent)input;
      String emailPattern= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
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
        }
    }
    
}

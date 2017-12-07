/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static schoolsystem.StudentMainModule.sequence;

/**
 *
 * @author codeGeek
 */
public class StudentMainModule extends javax.swing.JFrame {
StudentAdmission adm;
StudentSearch search;
Connection conn;
PreparedStatement pst;
ResultSet rs;
DatabaseConnect dbconn;
private String surname;
private String lastname;
private String firstname;
private String admno;
private String gender;
private int year;
private String dob;
private String stream;
private String higher;
private String lower;
private boolean clicked=false;
TeacherReg teacher_reg;
static String sequence;
    private FeeModuleDialog fee;
    private ReportModuleDialog report;
    private TeacherList list_taecher;
    private TeacherList list_teacher;
    private AddUser user;
    private ListDepartment list_department;
    private ListUser list_user;
    private DeleteStudent deleteStudent;
    private StudentPromption promote;
    private  static String username;
    /**
     * Creates new form StudentMainModule
     */
    public StudentMainModule(String username) {
        initComponents();
         this.sequence=sequence;
       StudentMainModule.username=username;
    try {  
        dbconn=new DatabaseConnect();
        conn=   dbconn.dbConnect();       
        populatestream();
        populateTable();
        populateAdmission();
        radio_all.setSelected(true);
        //teacher_reg=new TeacherReg(this,true);        
    } catch (ClassNotFoundException ex) {
       JOptionPane.showMessageDialog(null, ex);
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex); 
       close();
    }
    RefreshMyTable();
   
    }
      public  void populatestream(){
        
    try {
        String sql="select stream from stream";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
            combo_stream.addItem(rs.getString("stream"));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    finally{
        try {
        //  conn.close();
            pst.close();
            rs.close();
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                }
    
}
    public void populateTable(){
    String populate="select student_adm,surname,lastname ,stream,year, dob, gender from student where stream='"+combo_stream.getSelectedItem()+"' and year='"+combo_year.getSelectedItem()+"'  and gender='"+combo_gender.getSelectedItem()+"' "  ;
        try {           
        pst=conn.prepareStatement(populate);
        rs=pst.executeQuery();
        table_list_student.setModel(new ResultSetTableModel(rs));
        rs.last();
        txt_student_no.setText(""+rs.getRow());
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       
    } 
         finally{
//        try {
//           //conn.close();
////            rs.close();
////             pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
                }
    }
     public void  populateAdmission(){
         combo_from.removeAllItems();
         combo_to.removeAllItems();
        String admission="select student_adm from student where stream='"+combo_stream.getSelectedItem()+
                "' and year='"+combo_year.getSelectedItem()+"'and gender='"+combo_gender.getSelectedItem().toString()+"'";
           try {
        pst=conn.prepareStatement(admission);
         rs=pst.executeQuery();
         if(rs.next()){
             combo_from.setEnabled(true);
             combo_to.setEnabled(true);
             btn_view.setEnabled(true); 
         }
         else{
             combo_from.setEnabled(false);
             combo_to.setEnabled(false);
             btn_view.setEnabled(false);
         }
         rs.beforeFirst();
            while(rs.next()){
                combo_from .addItem(rs.getString("student_adm"));
                combo_to .addItem(rs.getString("student_adm"));
            }
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
public boolean isValidRange(){
    if( combo_from.getSelectedItem().toString().compareTo(combo_to.getSelectedItem().toString())>0){
        return false;
    
}
    else
        return true;
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_selection = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        combo_from = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        combo_year = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        combo_to = new javax.swing.JComboBox();
        btn_view = new javax.swing.JButton();
        combo_gender = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        radio_all = new javax.swing.JRadioButton();
        radio_selection = new javax.swing.JRadioButton();
        combo_stream = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        student_module_search_combo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_list_student = new javax.swing.JTable();
        btn_report = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_student_no = new javax.swing.JTextField();
        delete_student = new javax.swing.JButton();
        update_student = new javax.swing.JButton();
        btn_admit_student = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_student_admission = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        menu_item_student_report = new javax.swing.JMenuItem();
        menu_item_view_student = new javax.swing.JMenuItem();
        menu_department = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        menu_user = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        menu_item_reg_teacher = new javax.swing.JMenuItem();
        menu_item_list_teacher = new javax.swing.JMenuItem();
        menu_item_delete_teacher = new javax.swing.JMenuItem();
        menu_item_update_teacher = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jMenu5.setText("jMenu5");

        jMenu8.setText("jMenu8");

        jMenu9.setText("jMenu9");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem9.setText("jMenuItem9");

        jMenuItem6.setText("jMenuItem6");

        jMenuItem7.setText("jMenuItem7");

        jMenuItem8.setText("jMenuItem8");

        jMenu2.setText("jMenu2");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Main School system module");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("View student by"));

        jLabel2.setText("Year");

        jLabel1.setText("Stream");

        jLabel5.setText("To");

        combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        combo_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_yearItemStateChanged(evt);
            }
        });

        jLabel3.setText("Gender");

        btn_view.setText("View");
        btn_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewActionPerformed(evt);
            }
        });

        combo_gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        combo_gender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_genderItemStateChanged(evt);
            }
        });

        jLabel4.setText("From");

        btng_selection.add(radio_all);
        radio_all.setText("All Student");
        radio_all.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_allItemStateChanged(evt);
            }
        });
        radio_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_allActionPerformed(evt);
            }
        });

        btng_selection.add(radio_selection);
        radio_selection.setText("Selection");
        radio_selection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_selectionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radio_all)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(combo_from, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(combo_to, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radio_selection)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(347, 347, 347))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(btn_view))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_gender, 0, 101, Short.MAX_VALUE)
                                    .addComponent(combo_stream, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(201, 201, 201))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_all)
                    .addComponent(radio_selection))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(combo_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(combo_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_view))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Search student"));

        student_module_search_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admission no", "firstname", "surname" }));
        student_module_search_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_module_search_comboActionPerformed(evt);
            }
        });

        jLabel6.setText("Search student by");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(student_module_search_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(student_module_search_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("List of Student"));
        jPanel3.setToolTipText("");

        table_list_student.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_list_student);

        btn_report.setText("Generate Report");
        btn_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportActionPerformed(evt);
            }
        });

        jLabel7.setText("Total student Found");

        delete_student.setText("Delete Student");
        delete_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_studentActionPerformed(evt);
            }
        });

        update_student.setText("Update student");
        update_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_studentActionPerformed(evt);
            }
        });

        btn_admit_student.setText("Add student");
        btn_admit_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admit_studentActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(update_student, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(delete_student)
                                .addComponent(btn_admit_student, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_student_no, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_report)
                        .addGap(41, 41, 41))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_admit_student)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(update_student)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_student)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_report)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_student_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        menu_student_admission.setText("StudentAdmission");
        menu_student_admission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_student_admissionMouseClicked(evt);
            }
        });
        menu_student_admission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_student_admissionActionPerformed(evt);
            }
        });
        jMenuBar1.add(menu_student_admission);

        jMenu6.setText("Student  Module");

        menu_item_student_report.setText("View Student fee");
        menu_item_student_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_student_reportActionPerformed(evt);
            }
        });
        jMenu6.add(menu_item_student_report);

        menu_item_view_student.setText("View Student Report");
        menu_item_view_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_view_studentActionPerformed(evt);
            }
        });
        jMenu6.add(menu_item_view_student);

        jMenuBar1.add(jMenu6);

        menu_department.setText("Department");
        menu_department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_departmentMouseClicked(evt);
            }
        });
        menu_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_departmentActionPerformed(evt);
            }
        });

        jMenuItem3.setText("List department");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_department.add(jMenuItem3);

        jMenuBar1.add(menu_department);

        menu_user.setText(" User");
        menu_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_userMouseClicked(evt);
            }
        });
        menu_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_userActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Add user");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menu_user.add(jMenuItem5);

        jMenuItem4.setText("List User");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu_user.add(jMenuItem4);

        jMenuBar1.add(menu_user);

        jMenu7.setText("Teacher Module");

        menu_item_reg_teacher.setText("Register teacher");
        menu_item_reg_teacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_item_reg_teacherMouseClicked(evt);
            }
        });
        menu_item_reg_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_reg_teacherActionPerformed(evt);
            }
        });
        jMenu7.add(menu_item_reg_teacher);

        menu_item_list_teacher.setText("List Teachers");
        menu_item_list_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_list_teacherActionPerformed(evt);
            }
        });
        jMenu7.add(menu_item_list_teacher);

        menu_item_delete_teacher.setText("Delete Teacher");
        menu_item_delete_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_delete_teacherActionPerformed(evt);
            }
        });
        jMenu7.add(menu_item_delete_teacher);

        menu_item_update_teacher.setText("Update");
        menu_item_update_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_update_teacherActionPerformed(evt);
            }
        });
        jMenu7.add(menu_item_update_teacher);

        jMenuBar1.add(jMenu7);

        jMenu10.setText("Promote Student");
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        jMenu11.setText("change password");
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenu11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu11ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 99, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_item_student_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_student_reportActionPerformed
       fee=new FeeModuleDialog(this,true);
       fee.setVisible(true);
    }//GEN-LAST:event_menu_item_student_reportActionPerformed

    private void btn_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewActionPerformed
          
        clicked=true;
        if(isValidRange()){        
        stream=combo_stream.getSelectedItem().toString();
        year=Integer.parseInt(combo_year.getSelectedItem().toString());
         gender=combo_gender.getSelectedItem().toString();
         higher=combo_to.getSelectedItem().toString();
         lower=combo_from.getSelectedItem().toString();
           //String populate = "select student_adm,surname,lastname ,stream,year, dob, gender from student where student_adm='"+lower+"' ";
           
           String populate = "select student_adm,surname,lastname ,stream,year, dob, gender from student "
                   + "where stream='"+stream+"' and student_adm between '"+lower+"' and '"+higher+"' "
                   + "and year='"+year+"' and gender='"+gender+"' ";
           
          
    try {
        pst=conn.prepareStatement(populate);
        rs=pst.executeQuery();
         
        if(rs.next()){
            rs.previous();
       table_list_student.setModel(new ResultSetTableModel(rs));
     table_list_student.setRowSelectionInterval(0, 0);
       rs.last();
      txt_student_no.setText(""+rs.getRow());
        }
        
        else{  
       
            populateTable();
            RefreshMyTable();
             //clicked=false;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
//    finally{
//        try {
//           // conn.close();
//            rs.close();
//             pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
//                }
       }
       else
           JOptionPane.showMessageDialog(null, "Invalid Range");
               combo_from.grabFocus();
              // clicked=false;
    }//GEN-LAST:event_btn_viewActionPerformed

    private void student_module_search_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_module_search_comboActionPerformed
       
        String search_result="";
        switch(student_module_search_combo.getSelectedIndex()){
            case 0:
                String adm= JOptionPane.showInputDialog(null, "Enter Admission");
           search_result="select student_adm,surname,firstname "
                   + ",stream,year, dob, gender,completion from student where student_adm='"+adm+"'";
                break;
            case 1:
                 String firstname= JOptionPane.showInputDialog(null, "Enter Firstname");
           search_result="select student_adm,surname,firstname ,stream,year, dob, "
                   + "gender,completion from student where firstname='"+firstname+"'";
                break;
            case 2:
                String surname= JOptionPane.showInputDialog(null, "Enter surname ");
             search_result="select student_adm,surname,firstname ,stream,year, dob, "
                     + "gender,completion from student where surname='"+surname+"'";
            default:
        }
      
    try {
        pst=conn.prepareStatement(search_result);
        rs=pst.executeQuery();
        if(rs.next()){
           year=rs.getInt("year");
           surname=rs.getString("surname");
           firstname=rs.getString("firstname");
           stream=rs.getString("stream");
           dob=rs.getString("dob");
           gender=rs.getString("gender");
           admno=rs.getString("student_adm");   
           String completion =rs.getString("completion"); 
           
           search=new StudentSearch(this, true,year, surname, firstname, stream, dob, gender, admno,completion);
           search.initialize(year,surname,firstname,stream,dob,gender,admno);
           search.setVisible(true);
        }
        else{
         JOptionPane.showMessageDialog(null, "No search Results");         
        // new StudentMainModule().setVisible(true);
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
    }
//    finally{
//        try {
//           // conn.close();
//            rs.close();
//             pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
               
              //  }
    }//GEN-LAST:event_student_module_search_comboActionPerformed

    private void btn_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportActionPerformed
      try {
        String xmlToLoad="C:\\Users\\codeGeek\\Documents\\NetBeansProjects\\SchoolSystem\\src\\schoolsystem\\reports\\list_student.jrxml";
        String populate="";
        if(clicked){
            String admno=combo_from.getSelectedItem().toString();
            String stream=combo_stream.getSelectedItem().toString();
            String year=combo_year.getSelectedItem().toString();
            String gender=combo_gender.getSelectedItem().toString();
            String higher=combo_to.getSelectedItem().toString();
            String lower=combo_from.getSelectedItem().toString();
            populate="select student_adm,surname,firstname,lastname ,stream,year, dob, gender from student "
                    + "where stream='"+stream+"' and student_adm between '"+lower+"' and '"+higher+"' "
                    + "and year='"+year+"' and gender='"+gender+"' ";
        }
        else{
            populate="select student_adm,surname,firstname,lastname "
                    + ",stream,year, dob, gender from student where stream='"+combo_stream.getSelectedItem()+"'"  ;
        }
        
        JasperDesign jd=JRXmlLoader.load(xmlToLoad);
        JRDesignQuery query=new JRDesignQuery();
        query.setText(populate);
        jd.setQuery(query);
        JasperReport report=JasperCompileManager.compileReport(jd);
        JasperPrint print=JasperFillManager.fillReport(report, null,conn);
        JasperViewer viewer=new JasperViewer(print,false);
        viewer.setTitle("List Of student");
        viewer.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\codeGeek\\Documents\\NetBeansProjects"
                + "\\SchoolSystem\\src\\schoolsystem\\images\\report.jpg"));
        // viewer.setDefaultCloseOperatio(DO_NOTHING_ON_CLOSE);
        viewer.setVisible(true);
        clicked=false;
        populateTable();
        RefreshMyTable();
       
    } catch (JRException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }//GEN-LAST:event_btn_reportActionPerformed

    private void combo_genderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_genderItemStateChanged
         populateAdmission(); 
         populateTable();
         RefreshMyTable();
 // TODO add your handling code here:
    }//GEN-LAST:event_combo_genderItemStateChanged

    private void combo_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_yearItemStateChanged
      
        populateAdmission();
        populateTable();
        RefreshMyTable();
// TODO add your handling code here:
    }//GEN-LAST:event_combo_yearItemStateChanged

    private void radio_allItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_allItemStateChanged
      combo_from.setEnabled(false);
      combo_stream.setEnabled(false);
      combo_to.setEnabled(false);
      combo_year.setEnabled(false);
      combo_gender.setEnabled(false);
      btn_view.setEnabled(false);
        populateAllStudent();
         RefreshMyTable();
        if (evt.getStateChange()==ItemEvent.SELECTED) {
          combo_stream.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent e) {
               populateAdmission();
               populateTable();
 RefreshMyTable();//To change body of generated methods, choose Tools | Templates.
              }
          });
            
        }
        else{            
          combo_stream.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent e) {
               populateAdmission();
               populateTable();
RefreshMyTable();//To change body of generated methods, choose Tools | Templates.
              }
          });   
        }
    }//GEN-LAST:event_radio_allItemStateChanged

    private void radio_selectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_selectionItemStateChanged
       
        combo_from.setEnabled(true);
       combo_to.setEnabled(true);
       combo_year.setEnabled(true);
       combo_gender.setEnabled(true);
       btn_view.setEnabled(true);
       combo_stream.setEnabled(true);
       populateAdmission();
       populateTable();
       RefreshMyTable();
        if (evt.getStateChange()==ItemEvent.SELECTED) {
          combo_stream.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent e) {
                     populateAdmission();
                     populateTable();
 if(table_list_student.getRowCount()>0){
      delete_student.setEnabled(true);
      update_student.setEnabled(true);
      table_list_student.setRowSelectionInterval(0, 0);
    }
    else{
        update_student.setEnabled(false);
        delete_student.setEnabled(false); 
    }//To change body of generated methods, choose Tools | Templates.
              }
          });
            
        }
        else{            
          combo_stream.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent e) {
                                populateAdmission(); 
                               populateTable();
 

RefreshMyTable();
//To change body of generated methods, choose Tools | Templates.
              }
          });   
        }
    }//GEN-LAST:event_radio_selectionItemStateChanged

    private void radio_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_allActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_allActionPerformed

    private void menu_student_admissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_student_admissionMouseClicked
        adm=new StudentAdmission(this, true);
        adm.setVisible(true);
    }//GEN-LAST:event_menu_student_admissionMouseClicked

    private void menu_student_admissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_student_admissionActionPerformed
       adm=new StudentAdmission(this, true);
       adm.setVisible(true);
    }//GEN-LAST:event_menu_student_admissionActionPerformed

    private void menu_item_view_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_view_studentActionPerformed
     report=new ReportModuleDialog(this, true);
     report.setVisible(true);
    }//GEN-LAST:event_menu_item_view_studentActionPerformed

    private void menu_item_reg_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_reg_teacherActionPerformed
       teacher_reg=new TeacherReg(this, true);
       teacher_reg.setVisible(true);
    }//GEN-LAST:event_menu_item_reg_teacherActionPerformed

    private void menu_item_list_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_list_teacherActionPerformed
        list_teacher=new TeacherList(this,true);        
        list_teacher.setVisible(true);
    }//GEN-LAST:event_menu_item_list_teacherActionPerformed

    private void menu_item_delete_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_delete_teacherActionPerformed
       list_teacher=new TeacherList(this,true);        
        list_teacher.setVisible(true);
    }//GEN-LAST:event_menu_item_delete_teacherActionPerformed

    private void menu_item_update_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_update_teacherActionPerformed
         list_teacher=new TeacherList(this,true);        
        list_teacher.setVisible(true);
    }//GEN-LAST:event_menu_item_update_teacherActionPerformed

    private void menu_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_departmentActionPerformed
     list_department=new ListDepartment(this,true);
     list_department.setVisible(true);
    }//GEN-LAST:event_menu_departmentActionPerformed

    private void menu_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_userActionPerformed
    
    }//GEN-LAST:event_menu_userActionPerformed

    private void menu_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_userMouseClicked
    ;
    }//GEN-LAST:event_menu_userMouseClicked

    private void menu_item_reg_teacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_item_reg_teacherMouseClicked
      teacher_reg=new TeacherReg(this, true);
       teacher_reg.setVisible(true);
    }//GEN-LAST:event_menu_item_reg_teacherMouseClicked

    private void menu_departmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_departmentMouseClicked
       
    }//GEN-LAST:event_menu_departmentMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        list_department=new ListDepartment(this,true);
        list_department.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        user=new AddUser(this,true);
        user.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
      list_user=new ListUser(this, true);
      list_user.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
       user=new AddUser(this,true);
      user.setVisible(true);
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void delete_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_studentActionPerformed
     int row= table_list_student.getSelectedRow();
     String admno=table_list_student.getModel().getValueAt(row, 0).toString();
     String fistname=table_list_student.getModel().getValueAt(row, 2).toString();
     String surname=table_list_student.getModel().getValueAt(row, 1).toString();
     String studentname=fistname+"  "+ surname;
     deleteStudent=new DeleteStudent(admno, studentname, this, clicked);
     deleteStudent.setVisible(true);
    }//GEN-LAST:event_delete_studentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // if(radio_all.isSelected()){
                populateAllStudent();
                RefreshMyTable();

             //populateTable();
     //   }
       // else populateTable();
       //  RefreshMyTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_admit_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admit_studentActionPerformed
          adm=new StudentAdmission(this, true);
          adm.setVisible(true);
    }//GEN-LAST:event_btn_admit_studentActionPerformed

    private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed
      promote=new StudentPromption(this, true);
      promote.setVisible(true);
    }//GEN-LAST:event_jMenu10ActionPerformed

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        promote=new StudentPromption(this, true);
        promote.setVisible(true);
    }//GEN-LAST:event_jMenu10MouseClicked

    private void update_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_studentActionPerformed
       int row= table_list_student.getSelectedRow();
       String admno=table_list_student.getModel().getValueAt(row, 0).toString();
        adm=new StudentAdmission(this, true,admno,username);
          adm.setVisible(true);  
    }//GEN-LAST:event_update_studentActionPerformed

    private void jMenu11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu11ActionPerformed
        ChangePassword password=new ChangePassword(this,true,"principal",username);
        password.setVisible(true);
    }//GEN-LAST:event_jMenu11ActionPerformed

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked
        ChangePassword password=new ChangePassword(this,true,"principal",username);
        password.setVisible(true);
    }//GEN-LAST:event_jMenu11MouseClicked

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
            java.util.logging.Logger.getLogger(StudentMainModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentMainModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentMainModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentMainModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentMainModule(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_admit_student;
    private javax.swing.JButton btn_report;
    private javax.swing.JButton btn_view;
    private javax.swing.ButtonGroup btng_selection;
    private javax.swing.JComboBox combo_from;
    private javax.swing.JComboBox combo_gender;
    private javax.swing.JComboBox combo_stream;
    private javax.swing.JComboBox combo_to;
    private javax.swing.JComboBox combo_year;
    private javax.swing.JButton delete_student;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu_department;
    private javax.swing.JMenuItem menu_item_delete_teacher;
    private javax.swing.JMenuItem menu_item_list_teacher;
    private javax.swing.JMenuItem menu_item_reg_teacher;
    private javax.swing.JMenuItem menu_item_student_report;
    private javax.swing.JMenuItem menu_item_update_teacher;
    private javax.swing.JMenuItem menu_item_view_student;
    private javax.swing.JMenu menu_student_admission;
    private javax.swing.JMenu menu_user;
    private javax.swing.JRadioButton radio_all;
    private javax.swing.JRadioButton radio_selection;
    private javax.swing.JComboBox student_module_search_combo;
    private javax.swing.JTable table_list_student;
    private javax.swing.JTextField txt_student_no;
    private javax.swing.JButton update_student;
    // End of variables declaration//GEN-END:variables


    private void close() {
        WindowEvent event=new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
                //To change body of generated methods, choose Tools | Templates.
    }

    private void populateAllStudent() {
      String populate="select student_adm,surname,firstname ,stream,year, dob, gender from student where year<5";
             // + "where stream='"+combo_stream.getSelectedItem()+"'  "  ;
        try {           
        pst=conn.prepareStatement(populate);
        rs=pst.executeQuery();
        table_list_student.setModel(new ResultSetTableModel(rs));
        rs.last();
        txt_student_no.setText(""+rs.getRow());
    } catch (SQLException ex) {
        Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
       
    } 
//         finally{
//        try {
//           //conn.close();
//            rs.close();
//             pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentMainModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
//                }
    }

private void RefreshMyTable(){
     if(table_list_student.getRowCount()>0){
      delete_student.setEnabled(true);
      update_student.setEnabled(true);
      table_list_student.setRowSelectionInterval(0, 0);
    }
    else{
        update_student.setEnabled(false);
        delete_student.setEnabled(false); 
    }
}

}

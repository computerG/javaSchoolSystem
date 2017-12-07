/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author codeGeek
 */
public class MarksEntrydialog extends javax.swing.JFrame {
Connection conn;
PreparedStatement pst;
ResultSet rs;
DatabaseConnect dbConn;
    private String teacher_name;
    private String grade;
    private String remarks;
    private String marks;
    private String admission;
    private String idno;
    private String category;
    /**
     * Creates new form marksEntry
     */
    public MarksEntrydialog() {
    
    }

    MarksEntrydialog(String tName,String id) {
        try {
        initComponents();
          this.setIconImage(new ImageIcon("C:\\Users\\codeGeek\\Documents\\NetBeansProjects"
                      + "\\SchoolSystem\\src\\schoolsystem\\images\\schoolicon.jpg").getImage());
        this.idno=id;
        this.teacher_name=tName;
        dbConn=new DatabaseConnect();
        conn= dbConn.dbConnect();   
        //btn_update.setEnabled(false);
        btn_update.setEnabled(false);
        combo_admno.setEnabled(false);
        txt_marks.setEnabled(false);
        btn_nxt_student.setEnabled(false);
        btn_change_subject.setEnabled(false);
        
        populateComboStream();
        populateComboExamType();
        populateComboSession();
        populateComboSubject();
        
     
        label_teacher_name.setText("Marks being Entered by "+this.teacher_name);
    } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
        //To change body of generated methods, choose Tools | Templates.
    }
public void populateComboStream(){
    String stream="select stream from stream";
    try{ pst=conn.prepareStatement(stream);
            rs=pst.executeQuery();
            while(rs.next()){
                 marks_entry_combo_stream.addItem(rs.getString("stream"));
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
public void populateComboSubject(){
    String stream="select subject from teacher where tscNo='"+idno+"'";
    try{pst=conn.prepareStatement(stream);
        rs=pst.executeQuery();
        if(rs.next()){
            String subject=rs.getString("subject");             
             String newsubject=subject.replace("[","");
             String finalsubject= newsubject.replace("]","");
             String noSpaceSubject=finalsubject.replace(", ", ",");
            String[] subjectarr=noSpaceSubject.split(",");
            System.out.println("SUBJECTS: "+noSpaceSubject); 
            for(int i=0;i<subjectarr.length;i++)
                 marks_entry_combo_subject.addItem(subjectarr[i]+"\n");
         
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
public void populateComboExamType(){
    String stream="select exam_type from exam";
    try{    pst=conn.prepareStatement(stream);
            rs=pst.executeQuery();
            while(rs.next()){
                 marks_entry_combo_exam.addItem(rs.getString("exam_type"));
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
public void populateComboSession(){
    String stream="select session_name from session";
    try{
            pst=conn.prepareStatement(stream);
            rs=pst.executeQuery();
            while(rs.next()){
                 marks_entry_combo_term.addItem(rs.getString("session_name"));
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entry_mark_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        marks_entry_combo_term = new javax.swing.JComboBox();
        marks_entry_combo_exam = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        marks_entry_btn_enter = new javax.swing.JButton();
        marks_entry_combo_stream = new javax.swing.JComboBox();
        marks_entry_combo_subject = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        marks_entry_combo_year = new javax.swing.JComboBox();
        panel_marks_entry = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_marks = new javax.swing.JTable();
        btn_update = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_admno_update = new javax.swing.JTextField();
        txt_marks_update = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        admin_filter = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_subject_update = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_cat1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_cat2 = new javax.swing.JTextField();
        marks_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_marks = new javax.swing.JTextField();
        combo_admno = new javax.swing.JComboBox();
        btn_nxt_student = new javax.swing.JButton();
        label_teacher_name = new javax.swing.JLabel();
        btn_change_subject = new javax.swing.JButton();
        lbl_out_of = new javax.swing.JLabel();
        marks_entered = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Marks Entry");
        setName("marks_entry"); // NOI18N
        setResizable(false);

        entry_mark_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marks Entry for"));

        jLabel3.setText("Exam type");

        jLabel2.setText("Stream");

        jLabel4.setText("Term");

        marks_entry_btn_enter.setText("Enter Marks");
        marks_entry_btn_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marks_entry_btn_enterActionPerformed(evt);
            }
        });

        marks_entry_combo_stream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marks_entry_combo_streamActionPerformed(evt);
            }
        });

        jLabel1.setText("Subject");

        jLabel5.setText("Year");

        marks_entry_combo_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        javax.swing.GroupLayout entry_mark_panelLayout = new javax.swing.GroupLayout(entry_mark_panel);
        entry_mark_panel.setLayout(entry_mark_panelLayout);
        entry_mark_panelLayout.setHorizontalGroup(
            entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entry_mark_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(marks_entry_combo_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(marks_entry_combo_term, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(marks_entry_combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(entry_mark_panelLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(marks_entry_btn_enter)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(entry_mark_panelLayout.createSequentialGroup()
                        .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(marks_entry_combo_stream, 0, 98, Short.MAX_VALUE))
                            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(marks_entry_combo_exam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        entry_mark_panelLayout.setVerticalGroup(
            entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entry_mark_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(marks_entry_combo_subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marks_entry_combo_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(marks_entry_combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(marks_entry_combo_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entry_mark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(marks_entry_combo_exam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marks_entry_btn_enter)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        panel_marks_entry.setBorder(javax.swing.BorderFactory.createTitledBorder("Entered  marks of the student"));

        table_marks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_marks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_marksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_marks);

        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLabel9.setText("Adm no");

        jLabel8.setText("End exam");

        jLabel10.setText("Filter Records by admno");

        admin_filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                admin_filterKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                admin_filterKeyTyped(evt);
            }
        });

        jLabel11.setText("Subject");

        jLabel12.setText("CAT1");

        jLabel13.setText("CAT2");

        javax.swing.GroupLayout panel_marks_entryLayout = new javax.swing.GroupLayout(panel_marks_entry);
        panel_marks_entry.setLayout(panel_marks_entryLayout);
        panel_marks_entryLayout.setHorizontalGroup(
            panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_marks_entryLayout.createSequentialGroup()
                .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_marks_entryLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_marks_entryLayout.createSequentialGroup()
                                .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_subject_update)
                                    .addComponent(txt_marks_update, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(txt_admno_update)
                                    .addComponent(txt_cat1)
                                    .addComponent(txt_cat2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_marks_entryLayout.createSequentialGroup()
                                .addComponent(btn_update)
                                .addGap(9, 9, 9))))
                    .addGroup(panel_marks_entryLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(admin_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_marks_entryLayout.setVerticalGroup(
            panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_marks_entryLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(admin_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_marks_entryLayout.createSequentialGroup()
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_admno_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_subject_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_cat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_cat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_marks_entryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_marks_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_update))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        marks_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marks"));

        jLabel6.setText("Admission No");

        jLabel7.setText("Marks");

        txt_marks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_marksActionPerformed(evt);
            }
        });
        txt_marks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_marksKeyTyped(evt);
            }
        });

        combo_admno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_admnoItemStateChanged(evt);
            }
        });
        combo_admno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_admnoActionPerformed(evt);
            }
        });

        btn_nxt_student.setText("Save Marks");
        btn_nxt_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nxt_studentActionPerformed(evt);
            }
        });

        btn_change_subject.setText("Change Subject/Exam");
        btn_change_subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_change_subjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout marks_panelLayout = new javax.swing.GroupLayout(marks_panel);
        marks_panel.setLayout(marks_panelLayout);
        marks_panelLayout.setHorizontalGroup(
            marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marks_panelLayout.createSequentialGroup()
                .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marks_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_nxt_student)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_change_subject))
                    .addGroup(marks_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_teacher_name, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(marks_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marks_panelLayout.createSequentialGroup()
                        .addComponent(marks_entered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(marks_panelLayout.createSequentialGroup()
                        .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(marks_panelLayout.createSequentialGroup()
                                .addComponent(combo_admno, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(marks_panelLayout.createSequentialGroup()
                                .addComponent(txt_marks, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_out_of, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
                        .addGap(82, 82, 82))))
        );
        marks_panelLayout.setVerticalGroup(
            marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marks_panelLayout.createSequentialGroup()
                .addComponent(label_teacher_name, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marks_entered, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(combo_admno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_out_of, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_marks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(marks_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nxt_student)
                    .addComponent(btn_change_subject))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jMenu1.setText("Student Ranking");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(entry_mark_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(marks_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_marks_entry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(entry_mark_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marks_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_marks_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void marks_entry_btn_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marks_entry_btn_enterActionPerformed
            populateAdmission();  
            if(combo_admno.getSelectedIndex()<0){
                JOptionPane.showMessageDialog(null , "no student in the class");
            }
            else{
            populateTable();
            btn_nxt_student.setEnabled(true);
            txt_marks.setEditable(true);
            marks_panel.setVisible(true);
            txt_marks.setEnabled(true);
            combo_admno.setEnabled(true);
            panel_marks_entry.setVisible(true);
            marks_entry_combo_stream.setEnabled(false);
            marks_entry_combo_exam.setEnabled(false);
            marks_entry_combo_subject.setEnabled(false);
            marks_entry_combo_year.setEnabled(false);
            marks_entry_combo_term.setEnabled(false);
            marks_entry_btn_enter.setEnabled(false);
            marks_entered.setText("Hello "+ teacher_name+"! Your Now Entering "+marks_entry_combo_exam.getSelectedItem()+" Marks\n"
                    + " for "+marks_entry_combo_subject.getSelectedItem().toString()+"\n")
                   ;
            marks_entered.setForeground(Color.red);
          if(marks_entry_combo_exam.getSelectedItem().toString().equals("C.A.T 1")|| 
                   (marks_entry_combo_exam.getSelectedItem().toString().equals("C.A.T 2"))){
               lbl_out_of.setText("/40");
           }
           else  if(marks_entry_combo_exam.getSelectedItem().toString().equals("Exam")){
               lbl_out_of.setText("/100");  
           }
            }
    }//GEN-LAST:event_marks_entry_btn_enterActionPerformed

    private void btn_nxt_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nxt_studentActionPerformed
        btn_nxt_student.setEnabled(false);
        btn_change_subject.setEnabled(true);
        txt_marks.setEnabled(false);
        combo_admno.setEnabled(false);
        if(!txt_marks.getText().isEmpty()){
        if(marks_entry_combo_exam.getSelectedItem().toString().equals("C.A.T 1"))  {//check the exam selected
           //case 1
          if(checkMarksEntered("cat1"))
          {//check if the marks already entered
                     JOptionPane.showMessageDialog(null, "Marks for admission no  "+combo_admno.getSelectedItem().toString()+" Entered\n"
                + "Entered marks for another student OR anaother subject or update");
           if(JOptionPane.showConfirmDialog(null, "UPDATE DETAILS?","Update",
                   JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
               {//check the user confirmation
               if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=40)
                   {//validate marks entered
                 try 
                 { //start a try block for updating details             
               String insert="update marks set cat1=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setString(1, txt_marks.getText());
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try block
                 catch(Exception e){
                  //exception handling   
           }
                   }//end validate marks if
               else{
                   JOptionPane.showMessageDialog(null, "Invalid Mark");
                   txt_marks.grabFocus();
                   btn_nxt_student.setEnabled(true);
                    txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
               }
                 }//end if confirmation
           else{
               //do nothing when cancel button pressed
           }
           }//end if marks entered
           else{//else of if marksentered condition
             if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=40){//start if to validate marks entered           
                         if(admnoEntered()){//check whether the admno is already entered
                  try { //start try catch block             
               String insert="update marks set cat1=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setString(1,  txt_marks.getText());
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try
                 catch(Exception e){
                 //exception code here    
           }//end try catch block
                  
            }//close if admn entered
            else{//else
                   try {//open try catch block
               String insert="insert into marks(admno,stream,subject,cat1,exam,subject_teacher,term) values (?,?,?"
                      + ",?,?,?,?) ";
              pst=conn.prepareStatement(insert);
              pst.setString(1, combo_admno.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_stream.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setInt(4, Integer.parseInt(txt_marks.getText()));
              pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(6, teacher_name);
              pst.setString(7, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
              ,marks_entry_combo_stream.getSelectedItem().toString()
              ,marks_entry_combo_subject.getSelectedItem().toString()
              ,marks_entry_combo_term.getSelectedItem().toString()
              ,marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
          } catch (SQLException ex) {
              Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
          }            
             }
             }//end check marks validity
           else{
                 JOptionPane.showMessageDialog(null, "INVALID CAT MARK");
                    txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
                   } 
           //end case 1
          }          
          }//close the if
        else
           if(marks_entry_combo_exam.getSelectedItem().toString().equals("C.A.T 2"))  {//check the exam selected
           //case 1
          if(checkMarksEntered("cat2"))
          {//check if the marks already entered
                     JOptionPane.showMessageDialog(null, "Marks for admission no  "+combo_admno.getSelectedItem().toString()+" Entered\n"
                + "Entered marks for another student OR anaother subject or update");
           if(JOptionPane.showConfirmDialog(null, "UPDATE DETAILS?","Update",
                   JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
               {//check the user confirmation
               if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=40)
                   {//validate marks entered
                 try 
                 { //start a try block for updating details             
               String insert="update marks set cat2=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setString(1, txt_marks.getText());
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try block
                 catch(Exception e){
                  //exception handling   
           }
                   }//end validate marks if
               else{
                   JOptionPane.showMessageDialog(null, "Invalid Mark");
                   btn_nxt_student.setEnabled(true);
                   txt_marks.grabFocus(); txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
               }
                 }//end if confirmation
           else{
               //do nothing when cancel button pressed
           }
           }//end if marks entered
           else{//else of if marksentered condition
             if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=40){//start if to validate marks entered           
                         if(admnoEntered()){//check whether the admno is already entered
                  try { //start try catch block             
               String insert="update marks set cat2=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setString(1, txt_marks.getText());
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try
                 catch(Exception e){
                 //exception code here    
           }//end try catch block
                  
            }//close if admn entered
            else{//else
                   try {//open try catch block
               String insert="insert into marks(admno,stream,subject,cat2,exam,subject_teacher,term) values (?,?,?"
                      + ",?,?,?,?) ";
              pst=conn.prepareStatement(insert);
              pst.setString(1, combo_admno.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_stream.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setInt(4, Integer.parseInt(txt_marks.getText()));
              pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(6, teacher_name);
              pst.setString(7, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
              ,marks_entry_combo_stream.getSelectedItem().toString()
              ,marks_entry_combo_subject.getSelectedItem().toString()
              ,marks_entry_combo_term.getSelectedItem().toString()
              ,marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
          } catch (SQLException ex) {
              Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
          }            
             }
             }//end check marks validity
           else{
                 JOptionPane.showMessageDialog(null, "INVALID CAT MARK");
                   txt_marks.grabFocus();
                   btn_nxt_student.setEnabled(true);
                    txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
                   } 
           //end case 1
          }          
          }
           else
                if(marks_entry_combo_exam.getSelectedItem().toString().equals("Exam"))  {//check the exam selected
           //case 1
          if(checkMarksEntered("marks"))
          {//check if the marks already entered
                     JOptionPane.showMessageDialog(null, "Marks for admission no  "+combo_admno.getSelectedItem().toString()+" Entered\n"
                + "Entered marks for another student OR anaother subject or update");
           if(JOptionPane.showConfirmDialog(null, "UPDATE DETAILS?","Update",
                   JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
               {//check the user confirmation
               if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=100)
                   {//validate marks entered
                          float marks_avrg=Float.valueOf(txt_marks.getText())/100*60;
                               System.out.println(getMarks());
                                System.out.println(marks_avrg);
                 try 
                 { //start a try block for updating details             
               String insert="update marks set marks=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setInt(1,(int)marks_avrg);
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try block
                 catch(Exception e){
                  //exception handling   
           }
                   }//end validate marks if
               else{
                   JOptionPane.showMessageDialog(null, "Invalid EXAM Mark");
                   txt_marks.grabFocus();
                   btn_nxt_student.setEnabled(true);
                    txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
               }
                 }//end if confirmation
           else{
               //do nothing when cancel button pressed
           }
           }//end if marks entered
           else{//else of if marksentered condition
             if(Integer.parseInt(getMarks())>=0&&Integer.parseInt(getMarks())<=100){//start if to validate marks entered           
                         if(admnoEntered()){
                               float marks_avrg=Float.valueOf(txt_marks.getText())/100*60;
                               System.out.println(getMarks());
                                System.out.println(marks_avrg);
//check whether the admno is already entered
                  try { //start try catch block    
                       
               String insert="update marks set marks=? where subject=? and term=? and admno=? and stream=? "                                        ;
              pst=conn.prepareStatement(insert);
              pst.setString(4, combo_admno.getSelectedItem().toString());
              pst.setString(5, marks_entry_combo_stream.getSelectedItem().toString());             
              pst.setInt(1,(int)marks_avrg);
             // pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
                      ,marks_entry_combo_stream.getSelectedItem().toString()
                      ,marks_entry_combo_subject.getSelectedItem().toString()
                      ,marks_entry_combo_term.getSelectedItem().toString(),
                       marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
           }//end try
                 catch(Exception e){
                 //exception code here    
           }//end try catch block
                  
            }//close if admn entered
            else{//else
                   try {//open try catch block
                        float marks_avrg=Float.valueOf(txt_marks.getText())/100*60;
                               System.out.println(getMarks());
                                System.out.println(marks_avrg);
               String insert="insert into marks(admno,stream,subject,marks,exam,subject_teacher,term) values (?,?,?"
                      + ",?,?,?,?) ";
              pst=conn.prepareStatement(insert);
              pst.setString(1, combo_admno.getSelectedItem().toString());
              pst.setString(2, marks_entry_combo_stream.getSelectedItem().toString());
              pst.setString(3, marks_entry_combo_subject.getSelectedItem().toString());
              pst.setInt(4, (int)marks_avrg);
              pst.setString(5, marks_entry_combo_exam.getSelectedItem().toString());
              pst.setString(6, teacher_name);
              pst.setString(7, marks_entry_combo_term.getSelectedItem().toString());              
              pst.executeUpdate();
              insertRemarkAndGrade(combo_admno.getSelectedItem().toString()
              ,marks_entry_combo_stream.getSelectedItem().toString()
              ,marks_entry_combo_subject.getSelectedItem().toString()
              ,marks_entry_combo_term.getSelectedItem().toString()
              ,marks_entry_combo_year.getSelectedItem().toString());
              populateTable();
          } catch (SQLException ex) {
              Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
          }            
             }
             }//end check marks validity
           else{
                 JOptionPane.showMessageDialog(null, "INVALID Exam MARK");
                   txt_marks.grabFocus();
                   btn_nxt_student.setEnabled(true);
                    txt_marks.setEnabled(true);
                    combo_admno.setEnabled(true);
                   } 
           //end case 1
          }          
          
          
        }
        }
        else
            
            {JOptionPane.showMessageDialog(null, "Marks Field cannot be empty!");
            btn_nxt_student.setEnabled(true);
            txt_marks.setEnabled(true);
            txt_marks.grabFocus();
            combo_admno.setEnabled(true);
            }
        
 
    }//GEN-LAST:event_btn_nxt_studentActionPerformed
 public String getMarks() {
        return txt_marks.getText().toString();
    }
    private void txt_marksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_marksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_marksActionPerformed

    private void txt_marksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_marksKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'){             
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_txt_marksKeyTyped

    private void table_marksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_marksMouseClicked
   btn_update.setEnabled(true);
    int row=table_marks.getSelectedRow();    
    int column=table_marks.getSelectedColumn();
    String subject=table_marks.getModel().getValueAt(row, 1).toString();
    String marks=table_marks.getModel().getValueAt(row, 5).toString();
    String cat2 = table_marks.getModel().getValueAt(row, 4).toString();
    String cat1 = table_marks.getModel().getValueAt(row, 3).toString();
     admission =table_marks.getModel().getValueAt(row, 0).toString();
     txt_cat1.setText(cat1);
     txt_cat2.setText(cat2);
    txt_marks_update.setText(marks);
    txt_admno_update.setEditable(false);
    txt_admno_update.setText(admission); 
    txt_subject_update.setText(subject);
    btn_update.setEnabled(true);
    }//GEN-LAST:event_table_marksMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
    btn_update.setEnabled(false);
        try {
        String update="update marks set marks=?,cat1=?,cat2=? where admno=?";
        pst=conn.prepareStatement(update);
        pst.setString(1, txt_marks_update.getText());
         pst.setString(2, txt_cat1.getText());
        pst.setString(3,txt_cat2.getText());
        pst.setString(4,txt_admno_update.getText());
        pst.executeUpdate();
        populateTable();
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void admin_filterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_filterKeyTyped
        if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'||evt.getKeyChar()==KeyEvent.VK_SLASH){  
            try {
                String filter="Select admno,subject,term,cat1,cat2,marks,"
                        + "grade,remarks from marks where admno LIKE '%"+admin_filter.getText()+"%'";
                pst=conn.prepareStatement(filter);
                rs=pst.executeQuery(); 
                table_marks.setModel(new ResultSetTableModel(rs));   
                    } catch (SQLException ex) {
                Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_admin_filterKeyTyped

    private void admin_filterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_filterKeyReleased
       if(evt.getKeyChar()>='0'&&evt.getKeyChar()<='9'||evt.getKeyChar()==KeyEvent.VK_SLASH){  
            try {
                String filter="Select admno,subject,term,cat1,cat2,marks,grade,remarks from marks where admno LIKE '"+admin_filter.getText()+"%'";
                pst=conn.prepareStatement(filter);
                rs=pst.executeQuery();
                table_marks.setModel(new ResultSetTableModel(rs));   
                    } catch (SQLException ex) {
                Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else{
           evt.consume();
           Toolkit.getDefaultToolkit().beep();
       }
    }//GEN-LAST:event_admin_filterKeyReleased

    private void btn_change_subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_change_subjectActionPerformed
            marks_entered.setText("");
            btn_change_subject.setEnabled(false);
            txt_marks.setEditable(false);
            txt_marks.setEnabled(false);
            combo_admno.setEnabled(false);
            marks_entry_combo_stream.setEnabled(true);
            marks_entry_combo_exam.setEnabled(true);
            marks_entry_combo_subject.setEnabled(true);
            marks_entry_combo_year.setEnabled(true);
            marks_entry_combo_term.setEnabled(true);
            marks_entry_btn_enter.setEnabled(true);
            btn_nxt_student.setEnabled(false);

    }//GEN-LAST:event_btn_change_subjectActionPerformed

    private void combo_admnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_admnoItemStateChanged
    //  populateTable();
    }//GEN-LAST:event_combo_admnoItemStateChanged

    private void combo_admnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_admnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_admnoActionPerformed

    private void marks_entry_combo_streamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marks_entry_combo_streamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marks_entry_combo_streamActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
      new Ranking(this,true).setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
         new Ranking(this, true).setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField admin_filter;
    private javax.swing.JButton btn_change_subject;
    private javax.swing.JButton btn_nxt_student;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox combo_admno;
    private javax.swing.JPanel entry_mark_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label_teacher_name;
    private javax.swing.JLabel lbl_out_of;
    private javax.swing.JLabel marks_entered;
    private javax.swing.JButton marks_entry_btn_enter;
    private javax.swing.JComboBox marks_entry_combo_exam;
    private javax.swing.JComboBox marks_entry_combo_stream;
    private javax.swing.JComboBox marks_entry_combo_subject;
    private javax.swing.JComboBox marks_entry_combo_term;
    private javax.swing.JComboBox marks_entry_combo_year;
    private javax.swing.JPanel marks_panel;
    private javax.swing.JPanel panel_marks_entry;
    private javax.swing.JTable table_marks;
    private javax.swing.JTextField txt_admno_update;
    private javax.swing.JTextField txt_cat1;
    private javax.swing.JTextField txt_cat2;
    private javax.swing.JTextField txt_marks;
    private javax.swing.JTextField txt_marks_update;
    private javax.swing.JTextField txt_subject_update;
    // End of variables declaration//GEN-END:variables

     public boolean checkMarksEntered(String present){
       try {
         String entered="Select " +present+ 
                 " from marks where admno='"+combo_admno.getSelectedItem()+
                 "' and subject='"+marks_entry_combo_subject.getSelectedItem().toString()+"'";
         pst=conn.prepareStatement(entered);
         rs=pst.executeQuery();    
         if(rs.next()&&rs.getInt(present)>0){            
           return true;            
         }
         else return false;
         //To change body of generated methods, choose Tools | Templates.
     } catch (SQLException ex) {
         Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
         return false;
     }
  
    }
     public void populateTable(){
         String populate="Select admno,subject,term,cat1 as CAT1,cat2 AS CAT2,marks as Exam,FORMAT(((cat1+cat2)/2)+marks,0) as TotalMarks,grade,remarks from marks inner join"
                 + " student on marks.admno=student.student_adm  where"
                    + " admno='"+combo_admno.getSelectedItem().toString()+"'"
                    + " and subject='"+marks_entry_combo_subject.getSelectedItem().toString()+"'"
                    + " and term='"+marks_entry_combo_term.getSelectedItem().toString()+"'"
                    + " and marks.stream='"+marks_entry_combo_stream.getSelectedItem().toString()+"'"
                    + " and student.year='"+marks_entry_combo_year.getSelectedItem().toString()+"'";
                                  
        try {           
        pst=conn.prepareStatement(populate);
        rs=pst.executeQuery();
        table_marks.setModel(DbUtils.resultSetToTableModel(rs));
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
   
     public void  populateAdmission(){
         combo_admno.removeAllItems();
        String admission="select student_adm from student where stream='"+marks_entry_combo_stream.getSelectedItem()+
                "' and year='"+marks_entry_combo_year.getSelectedItem()+"'";
           try {
        pst=conn.prepareStatement(admission);
         rs=pst.executeQuery();
            while(rs.next()){
                combo_admno .addItem(rs.getString("student_adm"));
            }
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        
    }

    public String getGrade() {
        return grade;
    }

    public String getRemarks() {
        return remarks;
    }

    private void setGradeAndRemarks(int parseInt) {
        if(parseInt>79)
        {
            grade="A";
            remarks="Excelent";
        }
        else
            if(parseInt>74&&parseInt<=79){
                grade="A-";
            remarks="very Good";
            }
         else
            if(parseInt>69&&parseInt<=74){
                grade="B+";
            remarks="Good";
            }
         else
            if(parseInt>60&&parseInt<=65){
                grade="B-";
            remarks="Welldone";
            }
         else
            if(parseInt>54&&parseInt<=59){
                grade="C+";
            remarks="fair";
            }
         else
            if(parseInt>44&&parseInt<=54){
                grade="C";
            remarks="Good job";
            }
         else
            if(parseInt>39&&parseInt<=44){
                grade="C-";
            remarks="work hard";
            }
        else
            if(parseInt>34&&parseInt<=39){
                grade="D+";
            remarks="Good trial";
            }
        else
            if(parseInt>29&&parseInt<=34){
                grade="D";
            remarks="can do better";
            }
        else
            if(parseInt>24&&parseInt<=29){
                grade="D-";
            remarks="pull up your socks";
            }
        else
            if(parseInt>=0&&parseInt<=24){
                grade="E";
            remarks="poor";
            }
      
    }

    private void insertRemarkAndGrade(String adm, String stream, String subject, String term, String year) {
    try {
        String retrieveMarks="SELECT cat1,cat2,marks from marks inner join"
                 + " student on marks.admno=student.student_adm  where "
                + " admno='"+adm+"'"
                + "and subject='"+subject+"'"
                + "and term='"+term+"'"
                + "and marks.stream='"+stream+"'";
               
        pst=conn.prepareStatement(retrieveMarks);
        rs=pst.executeQuery();
        if(rs.next()){
           int cat1=(rs.getInt("cat1")) ;
           int cat2=(rs.getInt("cat2")) ;
           int marks=(rs.getInt("marks")) ;
           int averagemarks=(cat1+cat2)/2+marks;           
           setGradeAndRemarks(averagemarks);
            String insert="update marks set grade=?,remarks=? where"
                    + " admno='"+adm+"'"
                    + "and subject='"+subject+"'"
                    + "and term='"+term+"'"
                    + "and stream='"+stream+"'";
                                  
                 
      pst=conn.prepareStatement(insert);
      pst.setString(1,getGrade());
      pst.setString(2,getRemarks());
      pst.executeUpdate();              

        }
        
        //To change body of generated methods, choose Tools | Templates.
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private boolean admnoEntered() {
    try {
        String select="select admno from marks where subject="
                + "'"+marks_entry_combo_subject.getSelectedItem().toString()+"' "
                + "and admno='"+combo_admno.getSelectedItem().toString()+"'and term='"+marks_entry_combo_term.getSelectedItem().toString()+"' ";
        pst=conn.prepareStatement(select);
        rs=pst.executeQuery();
        if (rs.next()) {
            return true;            
        }
        else return false;
    } catch (SQLException ex) {
        Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
        return  false;
    }
   }
    
}

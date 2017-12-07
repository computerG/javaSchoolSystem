/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author codeGeek
 */
public class Ranking extends javax.swing.JDialog {

    Connection conn = null;
    PreparedStatement pst = null;
    private ResultSet rs;
    private String roll_id;
    private String department_id;
    private String oldid;
    private DatabaseConnect dbcon;
    private String selectedSubject;
    private String admno;
    private String totalmarks;
    private String stre;
    private String year;
    private String subject;
    private String grade;
    private String subj;
    private String subjectid;
    private final StringBuffer key=new StringBuffer();

    /**
     * Creates new form Ranking
     */
    public Ranking(JFrame frame, boolean b) 
    {
        super(frame, b);
        
        initComponents();
        try {
            dbcon = new DatabaseConnect();
            conn = dbcon.dbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_suject.removeAllItems();//clear the combo before poulating again
        populateComboSubject();//populate the combo with itemsfrom database
        populateComboStream();
        delete();//clear the total marks table in database
        totalStudentPerSubject();//calculate the total for each student per subject and store it in the totalmarks table
        disable();
        getTotalPerSubject();
     
      lbl_bio.setText("Bio -Biology");
      lbl_cre.setText("chr -Christian religious Education");
      lbl_kisw.setText("Kis -Kiswahili");
      lbl_en.setText("Eng -English");
      lbl_chem.setText("Che -Chemistry");
      lbl_geo.setText("Geo -Geography");
      lbl_hist.setText("His -History");
      lbl_phy.setText("Phy -Physics");
      lbl_mat.setText("Mat -Mathematics");
      lbl_bus.setText(" Bus -Business");
    }

   

    public void delete(){
        try {
            String delete="delete from totalmarks where markid>0";           
            pst=conn.prepareStatement(delete);
            pst.executeUpdate();
        String reset=    "ALTER TABLE totalmarks AUTO_INCREMENT = 1";
         pst=conn.prepareStatement(reset);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
           
}

    public void populateComboSubject() {
        String stream = "select subject_name from subject";
        try {
            pst = conn.prepareStatement(stream);
            rs = pst.executeQuery();
            while (rs.next()) {
                combo_suject.addItem(rs.getString("subject_name"));

            }
        } catch (Exception ex) {
            Logger.getLogger(MarksEntrydialog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
 public void totalStudentPerSubject() {
        try {
            String bestperSubject = "select firstname,lastname,subject,admno,FORMAT(((cat1+cat2)/2)+marks,0) as TotalMarks,marks.stream,grade,year "
                    + "from marks inner join student on student.student_adm=marks.admno order by TotalMarks desc ";
            pst = conn.prepareStatement(bestperSubject);          
            rs = pst.executeQuery(); int i=0;           
           while(rs.next()){
               admno=rs.getString("admno");
               totalmarks=rs.getString("TotalMarks");
               stre=rs.getString("marks.stream");
               year=rs.getString("year");
               subject=rs.getString("subject");                
               grade=rs.getString("grade");               
               String totalMark="insert into totalmarks (admno,subject,stream,year,total,grade)values(?,?,?,?,?,?)";
           pst=conn.prepareStatement(totalMark);
           pst.setString(1, admno);         
           pst.setString(2,subject.substring(0, 3));           
           pst.setString(3,stre);
           pst.setString(4, year);
           pst.setString(5, totalmarks);
           pst.setString(6, grade);          
           pst.executeUpdate(); 
            System.out.println(i++);
           }
                    

        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void totalPerStudent() {
        try {
            String totalmarks="select admno firstname,lastname,surname ,sum(total) as total from totalmarks inner join "
                    + "student on totalmarks.admno=student.student_adm  group by admno";          
            pst = conn.prepareStatement(totalmarks);
           // pst.setString(1, "1003/15");
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void TotalMarksPerStream() {
try {
            String totalmarks="select stream,year, sum(total) as TotalMarks from totalmarks where year=?  "
                    + "group by stream order by sum(total) desc";          
            pst = conn.prepareStatement(totalmarks);
            pst.setString(1, combo_class.getSelectedItem().toString());           
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void TotalMarksPerStreamPerForm() {
               try {     
                   String totalmarks="select stream,year, sum(total) as TotalMarks "
                           + "from totalmarks where year=? and stream=? "
                    + "group by stream order by sum(total) desc";          
            pst = conn.prepareStatement(totalmarks);
            pst.setString(1, combo_class.getSelectedItem().toString());
            pst.setString(2, combo_stream.getSelectedItem().toString());
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
                }

    private void TotalMarksPerFormPerStream() {
        try {
            String totalmarks="select surname,firstname,admno,sum(total) "
                    + "as Total "
                    + "from totalmarks inner join student on student.student_adm=totalmarks.admno "
                    + "where totalmarks.year=? and "
                    + "totalmarks.stream=? group by admno order by sum(total) desc ";          
            pst = conn.prepareStatement(totalmarks);
            pst.setString(1, combo_class.getSelectedItem().toString());
            pst.setString(2, combo_stream.getSelectedItem().toString());
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
                   //To change body of generated methods, choose Tools | Templates.
               }
     public void TotalMarksPerForm() {
         try {
            String totalmarks="select surname,firstname,admno,totalmarks.stream,sum(total) "
                    + "as Total "
                    + "from totalmarks inner join student on "
                    + "student.student_adm=totalmarks.admno where totalmarks.year=? "
                    + "group by admno order by Total desc ";          
            pst = conn.prepareStatement(totalmarks);
           pst.setString(1, combo_class.getSelectedItem().toString());
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private void getTotalPerSubject() {
      String subSubjest=combo_suject.getSelectedItem().toString().substring(0, 3);     
           try {
            String bestperSubject = "select firstname,lastname,admno,totalmarks.stream,subject,"
                    + "totalmarks.year,total,grade "
                    + "from totalmarks inner join student on "
                    + "student.student_adm=totalmarks.admno "
                    + "where subject=? and totalmarks.stream=? "
                    + "and totalmarks.year=? order by total desc ";
            pst = conn.prepareStatement(bestperSubject);             
            pst.setString(1,subSubjest);
            pst.setString(2,combo_stream.getSelectedItem().toString());
            pst.setString(3,combo_class.getSelectedItem().toString());
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void rankSubjectByyear() {              
        try {
            String bestperSubject = "select subject,"
                    + "totalmarks.year,sum(total) as Total "
                    + "from totalmarks  where year=? "
                    + "group by subject order by Total desc ";
               pst = conn.prepareStatement(bestperSubject);                 
               pst.setString(1,combo_class.getSelectedItem().toString());
               rs = pst.executeQuery();
               table_ranks.setModel(DbUtils.resultSetToTableModel(rs));//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }//To change body of generated methods, choose Tools | Templates.
            }
      private void rankSubjectbyStream() {              
        try {
            String bestperSubject = "select subject,"
                    + "totalmarks.year,sum(total) as Total "
                    + "from totalmarks  where stream=?"
                    + " group by subject order by Total desc ";
            pst = conn.prepareStatement(bestperSubject);  
            pst.setString(1,combo_stream.getSelectedItem().toString());            
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates.
            }
     private void rankSubject() {     
        try {
            String bestperSubject = "select subject,"
                    + "totalmarks.year,sum(total) as Total  "
                    + "from totalmarks  "
                    + "group by subject order by Total desc ";
            pst = conn.prepareStatement(bestperSubject);             
            rs = pst.executeQuery();
            table_ranks.setModel(DbUtils.resultSetToTableModel(rs));//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void populateComboStream(){
    String stream="select stream from stream";
    try{ pst=conn.prepareStatement(stream);
            rs=pst.executeQuery();
            while(rs.next()){
                 combo_stream.addItem(rs.getString("stream"));
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

        chkgroupselection = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        chk_stud_ranking = new javax.swing.JCheckBox();
        chk_subject = new javax.swing.JCheckBox();
        chk_stream = new javax.swing.JCheckBox();
        chk_form = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_stream = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        combo_suject = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        combo_class = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ranks = new javax.swing.JTable();
        lbl_eng = new javax.swing.JPanel();
        btn_print = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        labl_bio = new javax.swing.JLabel();
        lbl_bio = new javax.swing.JLabel();
        lbl_cre = new javax.swing.JLabel();
        lbl_phy = new javax.swing.JLabel();
        lbl_hist = new javax.swing.JLabel();
        lbl_kisw = new javax.swing.JLabel();
        lbl_chem = new javax.swing.JLabel();
        lbl_geo = new javax.swing.JLabel();
        lbl_mat = new javax.swing.JLabel();
        lbl_bus = new javax.swing.JLabel();
        lbl_en = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ranking Of result");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selection Criterion"));

        chkgroupselection.add(chk_stud_ranking);
        chk_stud_ranking.setText("Student rank");
        chk_stud_ranking.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_stud_rankingItemStateChanged(evt);
            }
        });

        chkgroupselection.add(chk_subject);
        chk_subject.setText("subject");
        chk_subject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_subjectItemStateChanged(evt);
            }
        });

        chkgroupselection.add(chk_stream);
        chk_stream.setText("stream");
        chk_stream.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_streamItemStateChanged(evt);
            }
        });

        chkgroupselection.add(chk_form);
        chk_form.setText("Form");
        chk_form.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_formItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk_form)
                .addGap(10, 10, 10)
                .addComponent(chk_subject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_stream)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chk_stud_ranking)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_form)
                    .addComponent(chk_subject)
                    .addComponent(chk_stream)
                    .addComponent(chk_stud_ranking))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter Your selection"));

        jLabel1.setText("Form");

        jLabel3.setText("stream");

        combo_suject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Subject");

        combo_class.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        table_ranks.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_ranks);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo_suject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(combo_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(combo_stream, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_suject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbl_eng.setBorder(javax.swing.BorderFactory.createTitledBorder("Your fiiltered Result"));

        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        jLabel4.setText("Key");

        lbl_bio.setText("jLabel5");

        lbl_cre.setText("jLabel6");

        lbl_phy.setText("jLabel7");

        lbl_hist.setText("jLabel8");

        lbl_kisw.setText("jLabel9");

        lbl_chem.setText("jLabel10");

        lbl_geo.setText("jLabel11");

        lbl_mat.setText("jLabel12");

        lbl_bus.setText("jLabel13");

        lbl_en.setText("jLabel5");

        javax.swing.GroupLayout lbl_engLayout = new javax.swing.GroupLayout(lbl_eng);
        lbl_eng.setLayout(lbl_engLayout);
        lbl_engLayout.setHorizontalGroup(
            lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_engLayout.createSequentialGroup()
                .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbl_engLayout.createSequentialGroup()
                        .addGap(546, 546, 546)
                        .addComponent(btn_print))
                    .addGroup(lbl_engLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lbl_engLayout.createSequentialGroup()
                .addComponent(labl_bio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_geo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(lbl_bio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_cre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_phy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbl_engLayout.createSequentialGroup()
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_kisw, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_hist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_chem, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_mat, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_en, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(220, 220, 220))
        );
        lbl_engLayout.setVerticalGroup(
            lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_engLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_print)
                .addGap(200, 200, 200)
                .addComponent(jLabel4)
                .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbl_engLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labl_bio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_bio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_hist)
                                .addComponent(lbl_mat)))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_phy)
                            .addComponent(lbl_chem)))
                    .addGroup(lbl_engLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_kisw)
                            .addComponent(lbl_bus)
                            .addComponent(lbl_cre))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_engLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_geo)
                            .addComponent(lbl_en))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_eng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_eng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chk_formItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_formItemStateChanged
       if(evt.getStateChange()==ItemEvent.SELECTED){   
           combo_stream.setEnabled(true);
           combo_class.setEnabled(true);
           TotalMarksPerForm();
        combo_class.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               TotalMarksPerForm();
                //To change body of generated methods, choose Tools | Templates.
            }
        });
         combo_stream.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               TotalMarksPerFormPerStream();
                //To change body of generated methods, choose Tools | Templates.
            }

               
        });
       }
       else{
           combo_class.setEnabled(false);
           disable();
            
       }
    }//GEN-LAST:event_chk_formItemStateChanged

    private void chk_subjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_subjectItemStateChanged
     if(evt.getStateChange()==ItemEvent.SELECTED){
        combo_stream.setEnabled(true);
        combo_class.setEnabled(true);        
        rankSubject();        
         combo_stream.addActionListener(new ActionListener() {
                 @Override
                public void actionPerformed(ActionEvent e) {
             rankSubjectbyStream();
                //To change body of generated methods, choose Tools | Templates.
            }          
        });
          combo_class.addActionListener(new ActionListener() {
                 @Override
                public void actionPerformed(ActionEvent e) {
               rankSubjectByyear();
                //To change body of generated methods, choose Tools | Templates.
            }           
        });
       }
       else{
           combo_suject.setEnabled(false);
           disable();
            
       }
    }//GEN-LAST:event_chk_subjectItemStateChanged

    private void chk_streamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_streamItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED){           
            combo_class.setEnabled(true); 
            combo_stream.setEnabled(true);
            TotalMarksPerStream();
            combo_class.addActionListener(new ActionListener() {
                 @Override
                public void actionPerformed(ActionEvent e) {
               TotalMarksPerStream();
                //To change body of generated methods, choose Tools | Templates.
            }
        });
            combo_stream.addActionListener(new ActionListener() {
                 @Override
                public void actionPerformed(ActionEvent e) {
               TotalMarksPerStreamPerForm();
                //To change body of generated methods, choose Tools | Templates.
            }

               
        });
        }
        else{  
            disable();
            combo_class.setEnabled(false);
            
        }
         
    }//GEN-LAST:event_chk_streamItemStateChanged

    private void chk_stud_rankingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_stud_rankingItemStateChanged
       if(evt.getStateChange()==ItemEvent.SELECTED){           
            combo_class.setEnabled(true); 
            combo_suject.setEnabled(true);
            combo_stream.setEnabled(true);
            TotalMarksPerForm();
            combo_suject.addActionListener(new ActionListener() {
                 @Override
                public void actionPerformed(ActionEvent e) {
                   getTotalPerSubject();//To change body of generated methods, choose Tools | Templates.
                }
        });
             combo_class.addActionListener(new ActionListener() { 
                 @Override
                public void actionPerformed(ActionEvent e) {
                    TotalMarksPerForm();
                     //To change body of generated methods, choose Tools | Templates.
                }                 
        });
            combo_stream.addActionListener(new ActionListener() {          

                @Override
                public void actionPerformed(ActionEvent e) {
                    TotalMarksPerFormPerStream();
                     //To change body of generated methods, choose Tools | Templates.
                }              
        });
        }
        else{  
            disable();
            combo_class.setEnabled(false);
            
                //To change body of generated methods, choose Tools | Templates.
            
        }
    }//GEN-LAST:event_chk_stud_rankingItemStateChanged

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        try {
            //  RefreshMyTable();
            table_ranks.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_printActionPerformed

    /**
     * @param args the command line arguments
     */
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_print;
    private javax.swing.JCheckBox chk_form;
    private javax.swing.JCheckBox chk_stream;
    private javax.swing.JCheckBox chk_stud_ranking;
    private javax.swing.JCheckBox chk_subject;
    private javax.swing.ButtonGroup chkgroupselection;
    private javax.swing.JComboBox combo_class;
    private javax.swing.JComboBox combo_stream;
    private javax.swing.JComboBox combo_suject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labl_bio;
    private javax.swing.JLabel lbl_bio;
    private javax.swing.JLabel lbl_bus;
    private javax.swing.JLabel lbl_chem;
    private javax.swing.JLabel lbl_cre;
    private javax.swing.JLabel lbl_en;
    private javax.swing.JPanel lbl_eng;
    private javax.swing.JLabel lbl_geo;
    private javax.swing.JLabel lbl_hist;
    private javax.swing.JLabel lbl_kisw;
    private javax.swing.JLabel lbl_mat;
    private javax.swing.JLabel lbl_phy;
    private javax.swing.JTable table_ranks;
    // End of variables declaration//GEN-END:variables
public void disable(){
    combo_class.setEnabled(false);
    combo_stream.setEnabled(false);
    combo_suject.setEnabled(false);
}
public String selectSubject(){
    StringBuffer subje = new StringBuffer();
        try {
            String select="Select subject from totalmarks";
            pst=conn.prepareStatement(select);
            rs = pst.executeQuery();
            int i=0;
           
            while(rs.next()){                
              subje.append(rs.getString("subject"));
              i++;
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subje.toString();
}
  
}
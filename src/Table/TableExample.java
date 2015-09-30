/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author codeGeek
 */
public class TableExample extends AbstractTableModel implements  TableModel{
    ResultSetMetaData metadata;
    private int columnCount;
    ResultSet rs;
    private int numberOfRows;
    public TableExample(ResultSet resultSet) {
        this.rs=resultSet;
       
        try {
            metadata=resultSet.getMetaData();
            resultSet.last();
             numberOfRows=resultSet.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(TableExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {     
        try {
          columnCount = metadata.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(TableExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex+1);
            return rs.getObject(columnIndex+1);
        } catch (SQLException ex) {
            Logger.getLogger(TableExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  "";
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        String columnname = null;
        try{
           columnname=metadata.getColumnClassName(columnIndex+1);  
           return Class.forName(columnname).getClass(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(TableExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        try {
           return metadata.getColumnLabel(column+1);
            //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(TableExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
}

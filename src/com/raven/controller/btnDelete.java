/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.controller;

import com.raven.swing.Table;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thinh nguyen
 */
public class btnDelete extends JTable{
    public void btnDelete(Table tbl){
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        if(tbl.getSelectedRowCount()==1){
            tblModel.removeRow(tbl.getSelectedRow());
        }
        else{
            if (tbl.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"Danh sách trống");
            }
            else{
                JOptionPane.showMessageDialog(this,"Hãy chọn dòng muốn xoá");
            }
        }
    }
           
}

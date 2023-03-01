/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import com.raven.conection.ConnectDatabase;
import com.raven.model.Assignment;
import com.raven.model.ClassName;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thinh nguyen
 */
public class AssignmentDAO implements DAOInterface<Assignment> {

    @Override
    public List<Assignment> getAll() {
    List<Assignment> list = new ArrayList<>();
        ConnectDatabase myConnection = new ConnectDatabase();
        String sql = "{CALL SelectAllAssignment()}";
        Connection conn = myConnection.openConnection();
        try {
            if (conn != null) {
                try {
                    CallableStatement p = conn.prepareCall(sql);
                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        list.add(new Assignment(rs.getString("TenDeTai"), rs.getString("GVHuongDan"), rs.getFloat("DiemHuongDan"),rs.getString("GVPhanbien"), rs.getFloat("DiemPhanBien")));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Connection fail!");
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return list;
    }

    @Override
    public Optional<Assignment> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Assignment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Assignment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Assignment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

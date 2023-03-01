/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import com.raven.conection.ConnectDatabase;
import com.raven.model.Session;
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
public class SessionDAO implements DAOInterface<Session> {

    @Override
    public List<Session> getAll() {
        List<Session> list = new ArrayList<>();
        ConnectDatabase myConnection = new ConnectDatabase();
        String sql = "{CALL SelectAllKhoaDaoTao()}";
        Connection conn = myConnection.openConnection();
        try {
            if (conn != null) {
                try {
                    CallableStatement p = conn.prepareCall(sql);
                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        list.add(new Session(rs.getString("IDKhoaDaoTao"), rs.getString("NienKhoa"), rs.getString("GhiChu")));
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
    public Optional<Session> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Session t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Session t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Session t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

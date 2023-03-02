/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import com.raven.conection.ConnectDatabase;
import com.raven.model.Account;
import com.raven.model.Subject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author thinh nguyen
 */
public class SubjectDAO implements DAOInterface<Subject> {

    @Override
    public List<Subject> getAll() {
        List<Subject> list = new ArrayList<>();
        ConnectDatabase myConnection = new ConnectDatabase();
        String sql = "{CALL SelectAllSubject()}";
        Connection conn = myConnection.openConnection();
        try {
            if (conn != null) {
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        list.add(new Subject(rs.getString("TenDeTai"),rs.getString("HoTen"),rs.getString("GVHuongDan"),rs.getFloat(4),rs.getString(5),rs.getFloat(6),rs.getString(7),rs.getFloat(8),rs.getString(9)));
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
                System.out.println("close failed");
            }
        }

        return list;
    }

    @Override
    public Optional<Subject> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Subject t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Subject t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Subject t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

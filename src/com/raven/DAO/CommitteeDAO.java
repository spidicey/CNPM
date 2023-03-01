/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import com.raven.conection.ConnectDatabase;
import com.raven.model.Account;
import com.raven.model.Committee;
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
public class CommitteeDAO implements DAOInterface<Committee>{

    @Override
    public List<Committee> getAll() {
        List<Committee> list = new ArrayList<>();
        ConnectDatabase myConnection = new ConnectDatabase();
        String sql = "{CALL SelectAllCommittee()}";
        Connection conn = myConnection.openConnection();
        try {
            if (conn != null) {
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        list.add(new Committee(rs.getString("IDHoiDong"), rs.getString("ChuTichHoiDong"), rs.getInt("SoThanhVien"),rs.getString("NhanXet")));
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
    public Optional<Committee> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Committee t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Committee t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Committee t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

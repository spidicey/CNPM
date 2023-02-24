/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.DAO;

import com.raven.conection.ConnectDatabase;
import com.raven.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thinh nguyen
 */
public class StudentDAO implements DAOInterface<Student> {

    @Override
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        ConnectDatabase myConnection = new ConnectDatabase();
        String sql = "{CALL SelectAllStudent()}";
        Connection conn = myConnection.openConnection();
        try {
            if (conn != null) {
                try {
                    CallableStatement p = conn.prepareCall(sql);
                    ResultSet rs = p.executeQuery();
                    while (rs.next()) {
                        list.add(new Student(rs.getString("IDSinhVien"), rs.getString("HoTen"), rs.getString("TenLop"), rs.getDate("NamSinh"), rs.getString("GioiTinh"), rs.getString("QueQuan"), rs.getString("Email"), rs.getFloat("GPA")));
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
    public Optional<Student> get(int id) {
        return null;
    }

    @Override
    public boolean save(Student t) {
        return false;
    }

    @Override
    public boolean update(Student t) {
        return false;

    }

    @Override
    public boolean delete(Student t) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
//        String sql = "{CALL SelectAllStudent()}";
//        ConnectDatabase myConnection = new ConnectDatabase();
//        Connection conn = myConnection.openConnection();
//        CallableStatement p = conn.prepareCall(sql);
////        p.setString(1, "admin");
////        p.setString(2, "1234");
//        ResultSet r = p.executeQuery();
//        while (r.next()) {
//            System.out.println(r.getString("HoTen"));
//        }

    }
}

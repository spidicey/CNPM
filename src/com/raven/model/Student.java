/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.util.Date;

/**
 *
 * @author thinh nguyen
 */
public class Student {

    @Override
    public String toString() {
        return "Student{" + "idSinhVien=" + idSinhVien + ", hoTen=" + hoTen + ", lop=" + lop + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh + ", queQuan=" + queQuan + ", email=" + email + ", gpa=" + gpa + '}';
    }
    
    private String idSinhVien;
    private String hoTen;
    private String lop;
    private Date namSinh;
    private String gioiTinh;
    private String queQuan;
    private String email;
    private float gpa;

    public String getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(String idSinhVien) {
        this.idSinhVien = idSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Student(String idSinhVien, String hoTen, String lop, Date namSinh, String gioiTinh, String queQuan, String email, float gpa) {
        this.idSinhVien = idSinhVien;
        this.hoTen = hoTen;
        this.lop = lop;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.email = email;
        this.gpa = gpa;
    }
    
    public Student() {
    }
//    
}

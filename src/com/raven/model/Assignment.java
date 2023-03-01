/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class Assignment {
    private String tenDeTai;
    private String GVHuongDan;
    private float diemHuongDan;
    private String GVPhanBien;
    private float diemPhanBien;

    public String getTenDeTai() {
        return tenDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public String getGVHuongDan() {
        return GVHuongDan;
    }

    public void setGVHuongDan(String GVHuongDan) {
        this.GVHuongDan = GVHuongDan;
    }

    public float getDiemHuongDan() {
        return diemHuongDan;
    }

    public void setDiemHuongDan(float diemHuongDan) {
        this.diemHuongDan = diemHuongDan;
    }

    public String getGVPhanBien() {
        return GVPhanBien;
    }

    public void setGVPhanBien(String GVPhanBien) {
        this.GVPhanBien = GVPhanBien;
    }

    public float getDiemPhanBien() {
        return diemPhanBien;
    }

    public void setDiemPhanBien(float diemPhanBien) {
        this.diemPhanBien = diemPhanBien;
    }

    public Assignment(String tenDeTai, String GVHuongDan, float diemHuongDan, String GVPhanBien, float diemPhanBien) {
        this.tenDeTai = tenDeTai;
        this.GVHuongDan = GVHuongDan;
        this.diemHuongDan = diemHuongDan;
        this.GVPhanBien = GVPhanBien;
        this.diemPhanBien = diemPhanBien;
    }

    public Assignment() {
    }
}

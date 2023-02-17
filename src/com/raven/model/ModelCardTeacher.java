/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import javax.swing.Icon;

/**
 *
 * @author thinh nguyen
 */
public class ModelCardTeacher extends Model_Card{
    private String degree;
    private String position;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ModelCardTeacher(Icon icon, String id, String name, String gender, String classDepartment, String email,String degree, String position) {
        super(icon, id, name, gender, classDepartment, email);
        this.degree = degree;
        this.position = position;
    }
    
    
}

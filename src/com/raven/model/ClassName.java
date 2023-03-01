/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class ClassName {
    private String idClass;
    private String className;
    private int attendants;
    private String department;
    private String typeTrainning;
    private String session;

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAttendants() {
        return attendants;
    }

    public void setAttendants(int attendants) {
        this.attendants = attendants;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTypeTrainning() {
        return typeTrainning;
    }

    public void setTypeTrainning(String typeTrainning) {
        this.typeTrainning = typeTrainning;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public ClassName() {
    }

    public ClassName(String idClass, String className, int attendants, String department, String typeTrainning, String session) {
        this.idClass = idClass;
        this.className = className;
        this.attendants = attendants;
        this.department = department;
        this.typeTrainning = typeTrainning;
        this.session = session;
    }
}

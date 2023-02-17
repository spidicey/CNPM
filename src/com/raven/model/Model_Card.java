package com.raven.model;

import javax.swing.Icon;

public class Model_Card {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return "Ho ten    : "+name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return "Gioi tinh : "+gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return "Email: "+email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDepartment() {
        return "Khoa       : "+Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public Model_Card() {
    }

    private Icon icon;
    private String id;
    private String name;
    private String gender;
    private String Department;
    private String email;

    public Model_Card(Icon icon, String id, String name, String gender, String classDepartment, String email) {
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.Department = classDepartment;
        this.email = email;
    }

    


    

    
}

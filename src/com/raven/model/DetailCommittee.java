/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class DetailCommittee {
    private String idComittee;
    private String idMember;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public DetailCommittee(String idComittee, String idMember, String member) {
        this.idComittee = idComittee;
        this.idMember = idMember;
        this.member = member;
    }
    private String member;

    public String getIdComittee() {
        return idComittee;
    }

    public void setIdComittee(String idComittee) {
        this.idComittee = idComittee;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public DetailCommittee() {
    }

    public DetailCommittee(String idComittee, String committeeName) {
        this.idComittee = idComittee;
        this.member = committeeName;
    }
}

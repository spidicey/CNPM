/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class Committee {
    String idCommittee;

    public Committee(String idCommittee, String president, int members, String comment) {
        this.idCommittee = idCommittee;
        this.president = president;
        this.members = members;
        this.comment = comment;
    }

    public String getIdCommittee() {
        return idCommittee;
    }

    public void setIdCommittee(String idCommittee) {
        this.idCommittee = idCommittee;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    private String president;
    private int members;
    private String comment;
}

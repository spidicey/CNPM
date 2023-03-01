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
    private String committeeName;

    public String getIdComittee() {
        return idComittee;
    }

    public void setIdComittee(String idComittee) {
        this.idComittee = idComittee;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public DetailCommittee() {
    }

    public DetailCommittee(String idComittee, String committeeName) {
        this.idComittee = idComittee;
        this.committeeName = committeeName;
    }
}

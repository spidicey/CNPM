/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author thinh nguyen
 */
public class Subject {

    private String nameSubject;
    private String student;

    public Subject(String nameSubject, String student, String instructor, float instructorMark, String thesis_dissertation, float thesisMark, String committee, float committeeMark,String comment) {
        this.nameSubject = nameSubject;
        this.student = student;
        this.instructor = instructor;
        this.instructorMark = instructorMark;
        this.thesis_dissertation = thesis_dissertation;
        this.thesisMark = thesisMark;
        this.committee = committee;
        this.committeeMark = committeeMark;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

  

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public float getInstructorMark() {
        return instructorMark;
    }

    public void setInstructorMark(float instructorMark) {
        this.instructorMark = instructorMark;
    }

    public String getThesis_dissertation() {
        return thesis_dissertation;
    }

    public void setThesis_dissertation(String thesis_dissertation) {
        this.thesis_dissertation = thesis_dissertation;
    }

    public float getThesisMark() {
        return thesisMark;
    }

    public void setThesisMark(float thesisMark) {
        this.thesisMark = thesisMark;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public float getCommitteeMark() {
        return committeeMark;
    }

    public void setCommitteeMark(float committeeMark) {
        this.committeeMark = committeeMark;
    }
    private String instructor;
    private float instructorMark;
    private String thesis_dissertation;
    private float thesisMark;
    private String committee;
    private float committeeMark;
    private String comment;
    public Subject() {
    }

}

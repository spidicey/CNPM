package com.raven.swing;

import com.raven.model.Student;

public class ModelAction {

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(Student student, EventAction event) {
        this.student = student;
        this.event = event;
    }

    public ModelAction() {
    }

    private Student student;
    private EventAction event;
}

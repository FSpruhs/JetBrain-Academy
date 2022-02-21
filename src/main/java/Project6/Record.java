package main.java.Project6;


import java.time.LocalDateTime;

public abstract class Record {
    protected String name;
    protected String phoneNumber;
    protected boolean isPerson;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeLastEdit;


    public String toEditFields() {
        return "";
    }

    public String getSearchName() {
        return "";
    }

    //getter and setter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void info() {
    }
}


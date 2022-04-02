package main.java.PhoneBook;

import java.time.LocalDateTime;

public class Organization extends Record{
    private String address;

    private Organization(String address, String name, String phoneNumber, boolean isPerson,
                         LocalDateTime timeCreated, LocalDateTime timeLastEdit) {
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isPerson = isPerson;
        this.timeCreated = timeCreated;
        this.timeLastEdit = timeLastEdit;
    }

    @Override
    public void info() {
        String info = "Organization name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Number: " + phoneNumber + "\n" +
                "Time created: " + timeCreated + "\n" +
                "Time last edit: " + timeLastEdit + "\n";
        System.out.println(info);
    }

    @Override
    public String toEditFields() {
        return "(name, address, number)";
    }

    @Override
    public String getSearchName() {
        return name + phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    static class Builder {
        private String address;
        private String name;
        private String phoneNumber;
        private boolean isPerson;
        private LocalDateTime timeCreated;
        private LocalDateTime timeLastEdit;

        Builder() {}

        Builder setName(String name) {
            this.name = name;
            return this;
        }
        Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        Builder setIsPerson(boolean isPerson) {
            this.isPerson = isPerson;
            return this;
        }

        Builder setTimeCreated(LocalDateTime timeCreated) {
            this.timeCreated = timeCreated;
            return this;
        }

        Builder setTimeLastEdit(LocalDateTime timeLastEdit) {
            this.timeLastEdit = timeLastEdit;
            return this;
        }

        Organization build () {
            return new Organization(address, name, phoneNumber, isPerson, timeCreated, timeLastEdit);
        }

    }
}


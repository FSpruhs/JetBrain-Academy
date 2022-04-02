package main.java.PhoneBook;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Record{
    private String surname;
    private Gender gender;
    private LocalDate birthDate;

    Person(String surname, Gender gender, LocalDate birthDate, String name, String phoneNumber,
           boolean isPerson, LocalDateTime timeCreated, LocalDateTime timeLastEdit) {
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isPerson = isPerson;
        this.timeCreated = timeCreated;
        this.timeLastEdit = timeLastEdit;
    }

    @Override
    public void info() {
        StringBuilder info = new StringBuilder();
        info.append("Name: ").append(name).append("\n");
        info.append("Surname: ").append(surname).append("\n");
        if (gender == Gender.MALE) {
            info.append("Gender: Male\n" );
        } else if (gender == Gender.FEMALE) {
            info.append("Gender: Female\n" );
        } else {
            info.append("Gender: [no data]\n");
        }
        if (birthDate == null) {
            info.append("Birth date: [no data]\n");
        } else {
            info.append("Birth date: ").append(birthDate).append("\n");
        }
        info.append("Number: ").append(phoneNumber).append("\n");
        info.append("Time created: ").append(timeCreated).append("\n");
        info.append("Time last edit: ").append(timeLastEdit).append("\n");
        System.out.println(info);
    }

    @Override
    public String toEditFields() {
        return "(name, surname, birth, gender, number)";
    }

    @Override
    public String getSearchName() {
        return name + " " +  surname + phoneNumber;
    }

    @Override
    public String toString() {
        return name + surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    static class Builder {
        private String surname;
        private Gender gender;
        private LocalDate birthDate;
        private String name;
        private String phoneNumber;
        private boolean isPerson;
        private LocalDateTime timeCreated;
        private LocalDateTime timeLastEdit;

        Builder() {}

        Person.Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Person.Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        Person.Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        Person.Builder setName(String name) {
            this.name = name;
            return this;
        }

        Person.Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        Person.Builder setIsPerson(boolean isPerson) {
            this.isPerson = isPerson;
            return this;
        }

        Person.Builder setTimeCreated(LocalDateTime timeCreated) {
            this.timeCreated = timeCreated;
            return this;
        }

        Person.Builder setTimeLastEdit(LocalDateTime timeLastEdit) {
            this.timeLastEdit = timeLastEdit;
            return this;
        }

        Person build () {
            return new Person(surname, gender, birthDate, name, phoneNumber, isPerson, timeCreated, timeLastEdit);
        }
    }
}


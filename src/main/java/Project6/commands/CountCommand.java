package main.java.Project6.commands;

import main.java.Project6.PhoneBook;

public class CountCommand implements Command{

    private final PhoneBook phoneBook;

    public CountCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void execute() {
        phoneBook.count();
    }
}

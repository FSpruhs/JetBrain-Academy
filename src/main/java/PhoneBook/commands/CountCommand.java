package main.java.PhoneBook.commands;

import main.java.PhoneBook.PhoneBook;

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

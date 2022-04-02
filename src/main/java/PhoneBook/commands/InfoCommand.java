package main.java.PhoneBook.commands;

import main.java.PhoneBook.PhoneBook;
import main.java.PhoneBook.Record;

public class InfoCommand implements Command{

    private final PhoneBook phoneBook;
    private final Record record;

    public InfoCommand(PhoneBook phoneBook, Record record) {
        this.phoneBook = phoneBook;
        this.record = record;
    }

    @Override
    public void execute() {
        phoneBook.info(record);
    }
}


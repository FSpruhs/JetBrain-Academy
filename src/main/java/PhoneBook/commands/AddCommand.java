package main.java.PhoneBook.commands;

import main.java.PhoneBook.Record;
import main.java.PhoneBook.PhoneBook;

public class AddCommand implements Command{
    private final Record record;
    private final PhoneBook phoneBook;

    public AddCommand(Record record, PhoneBook phoneBook) {
        this.record = record;
        this.phoneBook = phoneBook;
    }
    @Override
    public void execute() {
        phoneBook.add(record);
    }
}
package main.java.Project6.commands;

import main.java.Project6.Record;
import main.java.Project6.PhoneBook;

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
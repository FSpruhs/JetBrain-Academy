package main.java.PhoneBook.commands;

import main.java.PhoneBook.PhoneBook;
import main.java.PhoneBook.Record;

public class RemoveCommand implements Command{

    private final PhoneBook phoneBook;
    private final Record record;

    public RemoveCommand(PhoneBook phoneBook, Record record) {
        this.phoneBook = phoneBook;
        this.record = record;
    }

    @Override
    public void execute() {
        phoneBook.remove(record);

    }
}

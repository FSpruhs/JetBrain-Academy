package main.java.Project6.commands;

import main.java.Project6.PhoneBook;
import main.java.Project6.Record;

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

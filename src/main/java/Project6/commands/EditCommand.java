package main.java.Project6.commands;


import main.java.Project6.Record;
import main.java.Project6.PhoneBook;

public class EditCommand implements Command{

    private final PhoneBook phoneBook;
    private final Record record;

    public EditCommand(PhoneBook phoneBook, Record record) {
        this.phoneBook = phoneBook;
        this.record = record;
    }

    @Override
    public void execute() {
        phoneBook.edit(record);
    }
}


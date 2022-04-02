package main.java.PhoneBook.commands;


import main.java.PhoneBook.Record;
import main.java.PhoneBook.PhoneBook;

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


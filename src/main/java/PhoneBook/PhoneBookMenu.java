package main.java.PhoneBook;


import main.java.PhoneBook.commands.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook;
    private Command command;

    public PhoneBookMenu() {
        phoneBook = new PhoneBook();
        startMenu();
    }

    private void startRecordMenu(Record record) {
        info(record);
        String userInput = "";
        while (!userInput.equals("menu")) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            userInput = scanner.nextLine();
            if (userInput.equals("edit")) {
                edit(record);
            } else if (userInput.equals("delete")) {
                remove(record);
            }
        }
    }

    private void startListMenu() {
        int index = 1;
        for (Record record : phoneBook.getPhoneBook()) {
            System.out.println(index + " " + record.getSearchName());
            index++;
        }
        System.out.print("\n[list] Enter action ([number], back):");
        String userInput = scanner.nextLine();
        if (!userInput.equals("back")) {
            startRecordMenu(phoneBook.getPhoneBook().get(Integer.parseInt(userInput) - 1));
        }
    }

    private void startSearchMenu() {
        String userInput = "again";
        ArrayList<Record> tempPhoneBook = new ArrayList<>();
        while (userInput.equals("again")) {
            System.out.print("Enter search query: ");
            userInput = ".*" + scanner.nextLine() + ".*";
            Pattern pattern = Pattern.compile(userInput, Pattern.CASE_INSENSITIVE);
            int index = 1;
            for (Record record : phoneBook.getPhoneBook()) {
                Matcher matcher = pattern.matcher(record.getSearchName());
                if (matcher.matches()) {
                    System.out.println(index + " " + record.getSearchName());
                    tempPhoneBook.add(record);
                    index++;
                }
            }
            System.out.print("[search] Enter action ([number], back, again): ");
            userInput = scanner.nextLine();
            if (!userInput.equals("back") && !userInput.equals("again")) {
                startRecordMenu(tempPhoneBook.get(Integer.parseInt(userInput) - 1));
            }
        }
    }

    private void startMenu() {
        String userInput = "";
        while (!userInput.equals("exit")) {
            System.out.print("[menu]Enter action (add, list, search, count, exit): ");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "add":
                    add();
                    break;
                case "list":
                    startListMenu();
                    break;
                case "search":
                    startSearchMenu();
                    break;
                case "count":
                    command = new CountCommand(phoneBook);
                    command.execute();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Wrong Input");
            }
        }
    }

    private void add() {
        System.out.print("Enter the type (person, organization): ");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "person":
                Person.Builder personBuilder = new Person.Builder();
                personBuilder.setIsPerson(true);
                personBuilder.setTimeCreated(LocalDateTime.now());
                personBuilder.setTimeLastEdit(LocalDateTime.now());
                System.out.print("Enter the name: ");
                personBuilder.setName(scanner.nextLine());
                System.out.print("Enter the surname: ");
                personBuilder.setSurname(scanner.nextLine());
                getInputBirthDate(personBuilder);
                getInputGender(personBuilder);
                getInputPhoneNumberPerson(personBuilder);
                Person person = personBuilder.build();
                command = new AddCommand(person, phoneBook);
                command.execute();
                break;
            case "organization":
                Organization.Builder organisationBuilder = new Organization.Builder();
                organisationBuilder.setIsPerson(false);
                organisationBuilder.setTimeCreated(LocalDateTime.now());
                organisationBuilder.setTimeLastEdit(LocalDateTime.now());
                System.out.print("Enter the organization name: ");
                organisationBuilder.setName(scanner.nextLine());
                System.out.print("Enter the address: ");
                organisationBuilder.setAddress(scanner.nextLine());
                getInputPhoneNumberOrganisation(organisationBuilder);
                Organization organization = organisationBuilder.build();
                command = new AddCommand(organization, phoneBook);
                command.execute();
                break;
            default:
                System.out.println("Wrong Input");
        }
    }

    private void remove(Record record) {
        command = new RemoveCommand(phoneBook, record);
        command.execute();

    }

    private void edit(Record record) {
        command = new EditCommand(phoneBook, record);
        command.execute();

    }

    private void info(Record record) {
        command = new InfoCommand(phoneBook, record);
        command.execute();

    }

    private void getInputBirthDate(Person.Builder builder) {
        System.out.print("Enter the birth date: ");
        try {
            builder.setBirthDate(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
    }

    private void getInputGender(Person.Builder builder) {
        System.out.print("Enter the gender (M, F): ");
        String userInput = scanner.nextLine();
        if (userInput.equals("M")) {
            builder.setGender(Gender.MALE);
        } else if (userInput.equals("F")) {
            builder.setGender(Gender.FEMALE);
        } else {
            System.out.println("Bad gender!");
        }
    }

    public void getInputPhoneNumberPerson(Person.Builder builder) {
        System.out.print("Enter the number: ");
        String userInput = scanner.nextLine();
        if (phoneBook.checkPhoneNumber(userInput)) {
            builder.setPhoneNumber(userInput);
        } else {
            System.out.println("Bad Number");
            builder.setPhoneNumber("[no number]");
        }
    }

    public void getInputPhoneNumberOrganisation(Organization.Builder builder) {
        System.out.print("Enter the number: ");
        String userInput = scanner.nextLine();
        if (phoneBook.checkPhoneNumber(userInput)) {
            builder.setPhoneNumber(userInput);
        } else {
            System.out.println("Bad Number");
            builder.setPhoneNumber("[no number]");
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}


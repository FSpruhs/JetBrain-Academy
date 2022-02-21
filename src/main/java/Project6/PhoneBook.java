package main.java.Project6;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Record> phoneBook = new ArrayList<>();

    public boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    public void add(Record record) {
        phoneBook.add(record);
        System.out.println("The record added.\n");
    }

    public void remove(Record record) {
        phoneBook.remove(record);
        System.out.print("The record removed!\n");
    }


    private void editSurname(Person toEdit) {
        System.out.print("Enter surname: ");
        toEdit.setSurname(scanner.nextLine());
    }

    private void editName(Record toEdit) {
        System.out.print("Enter name: ");
        toEdit.setName(scanner.nextLine());
    }

    private void editBirth(Person toEdit) {
        System.out.print("Enter the birth date: ");
        try {
            toEdit.setBirthDate(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
    }

    private void editGender(Person toEdit) {
        System.out.print("Enter the gender (M, F): ");
        String userInput = scanner.nextLine();
        if (userInput.equals("M")) {
            toEdit.setGender(Gender.MALE);
        } else if (userInput.equals("F")) {
            toEdit.setGender(Gender.FEMALE);
        } else {
            System.out.println("Bad gender!");
        }
    }

    private void editAddress(Organization toEdit) {
        System.out.print("Enter address: ");
        toEdit.setAddress(scanner.nextLine());
    }

    private void editNumber(Record toEdit) {
        System.out.print("Enter number: ");
        String phoneNumber = scanner.nextLine();
        if (!checkPhoneNumber(phoneNumber)) {
            phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
        toEdit.setPhoneNumber(phoneNumber);
    }


    public void edit(Record toEdit) {
        System.out.print("Select a field " + toEdit.toEditFields() + ": ");
        switch (scanner.nextLine()) {
            case "name":
                editName(toEdit);
                break;
            case "surname":
                editSurname((Person) toEdit);
                break;
            case "birth":
                editBirth((Person) toEdit);
                break;
            case  "gender":
                editGender((Person) toEdit);
                break;
            case "address":
                editAddress((Organization) toEdit);
                break;
            case "number":
                editNumber(toEdit);
                break;
            default:
                System.out.println("Wrong input");
        }
        toEdit.setTimeLastEdit(LocalDateTime.now());
        System.out.print("The record edited.\n\n");
    }


    public void count() {
        if (phoneBook.isEmpty()) {
            System.out.println("The Phone Book has 0 records.");
        } else {
            System.out.printf("The Phone Book has %d records.", phoneBook.size());
        }
    }


    public void info(Record record) {
        record.info();
    }

    public ArrayList<Record> getPhoneBook() {
        return phoneBook;
    }
}


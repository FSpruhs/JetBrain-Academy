package main.java.BankingSystem;





import main.java.BankingSystem.utils.LuhnAlgorithm;
import main.java.BankingSystem.utils.RandomUtils;

import java.util.Scanner;

public class BankMenu {


    private static final Scanner scanner = new Scanner(System.in);
    static BankDataBase db;


    private static void createCard() {
        String cardNumber = RandomUtils.createCreditCardNumber(15);
        cardNumber = cardNumber + LuhnAlgorithm.calculateCheckDigit(cardNumber);
        String pin = RandomUtils.createPin();
        System.out.printf("\n" +
                "Your card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n", cardNumber, pin);
        CreditCardAccount newAccount = new CreditCardAccount(cardNumber, pin, 0.0F);
        db.insertCreditCard(newAccount);
    }

    private static CreditCardAccount logIn(String cardNumber, String pin) {
        CreditCardAccount account = db.getAccount(cardNumber);
        if (account != null) {
            if (account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    private static void getLogInInformation() {
        System.out.println("Enter your card number:");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        CreditCardAccount account = logIn(cardNumber, pin);
        if (account == null) {
            System.out.println("Wrong card number or PIN!");
        } else {
            System.out.println("You have successfully logged in!");
            startCreditCardMenu(account);
        }
    }



    private static void startCreditCardMenu(CreditCardAccount account) {
        System.out.println("1. Balance\n" +
                "2. Add income\n" +
                "3. Do transfer\n" +
                "4. Close account\n" +
                "5. Log out\n" +
                "0. Exit");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                System.out.println(account.getBalance());
                break;
            case "2":
                System.out.println("Enter income:");
                int income = Integer.parseInt(scanner.nextLine());
                db.addIncome(account, income);
                System.out.println("Income was added!");
                break;
            case "3":
                System.out.println("Transfer\nEnter card number:");
                String cardNumber = scanner.nextLine();
                if (!LuhnAlgorithm.checkCreditCard(cardNumber)) {
                    System.out.println("Probably you made a mistake in the card number. Please try again!");
                    break;
                }
                CreditCardAccount receiver = db.getAccount(cardNumber);
                if (receiver == null) {
                    System.out.println("Such a card does not exist.");
                    break;
                }

                System.out.println("Enter how much money you want to transfer: ");
                int transferMoney = Integer.parseInt(scanner.nextLine());
                if (transferMoney > db.getBalance(account)) {
                    System.out.println("Not enough money!");
                    break;
                }
                db.doTransfer(account, receiver, transferMoney);
                break;
            case "4":
                db.delete(account);

                break;
            case "5":
                System.out.println("You have successfully logged out!");
                return;
            case "0":
                System.exit(0);
            default:
                System.out.println("Wrong input");
        }
        BankMenu.startCreditCardMenu(account);
    }

    public static void startMenu() {
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                createCard();
                break;
            case "2":
                getLogInInformation();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Wrong input");
        }
        BankMenu.startMenu();
    }
}


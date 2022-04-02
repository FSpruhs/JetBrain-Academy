package main.java.BudgetManager;


import java.util.Scanner;

public class BudgetMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final BudgetManager manager = new BudgetManager();

    private void chooseSortCategories() {
        String menu = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other";
        int menuInput = 1;
        System.out.println(menu);
        menuInput = scanner.nextInt();
        switch (menuInput) {
            case 1:
                manager.sortCategorie(Categories.FOOD);
                break;
            case 2:
                manager.sortCategorie(Categories.CLOTHES);
                break;
            case 3:
                manager.sortCategorie(Categories.ENTERTAINMENT);
                break;
            case 4:
                manager.sortCategorie(Categories.OTHER);
                break;
            case 5:
                break;
            default:
                System.out.println("Wrong Input. Try again");
                break;
        }
    }


    private void chooseTypeOfSort() {
        String menu = "\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type \n" +
                "3) Sort certain type \n" +
                "4) Back";
        int menuInput = 1;
        while (menuInput != 4) {
            System.out.println(menu);
            menuInput = scanner.nextInt();
            switch (menuInput) {
                case 1:
                    manager.sortAll();
                    //manager.showListOfPurchasesAllCategories();
                    if (manager.getPurchases().isEmpty()) {
                        System.out.println("\nThe purchase list is empty!");
                    } else {
                        for (Purchases purchase : manager.getPurchases()) {
                            System.out.printf(purchase.getName() + " $%.2f\n", purchase.getPrice());
                        }
                    }
                    break;
                case 2:
                    manager.sortByType();
                    break;
                case 3:
                    chooseSortCategories();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong Input. Try again");
                    break;
            }
        }
    }

    private void chooseTypeOfPurchaseAdd() {
        String menu = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) Back";
        int menuInput = 1;
        while (menuInput != 5) {
            System.out.println(menu);
            menuInput = scanner.nextInt();
            switch (menuInput) {
                case 1:
                    manager.addPurchase(Categories.FOOD);
                    break;
                case 2:
                    manager.addPurchase(Categories.CLOTHES);
                    break;
                case 3:
                    manager.addPurchase(Categories.ENTERTAINMENT);
                    break;
                case 4:
                    manager.addPurchase(Categories.OTHER);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wrong Input. Try again");
                    break;
            }
        }
    }

    private void chooseTypeOfPurchaseShowList() {
        String menu = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) All \n" +
                "6) Back";
        int menuInput = 1;
        while (menuInput != 6) {
            System.out.println(menu);
            menuInput = scanner.nextInt();
            switch (menuInput) {
                case 1:
                    manager.showListOfPurchasesCategories(Categories.FOOD);
                    break;
                case 2:
                    manager.showListOfPurchasesCategories(Categories.CLOTHES);
                    break;
                case 3:
                    manager.showListOfPurchasesCategories(Categories.ENTERTAINMENT);
                    break;
                case 4:
                    manager.showListOfPurchasesCategories(Categories.OTHER);
                    break;
                case 5:
                    manager.showListOfPurchasesAllCategories();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Wrong Input. Try again");
                    break;
            }
        }
    }

    public void startMenu() {
        final String menu = "\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase \n" +
                "3) Show list of purchases \n" +
                "4) Balance \n" +
                "5) Save \n" +
                "6) Load \n" +
                "7) Analyze (Sort) \n" +
                "0) Exit";
        int menuInput = 1;
        while (menuInput != 0) {
            System.out.println(menu);
            menuInput = scanner.nextInt();
            extracted(menuInput);
        }
    }

    private void extracted(int menuInput) {
        switch (menuInput) {
            case 0:
                System.out.println("\nBye!");
                break;
            case 1:
                manager.addIncome();
                break;
            case 2:
                chooseTypeOfPurchaseAdd();
                break;
            case 3:
                chooseTypeOfPurchaseShowList();
                break;
            case 4:
                manager.getBalance();
                break;
            case 5:
                manager.save();
                break;
            case 6:
                manager.load();
                break;
            case 7:
                chooseTypeOfSort();
                break;
            default:
                System.out.println("Wrong Input. Try again");
                break;
        }
    }
}

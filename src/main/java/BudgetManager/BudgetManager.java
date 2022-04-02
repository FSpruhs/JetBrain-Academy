package main.java.BudgetManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BudgetManager {

    private final String FILE_SOURCE = "purchases.txt";
    //private final String FILE_SOURCE = "./Budget Manager/task/src/budget/purchases.txt";
    private float income = 0.0F;
    private final Scanner scanner = new Scanner(System.in);
    private List<Purchases> purchases = new ArrayList<>();

    public void sortByType() {

        System.out.println();
        Map<String, Float> temp = new HashMap<>();
        float food = 0.0F;
        float clothes = 0.0F;
        float entertainment = 0.0F;
        float other = 0.0F;
        for (Purchases purchase: purchases) {
            if (purchase.getCategories() == Categories.FOOD) {
                food += purchase.getPrice();
            } else if (purchase.getCategories() == Categories.CLOTHES) {
                clothes += purchase.getPrice();
            } else if (purchase.getCategories() == Categories.ENTERTAINMENT) {
                entertainment += purchase.getPrice();
            } else {
                other += purchase.getPrice();
            }
        }
        temp.put("Food", food);
        temp.put("Clothes", clothes);
        temp.put("Entertainment", entertainment);
        temp.put("Other", other);
        List list = new LinkedList(temp.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        for (Object mapElement : list) {
            StringBuilder str = new StringBuilder();
            str.append(mapElement);
            System.out.print(str.substring(0, str.indexOf("=")));
            str.delete(0, str.indexOf("=") + 1);
            System.out.printf(" - $%.2f\n", Float.parseFloat(str.toString()));
        }
    }


    public void sortCategorie(Categories categories) {
        if (purchases.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            sortAll();
            for (Purchases purchase : purchases) {
                if (purchase.getCategories() == categories) {
                    System.out.printf(purchase.getName() + " $%.2f\n", purchase.getPrice());
                }
            }
        }
    }


    public void setIncome(float income) {
        this.income = income;
    }

    public void sortAll() {
        if (purchases.isEmpty()) {
            return;
        } else {
            Collections.sort(purchases, new Comparator<Purchases>() {
                @Override
                public int compare(Purchases o1, Purchases o2) {
                    return o2.compareTo(o1);
                }
            });
            System.out.println();
        }
    }

    public List<Purchases> getPurchases() {
        return purchases;
    }

    public float getIncome() {
        return income;
    }

    private void createFile() {
        File file = new File(FILE_SOURCE);
        try {
            boolean createdNew = file.createNewFile();
            if (createdNew) {
                System.out.println("The file was successfully created.");
            } else {
                System.out.println("The file already exists.");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + file.getPath());
        }
    }

    public void save() {
        createFile();
        if(!purchases.isEmpty()) {
            File file = new File(FILE_SOURCE);
            try (FileWriter writer = new FileWriter(file)){
                writer.write(Float.toString(getIncome())+ "\n");
                for (Purchases purchase : purchases) {
                    writer.write(purchase.toString() + "\n");
                }
                System.out.println("\nPurchases were saved!\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Categories findCategories(StringBuilder line) {
        String categories = line.substring(line.indexOf("=") + 1, line.indexOf(","));
        line.delete(0, line.indexOf(",") + 1);
        if (categories.equals("FOOD")) {
            return Categories.FOOD;
        } else if (categories.equals("CLOTHES")) {
            return Categories.CLOTHES;
        } else if (categories.equals("ENTERTAINMENT")) {
            return Categories.ENTERTAINMENT;
        } else {
            return Categories.OTHER;
        }
    }

    private float findPrice(StringBuilder line) {
        String price = line.substring(line.indexOf("=") + 1, line.indexOf(","));
        line.delete(0, line.indexOf(",") + 1);
        return Float.parseFloat(price);
    }

    private String findName(StringBuilder line) {
        return line.substring(line.indexOf("=") + 2, line.length() - 2);
    }

    public void load() {
        File file = new File(FILE_SOURCE);
        try (Scanner scanner = new Scanner(file)) {
            setIncome(Float.parseFloat(scanner.nextLine()));
            while (scanner.hasNext()) {
                StringBuilder line = new StringBuilder();
                line.append(scanner.nextLine());
                Categories categories = findCategories(line);
                float price = findPrice(line);
                String name = findName(line);
                purchases.add(new Purchases(categories, price, name));
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + FILE_SOURCE);
        }
    }

    private float sumPurchasesAllCategories() {
        float sumPurchases = 0.0F;
        for (Purchases purchase : purchases) {
            sumPurchases += purchase.getPrice();
        }
        return sumPurchases;
    }

    public void addPurchase(Categories categories) {
        System.out.println("\nEnter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        float price = Float.parseFloat(scanner.nextLine());
        purchases.add(new Purchases(categories, price, name));
        System.out.println("Purchase was added!");
    }

    private String printCategories(Categories categories) {
        if (categories == Categories.FOOD) {
            return "Food:";
        } else if (categories == Categories.CLOTHES) {
            return "Clothes:";
        } else if (categories == Categories.ENTERTAINMENT) {
            return "Entertainment:";
        }else {
            return "Other:";
        }
    }

    public void showListOfPurchasesCategories(Categories categories) {
        boolean isEmpty = purchasesCategoriesEmpty(categories);
        if (isEmpty) {
            System.out.println("\nThe purchase list is empty");
        } else {
            float sumPurchases = 0.0F;
            System.out.println("\n" + printCategories(categories));
            for (Purchases purchase : purchases) {
                if (purchase.getCategories() == categories) {
                    System.out.printf(purchase.getName() + " $%.2f \n", purchase.getPrice());
                    sumPurchases += purchase.getPrice();
                }
            }
            System.out.println("Total sum: $" + sumPurchases);
        }
    }

    private boolean purchasesCategoriesEmpty(Categories categories) {
        if (purchases.isEmpty()) {
            return true;
        } else {
            for (Purchases purchase : purchases) {
                if (purchase.getCategories() == categories) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showListOfPurchasesAllCategories() {
        if (purchases.isEmpty()) {
            System.out.println("\nThe purchase list is empty");
        } else {
            float sumPurchases = 0.0F;
            System.out.println("\nAll:");
            for (Purchases purchase : purchases) {
                System.out.printf(purchase.getName() + " $%.2f \n", purchase.getPrice());
                sumPurchases += purchase.getPrice();
            }
            System.out.printf("Total sum: $%.2f\n", sumPurchases);
        }
    }

    public void getBalance() {
        System.out.println("\nBalance: $" + (income - sumPurchasesAllCategories()) + "\n");
    }

    public void addIncome() {
        System.out.println("\nEnter income:");
        income += Float.parseFloat(scanner.nextLine());
        System.out.println();
    }
}

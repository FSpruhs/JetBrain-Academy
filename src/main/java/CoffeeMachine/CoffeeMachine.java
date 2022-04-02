package Project1;

import java.util.Scanner;
import java.util.Objects;

public class CoffeeMachine {

    final int espresso = 1;
    final int latte = 2;
    final int cappuccino = 3;
    final int espressoWater = 250;
    final int espressoBeans = 16;
    final int espressoMoney = 4;
    final int latteWater = 350;
    final int latteMilk = 75;
    final int latteBeans = 20;
    final int latteMoney = 7;
    final int cappuccinoWater = 200;
    final int cappuccinoMilk = 100;
    final int cappuccinoBeans = 12;
    final int cappuccinoMoney = 6;


    private int water;
    private int milk;
    private int beans;
    private int money;
    private int cups;

    public CoffeeMachine(int water, int milk, int beans, int money,int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
        this.cups = cups;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBeans() {
        return beans;
    }

    public int getMilk() {
        return milk;
    }

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public int getCup() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public void buy () {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        Scanner scanner = new Scanner(System.in);

        int intInput = 0;
        String stringInput = "dummy";

        if (scanner.hasNextInt()) {
            intInput = scanner.nextInt();
        } else {
            stringInput = scanner.nextLine();
        }
        //int input = new java.util.Scanner(System.in).nextInt();
        System.out.println();
        if (stringInput == "back") {
            return;
        } else if (intInput == espresso && checkSupplies(intInput)) {
            setWater(getWater() - espressoWater);
            setBeans(getBeans() - espressoBeans);
            setMoney(getMoney() + espressoMoney);
            setCups(getCup() - 1);

        } else if (intInput == latte && checkSupplies(intInput)) {
            setWater(getWater() - latteWater);
            setBeans(getBeans() - latteBeans);
            setMoney(getMoney() + latteMoney);
            setMilk(getMilk() - latteMilk);
            setCups(getCup() - 1);

        } else if (intInput == cappuccino && checkSupplies(intInput)) {
            setWater(getWater() - cappuccinoWater);
            setBeans(getBeans() - cappuccinoBeans);
            setMoney(getMoney() + cappuccinoMoney);
            setMilk(getMilk() - cappuccinoMilk);
            setCups(getCup() - 1);

        }

    }

    public void fill () {
        System.out.println("Write how many ml of water you want to add: ");
        int water = new java.util.Scanner(System.in).nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int milk = new java.util.Scanner(System.in).nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int beans = new java.util.Scanner(System.in).nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int cups = new java.util.Scanner(System.in).nextInt();
        setWater(getWater() + water);
        setMilk(getMilk() + milk);
        setBeans(getBeans() + beans);
        setCups(getCup() + cups);
    }

    public void take () {
        System.out.println("I gave you $" + getMoney() + "\n");
        setMoney(0);

    }

    public void menu() {

        boolean menuActive = true;

        while (menuActive) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String input = new java.util.Scanner(System.in).nextLine();

            if (Objects.equals(input, "buy")) {
                buy();
            } else if (Objects.equals(input, "fill")) {
                fill();
            } else if (Objects.equals(input, "take")) {
                take();
            } else if (Objects.equals(input, "remaining")) {
                System.out.println(this);
            } else if (Objects.equals(input, "exit")) {
                menuActive = false;
            }
        }

    }

    public boolean checkSupplies (int coffeeType) {

        boolean check = false;

        if (coffeeType == espresso) {
            check = checkSuppliesEspresso();
        } else if (coffeeType == latte) {
            check =  checkSuppliesLatte();
        } else if (coffeeType == cappuccino) {
            check = checkSuppliesCappuccino();
        }
        return check;
    }

    public boolean checkSuppliesEspresso() {

        if (getWater() - espressoWater < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (getBeans() - espressoBeans < 0) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (getCup() - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }

    public boolean checkSuppliesLatte() {
        if (getWater() - latteWater < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (getMilk() -latteMilk < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (getBeans() - latteBeans < 0) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (getCup() - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }

    public boolean checkSuppliesCappuccino() {
        if (getWater() - cappuccinoWater < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (getMilk() -cappuccinoMilk < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (getBeans() - cappuccinoBeans < 0) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (getCup() - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }

    @Override
    public String toString() {
        return "The coffee machine has:\n" +
                water +" ml of water\n"  +
                milk + " ml of milk\n" +
                beans + " g of coffee beans\n"  +
                cups + " disposable cups\n" +
                "$" + money +" of money\n";
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 550, 9);
        machine.menu();


    }
}
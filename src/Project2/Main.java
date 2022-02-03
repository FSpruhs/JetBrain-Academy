package Project2;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //BattleshipMatch match = new BattleshipMatch();
        Scanner scanner = new Scanner(System.in);

        double base = scanner.nextDouble();
        double height = scanner.nextDouble();
        double area = (base * height) / 2;

        System.out.println(area);
    }
}

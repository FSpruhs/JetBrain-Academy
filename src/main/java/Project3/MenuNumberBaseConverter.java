package Project3;

import java.util.Arrays;
import java.util.Scanner;

public class MenuNumberBaseConverter {

    public MenuNumberBaseConverter() {
        startMenu();
    }

    private void baseConvertMenu(int[] bases) {
        Scanner scanner = new Scanner(System.in);
        String inputMenu = "";
        while (!inputMenu.equals("/back")) {
            System.out.printf("\nEnter number in base %d to convert to base %d (To go back type /back) ",
                    bases[0] , bases[1]);
            inputMenu = scanner.nextLine();
            if (!inputMenu.equals("/back")) {
                if (inputMenu.contains(".")) {
                    System.out.println("Conversion result: " + NumberBaseConverter.baseToBaseFractional
                            (bases[0], bases[1], inputMenu));
                } else {
                    System.out.println("Conversion result: " + NumberBaseConverter.baseToBase
                            (bases[0], bases[1], inputMenu));
                }
            }
        }
    }

    private void startMenu() {
        Scanner scanner = new Scanner(System.in);
        String inputMenu = "";
        while (!inputMenu.equals("/exit")) {
            System.out.print("\nEnter two numbers in format: {source base} {target base} (To quit type /exit) ");
            inputMenu = scanner.nextLine();
            if (!inputMenu.equals("/exit")) {
                int[] bases = Arrays.stream(inputMenu.split(" ")).mapToInt(Integer::parseInt).toArray();
                baseConvertMenu(bases);
            }
        }
    }
}


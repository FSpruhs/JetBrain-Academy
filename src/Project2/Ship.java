package Project2;



import java.util.Arrays;
import java.util.Scanner;

public class Ship {

    private final int  AIRCRAFT_CARRIER_LENGTH = 5;
    private final int  BATTLESHIP_LENGTH = 4;
    private final int  SUBMARINE_LENGTH = 3;
    private final int  CRUISER_LENGTH = 3;
    private final int  DESTROYER_LENGTH = 2;


    private String name;
    private int length;
    private BattleField battleField;
    private int[] startField = new int[2];
    private int[] endField = new int[2];
    private boolean sunken = false;
    private int life;

    enum ShipType {
        AIRCRAFT_CARRIER, BATTLESHIP, SUBMARINE, CRUISER, DESTROYER
    }

    public Ship(ShipType ship, BattleField battleField) {
        this.battleField = battleField;
        if (ship == ShipType.AIRCRAFT_CARRIER) {
            this.length = AIRCRAFT_CARRIER_LENGTH;
            this.life = AIRCRAFT_CARRIER_LENGTH;
            this.name = "Aircraft Carrier";
        } else if (ship == ShipType.BATTLESHIP) {
            this.length = BATTLESHIP_LENGTH;
            this.life = BATTLESHIP_LENGTH;
            this.name = "Battleship";
        } else if (ship == ShipType.SUBMARINE) {
            this.length = SUBMARINE_LENGTH;
            this.life = SUBMARINE_LENGTH;
            this.name = "Submarine";
        } else if (ship == ShipType.CRUISER) {
            this.length = CRUISER_LENGTH;
            this.life = CRUISER_LENGTH;
            this.name = "Cruiser";
        } else if (ship == ShipType.DESTROYER) {
            this.length = DESTROYER_LENGTH;
            this.life = DESTROYER_LENGTH;
            this.name = "Destroyer";
        }
        startPlaceShip();
    }

    private void startPlaceShip() {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", name, length);
        placeShip();
    }

    private void placeShip() {

        boolean invalidInput = false;
        while (!invalidInput) {
            Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            try {
                checkInput(inputs[0], inputs[1]);
                invalidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (this.startField[0] == this.endField[0]) {
            for (int i = this.startField[1]; i <= this.endField[1]; i++) {
                battleField.setShip(startField[0], i, this);
            }
        } else {
            for (int i = this.startField[0]; i <= this.endField[0]; i++) {
                battleField.setShip(i, startField[1], this);
            }
        }
    }

    private void checkInputFormat (char line, int colum) {
        if (colum < 0 || colum > 10) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        } else if (line < 'A' || line > 'J') {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }
    }

    private void checkInput(String input1,String input2) {
        int colum1 = Integer.parseInt(input1.substring(1));
        char line1 = input1.charAt(0);
        int colum2 = Integer.parseInt(input2.substring(1));
        char line2 = input2.charAt(0);
        checkInputFormat(line1, colum1);
        checkInputFormat(line2, colum2);
        int[] cordsA = new int[2];
        int[] cordsB = new int[2];
        getInputCords(line1, colum1, line2, colum2, cordsA, cordsB);
        checkInputLength(startField[0], startField[1], endField[0], endField[1]);
        checkFreeSpace(cordsA, cordsB);
        checkFreeNeighbours(cordsA, cordsB);
    }

    private void checkFreeNeighbours(int[] cordsA, int[] cordsB) {
        int startPosX = (cordsA[0] - 1 < 0) ? cordsA[0] : cordsA[0] - 1;
        int startPosY = (cordsA[1] - 1 < 0) ? cordsA[1] : cordsA[1] - 1;
        int endPosX = (cordsB[0] + 1 > 9) ? cordsB[0] : cordsB[0] + 1;
        int endPosY = (cordsB[1] + 1 > 9) ? cordsB[1] : cordsB[1] + 1;

        for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
            for (int colNum = startPosY; colNum <= endPosY; colNum++) {
                if (battleField.getFieldStatus(rowNum, colNum) != Field.FieldStatus.FOG) {
                    throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
                }
            }
        }
    }

    private void getInputCords(char line1, int colum1, char line2, int colum2, int[] cordsA, int[] cordsB) {
        cordsA[1] = colum1 - 1;
        cordsB[1] = colum2 - 1;
        cordsA[0] = line1 - 65;
        cordsB[0] = line2 - 65;
        if (cordsA[0] + cordsA[1] > cordsB[0] + cordsB[1]) {
            int helpSwitch;
            helpSwitch = cordsA[0];
            cordsA[0] = cordsB[0];
            cordsB[0] = helpSwitch;
            helpSwitch = cordsA[1];
            cordsA[1] = cordsB[1];
            cordsB[1] = helpSwitch;
        }
        this.startField[0] = cordsA[0];
        this.startField[1] = cordsA[1];
        this.endField[0] = cordsB[0];
        this.endField[1] = cordsB[1];
    }

    private void checkInputLength(int colum1, int line1, int colum2, int line2) {
        if (line1 != line2 && colum1 != colum2) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        } else if (line1 == line2 && colum2 - colum1 != this.length - 1) {
            throw new IllegalArgumentException("Error! Wrong length of the Submarine! Try again:");
        } else if (colum1 == colum2 && line2 - line1 != this.length - 1) {
            throw new IllegalArgumentException("Error! Wrong length of the Submarine! Try again:");
        }
    }

    private void checkFreeSpace(int[] cordsA, int[] cordsB) {
        if (cordsA[0] == cordsB[0]) {
            for (int i = 0; i < this.length; i++) {

                if (battleField.getFieldStatus(cordsA[0], cordsA[1] +i ) != Field.FieldStatus.FOG) {
                    throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
                }
            }
        } else {
            for (int i = 0; i < this.length; i++) {
                if (battleField.getFieldStatus(cordsA[0] + i, cordsA[1]) != Field.FieldStatus.FOG) {
                    throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
                }
            }
        }
    }

    public void getHit() {
        this.life -= 1;
        if (this.life <= 0) {
            sunken = true;
            System.out.println("You sank a ship!");
        }
    }
}

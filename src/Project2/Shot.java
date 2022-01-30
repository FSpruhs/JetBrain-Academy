package Project2;

import java.util.Scanner;

public class Shot {
    private int cordX;
    private int cordY;
    private Player playerAttack;
    private Player playerDefense;


    public Shot(Player playerAttack,Player playerDefense) {
        this.playerAttack = playerAttack;
        this.playerDefense = playerDefense;
        shot();
    }

    private void checkInputFormat (char line, int colum) {
        if (colum < 0 || colum > 10) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        } else if (line < 'A' || line > 'J') {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }
    }

    private void checkInput(String input) {
        int colum = Integer.parseInt(input.substring(1));
        char line = input.charAt(0);
        checkInputFormat(line, colum);
        getInputCords(line, colum);

    }

    private void getInputCords(char line, int colum) {
        this.cordY = colum - 1;
        this.cordX = line - 65;
    }

    private void shot() {
        System.out.printf("%s, it's your turn", playerAttack.getPlayerName());
        boolean invalidInput = false;
        while (!invalidInput) {
            Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();
            try {
                checkInput(input);
                invalidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (playerDefense.getPlayerBattlefield().getFieldStatus(cordX, cordY) == Field.FieldStatus.FOG) {
            playerDefense.getPlayerBattlefield().setFieldStatus(cordX, cordY, Field.FieldStatus.MISS);
            playerAttack.getPlayerFogOfWarBattlefield().setFieldStatus(cordX, cordY, Field.FieldStatus.MISS);
            System.out.println("You missed!");
        } else if (playerDefense.getPlayerBattlefield().getFieldStatus(cordX, cordY) == Field.FieldStatus.SHIP) {
            playerDefense.getPlayerBattlefield().setFieldStatus(cordX, cordY, Field.FieldStatus.HIT);
            playerAttack.getPlayerFogOfWarBattlefield().setFieldStatus(cordX, cordY, Field.FieldStatus.HIT);
            System.out.println("You hit a ship!");
            playerDefense.getPlayerBattlefield().getShip(cordX, cordY).getHit();
        }

    }



}

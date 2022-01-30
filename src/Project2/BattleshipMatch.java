package Project2;


import java.util.Scanner;

public class BattleshipMatch {


    private Player playerA;
    private Player playerB;

    public static void enterToNext() {
        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    private void takeShot(Player playerAttack, Player playerDefense) {
        Shot shot = new Shot(playerAttack, playerDefense);
    }

    private void placeShips(Player player) {
        System.out.printf("%s, place your ships on the game field\n", player.getPlayerName());
        System.out.println(player.getPlayerBattlefield());
        Ship carrier = new Ship(Ship.ShipType.AIRCRAFT_CARRIER, player.getPlayerBattlefield());
        System.out.println(player.getPlayerBattlefield());
        Ship battleship = new Ship(Ship.ShipType.BATTLESHIP, player.getPlayerBattlefield());
        System.out.println(player.getPlayerBattlefield());
        Ship submarine = new Ship(Ship.ShipType.SUBMARINE, player.getPlayerBattlefield());
        System.out.println(player.getPlayerBattlefield());
        Ship cruiser = new Ship(Ship.ShipType.CRUISER, player.getPlayerBattlefield());
        System.out.println(player.getPlayerBattlefield());
        Ship destroyer = new Ship(Ship.ShipType.DESTROYER, player.getPlayerBattlefield());
        System.out.println(player.getPlayerBattlefield());
        enterToNext();
    }


    private void startMatch() {
        this.playerA = new Player("Player 1");
        placeShips(playerA);
        this.playerB = new Player("Player 2");
        placeShips(playerB);

    }

    private void showBattlefield(Player player) {
        System.out.println(player.getPlayerFogOfWarBattlefield());
        System.out.println("---------------------");
        System.out.println(player.getPlayerBattlefield());

    }

    public BattleshipMatch() {
        startMatch();
        while (true) {
            showBattlefield(playerA);
            takeShot(playerA, playerB);
            if (playerB.getPlayerBattlefield().checkScore() == true) {
                playerA.setWinner(true);
                break;
            }
            enterToNext();
            showBattlefield(playerB);
            takeShot(playerB, playerA);
            if (playerA.getPlayerBattlefield().checkScore() == true) {
                playerB.setWinner(true);
                break;
            }
            enterToNext();
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }


}

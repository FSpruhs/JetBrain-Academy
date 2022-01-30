package Project2;


public class Main {

    public static void main(String[] args) {
        BattleField field = new BattleField();
        System.out.println(field);
        Ship carrier = new Ship(Ship.ShipType.AIRCRAFT_CARRIER, field);
        System.out.println(field);
        Ship battleship = new Ship(Ship.ShipType.BATTLESHIP, field);
        System.out.println(field);
        Ship submarine = new Ship(Ship.ShipType.SUBMARINE, field);
        System.out.println(field);
        Ship cruiser = new Ship(Ship.ShipType.CRUISER, field);
        System.out.println(field);
        Ship destroyer = new Ship(Ship.ShipType.DESTROYER, field);
        System.out.println(field);

    }
}

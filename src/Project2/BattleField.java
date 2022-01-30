package Project2;


import java.util.Arrays;

public class BattleField {
    private final int FIELD_SIZE = 10;
    private String playerName;

    Field[][] battleField = new Field[FIELD_SIZE][FIELD_SIZE];

    public BattleField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                battleField[i][j] = new Field();
            }
        }
    }

    public Field.FieldStatus getFieldStatus(int cordA, int cordB) {
        return battleField[cordA][cordB].getStatus();
    }

    public void setFieldStatus(int cordA, int cordB, Field.FieldStatus status) {
        battleField[cordA][cordB].setStatus(status);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(" 1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < FIELD_SIZE; i++) {
            char c =  (char) (65 + i);
            output.append(c).append(" ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                output.append(battleField[i][j]).append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }
}

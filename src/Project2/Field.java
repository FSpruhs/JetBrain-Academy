package Project2;


import static Project2.Field.FieldStatus.*;

public class Field{

    private FieldStatus status;

    enum FieldStatus {
        FOG, SHIP, HIT, MISS
    }

    public Field() {
        this.status = FOG;
    }

    public void setStatus(FieldStatus status) {
        this.status = status;
    }

    public FieldStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String output = "";
        if (status == FOG) {
            output = "~";
        } else if (status == SHIP) {
            output = "O";
        } else if (status == HIT) {
            output = "X";
        } else if (status == MISS){
            output = "M";
        }
        return output;
    }
}

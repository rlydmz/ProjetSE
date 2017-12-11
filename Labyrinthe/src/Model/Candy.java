package Model;

import java.util.concurrent.ThreadLocalRandom;

public class Candy extends Entity{

    private static int VALUE =  10;
    private int type;

    public Candy(){
        super();
        type = ThreadLocalRandom.current().nextInt(1,5);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static int getValue() {
        return VALUE;
    }

    public static void setValue(int value) {
        Candy.VALUE = value;
    }
}

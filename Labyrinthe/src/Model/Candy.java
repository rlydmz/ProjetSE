package Model;

import java.util.concurrent.ThreadLocalRandom;

public class Candy extends Entity{

    private static int VALUE =  10;
    private int type;

    /**
     * Constructeur de la classe Candy
     */
    public Candy(){
        super();
        type = ThreadLocalRandom.current().nextInt(1,5);
    }

    /**
     * Retourne le type du bonbon
     * @return sous forme d'entier entre 1 et 4 (= nombre de sprites)
     */
    public int getType() {
        return type;
    }

    /**
     * Fixe le type du bonbon
     * @param type Entier compris entre 1 et 4
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Retourne la valeur du bonbon permettant l'incrementation du score
     * @return Un entier superieur a 0
     */
    public static int getValue() {
        return VALUE;
    }

    /**
     * Fixe la valeur du bonbon
     * @param value Un entier superieur a 0
     */
    public static void setValue(int value) {
        Candy.VALUE = value;
    }
}

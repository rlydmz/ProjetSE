package Model;

public class NiceGuy extends Entity{

    private int nbEatenCandy;

    /**
     * Constructeur de la classe NiceGuy
     */
    public NiceGuy(){
        super();
        nbEatenCandy = 0;
    }

    /**
     * Retourne le nombre de Candy collectes jusqu'a present par le joueur
     * @return Un entier superieur ou egal a 0
     */
    public int getNbEatenCandy() {
        return nbEatenCandy;
    }

    /**
     * Fixe le nombre de Candy collectes jusqu'a present par le joueur
     * @param nbEatenCandy Un entier superieur ou egal a 0
     */
    public void setNbEatenCandy(int nbEatenCandy) {
        this.nbEatenCandy = nbEatenCandy;
    }

    /**
     * Augmente de 1 le nombre de Candy ingere par le joueur
     */
    public void incNbEatenCandy(){
        nbEatenCandy++;
    }
}

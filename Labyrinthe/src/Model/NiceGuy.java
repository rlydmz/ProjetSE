package Model;

public class NiceGuy extends Entity{

    private int nbEatenCandy;

    public NiceGuy(){
        super();
        nbEatenCandy = 0;
    }

    public int getNbEatenCandy() {
        return nbEatenCandy;
    }

    public void setNbEatenCandy(int nbEatenCandy) {
        this.nbEatenCandy = nbEatenCandy;
    }

    public void incNbEatenCandy(){
        nbEatenCandy++;
    }
}

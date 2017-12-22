package Model;


public class Door extends Entity{
    private Vertex position;
    boolean isOpen = false;

    /**
     * Constructeur de la classe Door
     */
    public Door (){
        super();
        isOpen = false;
    }

    /**
     * Retourne un boolean determinant l'etat ouvert ou ferme d'un porte
     * @return true si la porte est ouverte, false si elle est fermee
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Fixe l'etat ouvert ou ferme d'un porte
     * @param open true si on ouvre la porte, false si on la ferme
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }
}

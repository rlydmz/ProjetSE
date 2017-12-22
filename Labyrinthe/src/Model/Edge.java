package Model;
import org.jgrapht.graph.DefaultEdge;

/**
 * Created by brian on 20/11/17.
 */
public class Edge extends DefaultEdge{

    /**
     * Liste des etats possibles d'un Edge, c'est a dire d'une cellule du chemin dans le labyrinthe
     */
    public enum Type{
        OPENED_DOOR,
        CLOSED_DOOR,
        CORRIDOR;
    }

    private Type type;

    /**
     * Constructeur de la classe Edge
     * @param type L'etat de la cellule sous forme d'un Type
     */
    public Edge(Type type){
        super();
        this.type = type;
    }

    /**
     * Constructeur blanc de la classe Edge
     */
    public Edge() {
        super();
        this.type = Type.CORRIDOR;
    }

    /**
     * Retourne la source de l'Edge
     * @return Un objet de la classe Vertex
     */
    public Vertex getSource(){
        return (Vertex) super.getSource();
    }

    /**
     * Retourne la cible de l'Edge
     * @return Un objet de la classe Vertex
     */
    public Vertex getTarget(){
        return (Vertex) super.getTarget();
    }

    /**
     * Retourne l'etat de l'Edge
     * @return Une valeur de type Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Fixe la valeur de l'Edge
     * @param type Une valeur de type Type
     */
    public void setType(Type type) {
        this.type = type;
    }
}

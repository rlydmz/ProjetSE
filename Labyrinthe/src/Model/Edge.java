package Model;
import org.jgrapht.graph.DefaultEdge;

/**
 * Created by brian on 20/11/17.
 */
public class Edge extends DefaultEdge{
    public enum Type{
        OPENED_DOOR,
        CLOSED_DOOR,
        CORRIDOR;
    }

    private Type type;

    public Edge(Type type){
        super();
        this.type = type;
    }

    public Edge() {
        super();
        this.type = Type.CORRIDOR;
    }

    /**Getters & Setters**/

    public Vertex getSource(){
        return (Vertex) super.getSource();
    }

    public Vertex getTarget(){
        return (Vertex) super.getTarget();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

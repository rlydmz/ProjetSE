package Model;

import org.jgrapht.graph.SimpleGraph;
import Model.Labyrinthe.Directions;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Graph extends SimpleGraph<Vertex, Edge>{

    private Vertex[][] vertexList;
    private Switch[] switchList;
    private int nbElement;
    private static int SIZE = Labyrinthe.SIZE;

    /**
     * Contructeur de la classe Graph
     * @param size Un entier corresponsant a la taille tu graphe (= nombre de sommets au total)
     */
    public Graph(int size){
        super(Edge.class);
        nbElement = 0;
        vertexList = new Vertex[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                vertexList[i][j] = null;
            }
        }
    }

    /**
     * Retourne un Vertex avec une position aleatoire
     * @return Un objet de type Vertex
     */
    public Vertex randomVertex() {
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(16);
        int randomY = randomGenerator.nextInt(16);
        Vertex v = new Vertex(randomX,randomY);
        return v;
    }

    /**
     * Retourne un Vertex correspondant au deplacement d'un autre Vertex vers une direction donnee
     * @param actual Un objet de type Vertex considere comme point de depart du mouvement
     * @param dir Une direction de type Directions
     * @return Un objet de type Vertex
     */
    public Vertex getVertexByDir(Vertex actual, Directions dir) {
        Vertex v = new Vertex(0,0);
        switch(dir) {
        case NORTH:
            if(actual.getY() > 0) {
                v.setX(actual.getX());
                v.setY(actual.getY() - 1);
                return v;
            }else {
                return actual;
            }
        case SOUTH:
            if(actual.getY() < 15) {
                v.setX(actual.getX());
                v.setY(actual.getY() + 1);
                return v;
            }else {
                return actual;
            }
        case EAST:
            if(actual.getX() <= 15) {
                v.setX(actual.getX() + 1);
                v.setY(actual.getY());
                return v;
            }else {
                return actual;
            }
        case WEST:
            if(actual.getX() >= 0) {
                v.setX(actual.getX() - 1);
                v.setY(actual.getY());
                return v;
            }else {
                return actual;
            }
        }
        return null;
    }

    /**
     * Indique l'existence ou non d'un Vertex dans le Graph en fonction d'un direction donnee
     * @param vertex Un objet de type Vertex
     * @param dir Une direction de type Directions
     * @return Un objet de type Vertex
     */
    public boolean doesntExist(Vertex vertex, Directions dir) {
        Vertex newVertex = getVertexByDir(vertex,dir);
        if(vertexList[newVertex.getX()][newVertex.getY()] == null || !vertexList[newVertex.getX()][newVertex.getY()].equals(newVertex)){
            return true;
        }
        else
            return false;
    }

    /**
     * Retourne une arrete representee par un objet de type Edge contenu dans le Graph
     * @param v Un objet de type Vertex
     * @param dir Une direction de type Directions
     * @return Un objet de type Edge ou null
     */
    public Edge getEdge(Vertex v, Directions dir){
        Vertex newVertex = getVertexByDir(v, dir);
        return getEdge(v, newVertex);
    }

    /**
     * Retourne une arrete representee par un objet de type Edge contenu dans le Graph en fonction de deux Vertex donnes
     * @param vs Un objet de type Vertex, source
     * @param vt Un objet de type Vertex, cible
     * @return Un objet de type Edge ou null
     */
    public Edge getEdge(Vertex vs, Vertex vt){
        Set<Edge> edgeList = edgeSet();
        Iterator i = edgeList.iterator();
        while(i.hasNext()){
            Edge e = (Edge)i.next();
            if((e.getSource().equals(vs) && e.getTarget().equals(vt)) || (e.getSource().equals(vt) && e.getTarget().equals(vs))){
                return e;
            }
        }
        return null;
    }

    /**
     * Ajoute au Graph le Vertex qui lui est passe en parametre
     * @param v Un objet de type Vertex
     * @return true si l'ajout est fructueux, false sinon
     */
    public boolean addVertexToArray(Vertex v){
        if(vertexList[v.getX()][v.getY()] == null){
            vertexList[v.getX()][v.getY()] = v;
            return true;
        }
        else{
            return false;
        }
    }
    
}
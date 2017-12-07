package Model;

import org.jgrapht.graph.SimpleGraph;
import Model.Labyrinthe.Directions;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Random;

public class Graph extends SimpleGraph<Vertex, Edge>{

    private Vertex[][] vertexList;
    private int nbElement;
    private static int SIZE = Labyrinthe.getInstance().getHeight();

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

    public Vertex randomVertex() {
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(16);
        int randomY = randomGenerator.nextInt(16);
        Vertex v = new Vertex(randomX,randomY);
        return v;
    }

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
            if(actual.getX() < 15) {
                v.setX(actual.getX() + 1);
                v.setY(actual.getY());
                return v;
            }else {
                return actual;
            }
        case WEST:
            if(actual.getX() > 0) {
                v.setX(actual.getX() - 1);
                v.setY(actual.getY());
                return v;
            }else {
                return actual;
            }
        }
        return null;
    }

    public boolean doesntExist(Vertex vertex, Directions dir) {
        Vertex newVertex = getVertexByDir(vertex,dir);
        if(vertexList[newVertex.getX()][newVertex.getY()] == null || !vertexList[newVertex.getX()][newVertex.getY()].equals(newVertex)){
            System.out.println("doesntExist  = false, newVertex = " + vertexList[newVertex.getX()][newVertex.getY()]);
            return true;
        }
        else
            System.out.println("doesntExist  = true, newVertex = " + vertexList[newVertex.getX()][newVertex.getY()]);
            return false;
        /*
        Vertex tmpVertex = null;

        switch(dir) {
            case NORTH: tmpVertex = new Vertex(vertex.getX(), vertex.getY()-1); break;
            case SOUTH: tmpVertex = new Vertex(vertex.getX(), vertex.getY()+1); break;
            case EAST:  tmpVertex = new Vertex(vertex.getX()+1, vertex.getY()); break;
            case WEST:  tmpVertex = new Vertex(vertex.getX()-1, vertex.getY()); break;
        }
        if(tmpVertex == null){
            System.out.println("tmpVertex NULL");
            return false;
        }
        for (int i = 0; i < SIZE*SIZE; i++) {
            if(vertexList[i] != null){
                if(vertexList[i].equals(tmpVertex))
                    return false;
            }
        }
        return true;
        */
    }

    public Object getEqualVertex(Vertex v) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean addVertexToArray(Vertex v){
        if(vertexList[v.getX()][v.getY()] == null){
            vertexList[v.getX()][v.getY()] = v;
            return true;
        }
        else{
            return false;
        }
    }

    public void printVertexList(){
        for (int i = 0; i < SIZE*SIZE; i++) {
            if(vertexList[i] != null)
                System.out.println(vertexList[i]);
        }
    }

    public boolean isDone(){

        //System.out.println("nbELem = " + nbElement + " - SIZE = " + vertexList.length);
        //System.out.println("IS DONE = " + (nbElement == vertexList.length));
        if(nbElement == vertexList.length){
            return true;}
        return false;
    }
    
}
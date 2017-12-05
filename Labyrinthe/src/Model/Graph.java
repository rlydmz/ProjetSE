package Model;

import org.jgrapht.graph.SimpleGraph;
import Model.Labyrinthe.Directions;
import java.util.Random;

public class Graph extends SimpleGraph<Vertex, Edge>{

    private Vertex[][] vertexList;

    public Graph(int size){
        super(Edge.class);
        vertexList = new Vertex[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                vertexList[i][j] = new Vertex(i, j);
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
        /*
        switch(dir) {
        case NORTH: return (vertex.getY() == 0);
        case SOUTH: return (vertex.getY() == 15);
        case EAST: return (vertex.getX() == 15);
        case WEST: return (vertex.getX() == 0);
        }
        return false;
        */
        return true;
    }

    public Object getEqualVertex(Vertex v) {
        // TODO Auto-generated method stub
        return null;
    }

    public void printContent(){

    }
    
}
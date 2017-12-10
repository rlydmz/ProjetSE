package Model;

import org.jgrapht.graph.SimpleGraph;
import Model.Labyrinthe.Directions;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Graph extends SimpleGraph<Vertex, Edge>{

    private Vertex[][] vertexList;
    private int nbElement;
    private static int SIZE = Labyrinthe.SIZE;

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

    public boolean doesntExist(Vertex vertex, Directions dir) {
        Vertex newVertex = getVertexByDir(vertex,dir);
        if(vertexList[newVertex.getX()][newVertex.getY()] == null || !vertexList[newVertex.getX()][newVertex.getY()].equals(newVertex)){
            return true;
        }
        else
            return false;
    }

    public Edge getEdge(Vertex v, Directions dir){
        Vertex newVertex = getVertexByDir(v, dir);
        return getEdge(v, newVertex);
    }

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

    public boolean hasXsEqualToLimits(){
        Set<Edge> set = edgeSet();
        Iterator i = set.iterator();
        int cpt = 0;
        while(i.hasNext()){
            Edge e = (Edge)i.next();
            System.out.println(e.getTarget());
            if(e.getTarget().getX() == 0 || e.getTarget().getX() == 15)
                cpt++;
        }
        System.out.println("cpt = " + cpt);
        if(cpt != 0)
            return true;
        else
            return false;
    }
    
}
package model;

import org.jgrapht.graph.SimpleGraph;

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
        for (int i = 0; i < size-1; i++) {
            addEdge(vertexList[i][0], vertexList[i+1][0]);
            for (int j = 0; j < size-1; j++) {
                addEdge(vertexList[0][j], vertexList[0][j+1]);
            }
        }
    }



}

package Model;

import java.util.*;

import Model.Edge.Type;


public class Labyrinthe {

    private static int height = 16;
    private static int width = 16;
    private Graph graph;
    private NiceGuy packman;
    private Exit exit;
    private HashSet<BadGuy> badGuyArmy;
    private HashSet<Candy> candies;
    private Random random;

    public static int SIZE = 16;

    private static Labyrinthe ourInstance = null;

    public static Labyrinthe getInstance() {
        if (ourInstance == null) {
            ourInstance = new Labyrinthe();
        }
        return ourInstance;
    }

    private Labyrinthe() {
        graph = new Graph(SIZE);
        packman = new NiceGuy();
        exit = new Exit();
        badGuyArmy = new HashSet<BadGuy>();
        candies = new HashSet<Candy>();
        random = new Random();
    }

    public enum Directions {
        NORTH,
        SOUTH,
        EAST,
        WEST;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Graph getG() {
        return graph;
    }

    public NiceGuy getPackman() {
        return packman;
    }

    public Exit GetExit() {
        return exit;
    }

    public HashSet<BadGuy> getBadGuyArmy(){
        return badGuyArmy;
    }

    public void addBadGuyToArmy(BadGuy badGuy){
        badGuyArmy.add(badGuy);
    }

    public HashSet<Candy> getCandies(){
        return candies;
    }

    public void addCandy(Candy candy){
        candies.add(candy);
    }

    public void removeCandy(Candy candy){
        candies.remove(candy);
    }

    public void buildRandomPath(Vertex vertex) {

        graph.addVertexToArray(vertex);

        // une liste aleatoire des 4 directions
        Vector<Directions> v = new Vector<Directions>();
        for (int i = 0; i < 4; ++i)
            v.add(Directions.values()[i]);

        Directions directions[] = new Directions[4];
        for (int i = 0; i < directions.length; ++i) {
            int index = random.nextInt(v.size());
            directions[i] = v.get(index);
            v.remove(index);
        }

        //pour chacune de ces directions, on avance en profondeur d abord
        for (int i = 0; i < 4; ++i) {
            Directions dir = directions[i];
            if (vertex.inBorders(dir) && graph.doesntExist(vertex, dir)) {
                int x = vertex.getX();
                int y = vertex.getY();
                int xt = 0, yt = 0;
                switch (dir) {
                    case NORTH:
                        xt = x;
                        yt = y - 1;
                        break;
                    case SOUTH:
                        xt = x;
                        yt = y + 1;
                        break;
                    case EAST:
                        xt = x + 1;
                        yt = y;
                        break;
                    case WEST:
                        xt = x - 1;
                        yt = y;
                        break;
                }
                Vertex next = new Vertex(xt, yt);
                graph.addVertex(next);
                graph.addEdge(vertex, next);
                buildRandomPath(next);
            }
        }
    }

    public void openDoorRandom() {
        //On essaie 1000 fois, apres quoi on renonce
        for (int i = 1; i <= 1000; ++i) {
            //On choisi un sommet au hasard
            Vertex vertex = graph.randomVertex();
            if (vertex != null) {
                // On choisi une direction au hasard (on devrait prendre seulement
                // celles qui correspondent a des murs ...)
                Labyrinthe.Directions dir = Directions.values()[random.nextInt(Directions.values().length)];
                if (isWall(vertex, dir)) {
                    Vertex vertex2 = graph.getVertexByDir(vertex, dir);
                    if (vertex2 != null) {
                        Edge edge = graph.getEdge(vertex, vertex2);
                        if (edge == null) {
                            // On ajoute un saut entre ces sommets
                            graph.addEdge(vertex, vertex2, new Edge(Type.OPENED_DOOR));
                            return;
                        }
                    }
                }
            }
        }
    }

    private void calculateManhattanDistance(Vertex source, Vertex target) {
        System.out.println(source.getNbr() + " - " + target.getNbr());

        Set<Edge> listEdge = this.getG().edgeSet();
        Iterator i = listEdge.iterator();
        while(i.hasNext()){
            Edge e = (Edge)i.next();
            if(e.getSource().getNbr() != 0 || e.getTarget().getNbr() != 0)
                System.out.println(e);
        }

        Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        target.setNbr(1);
        fifo.add(target);
        while (!fifo.isEmpty()) {
            Vertex actual = fifo.remove();
            for (Directions dir : Directions.values()) {
                if (this.isOpened(actual, dir)) {
                    Vertex next = graph.getVertexByDir(actual, dir);
                    if (next.getNbr() == 0) {
                        next.setNbr(actual.getNbr() + 1);
                        if (!next.equals(source))
                            fifo.add(next);
                    }
                }
            }
        }
    }

    public boolean isWall(Vertex v, Directions dir){
        Edge e = graph.getEdge(v, dir);
        return (e==null);
    }

    private boolean isOpened(Vertex actual, Directions dir) {
        if(isWall(actual, dir))
            return false;
        return true;
    }

    public void launchManhattan(Vertex source, Vertex target) {
        for (Vertex vertex : graph.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }

    public boolean isConnected(Vertex src, Vertex target){
        if(getG().getEdge(src, target) != null)
            return true;
        return false;
    }

}

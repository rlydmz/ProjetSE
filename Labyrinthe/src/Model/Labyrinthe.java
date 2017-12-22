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

    /**
     * Retourne l'instance unique de la classe Labyrinthe
     * @return Une instance unique de la classe Labyrinthe
     */
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

    /**
     * Retourne la hauteur du labyrinthe
     * @return Un entier superieur a 0
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne la largeur du labyrinthe
     * @return Un entier superieur a 0
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne l'instance Graph du graphe associee au labyrinthe
     * @return Un objet de type Graph
     */
    public Graph getG() {
        return graph;
    }

    /**
     * Retourne l'objet de notre joueur
     * @return Un objet de type NiceGuy
     */
    public NiceGuy getPackman() {
        return packman;
    }

    /**
     * Retourne l'objet de notre sortie
     * @return Un objet de type Exit
     */
    public Exit GetExit() {
        return exit;
    }

    /**
     * Retourne la liste des ennemis presents dans le labyrinthe
     * @return Un HashSet rempli de BadGuy
     */
    public HashSet<BadGuy> getBadGuyArmy(){
        return badGuyArmy;
    }

    /**
     * Ajoute un ennemi au labyrinthe
     * @param badGuy Un objet de type BadGuy
     */
    public void addBadGuyToArmy(BadGuy badGuy){
        badGuyArmy.add(badGuy);
    }

    /**
     * Retourne la liste des bonbons presents dans le labyrinthe
     * @return Un HashSet remplit de Candy
     */
    public HashSet<Candy> getCandies(){
        return candies;
    }

    /**
     * Ajoute un bonbon au labyrinthe
     * @param candy Un objet de type Candy
     */
    public void addCandy(Candy candy){
        candies.add(candy);
    }

    /**
     * Retire un bonbon du labyrinthe dans le cas ou il aurait ete ingere
     * @param candy
     */
    public void removeCandy(Candy candy){
        candies.remove(candy);
    }

    /**
     * Construit un graphe parfait jusqu'a ce que le labyrinthe soit remplit
     * @param vertex Un objet de type Vertex, point de depart de l'algorythme
     */
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

    /**
     * Casse des murs au hasard pour permettre un pasage plus aise du joueur dans le labyrinthe
     */
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

    /**
     * Calcule le chemin le plus court entre deux Vertex
     * @param source Un objet de type Vertex, source
     * @param target Un objet de type vertex, cible
     */
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

    /**
     * Determine si le passage d'un Vertex source vers une direction est possible ou non, ou autrement dit s'il existe un mur dans le direction donnee
     * @param v Un objet de type Vertex, source
     * @param dir Une direction de type Directions
     * @return true si le passage est impossible, false sinon
     */
    public boolean isWall(Vertex v, Directions dir){
        Edge e = graph.getEdge(v, dir);
        return (e==null);
    }

    private boolean isOpened(Vertex actual, Directions dir) {
        if(isWall(actual, dir))
            return false;
        return true;
    }

    /**
     * Lance l'algorithme de Manhattan a partir d'une source et d'une cible
     * @param source Un objet de type Vertex, source
     * @param target Un objet de type Vertex, cible
     */
    public void launchManhattan(Vertex source, Vertex target) {
        for (Vertex vertex : graph.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }

    /**
     * Determince si deux Vertex sont caracterises par une arrete dans le graphe du labyrinthe
     * @param src Un objet de type Vertex, source
     * @param target Un objet de type Vertex, cible
     * @return true si l'arrete existe, false sinon
     */
    public boolean isConnected(Vertex src, Vertex target){
        if(getG().getEdge(src, target) != null)
            return true;
        return false;
    }

}

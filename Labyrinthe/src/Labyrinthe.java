public class Labyrinthe {

    private static int HEIGHT = 16;
    private static int WIDTH = 16;

    private static Labyrinthe ourInstance = new Labyrinthe();

    public static Labyrinthe getInstance() {
        return ourInstance;
    }

    private Labyrinthe() {
    }
}

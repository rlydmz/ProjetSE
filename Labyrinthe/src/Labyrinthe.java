public class Labyrinthe {

    private static int height = 16;
    private static int width = 16;

    private static Labyrinthe ourInstance = new Labyrinthe();

    public static Labyrinthe getInstance() {
        return ourInstance;
    }

    private Labyrinthe() {
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

}

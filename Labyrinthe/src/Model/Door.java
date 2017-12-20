package Model;


public class Door {
    private Vertex position;
    boolean isOpen = false;

    public Door (int x, int y){
        position = new Vertex(x,y);
    }

    public Vertex getPosition() {
        return position;
    }

    public void setPosition(Vertex position) {
        this.position = position;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}

package Model;

import Model.Labyrinthe.Directions;

public class Vertex {

    private int x;
    private int y;
    private int nbr;

    public Vertex(int x, int y){
        this.x = x;
        this.y = y;
        this.nbr = 0;
    }

    public Vertex(int xt, int yt, int i) {
        this.x = xt;
        this.y = yt;
        this.nbr=i;
    }

    public boolean equals(Object v){
        Vertex vertex = (Vertex)v;
        if(vertex.getX() == x && vertex.getY() == y)
            return true;
        return false;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vertex{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int i) {
        this.nbr=nbr;
        
    }

    public boolean inBorders(Directions dir) {
        // TODO Auto-generated method stub
        switch(dir) {
        case NORTH: if(getY() == 0)
                        return false;
        case SOUTH: if(getY() == 15)
                        return false;
        case EAST: if(getX() == 15)
                        return false;
        case WEST: if(getX() == 0)
                        return false;
        }
        return true;
    }
}

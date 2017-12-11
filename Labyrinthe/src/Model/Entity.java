package Model;

import java.util.Random;

public class Entity {

    private Vertex position;

    public Entity() {
        position = new Vertex(0,0,0);
    }

    public Vertex getPosition() {
        return position;
    }

    public void setPosition(int x,int y) {
        position.setX(x);
        position.setY(y);
    }

    public void startPosition() {
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(16);
        int randomY = randomGenerator.nextInt(16);
        Vertex v = new Vertex(randomX,randomY);
        this.setPosition(randomX, randomY);
    }

}

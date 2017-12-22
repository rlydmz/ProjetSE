package Model;

import java.util.Random;

public class Entity {

    private Vertex position;

    /**
     * Contructeur de la classe Entity
     */
    public Entity() {
        position = new Vertex(0,0,0);
    }

    /**
     * Retourne la position sous la forme d'un objet de type Vertex de la postion de l'entite
     * @return Un objet de type Vertex
     */
    public Vertex getPosition() {
        return position;
    }

    /**
     * Fixe la position en x et en y du Vertex
     * @param x la position horizontale du Vertex
     * @param y la position verticale du Vertex
     */
    public void setPosition(int x,int y) {
        position.setX(x);
        position.setY(y);
    }

    /**
     * Gere et fixe aleatoirement la position horizontale et verticale du Vertex
     */
    public void startPosition() {
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(16);
        int randomY = randomGenerator.nextInt(16);
        Vertex v = new Vertex(randomX,randomY);
        this.setPosition(randomX, randomY);
    }

}

package Model;

import static Model.Labyrinthe.Directions.*;

public class BadGuy extends Entity{

    /**
     * Constructeur de la classe BadGuy
     */
    public BadGuy(){
        super();
    }

    /**
     * Déplace l'ennemi dans une direction
     * @param laby L'instance d'un objet Labyrinthe
     * @param dir Une direction, appartenant à l'enumeration Directions
     */
    public void move(Labyrinthe laby, Labyrinthe.Directions dir){
        Vertex oldPosition = getPosition();
        switch (dir){
            case EAST:
                if(getPosition().getX() < 15 && !laby.isWall(oldPosition, dir)){
                    setPosition(oldPosition.getX()+1, oldPosition.getY());
                }
                break;
            case WEST:
                if(getPosition().getX() > 0 && !laby.isWall(oldPosition, dir)){
                    setPosition(oldPosition.getX()-1, oldPosition.getY());
                }
                break;
            case NORTH:
                if(getPosition().getY() > 0 && !laby.isWall(oldPosition, dir)){
                    setPosition(oldPosition.getX(), oldPosition.getY()-1);
                }
                break;
            case SOUTH:
                if(getPosition().getY() < 15 && !laby.isWall(oldPosition, dir)){
                    setPosition(oldPosition.getX(), oldPosition.getY()+1);
                }
                break;
        }
    }

}

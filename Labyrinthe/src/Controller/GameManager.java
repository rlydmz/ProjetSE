package Controller;

import Model.BadGuy;
import Model.Candy;
import Model.Labyrinthe;
import Model.Vertex;
import View.View;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

public class GameManager {

    private Labyrinthe laby;
    private View view;
    private boolean gameOver = false;

    public GameManager(Labyrinthe laby, View view){
        this.laby = laby;
        this.view = view;
    }

    public void HandleGame(){
        manageEatenCandy();
        manageExit();
        manageEnnemies();
    }

    public void manageEatenCandy(){
        HashSet<Candy> candies = laby.getCandies();
        Iterator i = candies.iterator();
        while (i.hasNext()){
            Candy c = (Candy)i.next();
            if(laby.getPackman().getPosition().equals(c.getPosition())){
                laby.removeCandy(c);
                break;
            }
        }
    }

    public void manageEnnemies(){
        HashSet<BadGuy> candies = laby.getBadGuyArmy();
        Iterator i = candies.iterator();
        while (i.hasNext()){
            BadGuy badGuy = (BadGuy) i.next();
            if(laby.getPackman().getPosition().equals(badGuy.getPosition()) && gameOver == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("YOU LOSE");
                alert.setHeaderText(null);
                alert.setContentText("Well done, you have lost !");

                System.exit(0);
            }
        }
    }

    public void manageExit(){
        if (laby.getPackman().getPosition().equals(laby.GetExit().getPosition()) && canPassDoor() && gameOver == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("YOU WON");
            alert.setHeaderText(null);
            alert.setContentText("Well done, you reached the Exit !");

            alert.show();
            gameOver=true;
        }
    }

    public boolean canPassDoor(){
        HashSet<Candy> candies = laby.getCandies();
        if(candies.size() == 0)
            return true;
        else
            return false;
    }

    public void movebadGuy(Labyrinthe laby, BadGuy badGuy){
        System.out.println(badGuy.getPosition());
        Vertex vertex = badGuy.getPosition();
        for(Labyrinthe.Directions dir : Labyrinthe.Directions.values()){
            Vertex next = laby.getG().getVertexByDir(vertex, dir);
            if(laby.isConnected(vertex, next) && (next.getNbr()==vertex.getNbr()-1)){
                badGuy.move(laby, dir);
            }
        }
    }

}

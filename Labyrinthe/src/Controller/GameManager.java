package Controller;

import Model.BadGuy;
import Model.Candy;
import Model.Labyrinthe;
import Model.Vertex;
import View.View;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import static Model.Labyrinthe.Directions.*;

public class GameManager {

    private Labyrinthe laby;
    private View view;
    private Stage stage;
    private boolean gameOver = false;
    private boolean goNORTH,goSOUTH,goWEST,goEAST;

    /**
     * Constructeur de la classe GameManager
     * @param laby L'instance d'un objet Labyrinthe
     * @param view L'instance d'un objet View
     * @param stage L'instance d'un objet Stage, c'est a dire le contexte dans lequel on execute l'application
     */
    public GameManager(Labyrinthe laby, View view, Stage stage){
        this.laby = laby;
        this.view = view;
        this.stage = stage;
    }

    /**
     * Gere le Timer de l'application ainsi que les differentes autres fonctions de GameManagement
     * @param stage L'instance d'un objet Stage, c'est à dire le contexte dans lequel on execute l'application
     */
    public void HandleGame(Stage stage){
        manageMovements(stage);
    }

    /**
     * Gere la pression des touches au clavier et le déplacement du joeur
     * @param stage L'instance d'un objet Stage, c'est a dire le contexte dans lequel on execute l'application
     */
    public void manageMovements(Stage stage){
        view.getInstance().getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {


                if (event.getCode() == KeyCode.UP) {
                    goNORTH = true;
                    System.out.println("UP");
                    System.out.println("getx : "+ Labyrinthe.getInstance().getPackman().getPosition().getX());
                    System.out.println("gety : "+ Labyrinthe.getInstance().getPackman().getPosition().getY());
                }

                if (event.getCode() == KeyCode.DOWN) {
                    goSOUTH = true;
                    System.out.println("DOWN");
                    System.out.println("getx : "+ Labyrinthe.getInstance().getPackman().getPosition().getX());
                    System.out.println("gety : "+ Labyrinthe.getInstance().getPackman().getPosition().getY());
                }

                if (event.getCode() == KeyCode.LEFT) {
                    goWEST = true;
                    System.out.println("LEFT");
                    System.out.println("getx : "+ Labyrinthe.getInstance().getPackman().getPosition().getX());
                    System.out.println("gety : "+ Labyrinthe.getInstance().getPackman().getPosition().getY());
                }

                if (event.getCode() == KeyCode.RIGHT){
                    goEAST = true;
                    System.out.println("RIGHT");
                    System.out.println("getx : "+ Labyrinthe.getInstance().getPackman().getPosition().getX());
                    System.out.println("gety : "+ Labyrinthe.getInstance().getPackman().getPosition().getY());
                }
            }
        });
        view.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNORTH = false; break;
                    case DOWN:  goSOUTH = false; break;
                    case LEFT:  goWEST = false; break;
                    case RIGHT: goEAST  = false; break;
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {

            int cpt = 0;

            @Override
            public void handle(long now) {
                int x = laby.getPackman().getPosition().getX();
                int y = laby.getPackman().getPosition().getY();
                Vertex vTemp = laby.getPackman().getPosition();

                if (goNORTH) {
                    if(y>0 && !laby.isWall(vTemp,NORTH)) {
                        goNORTH = false;
                        y -= 1;
                    }
                }
                if (goSOUTH){
                    if(y<15 && !laby.isWall(vTemp,SOUTH)) {
                        goSOUTH = false;
                        y += 1;
                    }
                }
                if (goEAST){
                    if(x<15 && !laby.isWall(vTemp,EAST)) {
                        goEAST = false;
                        x += 1;
                    }
                }
                if (goWEST){
                    if(x>0 && !laby.isWall(vTemp,WEST)) {
                        goWEST = false;
                        x -= 1;
                    }
                }

                laby.getPackman().setPosition(x,y);
                view.getInstance().getPane().getChildren().remove(view.getInstance().getPlayerView());
                view.getInstance().drawNiceGuy(laby.getPackman().getPosition());

                if(cpt == 60){
                    BadGuy badGuy = laby.getBadGuyArmy().iterator().next();
                    movebadGuy(laby, badGuy);
                    cpt = 0;
                }

                manageEatenCandy();
                manageExit();
                manageEnnemies();

                view.getInstance().drawEntities(laby);
                cpt++;
            }
        };
        timer.start();
    }

    /**
     * Gere l'apparition et la disparition des bonbons
     */
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

    /**
     * Gere l'apparition, la disparition des ennemis et la mort du joueur dans le cas d'un contact avec l'un d'eux
     */
    public void manageEnnemies(){
        HashSet<BadGuy> candies = laby.getBadGuyArmy();
        Iterator i = candies.iterator();
        while (i.hasNext()){
            BadGuy badGuy = (BadGuy) i.next();
            if(laby.getPackman().getPosition().equals(badGuy.getPosition()) && !gameOver){
                gameOver=true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("YOU LOSE");
                alert.setHeaderText(null);
                alert.setContentText("Well done, you have lost !");

                System.exit(0);
            }
        }
    }

    /**
     * Gere l'accomplissement du niveau, c'est a dire quand le joueur arrive a la porte de sortie
     */
    public void manageExit(){
        if (laby.getPackman().getPosition().equals(laby.GetExit().getPosition()) && !gameOver ){
            gameOver=true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("YOU WON");
            alert.setHeaderText(null);
            alert.setContentText("Well done, you reached the Exit !");

            alert.show();

        }
    }

    /**
     * Renvoie la possibilite ou non de passer a travers une porte. Indirectement, dit si une porte est ouverte ou fermee
     * @return true si le joueur peut passer la porte, false sinon
     */
    public boolean canPassDoor(){
        HashSet<Candy> candies = laby.getCandies();
        if(candies.size() == 0)
            return true;
        return false;
    }

    /**
     * Deplace l'ennemi selon l'algorithme de Manhattan
     * @param laby L'instance d'un objet Labyrinthe
     * @param badGuy L'instance d'un objet BadGuy
     */
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

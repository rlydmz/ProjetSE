package View;


import Controller.GameManager;
import Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static Model.Labyrinthe.Directions.*;

public class View extends Application {

    private static View ourInstance = null;
    private Scene scene;
    private Pane pane;
    private ImageView playerView;
    private ImageView exitView;
    private GameManager gameManager;
    private boolean goNORTH,goSOUTH,goWEST,goEAST;


    static final int SPAN = 4;
    static final int WALL = 2;
    static final int CELL = 9;
    private static final Paint WALLCOLOR = Color.BURLYWOOD;
    private static final Paint SCENECOLOR = Color.WHITE;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Labyrinthe laby = Labyrinthe.getInstance();
        gameManager = new GameManager(laby, this, primaryStage);

        pane = new Pane();

        drawFrame(primaryStage, 16, 16);

        drawNiceGuy(laby.getPackman().getPosition());
        drawExit(Labyrinthe.getInstance().GetExit().getPosition());
        drawBadGuyArmy(laby.getBadGuyArmy());
        drawCandies(laby.getCandies());
        //drawPath(laby.getG());

        System.out.println(Labyrinthe.getInstance().getG().edgeSet());
        Labyrinthe.getInstance().getG().hasXsEqualToLimits();
        drawAllWalls(Labyrinthe.getInstance());

        primaryStage.show();


        gameManager.HandleGame(primaryStage);
    }

    public Scene getScene() {
        return scene;
    }


    public void drawFrame(Stage stage, int nbrX, int nbrY){
        scene = new Scene(pane,
                ((WALL + CELL) * nbrX + WALL) * SPAN,
                ((WALL + CELL) * nbrY + WALL) * SPAN
        );
        scene.setFill(SCENECOLOR);

        Rectangle square;
        stage.setScene(scene);

        square = new Rectangle(0, 0,
                SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALLCOLOR);
        pane.getChildren().add(square);

        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)),
                SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALLCOLOR);
        pane.getChildren().add(square);

        square = new Rectangle(0, 0,
                WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALLCOLOR);
        pane.getChildren().add(square);

        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0,
                WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALLCOLOR);
        pane.getChildren().add(square);

        for(int x = 0; x < nbrX-1; ++x){
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for(int y = 0; y < nbrY-1; ++y){
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY,
                        WALL * SPAN,
                        WALL * SPAN);
                square.setFill(WALLCOLOR);
                pane.getChildren().add(square);
            }
        }

    }


    public void drawWall(int xSource, int ySource, int xDest, int yDest, Paint color){
        int x=0, y=0, xspan=0, yspan=0;
        if(ySource == yDest){
            x = (WALL + xSource * (WALL + CELL)) * SPAN;
            y = (ySource * (WALL + CELL)) * SPAN;
            //x = ((WALL + CELL) + (WALL + CELL) * ((int)(xSource+xDest)/2)) * SPAN;
            //y = (WALL + ySource * (WALL + CELL)) * SPAN;
            xspan = CELL * SPAN;
            yspan = WALL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
        else if(xSource == xDest){
            x = (xSource * (WALL + CELL)) * SPAN;
            y = (WALL + ySource * (WALL + CELL)) * SPAN;
            //y = ((WALL + CELL) + (WALL + CELL) * ((int)(ySource+yDest)/2)) * SPAN;
            xspan = WALL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
    }

    public void drawAllWalls(Labyrinthe laby){
        Set<Edge> set = laby.getG().edgeSet();
        Iterator i = set.iterator();
        while(i.hasNext()){
            Edge e = (Edge)i.next();
            if(e.getTarget().getX() == 0 || e.getTarget().getX() == 15){
                if(laby.isWall(e.getTarget(), EAST))
                    drawWall(e.getTarget().getX()+1,
                            e.getTarget().getY(),
                            e.getTarget().getX()+1,
                            e.getTarget().getY()+1,
                            WALLCOLOR);
                if(laby.isWall(e.getTarget(), WEST))
                    drawWall(e.getTarget().getX(),
                            e.getTarget().getY(),
                            e.getTarget().getX(),
                            e.getTarget().getY()+1,
                            WALLCOLOR);
                if(laby.isWall(e.getTarget(), NORTH))
                    drawWall(e.getTarget().getX(),
                            e.getTarget().getY(),
                            e.getTarget().getX()+1,
                            e.getTarget().getY(),
                            WALLCOLOR);
                if(laby.isWall(e.getTarget(), SOUTH))
                    drawWall(e.getTarget().getX(),
                            e.getTarget().getY()+1,
                            e.getTarget().getX()+1,
                            e.getTarget().getY()+1,
                            WALLCOLOR);
            }
            else{
                if(laby.isWall(e.getSource(), EAST))
                    drawWall(e.getSource().getX()+1,
                            e.getSource().getY(),
                            e.getSource().getX()+1,
                            e.getSource().getY()+1,
                            WALLCOLOR);
                if(laby.isWall(e.getSource(), WEST))
                    drawWall(e.getSource().getX(),
                            e.getSource().getY(),
                            e.getSource().getX(),
                            e.getSource().getY()+1,
                            WALLCOLOR);
                if(laby.isWall(e.getSource(), NORTH))
                    drawWall(e.getSource().getX(),
                            e.getSource().getY(),
                            e.getSource().getX()+1,
                            e.getSource().getY(),
                            WALLCOLOR);
                if(laby.isWall(e.getSource(), SOUTH))
                    drawWall(e.getSource().getX(),
                            e.getSource().getY()+1,
                            e.getSource().getX()+1,
                            e.getSource().getY()+1,
                            WALLCOLOR);
            }


        }
    }


    public void drawNiceGuy(Vertex v){

        Image image = new Image(getClass().getResource("../images/player.png").toExternalForm());
        playerView = new ImageView(image);
        pane.getChildren().add(playerView);
        playerView.setX(v.getX()*((WALL+CELL)*SPAN) + (WALL*SPAN));
        playerView.setY(v.getY()*((WALL+CELL)*SPAN) + (WALL*SPAN));

    }

    public void drawExit(Vertex v){
        Image image = new Image(getClass().getResource("../images/door_open.png").toExternalForm());
        exitView = new ImageView(image);
        pane.getChildren().add(exitView);
        exitView.setX(v.getX()*((WALL+CELL)*SPAN) + (WALL*SPAN));
        exitView.setY(v.getY()*((WALL+CELL)*SPAN) + (WALL*SPAN));
    }

    public void drawBadGuyArmy(HashSet<BadGuy> badGuyArmy){
        Image image = new Image(getClass().getResource("../images/bad.png").toExternalForm());
        Iterator i = badGuyArmy.iterator();
        while(i.hasNext()){
            BadGuy badGuy = (BadGuy)i.next();
            ImageView badGuyView = new ImageView(image);
            pane.getChildren().add(badGuyView);
            badGuyView.setX(badGuy.getPosition().getX()*((WALL+CELL)*SPAN) + (WALL*SPAN));
            badGuyView.setY(badGuy.getPosition().getY()*((WALL+CELL)*SPAN) + (WALL*SPAN));
        }
    }

    public ImageView getPlayerView() {
        return playerView;
    }

    public void drawCandies(HashSet<Candy> candies){

        Iterator i = candies.iterator();
        Image image;
        while(i.hasNext()){
            Candy candy = (Candy)i.next();
            switch (candy.getType()){
                case 1:
                    image = new Image(getClass().getResource("../images/candy-1.png").toExternalForm());
                    break;
                case 2:
                    image = new Image(getClass().getResource("../images/candy-2.png").toExternalForm());
                    break;
                case 3:
                    image = new Image(getClass().getResource("../images/candy-3.png").toExternalForm());
                    break;
                case 4:
                    image = new Image(getClass().getResource("../images/candy-4.png").toExternalForm());
                    break;
                default:
                    image = new Image(getClass().getResource("../images/candy-1.png").toExternalForm());
                    break;
            }

            ImageView candyView = new ImageView(image);
            pane.getChildren().add(candyView);
            candyView.setX(candy.getPosition().getX()*((WALL+CELL)*SPAN) + (WALL*SPAN));
            candyView.setY(candy.getPosition().getY()*((WALL+CELL)*SPAN) + (WALL*SPAN));
        }
    }


    public void drawPath(Graph g){
        Set<Edge> set = g.edgeSet();
        Iterator i = set.iterator();
        int red = 20;
        int blue = 0;
        int cpt = 0;
        while(i.hasNext()){
            Edge e = (Edge)i.next();
            drawCell(e.getTarget().getX(),
                    e.getTarget().getY(),
                    Color.rgb(red, 0,blue));
            Text t = new Text((e.getTarget().getX()*(WALL+CELL) + WALL)*SPAN, (e.getTarget().getY()*(WALL+CELL) + WALL)*SPAN, Integer.toString(e.getSource().getNbr()));
            pane.getChildren().add(t);
            if(red == 200)
                blue++;
            else
            red++;
            cpt++;
        }
   }

        public void drawCell(int xSource, int ySource, Paint color){
            int x=0, y=0, xspan=0, yspan=0;
            x = (WALL + xSource * (WALL + CELL)) * SPAN;
            y = (WALL + ySource * (WALL + CELL)) * SPAN;
            xspan = CELL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }


    public static View getInstance() {
        if(ourInstance == null ) {
            ourInstance = new View();
        }
        return ourInstance;
    }

    public void drawEntities(Labyrinthe laby){
        for (int i = 0; i < pane.getChildren().size(); i++) {
            if(pane.getChildren().get(i).getClass().equals(ImageView.class))
                pane.getChildren().remove(i);
        }
        drawNiceGuy(laby.getPackman().getPosition());
        drawExit(laby.GetExit().getPosition());
        drawBadGuyArmy(laby.getBadGuyArmy());
        drawCandies(laby.getCandies());
    }

    public Pane getPane(){
        return pane;
    }


}

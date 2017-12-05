package View;


import Model.Labyrinthe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class View extends Application {

    private static View ourInstance = null;
    private Scene scene;
    private Pane pane;
    private ImageView playerView;

    static final int SPAN = 4;
    static final int WALL = 2;
    static final int CELL = 9;
    private static final Paint WALLCOLOR = Color.BURLYWOOD;
    private static final Paint SCENECOLOR = Color.WHITE;


    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane();

        drawFrame(primaryStage, 16, 16);

        drawNiceGuy(2, 0);

        primaryStage.show();
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

    public void drawNiceGuy(double x, double y){

        Image image = new Image(getClass().getResource("../images/player.png").toExternalForm());
        playerView = new ImageView(image);
        pane.getChildren().add(playerView);
        playerView.setX(x*((WALL+CELL)*SPAN) + (WALL*SPAN));
        playerView.setY(y*((WALL+CELL)*SPAN) + (WALL*SPAN));

    }

    public static View getInstance() {
        if(ourInstance == null ) {
            ourInstance = new View();
        }
        return ourInstance;
    }

}

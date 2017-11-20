package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends Application {

    private Scene scene;
    private StackPane stackPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stackPane = new StackPane();
        scene = new Scene(stackPane, 300, 300);
        primaryStage.setTitle("Labyrinthe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

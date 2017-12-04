package View;


import Model.Labyrinthe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends Application {

    private static View ourInstance = null;
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


    
    public static View getInstance() {
        if(ourInstance == null ) {
            ourInstance = new View();
        }
        return ourInstance;
    }

}

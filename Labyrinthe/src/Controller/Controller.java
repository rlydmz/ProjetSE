package Controller;

import javafx.stage.Stage;
import View.View;
import Model.*;

public class Controller {

	private static Controller instance = new Controller();
	private View view;
	private Labyrinthe laby;

	private Controller() {
		laby = Labyrinthe.getInstance();
		view = View.getInstance();
		Spawner spawner = new Spawner(laby);
		spawner.HandleSpawning();
		//laby.GetExit().startPosition();
		Vertex v = laby.GetExit().getPosition();
		laby.getG().addVertex(v);
		laby.buildRandomPath(v);
	}

	public static Controller getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) throws Exception {
		view.start(primaryStage);
	};

}
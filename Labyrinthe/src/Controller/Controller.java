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

	/**
	 * Retourne l'instance unique d'un objet Controller
	 * @return Une instance unique de notre Controller
	 */
	public static Controller getInstance() {
		return instance;
	}

	/**
	 *
	 * @param primaryStage L'instance d'un objet Stage, c'est a dire le contexte dans lequel on execute l'application
	 * @throws Exception Renvoie une Exception si une probleme survient
	 */

	public void start(Stage primaryStage) throws Exception {
		view.start(primaryStage);
	};

}
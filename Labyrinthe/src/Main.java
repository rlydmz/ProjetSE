import Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	static Controller controller;
	
	public static void main(String[] args) {
		controller = Controller.getInstance();
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		controller.start(stage);
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}
	
}
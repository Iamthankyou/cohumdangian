package com.duy;

import com.duy.controller.Game;
import com.duy.controller.GameImpl;
import com.duy.utils.Constants;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage window) throws Exception {
//		GameController gameController = new GameController(window);
		Game game = new GameImpl(window);
		
		window.setTitle("Chess");
		window.setWidth(Constants.maxWidth);
		window.setHeight(Constants.maxHeight);

		game.start();
//        window.setScene(new Scene(gameController.getPlay()));

		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

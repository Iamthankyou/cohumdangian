package com.duy.view;

import java.net.URL;

import com.duy.controller.GameController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class Menu2 extends GridPane{
	private Button onePlayer,twoPlayer;
	private Image one,two,background;
	private URL url ;
	
	public Menu2 (GameController controller){
		url = getClass().getResource("/com/duy/images/");
		one = new Image(url+"NguoiChoi1.png",301,85,true,true);
		two = new Image(url+"NguoiChoi2.png",301,85,true,true);
		background = new Image(url+"menuBackground.png");
		
		onePlayer = new Button("",new ImageView(one));
		twoPlayer = new Button("",new ImageView(two));
		BackgroundImage menuBackground = new BackgroundImage(background,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
		setBackground(new Background(menuBackground));
        getStylesheets().add(getClass().getResource("menuStyle.css").toExternalForm());
    	add(onePlayer,10,10);
    	add(twoPlayer,10,11);
    	
    	onePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	controller.setTwoPlayer(false);
            	controller.showBoard();
            }
        });
    	
    	twoPlayer.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			controller.setTwoPlayer(true);
    			controller.showBoard();
    		}
    	});
    	setVgap(20);
        setHgap(6);
	}
}

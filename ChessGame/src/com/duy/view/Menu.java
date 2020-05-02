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

public class Menu extends GridPane{
	private Button chonHum,chonTom,luatChoi;
	private Image cHum,cTom,background,luatChoiImg;
	private URL url ;
	
	public Menu (GameController controller){
		url = getClass().getResource("/com/duy/images/");
		cHum = new Image(url+"chonHum.png",301,85,true,true);
		cTom = new Image(url+"chonTom.png",301,85,true,true);
		luatChoiImg = new Image(url+"luatChoi.png",301,85,true,true);
		background = new Image(url+"menuBackground.png");
		
		chonHum = new Button("",new ImageView(cHum));
		chonTom = new Button("",new ImageView(cTom));
		luatChoi = new Button("",new ImageView(luatChoiImg));
		BackgroundImage menuBackground = new BackgroundImage(background,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
		setBackground(new Background(menuBackground));
        getStylesheets().add(getClass().getResource("menuStyle.css").toExternalForm());
    	add(chonHum,10,9);
    	add(chonTom,10,10);
    	add(luatChoi,10,11);
    	
    	chonHum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	controller.setIsHum(true);
            	controller.showMenu2();
            }
        });
    	
    	chonTom.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			controller.setIsHum(false);
    			controller.showMenu2();
    		}
    	});
    	
    	luatChoi.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			controller.showRoleGame();
    		}
    	});
    	setVgap(20);
        setHgap(6);
	}
}

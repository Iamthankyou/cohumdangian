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

public class EndGame extends GridPane{
	private Button winner,home;
	private Image humThang,tomThang,homeImg,background;
	private URL url ;
	
	public EndGame (GameController controller){
		url = getClass().getResource("/com/duy/images/");
		humThang = new Image(url+"humThang.png",301,85,true,true);
		tomThang = new Image(url+"tomThang.png",301,85,true,true);
		homeImg = new Image(url+"choiLai.png",301,85,true,true);
		background = new Image(url+"menuBackground.png");
		
		winner = new Button("",new ImageView(humThang));
		home = new Button("",new ImageView(homeImg));
		BackgroundImage menuBackground = new BackgroundImage(background,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
		setBackground(new Background(menuBackground));
        getStylesheets().add(getClass().getResource("menuStyle.css").toExternalForm());
    	add(winner,10,10);
    	add(home,10,11);
    	
    	winner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	controller.showBoard();
            }
        });
    	
    	home.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			controller.showHome();
    		}
    	});
    	setVgap(20);
        setHgap(6);
	}
	
	public void setWinner(boolean isHum) {
		if (isHum) {
			winner.setGraphic(new ImageView(humThang));
		}
		else {
			winner.setGraphic(new ImageView(tomThang));
		}
	}
}

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

public class RoleGame extends GridPane{
	private Button backHome;
	private Image backHomeImg,background;
	private URL url ;
	
	public RoleGame (GameController controller){
		url = getClass().getResource("/com/duy/images/");
		backHomeImg = new Image(url+"troVe.png",301,85,true,true);
		background = new Image(url+"luatChoiBackground.jpg");
		
		backHome = new Button("",new ImageView(backHomeImg));
		BackgroundImage menuBackground = new BackgroundImage(background,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
		setBackground(new Background(menuBackground));
        getStylesheets().add(getClass().getResource("menuStyle.css").toExternalForm());
    	add(backHome,10,25);
    	
    	backHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	controller.showHome();
            }
        });
    	
    	setVgap(20);
        setHgap(6);
	}
}

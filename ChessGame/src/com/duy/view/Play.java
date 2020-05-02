package com.duy.view;

import java.net.URL;
import java.util.List;

import com.duy.controller.GameController;
import com.duy.entity.Element;
import com.duy.entity.Elements;
import com.duy.entity.Point;
import com.duy.utils.Constants;

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

public class Play extends GridPane{
	private Button boardView[][];
	private BackgroundImage backgroundImage;
	private URL url ;
	private Image background, turnImg, homeImg, prevImg, nextImg, humImg, tomImg;
	private GameController controller;
	private Button turn,home,prev,next,hum,tom;
	
	public Play(GameController controller){
		boardView = new Button[5][7];
		url = getClass().getResource("/com/duy/images/");
		background  = new Image(url+"board03.jpg");
		backgroundImage = new BackgroundImage(background,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	
		this.controller = controller;
	}
	
	
	public void footer() {
		turn = new Button(); home = new Button(); prev = new Button(); next = new Button(); hum = new Button(); tom = new Button();
		turnImg = new Image(url+"luot.png",Constants.squareSize,Constants.squareSize,true,true); homeImg = new Image(url+"home.png",Constants.squareSize,Constants.squareSize,true,true); prevImg = new Image(url+"prev.png",Constants.squareSize,Constants.squareSize,true,true); nextImg = new Image(url+"next.png",Constants.squareSize,Constants.squareSize,true,true); humImg = new Image(url+"Hum02.png",Constants.squareSize,Constants.squareSize,true,true); tomImg = new Image(url+"BTom01.png",Constants.squareSize,Constants.squareSize,true,true);
		turn.setGraphic(new ImageView(turnImg)); home.setGraphic(new ImageView(homeImg)); prev.setGraphic(new ImageView(prevImg)); next.setGraphic(new ImageView(nextImg)); hum.setGraphic(new ImageView(humImg)); tom.setGraphic(new ImageView(tomImg));
		turn.setMinSize(40, 40); home.setMinSize(40, 40); prev.setMinSize(40, 40); next.setMinSize(40, 40); hum.setMinSize(40, 40); tom.setMinSize(40, 40);
		
		add(turn, 1, 8);
		add(hum,2,8);
		add(home,3,8);
		add(prev,4,8);
		add(next,5,8);
		
		home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	controller.showHome();
            }
        });
		
		prev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.showPrev();
			}
		});
		
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.showNext();
			}
		});
	}
	
	public void setTurnHum() {
		hum.setGraphic(new ImageView(humImg));
	}
	
	public void setTurnTom() {
		hum.setGraphic(new ImageView(tomImg));
	}
	
	public void set() {
		// Extend GridPane
        setBackground(new Background(backgroundImage));
        drawBoard();
        
    	for (int i=0; i<5; i++) {
			for (int j=0; j<7; j++) {
				boardView[i][j].setOnAction(new MoveEvent(i,j,controller));
			}
		}
    	
    	footer();
 
	}

	public void wayHover(List<Element> moves) {
		resetStyle();
		for (Element e: moves) {
				Point x = e.corr();
				boardView[x.getY()][x.getX()].setStyle("-fx-background-color: #69ff69;");
		}
	}
	
	public void resetStyle() {
		for (int i=0; i<7; i++) {
			for (int j=0; j<5; j++) {
				boardView[j][i].setStyle("");
			}
		}
	}
	
	public void drawBoard() {
		for (int i=0; i<5; i++) {
			for (int j=0; j<7; j++) {
				boardView[i][j] = new Button();
				boardView[i][j].setMinSize(40, 40);
				if (i==0 && j==0 || i==0 && j==1|| i==1 && j==0 || i==3 && j==0 || i==4 && j==0 || i==4 && j==1 )
					continue;
				add(boardView[i][j],i+1,j+1);
				getStylesheets().add(getClass().getResource("playStyle.css").toExternalForm());
				setVgap(40);
				setHgap(25);
			}
		}
	}
	
	public void updateBoard(Elements elements) {
		for (int i=0; i<7; i++) {
			for (int j=0; j<5; j++) {
				Element element = elements.getElement(new Point(i,j));
				boardView[j][i].setGraphic(new ImageView(element.getImage()));
			}
		}
	}
	
}

package com.duy.controller;

import java.util.List;

import com.duy.entity.BTom;
import com.duy.entity.Element;
import com.duy.entity.Elements;
import com.duy.entity.Empty;
import com.duy.entity.Hum;
import com.duy.entity.Point;
import com.duy.entity.Stop;
import com.duy.entity.Tom;
import com.duy.model.AIVer2;
import com.duy.model.ElementsManager;
import com.duy.view.EndGame;
import com.duy.view.Menu;
import com.duy.view.Menu2;
import com.duy.view.Play;
import com.duy.view.RoleGame;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {
//	private Elements elements;
	
	private ElementsManager elementsManager;
	
	private Play play;
	private Menu menu;
	private Menu2 menu2;
	private EndGame endGame;
	private RoleGame roleGame;
	
	private Stage window;
	
	private boolean isHum;
	private boolean isTwoPlayer;
	private List<Element> moves;
	private Point moveOne;
	private boolean activeFirst;
	private Game gameModel;
	
//	private AI ai;
	private AIVer2 ai; 
	
	public GameController(Stage window, Game gameModel){
		this.window = window;
		this.gameModel = gameModel;
	}
	
	public void showBoard() {
		play = new Play(this);
		window.setScene(new Scene(play));
		
		play.set();
		play.updateBoard(elementsManager.getElements());
		
		if (isHum) {
			play.setTurnHum();
		}
		else {
			play.setTurnTom();
		}
	}
	
	public void showHome() {
		elementsManager.resetInstance();
		gameModel.start();
	}
	
	
	public void isButtonActive(int i, int j) {
//		showGame(i,j);
		if (activeFirst == true) {
			Point x = new Point(j,i);
			System.out.println(x);
			Element buttonActive = elementsManager.getElement(x);
			
			if (moves.contains(buttonActive)) {
				elementsManager.move(moveOne,x);
				play.updateBoard(elementsManager.getElements());

				checkGame();
				if (isHum) {
					play.setTurnHum();
				}
				else {
					play.setTurnTom();
				}
			}
			activeFirst = false;
			
			if (!isTwoPlayer) {
				ai.AIMove(elementsManager,isHum);
				isHum = !isHum;
				if (isHum) {
					play.setTurnHum();
				}
				else {
					play.setTurnTom();
				}
				play.updateBoard(elementsManager.getElements());
			}
			
		}
		else {
			play.resetStyle();
			activeFirst = true;
			showWay(new Point(j,i));
		}
	}
	
	public void showGame(int i, int j) {
		if (isTwoPlayer) {
			showGameBi();
		}
		else {
//			showGameUni(i,j);
		}
	}
	
	public void checkGame() {
		if (elementsManager.isGameOver() == 1) {
			System.out.println("Tom thang");
			showEndGame();
			endGame.setWinner(false);
		}
		else if (elementsManager.isGameOver() == -1){
			System.out.println("Hum thang");
			showEndGame();
			endGame.setWinner(true);
		}
		
	}
	
	public void showGameBi() {
		System.out.println("For AI");
	}
	
	public void showWay(Point x) {
		checkGame();
		if (elementsManager.getElement(x) instanceof Empty || elementsManager.getElement(x) instanceof Stop) {
			activeFirst = false;
			return;
		}
		
		if (isHum) {
			if (elementsManager.getElement(x) instanceof Hum) {
				moveOne = x;
				moves = elementsManager.getElement(x).movesPossible(elementsManager.getMap());
				play.wayHover(moves);
				isHum = !isHum;
			}
			else {
				System.out.println("Luot Hum");
				activeFirst = false;
			}
		}
		else {
			if (elementsManager.getElement(x) instanceof Tom || elementsManager.getElement(x) instanceof BTom) {
				moveOne = x;
				moves = elementsManager.getElement(x).movesPossible(elementsManager.getMap());
				play.wayHover(moves);
				isHum=!isHum;
			}
			else {
				System.out.println("Luot Tom");
				activeFirst = false;
			}
		}
		
	}
	
	public Play getPlay() {
		return play;
	}
	
	public void showMenu() {
		elementsManager = ElementsManager.getInstance();
		ai = new AIVer2(elementsManager);
		activeFirst = false;
		
		
		menu = new Menu(this);
		window.setScene(new Scene(menu));
	}
	
	public void showMenu2() {
		menu2 = new Menu2(this);
		window.setScene(new Scene(menu2));
	}
	
	public void showEndGame() {
		endGame = new EndGame(this);
		window.setScene(new Scene(endGame));
	}
	
	public void showRoleGame() {
		roleGame = new RoleGame(this);
		window.setScene(new Scene(roleGame));
	}
	
	public void showPrev() {
		elementsManager.undo();
		play.updateBoard(elementsManager.getElements());
	}
	
	public void showNext() {
		elementsManager.redo();
		play.updateBoard(elementsManager.getElements());
	}
	
	public Menu2 getMenu2() {
		return menu2;
	}

	public void setMenu2(Menu2 menu2) {
		this.menu2 = menu2;
	}

	public void setIsHum(boolean bool) {
		isHum = bool;
	}

	public Elements getElements() {
		return elementsManager.getElements();
	}

//	public void setElements(Elements elements) {
//		this.elements = elements;
//	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}

	public boolean isHum() {
		return isHum;
	}

	public void setHum(boolean isHum) {
		this.isHum = isHum;
	}


	public boolean isTwoPlayer() {
		return isTwoPlayer;
	}

	public void setTwoPlayer(boolean isTwoPlayer) {
		this.isTwoPlayer = isTwoPlayer;
	}

	public void setPlay(Play play) {
		this.play = play;
	}
	
}

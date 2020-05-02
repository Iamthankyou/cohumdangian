package com.duy.view;

import com.duy.controller.GameController;
import com.duy.entity.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MoveEvent implements EventHandler<ActionEvent>{
	
	private GameController controller;
	private Point point;
	
	MoveEvent(int i, int j, GameController controller){
		point = new Point(i,j);
		this.controller = controller;
	}
	@Override
	public void handle(ActionEvent e) {
		controller.isButtonActive(point.getX(), point.getY());
	}
}

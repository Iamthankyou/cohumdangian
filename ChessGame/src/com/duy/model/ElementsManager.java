package com.duy.model;

import com.duy.entity.Element;
import com.duy.entity.Elements;
import com.duy.entity.Point;

public class ElementsManager {
	private static ElementsManager uniqueInstance= null;
	private Elements elements;
	
	public static ElementsManager getInstance() {
		if (uniqueInstance==null) {
			uniqueInstance = new ElementsManager();
		}
		return uniqueInstance;
	}
	
	private ElementsManager() {
		elements = new Elements();
	}
	
	public void resetInstance() {
		uniqueInstance = null;
	}
	
	public void undo() {
		elements.undo();
	}
	
	public void redo() {
		elements.redo();
	}
	
    public void move(Point x, Point y) {
		elements.move(x, y);
	}

	public void updateMap(Point x, Element e) {
		elements.updateMap(x, e);
	}

	public Element getElement(Point x) {
		return elements.getElement(x);
	}
	
	public int isGameOver() {
		return elements.isGameOver();
	}
	
	public Elements getElements() {
		return elements;
	}
	
	
	public Element[][] getMap() {
		return elements.getMap();
	}

	public void setMap(Element[][] map) {
		elements.setMap(map);
	}
	
}

package com.duy.entity;

import java.util.List;

import javafx.scene.image.Image;

public interface Element {
	public List<Element> movesPossible(Element[][] boardData);
	public Point corr();
	public Image getImage();
	public void setCorr(Point x);
}

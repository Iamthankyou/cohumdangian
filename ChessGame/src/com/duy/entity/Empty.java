package com.duy.entity;

import java.net.URL;
import java.util.List;

import com.duy.utils.Constants;

import javafx.scene.image.Image;

public class Empty implements Element {

	private Point x;
	private URL url = getClass().getResource("/com/duy/images/");
	private Image image = new Image(url+"TrongSuot.png",Constants.squareSize,Constants.squareSize,true,true);
	
	public Empty(Point x) {
		this.x = x;
	}
	
	@Override
	public List<Element> movesPossible(Element[][] boardData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point corr() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public Image getImage() {
		return image;
	}
	
	public void setCorr(Point x) {
		this.x = x;
	}

}

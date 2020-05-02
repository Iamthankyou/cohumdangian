package com.duy.entity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.duy.utils.Constants;

import javafx.scene.image.Image;

public class Tom implements Element {

	private Point x;
	private URL url = getClass().getResource("/com/duy/images/");
	private Image image = new Image(url+"Tot01.png",Constants.squareSize,Constants.squareSize,true,true);
	
	public Tom(Point x) {
		this.x = x;
	}
	
	@Override
	public List<Element> movesPossible(Element[][] boardData) {
		List<Element> moves = new ArrayList<>();
		int i = x.getX();
		int j = x.getY();
		
		if (( i-1>=0  && (i>=3 || j==2)) && boardData[i-1][j] instanceof Empty) {
			moves.add(boardData[i-1][j]);
		}
		
		if ((i+1<=6 && (i>=2 || j==2)) && boardData[i+1][j] instanceof Empty) {
			moves.add(boardData[i+1][j]);
		}

		if ((j-1>=0 && (j==2 || i>=2 || j==3)) && boardData[i][j-1] instanceof Empty) {
			moves.add(boardData[i][j-1]);
		}
		
		if ((j+1<=4 && (j==2 || i>=2 || j==1) ) && boardData[i][j+1] instanceof Empty) {
			moves.add(boardData[i][j+1]);
		}
		
		if (i+1<=6 && j+1 <=4  && (i-j==4||i-j==2||i-j==0||i-j==-2 && i!=1) && boardData[i+1][j+1] instanceof Empty) {
			moves.add(boardData[i+1][j+1]);
		}
		
		if (i-1>=0 && j-1 >=0 &&  (i-j==4||i-j==2||i-j==0||i-j==-2) && boardData[i-1][j-1] instanceof Empty) {
			moves.add(boardData[i-1][j-1]);
		}
		
		if (i-1>=0 && j+1<=4 && (i+j==2|| i+j==4||i+j==6||i+j==8) && boardData[i-1][j+1] instanceof Empty) {
			moves.add(boardData[i-1][j+1]);
		}
		
		if (i+1<=6 && j-1>=0 && ((i+j==2 && i!=1)|| i+j==4||i+j==6||i+j==8) && boardData[i+1][j-1] instanceof Empty) {
			moves.add(boardData[i+1][j-1]);
		}
		return moves;
	}

	@Override
	public Point corr() {
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

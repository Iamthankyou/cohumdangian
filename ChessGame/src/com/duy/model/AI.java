package com.duy.model;

import java.util.List;

import com.duy.entity.BTom;
import com.duy.entity.Element;
import com.duy.entity.Hum;
import com.duy.entity.Tom;

public class AI {
	private ElementsManager elementsManager;
	
	public AI(ElementsManager elementsManager) {
		this.elementsManager = elementsManager;
	}
	
	public void findBestMove(boolean isHum) {
		int max = -9999;
		if (isHum) {
			for (Element[] arr:elementsManager.getMap()) {
				for (Element e:arr) {
					if (e instanceof Hum) {
						System.out.println("Hum");
						List<Element> moves = e.movesPossible(elementsManager.getMap());
						for (Element mov:moves) {
							elementsManager.move(e.corr(), mov.corr());
							int best = minimax(2,e,true);
							elementsManager.undo();
							if (max < best) {
								max = best;
								elementsManager.move(e.corr(), mov.corr());
							}
						}
					}
				}
			}
		}
		else {
			for (Element[] arr:elementsManager.getMap()) {
				for (Element e:arr) {
					System.out.println("Tom");
					if (e instanceof Tom || e instanceof BTom) {
						List<Element> moves = e.movesPossible(elementsManager.getMap());
						for (Element mov:moves) {
							elementsManager.move(e.corr(), mov.corr());
							int best = minimax(2,e,true);
							elementsManager.undo();
							if (max < best) {
								max = best;
							}
						}
					}
				}
			}
		}
		
//		System.out.println("Max");
	}
	
	public int minimax(int depth, Element e, boolean isMax) {
		if (depth == 0) {
			return -evalution(elementsManager.getMap());
		}
		
		List<Element> games = e.movesPossible(elementsManager.getMap());
		if (isMax) {
			int bestMove = -9999;
			for (Element game:games) {
				elementsManager.move(e.corr(), game.corr());
				bestMove = Math.max(bestMove, minimax(depth-1,e,!isMax));
				elementsManager.undo();
			}
			return bestMove;
		}
		else {
			int bestMove = 9999;
			for (Element game:games) {
				elementsManager.move(e.corr(), game.corr());
				bestMove = Math.min(bestMove, minimax(depth-1,e,!isMax));
				elementsManager.undo();
			}
			return bestMove;
		}
		
	}
	
	public int evalution(Element[][] board) {
		int res = 0;
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] instanceof Tom) {
					res-=20;
				}
				if (board[i][j] instanceof BTom) {
					res-=80;
				}
				if (board[i][j] instanceof Hum) {
					res+=100;
				}
			}
		}
		
		return res;
	}

}














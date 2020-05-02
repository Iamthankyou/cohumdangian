package com.duy.model;

import com.duy.entity.BTom;
import com.duy.entity.Element;
import com.duy.entity.Empty;
import com.duy.entity.Hum;
import com.duy.entity.Point;
import com.duy.entity.Stop;
import com.duy.entity.Tom;
import com.duy.utils.Constants;

public class AIVer2 {
	private ElementsManager elementsManager;
	private String[][] board;
	private boolean isHum;
	
	public AIVer2(ElementsManager elementsManager) {
		this.elementsManager = elementsManager;
	}
	
	public void AIMove(ElementsManager elementManager, boolean isHum) {
		this.elementsManager = elementManager;
		this.isHum = isHum;
		init();
		
		for (int i=0; i<7; i++) {
			for (int j=0; j<5; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
		String [][] res = findBestMove(board,0);
		Element[][] newMap = new Element[7][5];
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				newMap[i][j] = new Empty(new Point(i, j));
			}
		}
		// Not board in game
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				newMap[i][j] = new Stop(new Point(i,j));
			}
		}
		
		for (int i=0; i<7; i++) {
			for (int j=0; j<5; j++) {
				if (res[i][j].equals("Hum")) {
					newMap[i][j] = new Hum(new Point(i,j));
				}
				
				if (res[i][j].equals("Tom")) {
					newMap[i][j] = new Tom(new Point(i,j));
				}
				
				if (res[i][j].equals("BTom")) {
					newMap[i][j] = new BTom(new Point(i,j));
				}
				
				if (res[i][j].equals("###")) {
					newMap[i][j] = new Empty(new Point(i,j));
				}
				
				if (res[i][j].equals("XXX")) {
					newMap[i][j] = new Stop(new Point(i,j));
				}
			}
		}
		
		elementsManager.setMap(newMap);
		
	}
	
	private void init() {
		board = new String[7][5];
		Element[][] map = elementsManager.getMap();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] instanceof Hum) {
					board[i][j]="Hum";
				}
				else if (map[i][j] instanceof Tom) {
					board[i][j]="Tom";
				}
				else if (map[i][j] instanceof BTom) {
					board[i][j]="BTom";
				}
				else if (map[i][j] instanceof Stop) {
					board[i][j]="XXX";
				}
				else {
					board[i][j]="###";
				}
			}
		}
	}

	private Point[] allMove(int i, int j, String[][] boardData) {
		Point[] moves = new Point[29];
		int index = 0;

		if (boardData[i][j].equals("Hum")) {
//			System.out.println(i+","+j);
			if ((i - 1 >= 0 && (i >= 3 || j == 2)) && boardData[i - 1][j].equals("###")) {
				moves[index++] = new Point(i - 1, j);
				if (i - 2 >= 0 && (boardData[i - 2][j].equals("Tom") || boardData[i - 2][j].equals("TTom"))) {
					moves[index++] = new Point(i - 1, j);
				}
			}

			if ((i + 1 <= 6 && (i >= 2 || j == 2)) && boardData[i + 1][j].equals("###")) {
				moves[index++] = new Point(i + 1, j);
				if (i + 2 <= 6 && (boardData[i + 2][j].equals("Tom") || boardData[i + 2][j].equals("TTom"))) {
					moves[index++] = new Point(i + 2, j);
				}
			}

			if ((j - 1 >= 0 && (j == 2 || i >= 2 || j == 3)) && boardData[i][j - 1].equals("###")) {
				moves[index++] = new Point(i, j - 1);
				if (j - 2 >= 0 && (boardData[i][j - 2].equals("Tom") || boardData[i][j - 2].equals("TTom"))) {
					moves[index++] = new Point(i, j - 2);
				}
			}

			if ((j + 1 <= 4 && (j == 2 || i >= 2 || j == 1)) && boardData[i][j + 1].equals("###")) {
				moves[index++] = new Point(i, j + 1);
				if (j + 2 <= 4 && (boardData[i][j + 2].equals("Tom") || boardData[i][j + 2].equals("TTom"))) {
					moves[index++] = new Point(i, j + 2);
				}
			}

			if (i + 1 <= 6 && j + 1 <= 4 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2 && i != 1)
					&& boardData[i + 1][j + 1].equals("###")) {
				moves[index++] = new Point(i + 1, j + 1);
				if (i + 2 <= 6 && j + 2 <= 4
						&& (boardData[i + 2][j + 2].equals("Tom") || boardData[i + 2][j + 2].equals("TTom"))) {
					moves[index++] = new Point(i + 2, j + 2);
				}
			}

			if (i - 1 >= 0 && j - 1 >= 0 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2)
					&& boardData[i - 1][j - 1].equals("###")) {
				moves[index++] = new Point(i - 1, j - 1);
				if (i - 2 >= 0 && j - 2 >= 0
						&& (boardData[i - 2][j - 2].equals("Tom") || boardData[i - 2][j - 2].equals("TTom"))) {
					moves[index++] = new Point(i - 2, j - 2);
				}
			}

			if (i - 1 >= 0 && j + 1 <= 4 && (i + j == 2 || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i - 1][j + 1].equals("###")) {
				moves[index++] = new Point(i - 1, j + 1);
				if (i - 2 >= 0 && j + 2 <= 4
						&& (boardData[i - 2][j + 2].equals("Tom") || boardData[i - 2][j + 2].equals("TTom"))) {
					moves[index++] = new Point(i - 2, j + 2);
				}
			}

			if (i + 1 <= 6 && j - 1 >= 0 && ((i + j == 2 && i != 1) || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i + 1][j - 1].equals("###")) {
				moves[index++] = new Point(i + 1, j - 1);
				if (i + 2 <= 6 && j - 2 >= 0
						&& (boardData[i + 2][j - 2].equals("Tom") || boardData[i + 2][j - 2].equals("TTom"))) {
					moves[index++] = new Point(i + 2, j - 2);
				}
			}

		}

		if (boardData[i][j].equals("Tom")) {
			if ((i - 1 >= 0 && (i >= 3 || j == 2)) && boardData[i - 1][j].equals("###")) {
				moves[index++] = new Point(i - 1, j);
			}

			if ((i + 1 <= 6 && (i >= 2 || j == 2)) && boardData[i + 1][j].equals("###")) {
				moves[index++] = new Point(i + 1, j);
			}

			if ((j - 1 >= 0 && (j == 2 || i >= 2 || j == 3)) && boardData[i][j - 1].equals("###")) {
				moves[index++] = new Point(i, j - 1);
			}

			if ((j + 1 <= 4 && (j == 2 || i >= 2 || j == 1)) && boardData[i][j + 1].equals("###")) {
				moves[index++] = new Point(i, j + 1);
			}

			if (i + 1 <= 6 && j + 1 <= 4 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2 && i != 1)
					&& boardData[i + 1][j + 1].equals("###")) {
				moves[index++] = new Point(i + 1, j + 1);
			}

			if (i - 1 >= 0 && j - 1 >= 0 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2)
					&& boardData[i - 1][j - 1].equals("###")) {
				moves[index++] = new Point(i - 1, j - 1);
			}

			if (i - 1 >= 0 && j + 1 <= 4 && (i + j == 2 || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i - 1][j + 1].equals("###")) {
				moves[index++] = new Point(i - 1, j + 1);
			}

			if (i + 1 <= 6 && j - 1 >= 0 && ((i + j == 2 && i != 1) || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i + 1][j - 1].equals("###")) {
				moves[index++] = new Point(i + 1, j - 1);
			}
		}

		if (boardData[i][j].equals("BTom")) {
			if ((i - 1 >= 0 && (i >= 3 || j == 2)) && boardData[i - 1][j].equals("###")) {
				moves[index++] = new Point(i - 1, j);
			}

			if ((i + 1 <= 6 && (i >= 2 || j == 2)) && boardData[i + 1][j].equals("###")) {
				moves[index++] = new Point(i + 1, j);
			}

			if ((j - 1 >= 0 && (j == 2 || i >= 2 || j == 3)) && boardData[i][j - 1].equals("###")) {
				moves[index++] = new Point(i, j - 1);
			}

			if ((j + 1 <= 4 && (j == 2 || i >= 2 || j == 1)) && boardData[i][j + 1].equals("###")) {
				moves[index++] = new Point(i, j + 1);
			}

			if (i + 1 <= 6 && j + 1 <= 4 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2 && i != 1)
					&& boardData[i + 1][j + 1].equals("###")) {
				moves[index++] = new Point(i + 1, j + 1);
			}

			if (i - 1 >= 0 && j - 1 >= 0 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2)
					&& boardData[i - 1][j - 1].equals("###")) {
				moves[index++] = new Point(i - 1, j - 1);
			}

			if (i - 1 >= 0 && j + 1 <= 4 && (i + j == 2 || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i - 1][j + 1].equals("###")) {
				moves[index++] = new Point(i - 1, j + 1);
			}

			if (i + 1 <= 6 && j - 1 >= 0 && ((i + j == 2 && i != 1) || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i + 1][j - 1].equals("###")) {
				moves[index++] = new Point(i + 1, j - 1);
			}

			if ((i - 1 >= 0 && (i >= 3 || j == 2)) && boardData[i - 1][j].equals("Tom")) {
				if (i - 2 >= 0 && boardData[i - 2][j].equals("Hum")) {
					moves[index++] = new Point(i - 2, j);
				}
			}

			if ((i + 1 <= 6 && (i >= 2 || j == 2)) && boardData[i + 1][j].equals("Tom")) {
				if (i + 2 <= 6 && boardData[i + 2][j].equals("Hum")) {
					moves[index++] = new Point(i + 2, j);
				}
			}

			if ((j - 1 >= 0 && (j == 2 || i >= 2)) && boardData[i][j - 1].equals("Tom")) {
				if (j - 2 >= 0 && boardData[i][j - 2].equals("Hum")) {
					moves[index++] = new Point(i, j - 2);
				}
			}

			if ((j + 1 <= 4 && (j == 2 || i >= 2 || j == 1)) && boardData[i][j + 1].equals("Tom")) {
				if (j + 2 <= 4 && boardData[i][j + 2].equals("Hum")) {
					moves[index++] = new Point(i, j + 2);
				}
			}

			if (i + 1 <= 6 && j + 1 <= 4 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2 && i != 1)
					&& boardData[i + 1][j + 1].equals("Tom")) {
				if (i + 2 <= 6 && j + 2 <= 4 && boardData[i + 2][j + 2].equals("Hum")) {
					moves[index++] = new Point(i + 2, j + 2);
				}
			}

			if (i - 1 >= 0 && j - 1 >= 0 && (i - j == 4 || i - j == 2 || i - j == 0 || i - j == -2)
					&& boardData[i - 1][j - 1].equals("Tom")) {
				if (i - 2 >= 0 && j - 2 >= 0 && boardData[i - 2][j - 2].equals("Hum")) {
					moves[index++] = new Point(i - 2, j - 2);
				}
			}

			if (i - 1 >= 0 && j + 1 <= 4 && (i + j == 2 || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i - 1][j + 1].equals("Tom")) {
				if (i - 2 >= 0 && j + 2 <= 4 && boardData[i - 2][j + 2].equals("Hum")) {
					moves[index++] = new Point(i - 2, j + 2);
				}
			}

			if (i + 1 <= 6 && j - 1 >= 0 && ((i + j == 2 && i != 1) || i + j == 4 || i + j == 6 || i + j == 8)
					&& boardData[i + 1][j - 1].equals("Tom")) {
				if (i + 2 <= 6 && j - 2 >= 0 && boardData[i + 2][j - 2].equals("Hum")) {
					moves[index++] = new Point(i + 2, j - 2);
				}
			}

		}
		return moves;
	}

	public String[][] findBestMove(String board[][], int h) {
		String[][] boardCpy = new String[7][5];
		String[][] bestMove = new String[7][5];

		int max = -1000000;

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				
				if (isHum) {
					if (board[i][j].equals("Hum")) {
						Point moves[] = allMove(i, j, board);
						Point move1 = new Point(i, j);
						for (int k = 0; k < 30; k++) {
							if (moves[k] == null) {
								break;
							}

							boardCpy = copyArr(board);
							makeMove(boardCpy, move1, moves[k]);
							//		                        if(board[r][c].charAt(0)=='k')
//		                        {
//		                        	return boardCpy;
//		                        }
							int a = findBestMove2(boardCpy, -1000000, 1000000, h + 1);
							if (max <= a) {
								max = a;
								bestMove = copyArr(boardCpy);
							}
						}
					}
				}
				else {
					if (board[i][j].equals("Tom") || board[i][j].equals("BTom")) {
						Point moves[] = allMove(i, j, board);
						Point move1 = new Point(i, j);
						for (int k = 0; k < 30; k++) {
							if (moves[k] == null) {
								break;
							}

							boardCpy = copyArr(board);
							makeMove(boardCpy, move1, moves[k]);
							//		                        if(board[r][c].charAt(0)=='k')
//		                        {
//		                        	return boardCpy;
//		                        }
							int a = findBestMove2(boardCpy, -1000000, 1000000, h + 1);
							if (max <= a) {
								max = a;
								bestMove = copyArr(boardCpy);
							}
						}
					}
				}
			}
		}
		return bestMove;
	}

	String[][] copyArr(String str[][]) {
		String boardCpy[][] = new String[7][5];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				boardCpy[i][j] = str[i][j];
			}
		}
		return boardCpy;
	}

	int findBestMove2(String board[][], int alpha, int beta, int h) {
		int min, max;
		min = 1000000;
		max = -1000000;
		int ans;
		String boardCpy[][] = new String[7][5];
		if (h == Constants.GAME_TREE_HEIGHT) {
			ans = evaluate(board);
			return ans;
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {

				Point moves[] = allMove(i, j, board);
				Point move1 = new Point(i, j);
				for (int k = 0; k < 30; k++) {
					if (beta <= alpha) {
						if ((h % 2) == 0) {
							ans = max;
						} else {
							ans = min;
						}
						return ans;
					}
					if (moves[k] == null) {
						break;
					}
					

//                       if(board[r][c].charAt(0)=='k')
//                       {
//                           if(ch == data.playerColor)
//                           {
//                               return (-1000000);
//                           }
//                           else
//                           {
//                               return (1000000);
//                           }
//                       }

					boardCpy = copyArr(board);
					makeMove(boardCpy, move1, moves[k]);
					int a = findBestMove2(boardCpy, alpha, beta, h + 1);
					if ((h % 2) == 0) {
						if (max < a) {
							max = a;
						}
						if (alpha < a) {
							alpha = a;
						}
					} else {
						if (min > a) {
							min = a;
						}
						if (beta > a) {
							beta = a;
						}
					}
				}
			}
		}
		if ((h % 2) == 0) {
			ans = max;
		} else {
			ans = min;
		}
		return ans;
	}

	void makeMove(String board[][], Point move1, Point move2) {

		String temp = board[move1.getX()][move1.getY()];

		board[move2.getX()][move2.getY()] = temp;
		board[move1.getX()][move1.getY()] = "###";
	}

	int evaluate(String board[][]) {
		int ans = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				int a = 0;
				if (board[i][j].equals("###")) {
					a = 0;
				} else if (board[i][j].equals("Tom")) {
					a = -20;
				} else if (board[i][j].equals("BTom")) {
					a = -80;
				} else if (board[i][j].equals("Hum")) {
					a = 100;
				}
				ans += a;
			}
		}
		
//		if(data.cpu == 'T')
//       {
//           ans=ans*(-1);
//       }
		
		return ans;
	}

}

package game.tictactoe.ui;

import java.util.Scanner;

import game.tictactoe.exceptions.GameException;
import game.tictactoe.game.Game;

public class ConsoleUserInterface implements UserInterface {

	private Game game;
	private final static String POSITION_PATTERN="[%d,%d]";
	private final static String[] playerMark = {"  X  ","  O  "};
	private final static String INPUT_PATTERN="Input invalid. Please re-enter [1-%d]";
	private final static String REENTER_POSITION="The position has already been taken. Please re-enter";
	private final static String SPLIT_ROW="-----------------";
	private final static String SPLIT_COL="|";
	private final static String WELCOME="WELCOME to The TIC-TAC-TOE";
	
	private Scanner scan;
	public ConsoleUserInterface() {
		scan = new Scanner(System.in);
	}
	public void display() {
		if (game != null) {
			int board[][];
			try {
				board = game.getGameBoard();
				int size = board.length;
				for (int i=1;i<size;i++) {
					System.out.println();
					System.out.println(SPLIT_ROW);
					for (int j=1;j<size;j++) {
						if (board[i][j] == 0) {
							System.out.print(SPLIT_COL);
							System.out.print(String.format(POSITION_PATTERN, i,j));
							System.out.print(SPLIT_COL);
						}else {
							System.out.print(SPLIT_COL);
							System.out.print(playerMark[board[i][j] % playerMark.length]);
							System.out.print(SPLIT_COL);
						}
						}
					}
				System.out.println();
				System.out.println(SPLIT_ROW);
			} catch (GameException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void draw() {
		System.out.println("DRAW");	
		end();
	}

	@Override
	public void win() {
		if (game != null && game.getWinner() != null) {
			System.out.println( game.getWinner().getLabel() + " WIN!");
		}	
		end();
	}

	private int getInputInteger(int limit) {

			do {
				try {
					String input = scan.next();
					System.out.println(input);
					int value = Integer.parseInt(input);
					if (value < 0 || value > limit) {
						System.out.println(String.format(INPUT_PATTERN, limit));
					} else {
						return value;
					}
				} catch (Exception e) {
					System.out.println(String.format(INPUT_PATTERN, limit));
				}
			} while (true); 
	}
	@Override
	public void playerInput() {
		if (game != null && game.getCurrentPlayer() != null) {
			do {
				try 
				{
					System.out.println(" Wait for " + game.getCurrentPlayer().getLabel() + "'s input.");
					System.out.println(" Row:");
					int row = getInputInteger(game.getSize());
					System.out.println(" Column:");
					int column = getInputInteger(game.getSize());
					if (game.isEmpty(row, column)) {
						game.getCurrentPlayer().set(row, column);
						return;
					}else {
						System.out.print(REENTER_POSITION);
					}
				} catch (Exception e) {
				}
			} while (true);
		}
	}

	@Override
	public void start() {
		System.out.println(WELCOME);
		display();
	}
	private void end() {
		System.out.println("");
		System.out.println("BYE");
		if (scan != null) {
			scan.close();
		}
	}

	@Override
	public void setDataModel(Game game) {
		this.game = game;
	}
}

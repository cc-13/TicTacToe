package game.tictactoe.game;

import game.tictactoe.exceptions.GameException;
import game.tictactoe.ui.ConsoleUserInterface;
import game.tictactoe.ui.UserInterface;

public class Game {

	private int size;
	private WinCondition cond;
	private Player[] players;
	private UserInterface userIf;
	private Player currentPlayer = null;

	public Game(int size, WinCondition cond, int playerCount, UserInterface userIf) {
		this.size = size;
		this.cond = cond;
		this.userIf = userIf;
		players = new Player[playerCount];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(i + 1, this.size, this.cond);
		}
		userIf.setDataModel(this);
	}

	public int[][] getGameBoard() throws GameException {
		int[][] gameBoard = new int[size + 1][size + 1];
		for (int i = 0; i < size + 1; i++) {
			for (int j = 0; j < size + 1; j++) {
				boolean marked = false;
				for (Player player : players) {
					if (player.contains(i, j)) {
						gameBoard[i][j] = player.getId();
						marked = true;
						break;
					}
				}
				if (!marked) {
					gameBoard[i][j] = 0;
				}
			}
		}
		return gameBoard;
	}

	public Player getWinner() {
		for (Player player : players) {
			if (player.wins()) {
				return player;
			}
		}
		return null;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void run() {
		int step = 0;
		userIf.start();
		while (true) {
			if (step == size * size) {
				userIf.draw();
				return;
			}
			for (Player player : players) {
				this.currentPlayer = player;
				userIf.playerInput();
				userIf.display();
				if (player.wins()) {
					userIf.win();
					return;
				}
			}
		}
	}
	public boolean isEmpty(int row,int column) throws GameException
	{
		int[][] gameBoard = getGameBoard();
		return gameBoard[row][column] == 0; 
	}
	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		UserInterface userInterface = new ConsoleUserInterface();
		WinCondition cond = new TicTacTocWinCondition();
		Game myGame = new Game(3, cond, 2, userInterface);
		myGame.run();
		
	}
}

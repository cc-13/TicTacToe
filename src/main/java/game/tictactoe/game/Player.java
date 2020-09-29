package game.tictactoe.game;
import game.tictactoe.exceptions.GameException;

public class Player {
	private int id;
	private String label;
	private int[][] board;
	private int size;
	private WinCondition cond = null;
	boolean win = false;
	
	public Player(int id, int size,WinCondition cond) {
		this.id = id;
		this.label = "Player - " + id;
		this.size = size;
		board = new int[size+1][size+1];
		this.cond = cond;
	}
	public boolean contains(int row, int column) throws GameException {
		if (row > size && column > size) {
			throw new GameException();
		}
		return board[row][column] == 1;
	}
	
	public boolean set(int row, int column) throws GameException {
		if (contains(row,column)) {
			return false;
		}
		board[row][column] = 1;
		return true;
	}

	public boolean wins() {
		if (win)
			return win;
		win = cond.wins(board, size);
		return win;
	}
	public String getLabel() {
		return label;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

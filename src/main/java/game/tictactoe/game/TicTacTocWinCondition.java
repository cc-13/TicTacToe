package game.tictactoe.game;

public class TicTacTocWinCondition implements WinCondition {
	@Override
	public boolean wins(int[][] board, int size) {
		for (int i = 1; i < size + 1; i++) {
			boolean rowEqual = true;
			for (int j = 1; j < size + 1; j++) {
				if (board[i][j] != 1) {
					rowEqual = false;
					break;
				}
			}
			if (rowEqual) {
				return true;
			}
		}
		// column equals
		for (int j = 1; j < size + 1; j++) {
			boolean columnEqual = true;
			for (int i = 1; i < size + 1; i++) {
				if (board[i][j] != 1) {
					columnEqual = false;
					break;
				}	
			}
			if (columnEqual) {
				return true;
			}
		}
		// cross equal
		boolean crossEqual1 = true;
		boolean crossEqual2 = true;
		for (int i = 1; i < size + 1; i++) {
			if (board[i][i] != 1) {
				crossEqual1 = false;
			}
			if (board[i][size + 1 - i] != 1) {
				crossEqual2 = false;
			}
		}
		if (crossEqual1 || crossEqual2) {
			return true;
		}
		return false;
	}

}

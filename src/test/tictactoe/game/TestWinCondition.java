package test.tictactoe.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.tictactoe.game.TicTacTocWinCondition;
import game.tictactoe.game.WinCondition;

public class TestWinCondition {

	public TestWinCondition() {
		
	}
	int board[][] = null;
	private final static int SIZE=3;
	
	@Before
	public void initBoard() {
		this.board=new int[SIZE+1][SIZE+1];
		for (int i=0;i<SIZE+1;i++) {
			for (int j=0;j<SIZE+1;j++) {
				board[i][j] = 0;
			}
		}
	}
	@Test
	public void testRow() {
		WinCondition cond = new TicTacTocWinCondition();
		assertEquals(false,cond.wins(board, SIZE));
		board[2][1]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[2][2]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[2][3]=1;
		assertEquals(true,cond.wins(board, SIZE));
	}
	@Test
	public void testColumn() {
		WinCondition cond = new TicTacTocWinCondition();
		board[3][3]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[2][3]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[1][3]=1;
		assertEquals(true,cond.wins(board, SIZE));
	}
	
	@Test
	public void testCross1() {
		WinCondition cond = new TicTacTocWinCondition();
		board[3][3]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[2][2]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[1][1]=1;
		assertEquals(true,cond.wins(board, SIZE));
	}
	@Test
	public void testCross2() {
		WinCondition cond = new TicTacTocWinCondition();
		board[1][3]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[3][1]=1;
		assertEquals(false,cond.wins(board, SIZE));
		board[2][2]=1;
		assertEquals(true,cond.wins(board, SIZE));
	}
}

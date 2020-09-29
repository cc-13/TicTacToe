package game.tictactoe.ui;

import game.tictactoe.game.Game;

public interface UserInterface {
	public void setDataModel(Game game);
	public void start();
	public void display();
	public void draw();
	public void win();
	public void playerInput();
}

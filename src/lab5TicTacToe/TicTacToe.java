package lab5TicTacToe;
/**
 * This class will hold all the methods for a console based tic tac toe game, including a game loop
 * @author Ylva
 *
 */
public class TicTacToe {
	/**
	 * This character array will hold the game field, it will be the size of the game
	 * A value can be either 'A'(empty) 'O'(player sign) or 'X'(other player sign)
	 */
	private char[][] game;
	
	/**
	 * This integer counts the amount of moves, it is started at 0, every even move is a move made by the starting player (where 0 is said to be even)
	 */
	private int moveCount;
	
	/**
	 * This char is either X or O depending on which user starts
	 */
	private char starter;
	
	/**
	 * The size of the board
	 */
	private int gameSize;
	
	/**
	 * Default constructor,
	 * Sets the size of the game to 4
	 * The starting player to X
	 * And initializes the game array as being filled with 'A'
	 */
	public TicTacToe(){
		gameSize = 4;
		game = new char[gameSize][gameSize];
		emptyGame();
		moveCount = 0;
		starter = 'X';
	}
	
	/**
	 * I ran into problems with the way I initialized the game array,
	 * Since the array size is usually really small we can just use a simple loop iteration to make the array hold values of 'A'
	 */
	private void emptyGame(){
		int x = 0, y = 0;
		while (x<gameSize){
			while (y<gameSize){
				game[x][y] = 'A';
				y++;
			}
			x++;
		}
	}
	
	
	
	
}

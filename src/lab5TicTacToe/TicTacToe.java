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
	 * We could use a byte type to save memory here, and if we would do that we could make the moveCount a short.
	 * However due to the way java works on 32 bit systems (And most systems have either a 32 or 64 bit architecture) it will allocate the room for an int type anyway.
	 * This means the Java Virtual Machine has to do additional checks in order to do arithmetic on short and byte types.
	 * This actually results in a penalty in terms of actual computing time.
	 * More on this subject can be found at http://stackoverflow.com/questions/14531235/in-java-is-it-more-efficient-to-use-byte-or-short-instead-of-int-and-float-inst
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
	
	public TicTacToe(byte size){
		if (size<=2){
			//Invalid size
			System.err.println("Invalid board size, stopping the program");
			System.err.println("Error code 1, invalid board size");
			System.exit(1);
		}
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

package lab5TicTacToe;

/**
 * This class will hold the player versus computer methods, it uses the same methods as the TicTacToe class only it has a AI layer
 * This class only works on 3x3 boards, and the first player will always be the X, although you can specify which character the computer is
 * @author Ylva
 */
public class AI extends TicTacToe{
	/**
	 * This character will be the character that the computer has to play with
	 */
	private char computer;
	
	/**
	 * Default constructor, sets board size to 3, sets the starting player to X and the computer to play with 0
	 */
	public AI (){
		gameSize = 3;
		game = new char[gameSize][gameSize];
		emptyGame();
		moveCount = 0;
		starter = 'X';
		computer = 'O';
	}
	
	/**
	 * Constructor that sets which sign the computer should start with
	 * Sets the board size to 3x3, Sets the starting player to X and the computer to the input
	 * @param computer the char the computer should have, either X or O
	 */
	public AI (char computer){
		if(computer=='X'||computer=='O'){
			gameSize = 3;
			game = new char[gameSize][gameSize];
			emptyGame();
			moveCount = 0;
			starter = 'X';
			this.computer = computer;
		}else{
			//Invalid starting character
			System.err.println("Invalid character as start player");
			System.err.println("Error code 1, invalid starting character");
			System.exit(1);
		}
	}
}

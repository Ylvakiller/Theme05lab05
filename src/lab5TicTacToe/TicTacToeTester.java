package lab5TicTacToe;
/*
 * Description as found on blackboard:
 * 
 * Define a class called TicTacToe.
 * An object of the type TicTacToe is a single game of tic-tac-toe.
 * The game board has the same number of rows and columns.
 * The minimal board size is 3x3, the maximal board size is 7x7 and the default board size is 4x4.
 * Players enter their moves in turn at the same keyboard. 
 * One player places a 'X' on an empty field of the board, whereas the other player places a 'O'.
 * The first player that succeeds in occupying a whole row, or a whole column or one of the diagonals is the winner of the game. 
 * Store the player that starts the game as a variable of type char, which has either the value ‘X’ or ‘O’. 
 * By default the game is started by the player that places the ‘X’. 
 * Store the game board as a single two-dimensional array of base type char. 
 * 
 * Write three constructors: 
 * o a default constructor, 
 * o a constructor that takes the size of the board as the only parameter 
 * o a constructor that takes the char indicating which player starts the game as the 
 * only input. 
 * 
 * Include methods to: 
 * 1. add a move, 
 * 2. display the board (see attached code), 
 * 3. tell whose turn it is (‘X’ or ‘O’), 
 * 4. to change the turn (from ‘X’ to ‘O’ or vice versa) 
 * 5. set the player that starts the game (‘X’ or ‘O’), 
 * 6. tell whether there is a winner, 
 * 7. say who the winner is, 
 * 8. initialize the board (all fields empty) 
 * 9. start a new game. 
 * 
 * Write preconditions and postconditions for each method. 
 * Write a separate test class with code that tests methods 3, 5, 7 and 8. 
 */

/*
 * Remarks from blackboard:
 * To check whether there is a winner you need to check whether there is a row, a column or a diagonal (left-top to right-bottom or right-top to left-bottom) that is completely occupied with ‘X’ or ‘O’. 
 * Note 1: you only need to check this for one player: you can only win if it is your turn. 
 * Note2: game can also end in a draw when the board is full. 
 * 	You can determine that the board is full by counting the number of times a user has entered a turn. 
 * For a 4*4 board the board is full after 16 turns. 
 * Note3: you may need to define additional methods 
 */


/*
 * Personal remarks:
 * I decided to not use the methods posted on blackboard but to make my own based upon them. 
 * They will be integrated in the TicTacToe class but will be documented in a way that makes clear that they are based upon the blackboard code.
 * In other remarks, the program will work for any size larger then 2 for a board size, I will make a line of code that can be uncommented to set the limit to a 7x7 grid
 * The assignment is highly unclear about how the user input should be gotten, I therefore decided to get the user input by asking for a row and a column number.
 */

/**
 * This class will start with testing all the smaller methods in the TicTacToe class, and will then start the game loop which is in the TicTacToe class
 * @author Ylva
 *
 */
public class TicTacToeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToe game1 = new TicTacToe();
	}

}

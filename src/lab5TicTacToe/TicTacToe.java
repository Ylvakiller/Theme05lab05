package lab5TicTacToe;

import java.util.Scanner;

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
	 * Gets the user input.
	 */
	static private Scanner keyboard = new Scanner(System.in);
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
	
	/**
	 * Constructor that initializes the board on a specific size
	 * Sets the starting player to X
	 * @param size the size the board should have
	 */
	public TicTacToe(byte size){
		if (size<=2){
			//Invalid size
			System.err.println("Invalid board size, stopping the program");
			System.err.println("Error code 1, invalid board size");
			System.exit(1);
		}
		gameSize = size;
		game = new char[gameSize][gameSize];
		emptyGame();
		moveCount = 0;
		starter = 'X';
	}
	
	/**
	 * Constructor that specifies which user starts
	 * Sets the board size to 4
	 * @param start has to be either 'X' or 'O'
	 */
	public TicTacToe(char start){
		if(start=='X'||start=='O'){
			gameSize = 4;
			game = new char[gameSize][gameSize];
			emptyGame();
			moveCount = 0;
			starter = start;
		}else{
			//Invalid starting character
			System.err.println("Invalid character as start player");
			System.err.println("Error code 2, invalid starting character");
			System.exit(2);
		}
	}
	
	/**
	 * Constructor that can set the starting user and the board size
	 * @param size the board size, has to be higher then 2
	 * @param start the starting char, has to be either 'X' or 'O'
	 */
	public TicTacToe(int size, char start){
		if(start=='X'||start=='O'){
			if (size<=2){
				//Invalid size
				System.err.println("Invalid board size, stopping the program");
				System.err.println("Error code 1, invalid board size");
				System.exit(1);
			}
			gameSize = size;
			game = new char[gameSize][gameSize];
			emptyGame();
			moveCount = 0;
			starter = start;
		}else{
			//Invalid starting character
			System.err.println("Invalid character as start player");
			System.err.println("Error code 2, invalid starting character");
			System.exit(2);
		}
	}
	
	/**
	 * returns who's turn it is
	 * @return the char of the player who's turn it is
	 */
	private char turn(){
		//Bitmasking, we are now only looking at the least significant bit, if that is 0 then it is even, if it is 1 then it is odd
		if ((moveCount&1)==0){
			return starter;
		}else{
			//odd number, now we need to switch the starter
			if (starter=='X'){
				return 'O';
			}else{
				return 'X';
			}
		}
	}
	
	/**
	 * This method will check if the game has ended
	 * @return true if the game has ended
	 */
	private boolean endGame(){
		if (fullField()||leftDiagonalWin()||rightDiagonalWin()){
			return true;
		}else{
			int i = 0;
			while (i<gameSize){
				if (columnWin(i)||rowWin(i)){
					return true;
				}
				i++;
			}
		}
		return false;
	}
	
	/**
	 * Will return a true if the game has gone into a draw
	 * @return true if the game is a draw
	 */
	private boolean draw(){
		if(fullField()){
			if (leftDiagonalWin()||rightDiagonalWin()){
				return false;
			}else{
				int i = 0;
				while (i<gameSize){
					if (columnWin(i)||rowWin(i)){
						return false;
					}
					i++;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Will return true if the field is full
	 * @return true if all the places on the field have something in them
	 */
	private boolean fullField(){
		if (moveCount+1==Math.pow(gameSize, 2)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Will win if the player who's turn it is has a winning condition in the specified column
	 * @param column the column to check
	 * @return true if the current player has won with this column
	 */
	private boolean columnWin(int column){
		int i = 0;
		while(i<gameSize){
			if(turn()=='X'){
				if(game[column][i]=='O'||game[column][i]=='A'){
					//In this case, it is X his turn and in the column that we check there is a O, meaning that X cannot win with that column
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			if(turn()=='O'){
				if(game[column][i]=='X'||game[column][i]=='A'){
					//In this case, it is O his turn and in the column that we check there is a X, meaning that O cannot win with that column
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			i++;
		}
		//If it gets here without return a false we know that we have a true case
		return true;
	}
	
	/**
	 * Will win if the player who's turn it is has a winning condition in the specified row
	 * @param row the row to check
	 * @return true if the current player has won with this row
	 */
	private boolean rowWin(int row){
		int i = 0;
		while(i<gameSize){
			if(turn()=='X'){
				if(game[i][row]=='O'||game[i][row]=='A'){
					//In this case, it is X his turn and in the row that we check there is a O, meaning that X cannot win with that row
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			if(turn()=='O'){
				if(game[i][row]=='X'||game[i][row]=='A'){
					//In this case, it is O his turn and in the row that we check there is a X, meaning that O cannot win with that row
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			i++;
		}
		//If it gets here without return a false we know that we have a true case
		return true;
	}
	
	/**
	 * Will win if the player who's turn it is has a winning condition in diagonal from the top left to the bottom right
	 * @return true if the current player has won with this diagonal
	 */
	private boolean leftDiagonalWin(){
		int i = 0;
		while(i<gameSize){
			if(turn()=='X'){
				if(game[i][i]=='O'||game[i][i]=='A'){
					//In this case, it is X his turn and in the diagonal that we check there is a O, meaning that X cannot win with that diagonal
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			if(turn()=='O'){
				if(game[i][i]=='X'||game[i][i]=='A'){
					//In this case, it is O his turn and in the diagonal that we check there is a X, meaning that O cannot win with that diagonal
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			i++;
		}
		//If it gets here without return a false we know that we have a true case
		return true;
	}
	
	/**
	 * Will win if the player who's turn it is has a winning condition in diagonal from the bottom left to the top right
	 * @return true if the current player has won with this diagonal
	 */
	private boolean rightDiagonalWin(){
		int i = 0;
		while(i<gameSize){
			if(turn()=='X'){
				if(game[gameSize-i-1][i]=='O'||game[gameSize-i-1][i]=='A'){
					//In this case, it is X his turn and in the diagonal that we check there is a O, meaning that X cannot win with that diagonal
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			if(turn()=='O'){
				if(game[gameSize-i-1][i]=='X'||game[gameSize-i-1][i]=='A'){
					//In this case, it is O his turn and in the diagonal that we check there is a X, meaning that O cannot win with that diagonal
					//Also if there is an A to be found then that place does not have a sign on it, meaning no win can be gotten here
					return false;
				}
			}
			i++;
		}
		//If it gets here without return a false we know that we have a true case
		return true;
	}
	
	/**
	 * Retrieves the winner, should only be run when endGame() is true
	 * @return an 'A' if draw, otherwise an 'X' or 'O'
	 */
	private char getWinner(){
		if(draw()){
			return 'A';
		}else{
			return turn();
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
			y=0;
			x++;
		}
	}
	
	/**
	 * Will return a small array with 2 integer types in it
	 * These are the coordinates of the place where the user wants to place a sign
	 * These are send back in a way that they can directly be used in the array
	 * @return a small int array with 2 coordinates
	 */
	private int[] getMove(){
		int[] temp = new int[2];
		boolean temp1 = true;
		while(temp1){
			boolean temp2 = true;
			int tempColumn = 0;
			while(temp2){
				System.out.println("Now please enter the column where you want to place your sign:");
				tempColumn = keyboard.nextInt()-1;
				if(tempColumn>=gameSize){
					System.out.println("The input you have given is to large, please enter a different number");
				}else if(tempColumn==-1){
					System.out.println("There is not column number 0, please enter a different number");
				}else if(tempColumn<0){
					System.out.println("You cannot enter a negative number, please enter a different number");
				}else{
					temp2 = false;
				}
			}

			temp2 = true;
			int tempRow = 0;
			while(temp2){
				System.out.println("Please enter the row on which you want to place your sign:");
				tempRow = keyboard.nextInt()-1;				//minus one to change it to the system arrays use which starts at 0, therefore you can enter 1 for the first row etc
				if(tempRow>=gameSize){
					System.out.println("The input you have given is to large, please enter a different number");
				}else if(tempRow==-1){
					System.out.println("There is not row number 0, please enter a different number");
				}else if(tempRow<0){
					System.out.println("You cannot enter a negative number, please enter a different number");
				}else{
					temp2 = false;
				}
			}
						
			if(isEmptyCell(tempRow,tempColumn)){
				temp[0]=tempRow;
				temp[1]=tempColumn;
				temp1 = false;
			}else{
				System.out.println("There is already a sign at the position you specified");
				System.out.println("Please enter a different position");
			}
		}
		return temp;
	}
	
	/**
	 * Will return a true if the cell specified is empty
	 * @param row the first coordinate in the 2 dimensional playing field
	 * @param column the second coordinate in the 2 dimensional playing field
	 * @return true if the cell in the 2 dimensional playing field is empty
	 */
	private boolean isEmptyCell(int row, int column){
		if(game[row][column]=='A'){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This is a place holder method that will print the current playing field
	 */
	private void placeHolderPrint(){
		int x = 0, y = 0;
		while(x<gameSize){
			while(y<gameSize){
				System.out.print(game[x][y]);
				y++;
			}
			y=0;
			x++;
			System.out.println();
		}
		
	}
	
	/**
	 * This method will hold the complete game loop
	 */
	public void gameLoop(){
		while (true){
			printScreen();
			int[] temp = getMove();
			placeSign(temp[0],temp[1]);
			if(endGame()){
				if (getWinner()=='A'){
					System.out.println("The game has ended in a draw.");
				}else{
					System.out.println("The player playing with " + getWinner() + " has won this game");
					System.exit(0);
				}
			}
			moveCount++;
			
		}
		
	}
	
	/**
	 * Places a sign at the specified place
	 * @param row the row in which to place a sign
	 * @param column the column in which to place a sign
	 */
	private void placeSign(int row, int column){
		game[row][column] = turn();
	}
	
	
	/**
	 * The proper graphical screen
	 */
	private void printScreen(){
		int temp = 0;
		while(temp<gameSize){
			System.out.print("____");
			temp++;
		}
		System.out.println("");
		int x = 0, y = 0;
		while(x<gameSize){
			System.out.print("| ");
			while(y<gameSize){
				System.out.print(game[x][y] +" | ");
				y++;
			}
			y=0;
			x++;
			System.out.println("");
			temp = 0;
			while(temp<gameSize){
				System.out.print("|___");
				temp++;
			}
			System.out.println("|");
		}
	}
	
}

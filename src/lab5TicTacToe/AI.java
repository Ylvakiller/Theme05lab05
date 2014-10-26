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
	
	/**
	 * This will be the AI game loop. it will Override the game loop in the TicTacToe class
	 */
	@Override
	public void gameLoop(){
		while (true){
			printScreen();
			if (isAITurn()){
				doBestMove();
			}else{
				int[] temp = getMove();
				placeSign(temp[0],temp[1]);
			}
			
			if(endGame()){
				if (getWinner()=='A'){
					System.out.println("The game has ended in a draw.");
				}else if (getWinner()==computer){
					System.out.println("The computer has won.");
				}else{
					System.out.println("You have beaten the computer");
				}
			}
			moveCount++;
		}
	}
	
	/**
	 * Will check if it is the AI it's turn
	 * @return true if it is the AI turn
	 */
	private boolean isAITurn(){
		if(turn()==computer){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This calculates and enters the best move possible
	 */
	private void doBestMove(){
		//step 1, check if it is possible to win, if that is possible, do that
		//step 2, check if it is possible for the other player to win, if that is possible, do that
		//step 3, try to make a fork
		//step 4, if the opponent has the possibility to make a fork go into substeps:
			//substep 4.1 try to make a 2 in a row to force the opponent into blocking, unless this block means that they can get a fork
			//substep 4.2 if there is a possibility available where the opponent can fork, block that fork
		//step 5, try to mark the center
		//step 6, try to place your character in the opposing corner from the opponent
		//step 7, try to place your character in a random corner position
		//step 8, try to place your character on an empty side place
		
		//Step 1:
		int i = 0;
		int coordWin[] = {0,0};
		boolean winFound = false;
		while(i<gameSize){
			if(columnWinPossible(i,computer)){
				coordWin[0] = findWinColumn(i);
				coordWin[1] = i;
				winFound = true;
			}else if (rowWinPossible(i,computer)){
				coordWin[0] = i;
				coordWin[i] = findWinRow(i);
				winFound = true;
			}
			i++;
		}
		if (!winFound){
			if (leftDiagonalWinPossible(computer)){
				coordWin[0] = findWinLeftDiagonal();
				coordWin[1] = findWinLeftDiagonal();
				winFound = true;
			}else if (rightDiagonalWinPossible(computer)){
				coordWin[0] = 2-findWinRightDiagonal();
				coordWin[1] = findWinRightDiagonal();
				winFound = true;
			}
		}
		//At this point all possible wins for the computer have been checked, if one has been found it should now be entered:
		
		if (winFound){
			
		}
		
	}
	
	/**
	 * This method will check if it is possible for the character specified by player to win in the specific column
	 * @param column the column to check
	 * @param player the char to check for
	 * @return true if the specified char can with just one move win the game
	 */
	private boolean columnWinPossible(int column, char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[column][i]=='A'){
					countEmpty++;
				}
				if(game[column][i]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[column][i]=='A'){
					countEmpty++;
				}
				if(game[column][i]=='X'){
					countNonPlayer++;
				}
				
			}
			i++;
		}
		
		//Now if countEmpty will be 1, then we know that there is only 1 place on a row that is not the player char
		//If countNonPlayer is larger then 0 we can already see that is is impossible to have a win on this row
		if(countNonPlayer==0&&countEmpty==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if it possible for any player to win in a specified row
	 * @param row the row to check
	 * @param player the char to check for
	 * @return true if the specified char can win with just one move
	 */
	private boolean rowWinPossible(int row, char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[i][row]=='A'){
					countEmpty++;
				}
				if(game[i][row]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[i][row]=='A'){
					countEmpty++;
				}
				if(game[i][row]=='X'){
					countNonPlayer++;
				}
				
			}
			i++;
		}
		
		//Now if countEmpty will be 1, then we know that there is only 1 place on a row that is not the player char
		//If countNonPlayer is larger then 0 we can already see that is is impossible to have a win on this row
		if(countNonPlayer==0&&countEmpty==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if it is possible for any player to win in the diagonal from top left to bottom right
	 * @param player
	 * @return true if the char can win in one move
	 */
	private boolean leftDiagonalWinPossible(char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[i][i]=='A'){
					countEmpty++;
				}
				if(game[i][i]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[i][i]=='A'){
					countEmpty++;
				}
				if(game[i][i]=='X'){
					countNonPlayer++;
				}
				
			}
			i++;
		}
		
		//Now if countEmpty will be 1, then we know that there is only 1 place on a row that is not the player char
		//If countNonPlayer is larger then 0 we can already see that is is impossible to have a win on this row
		if(countNonPlayer==0&&countEmpty==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if it is possible for any player to win in the diagonal from top left to bottom right
	 * @param player the char to check for
	 * @return true if the char can win in one move
	 */
	private boolean rightDiagonalWinPossible(char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[gameSize-i-1][i]=='A'){
					countEmpty++;
				}
				if(game[gameSize-i-1][i]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[gameSize-i-1][i]=='A'){
					countEmpty++;
				}
				if(game[gameSize-i-1][i]=='X'){
					countNonPlayer++;
				}
				
			}
			i++;
		}
		
		//Now if countEmpty will be 1, then we know that there is only 1 place on a row that is not the player char
		//If countNonPlayer is larger then 0 we can already see that is is impossible to have a win on this row
		if(countNonPlayer==0&&countEmpty==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Will find the place where the player can win, will return 4 if no place is found.
	 * Should be used after the rowWingPossible method
	 * Will check for the first open space and return that
	 * @param row the row to check
	 * @return an integer with the column where the player has a spot to win (if any)
	 */
	private int findWinRow(int row){
		int i = 0;
		while(i<3){
			if (game[i][row]=='A'){
				return i;
			}
			i++;
		}
		return 4;
	}
	
	/**
	 * Will find the first empty place in a column and return that, will return a 4 otherwise
	 * Should be used after the columnWinPossible method
	 * @param column the column to check
	 * @return an integer with the row where the first empty place is (which if columnWinPossible returns true will be the place where a win is possible)
	 */
	private int findWinColumn(int column){
		int i = 0;
		while(i<3){
			if (game[column][i]=='A'){
				return i;
			}
			i++;
		}
		return 4;
	}
	
	/**
	 * Will return an integer that has the first empty place in the diagonal from top left to bottom right.
	 * If this integer = 0  then the place is 0,0 in the game array, if 1 then it is 1,1 etc
	 * Should be used if the leftDiagonalWinPossible is true
	 * @return the first empty the place in the diagonal
	 */
	private int findWinLeftDiagonal(){
		int i =0;
		while(i<3){
			if (game[i][i]=='A'){
				return i;
			}
			i++;
		}
		return 4;
	}
	
	/**
	 * Checks for the first empty place in the diagonal from top right to bottom left
	 * If this is 0 it means 3,1 if it is 1 then it is 2,2 if it is 2 it is 1,3 these values are not the array values
	 * @return the integer location of the first empty place in this diagonal
	 */
	private int findWinRightDiagonal(){
		int i =0;
		while(i<3){
			if (game[gameSize-i-1][i]=='A'){
				return i;
			}
			i++;
		}
		return 4;
	}
	
	private void enterMove(int row, int column){
		game[][]
	}
	
	
	
}

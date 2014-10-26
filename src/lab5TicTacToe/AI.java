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
		
	}
	
	/**
	 * Will check if it is possible to win for a specific character, and if so which place should be filled for that player to win
	 * @param player the char which to test, this can be either X or O depending on if you want to check for the computer of the player
	 * @return	Coordinates of the place where the player can win, if this is 4,4 then there is no possible win
	 */
	private int[][] possibleWin(char player){
		int[] winPosition = {4};
		
		return null;
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
	
}

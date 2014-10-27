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
	
	protected boolean ultraHardMode;
	
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
			if(computer=='X'){
				starter = 'O';
			}else{
				starter = 'X';
			}
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
				printScreen();
				if (getWinner()=='A'){
					System.out.println("The game has ended in a draw.");
				}else if (getWinner()==computer){
					System.out.println("The computer has won.");
				}else{
					System.out.println("You have beaten the computer");
				}
				System.exit(0);
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
				coordWin[0] = i;
				coordWin[1] = findWinColumn(i);
				winFound = true;
			}else if (rowWinPossible(i,computer)){
				coordWin[0] = findWinRow(i);
				coordWin[1] = i;
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
				coordWin[0] = findWinRightDiagonal();
				coordWin[1] = findWinRightDiagonal();
				winFound = true;
			}
		}
		//At this point all possible wins for the computer have been checked, if one has been found it should now be entered:
		
		if (winFound){
			System.out.println("Step 1");
			enterMove(coordWin[0], coordWin[1]);
		}else{
			//Here step 2 should be
			char player;
			if (computer == 'X'){
				player = 'O';
			}else{
				player = 'X';
			}
			i = 0;
			while(i<gameSize){
				if(columnWinPossible(i,player)){
					System.out.println("Column win is possible");
					coordWin[0] = findWinColumn(i);
					coordWin[1] = i;
					winFound = true;
				}else if (rowWinPossible(i,player)){

					System.out.println("row win is possible");
					coordWin[0] = i;
					coordWin[1] = findWinRow(i);;
					winFound = true;
				}
				i++;
			}
			if (!winFound){
				if (leftDiagonalWinPossible(player)){
					System.out.println("leftDiagonal win is possible");
					coordWin[0] = findWinLeftDiagonal();
					coordWin[1] = findWinLeftDiagonal();
					winFound = true;
				}else if (rightDiagonalWinPossible(player)){
					System.out.println("rightDiagonal win is possible");
					coordWin[0] = 2-findWinRightDiagonal();
					coordWin[1] = findWinRightDiagonal();
					winFound = true;
				}
			}
			//Here winFound is true if the opposing player can win.
			//If the opposing player can win then the coordWin is the place that needs to be blocked
			if (winFound){
				System.out.println("Step 2");
				enterMove(coordWin[0],coordWin[1]);
			}else{
				//This is where step 3 should be
				if (forkPossible(computer)){
					int temp[] = findForkPlace(computer);
					enterMove(temp[0],temp[1]);
					System.out.println("Step 3");
				}else{
					boolean ultraHardStep = false;
					//step ultraHardStep force the opponent to block (IE make 2 in a row)
					//This step will be the ultraHardMode
					if(ultraHardMode){
						if (checkForCreateBlockAgainstFork()){
							if(game[1][0]=='A'){
								ultraHardStep = true;
								enterMove(1,0);
							}else if(game[1][2]=='A'){
								ultraHardStep = true;
								enterMove(1,2);
							}else if(game[2][1]=='A'){
								ultraHardStep = true;
								enterMove(2,1);
							}else if(game[0][1]=='A'){
								ultraHardStep = true;
								enterMove(0,1);
							}
						}
					}
					
					if (!ultraHardStep){
						
						boolean step4 = true;
						if (computer=='O'){
							if (forkPossible('X')){
								int temp[] = findForkPlace('X');
								System.out.println("Step 4");
								enterMove(temp[0],temp[1]);
							}else{
								step4 = false;
							}
						}else{
							if (forkPossible('O')){
								int temp[] = findForkPlace('O');
								System.out.println("Step 4");
								enterMove(temp[0],temp[1]);
							}else{
								step4 = true;
							}
						}

						if (!step4){
							

							//step 5
							if (game[1][1]=='A'){
								System.out.println("Step 5");
								enterMove(1,1);
							}else{
								System.out.println("step 6");
								//step 6
								if (game[0][0]==player&&game[2][2]=='A'){
									enterMove(2,2);
								}else if (game[2][2]==player&&game[0][0]=='A'){
									enterMove(0,0);
								}else if (game[0][2]==player&&game[2][0]=='A'){
									enterMove(2,0);
								}else if (game[2][0]==player&&game[0][2]=='A'){
									enterMove(0,2);
								}else{
									System.out.println("Step 7");
									//step 7
									if (game[0][0]=='A'){
										enterMove(0,0);
									}else if (game[2][2]=='A'){
										enterMove(2,2);
									}else if (game[0][2]=='A'){
										enterMove(0,2);
									}else if (game[2][0]=='A'){
										enterMove(2,0);
									}else{
										System.out.println("Step 8");
										//step 8
										if (game[1][0]=='A'){
											enterMove(1,0);
										}else if (game[0][1]=='A'){
											enterMove(0,1);
										}else if (game[1][2]=='A'){
											enterMove(1,2);
										}else if (game[2][1]=='A'){
											enterMove(2,1);
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		
	}
	
	/**
	 * This method will check if it is possible for the character specified by player to win in the specific row
	 * @param row the row to check
	 * @param player the char to check for
	 * @return true if the specified char can with just one move win the game
	 */
	private boolean rowWinPossible(int row, char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[row][i]=='A'){
					countEmpty++;
				}
				if(game[row][i]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[row][i]=='A'){
					countEmpty++;
				}
				if(game[row][i]=='X'){
					countNonPlayer++;
				}
				
			}
			i++;
		}
		System.out.println("Row" + row);
		System.out.println("countNonPlayer:" + countNonPlayer);
		System.out.println("countEmpty:" + countEmpty);
		//Now if countEmpty will be 1, then we know that there is only 1 place on a row that is not the player char
		//If countNonPlayer is larger then 0 we can already see that is is impossible to have a win on this row
		if(countNonPlayer==0&&countEmpty==1){
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if it possible for any player to win in a specified column
	 * @param column the column to check
	 * @param player the char to check for
	 * @return true if the specified char can win with just one move
	 */
	private boolean columnWinPossible(int column, char player){
		int i = 0;
		int countEmpty =0;
		int countNonPlayer = 0;
		while(i<gameSize){
			if(player=='X'){
				if(game[i][column]=='A'){
					countEmpty++;
				}
				if(game[i][column]=='O'){
					countNonPlayer++;
				}
				
			}
			if(player=='O'){
				if(game[i][column]=='A'){
					countEmpty++;
				}
				if(game[i][column]=='X'){
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
			if (game[row][i]=='A'){
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
			if (game[i][column]=='A'){
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
	
	/**
	 * Enters a move for the computer to place a move in the specified location
	 * @param row the row in which to place a sign
	 * @param column the column in which to place a sign
	 */
	private void enterMove(int row, int column){
		game[row][column] = computer;
	}
	
	/**
	 * Will check if a fork is possible this method is horribly inefficient but it will do for now
	 * @param player The char to check if it has a possible place for a fork
	 * @return true if there is a place for a fork
	 */
	private boolean forkPossible(char player){
		/*
		 * This method is not efficient!
		 * It will check every single corner fork
		 * There are a lot of different combinations for this to happen, I have been looking for a more efficient method to do this, however time limitations force me to use this brute force method
		 * I do not need to check if there is a possibility for a fork which needs to place a char in the middle position.
		 * This because if you look closely at the algorithm, if the computer starts it will put his char in the middle
		 * If the player starts then either that player will put his char in the middle, or he will put it somewhere else which will result in the computer placing a char in the middle
		 * This means that since a fork needs a minimum of 2 chars for the computer it is impossible to create a fork by placing a char in the middle
		 * It is however quite possible to create a fork when the computer is already in the middle
		 * Also when calculating forks we can assume that cases where a win is already possible the program will have taken that win already and that condition cannot be part of the fork
		 */
		
		if(game[0][0]==player){
			if (game[2][2]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[1][2]=='A'){
					return true;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[2][1]=='A'){
					return true;
				}
			}else if (game[1][1]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[2][1]=='A'){
					return true;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[1][2]=='A'){
					return true;
				}
			}
		}else if (game[2][2]==player){
			if (game[0][0]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[1][2]=='A'){
					return true;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[2][1]=='A'){
					return true;
				}
			}else if (game[1][1]==player){
				if (game[2][1]=='A'&&game[2][0]=='A'&&game[0][1]=='A'){
					return true;
				}else if (game[1][2]=='A'&&game[0][2]=='A'&&game[1][0]=='A'){
					return true;
				}
			}
		}else if (game[0][2]==player){
			if (game[2][0]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[1][0]=='A'){
					return true;
				}else if (game[2][1]=='A'&&game[2][2]=='A'&&game[1][2]=='A'){
					return true;
				}
			}else if(game[1][1]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[2][1]=='A'){
					return true;
				}else if (game[1][2]=='A'&&game[1][0]=='A'&&game[2][2]=='A'){
					return true;
				}
			}
		}else if (game[2][0]==player){
			if (game[0][2]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[1][0]=='A'){
					return true;
				}else if (game[2][1]=='A'&&game[2][2]=='A'&&game[1][2]=='A'){
					return true;
				}
			}else if(game[1][1]==player){
				if (game[1][0]=='A'&&game[0][0]=='A'&&game[1][2]=='A'){
					return true;
				}else if (game[0][1]=='A'&&game[2][1]=='A'&&game[2][2]=='A'){
					return true;
				}
			}
		}else if (game[1][1]==player){
			if (game[0][1]==player){
				if (game[0][0]=='A'&&game[0][2]=='A'&&game[2][2]=='A'){
					return true;
				}else if (game[0][0]=='A'&&game[0][2]=='A'&&game[2][0]=='A'){
					return true;
				}
			}else if (game[1][2]==player){
				if (game[0][2]=='A'&&game[2][2]=='A'&&game[2][0]=='A'){
					return true;
				}else if (game[0][2]=='A'&&game[2][2]=='A'&&game[0][0]=='A'){
					return true;
				}
			}else if (game[2][1]==player){
				if (game[0][2]=='A'&&game[2][2]=='A'&&game[2][0]=='A'){
					return true;
				}else if (game[0][2]=='A'&&game[2][2]=='A'&&game[0][0]=='A'){
					return true;
				}
			}else if (game[1][0]==player){
				if (game[0][0]=='A'&&game[2][0]=='A'&&game[2][2]=='A'){
					return true;
				}else if (game[0][0]=='A'&&game[2][0]=='A'&&game[0][2]=='A'){
					return true;
				}
			}
		}
		//If none of all the possible cases above are true then there is no possibility for a fork and therefore this can return false;
		return false;
	}
	
	/**
	 * Will do the same as the forkPossible method but will return the coordinates of the space need to be filled to create a fork for the char specified in @param player
	 * WARNING, should only be run if forkPossible returns true, otherwise will yield incorrect results
	 * @param player the char for which to calculate the place where it needs to go to create a fork
	 * @return an int array with the coordinates of the place that needs to be set in order to create a fork where int[0] is the row and int[1] is the column
	 */
	private int[] findForkPlace(char player){
		/**
		 * This method is also horribly inefficient.
		 * It is in most perspective the same as the forkPossible method with one mayor difference.
		 * It will return the position that needs to be filled in order to create the fork
		 */
		int temp[] = {0,0};
		if(game[0][0]==player){
			if (game[2][2]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[1][2]=='A'){
					temp[0] = 0;
					temp[1] = 2;
					return temp;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[2][1]=='A'){
					temp[0] = 2;
					temp[1] = 0;
					return temp;
				}
			}else if (game[1][1]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[2][1]=='A'){
					temp[0] = 0;
					temp[1] = 1;
					return temp;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[1][2]=='A'){
					temp[0] = 1;
					temp[1] = 0;
					return temp;
				}
			}
		}else if (game[2][2]==player){
			if (game[0][0]==player){
				if (game[0][1]=='A'&&game[0][2]=='A'&&game[1][2]=='A'){
					temp[0] = 0;
					temp[1] = 2;
					return temp;
				}else if (game[1][0]=='A'&&game[2][0]=='A'&&game[2][1]=='A'){
					temp[0] = 2;
					temp[1] = 0;
					return temp;
				}
			}else if (game[1][1]==player){
				if (game[2][1]=='A'&&game[2][0]=='A'&&game[0][1]=='A'){
					temp[0] = 2;
					temp[1] = 1;
					return temp;
				}else if (game[1][2]=='A'&&game[0][2]=='A'&&game[1][0]=='A'){
					temp[0] = 1;
					temp[1] = 2;
					return temp;
				}
			}
		}else if (game[0][2]==player){
			if (game[2][0]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[1][0]=='A'){
					temp[0] = 0;
					temp[1] = 0;
					return temp;
				}else if (game[2][1]=='A'&&game[2][2]=='A'&&game[1][2]=='A'){
					temp[0] = 2;
					temp[1] = 2;
					return temp;
				}
			}else if(game[1][1]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[2][1]=='A'){
					temp[0] = 0;
					temp[1] = 1;
					return temp;
				}else if (game[1][2]=='A'&&game[1][0]=='A'&&game[2][2]=='A'){
					temp[0] = 1;
					temp[1] = 2;
					return temp;
				}
			}
		}else if (game[2][0]==player){
			if (game[0][2]==player){
				if (game[0][1]=='A'&&game[0][0]=='A'&&game[1][0]=='A'){
					temp[0] = 0;
					temp[1] = 0;
					return temp;
				}else if (game[2][1]=='A'&&game[2][2]=='A'&&game[1][2]=='A'){
					temp[0] = 2;
					temp[1] = 2;
					return temp;
				}
			}else if(game[1][1]==player){
				if (game[1][0]=='A'&&game[0][0]=='A'&&game[1][2]=='A'){
					temp[0] = 1;
					temp[1] = 0;
					return temp;
				}else if (game[0][1]=='A'&&game[2][1]=='A'&&game[2][2]=='A'){
					temp[0] = 2;
					temp[1] = 1;
					return temp;
				}
			}
		}else if (game[1][1]==player){
			if (game[0][1]==player){
				if (game[0][0]=='A'&&game[0][2]=='A'&&game[2][2]=='A'){
					temp[0] = 0;
					temp[1] = 0;
					return temp;
				}else if (game[0][0]=='A'&&game[0][2]=='A'&&game[2][0]=='A'){
					temp[0] = 2;
					temp[1] = 0;
					return temp;
				}
			}else if (game[1][2]==player){
				if (game[0][2]=='A'&&game[2][2]=='A'&&game[2][0]=='A'){
					temp[0] = 0;
					temp[1] = 2;
					return temp;
				}else if (game[0][2]=='A'&&game[2][2]=='A'&&game[0][0]=='A'){
					temp[0] = 2;
					temp[1] = 2;
					return temp;
				}
			}else if (game[2][1]==player){
				if (game[0][2]=='A'&&game[2][2]=='A'&&game[2][0]=='A'){
					temp[0] = 0;
					temp[1] = 2;
					return temp;
				}else if (game[0][2]=='A'&&game[2][2]=='A'&&game[0][0]=='A'){
					temp[0] = 0;
					temp[1] = 0;
					return temp;
				}
			}else if (game[1][0]==player){
				if (game[0][0]=='A'&&game[2][0]=='A'&&game[2][2]=='A'){
					temp[0] = 0;
					temp[1] = 0;
					return temp;
				}else if (game[0][0]=='A'&&game[2][0]=='A'&&game[0][2]=='A'){
					temp[0] = 2;
					temp[1] = 0;
					return temp;
				}
			}
		}
		return temp;
	}
	
	/**
	 * This will check if there is a way for the opponent to create a fork and block that fork
	 * for example, if the player holds a corner, the computer holds the center and the player holds the opposing corner then,
	 * The computer should not play a corner but instead play a middle section
	 * @return a true if there is a case in which the computer should stop the opponent from creating a fork by blocking
	 */
	private boolean checkForCreateBlockAgainstFork(){
		char player;
		if(computer=='X'){
			player = 'O';
		}else{
			player = 'X';
		}
		if (game[0][0]==player&&game[2][2]==player&&game[1][1]==computer){
			return true;
		}else if (game[2][0]==player&&game[0][2]==player&&game[1][1]==computer){
			return true;
		}
		return false;
	}
}

package lab5TicTacToe;

import java.util.Scanner;

/**
 * This class will make a player VS computer game
 * You can specify which player starts in the beginning.
 * The game will always be a 3x3 version, this because the AI was written for a 3x3 grid.
 * @author Ylva
 */
public class AIGame {

	public static void main(String[] args) {
		System.out.println("If you want to start, enter an X, if you want the computer to start enter an O");
		Scanner keyboard = new Scanner(System.in);
		boolean input =true;
		while(input){
			String player = keyboard.next();
			while (player.length()!=1){
				System.err.println("The input that you have given is to long, please try again");
				player = keyboard.next();
			}
			if(player=="X"||player=="O"){
				input = false;
			}else{
				System.err.println("The input that you have given is neither X or O. Keep in mind that this is case sensitive");
				System.out.println("Please try again:");
			}
		}
		System.out.println("Whoop die freaking doo");
		keyboard.close();
	}

}

package jalak;


import java.util.*;
import java.io.*;
import jalak.Alak.State;


/**
 * Alak game.
 */
public class Tui {

	private Scanner in;
	
	
	/**
	 * Tui()
	 * Creates a Tui object and the needed input scanner.
	 *
	 * @require none
	 * @ensure A Tui is created.
	 */	
    public Tui () {
		this.in = new Scanner(System.in);
	}
	
	
	
	/**
	 * readInput(String prompt)
	 * Reads a String input from the user.
	 *
	 * @require A message to display to the user so they know what to enter.
	 * @ensure Input String is captured.
	 */	
	public String readInput(String prompt) {
		String input = "";
		System.out.print(prompt); System.out.flush();
		input = in.next();
		return input;
	}
	
	
	
	/**
	 * readInt(String prompt)
	 * Reads an int input from the user.
	 *
	 * @require A message to display to the user so they know what to enter.
	 * @ensure Input int is captured.
	 */	
	public int readInt(String prompt) {
		int input = 0;
		System.out.print(prompt); System.out.flush();
		input = in.nextInt();
		return input;
	}
	
	
	
	/**
	 * displayBoard( State[] data )
	 * Displays the gameboard to the user.
	 *
	 * @require A data array of States.
	 * @ensure The user is shown the gameboard.
	 */	
	public void displayBoard( State[] data ) {
		for (int i=0; i<100; ++i) System.out.println(); // Crappy clear screen attempt.
		String ch = " ";
		System.out.println(" ");
		System.out.print("### ");
		for (int i=0; i < data.length; i++) {
			switch(data[i]) {
				case slotE: 
					ch = Integer.toString(i);
					break;
				case slotX:
					ch = "X";
					break;
				case slotO:
					ch = "O";
					break;
				case slotBx:
				case slotBo:
					ch = "B";
					break;
				default:
					ch = " ";
			}
			if (i != (data.length - 1)) {
				System.out.print(ch + " | ");
			}
			else {
				System.out.print(ch + " ");
			}
		}
		System.out.print("###");
		System.out.println(" ");
	}
	
	
	
	/**
	 * print( String data )
	 * Prints a given String to the screen.
	 *
	 * @require A string
	 * @ensure The user is shown a String
	 */	
	public void print( String data ) {
		System.out.print(data);
	}
	
	
	
	/**
	 * println( String data )
	 * Prints a given String to the screen with a newline character after.
	 *
	 * @require A string
	 * @ensure The user is shown a String
	 */	
	public void println( String data ) {
		System.out.println(data);
	}

}
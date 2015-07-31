package jalak;

import jalak.lang.*;
import jalak.model.*;


/**
 * Alak game.
 */
public class Alak {
	
	// The Basics
	private GameBoard board;
	private Player player1;
	private Player player2;
	private Tui view;
	private Lang lang;
	
	// The Game
	private Player activePlayer;
	private Player winner;
	private boolean gameOver;
	public static enum State {slotX, slotO, slotE, slotBx, slotBo}
	private String error;
	
	
	/**
	 * Alak()
	 * Creates the Alak game.
	 *
	 * @require none
	 * @ensure none
	 */	
	public Alak () {
		
		// Set some starting variables.
		this.view = new Tui();
		this.lang = new Lang();
		this.gameOver = false;
		this.error = "";
		
	}
	
	
	/**
	 * Alak()
	 * Creates the Alak game.
	 *
	 * @require size > 7
	 * @ensure Critical game components exist.
	 */	
	public Alak (int size, String p1name, String p2name) {
	
		// Create a game board and some players.
		this.board = new GameBoard(size);
		this.player1 = new Player(p1name, State.slotX);
		this.player2 = new Player(p2name, State.slotO);
		
		// Set some starting variables.
		this.view = new Tui();
		this.lang = new Lang();
		this.activePlayer = player1;
		this.gameOver = false;
		this.error = "";
		
	}
	
	
	
	/**
	 * start()
	 * Starts the Alak main game loop.
	 *
	 * @require none
	 * @ensure none
	 */	
	public void start() {
		
		// Grab game board size.		
		int size = 0;
		while (size < 7 || size > 26) {
			size = view.readInt(lang.gameSize);
		}
				
		// Grab Player 1 name.
		String p1name = view.readInput(lang.p1Name);
				
		// Grab Player 2 name.
		String p2name = view.readInput(lang.p2Name);
		
		// Create a game board and some players.
		this.board = new GameBoard(size);
		this.player1 = new Player(p1name, State.slotX);
		this.player2 = new Player(p2name, State.slotO);
		this.activePlayer = player1;
		
		// Main game loop.
		while (gameOver == false) {
			
			// Check if the game is over.
			if ( checkGameOver() ) {
				
				gameOver = true;
				
				// Find out who the winner is.
				calculateWinner();
				
				// Output the game ending board.
				view.displayBoard( board.slots() );
				
				// Tell who won.
				view.println( lang.parse( winner.name(), lang.gameOver ) );
				
			}
			else {
			
				// Remove any blocked spaces from the previous turn
				removeBlocked();
		
				// Output the board to the user.
				view.displayBoard( board.slots() );
			
				// Whoever's turn it is takes their turn.
				takeTurn();
			
				// Switch player turns since game isn't over.
				activePlayer = (activePlayer == player1) ? player2 : player1;
			}
		}
	}
	
	
	
	/**
	 * takeTurn()
	 * Executes the players turn.
	 * Will keep asking for a move via requestMove() until it returns true.
	 *
	 * @require none
	 * @ensure move is valid.
	 */	
	public void takeTurn() {
		
		// if there are any errors to display, display them.
		if (error.length() > 0) {
			
			// Display error
			view.println(error);
			
		}
		else {
		
			// Display whos turn it is
			view.println( lang.parse(activePlayer.name(), lang.currentTurn) );
			
		}
		
		// Ask for the move they want to make.
		if ( !requestMove() ) {
		
			// If requested move is not valid...
			view.println(lang.invalidMove);
			takeTurn();
		
		}
	}
	
	
	
	
	/**
	 * requestMove()
	 * Obtains the desired move from the player
	 * and determines if it's valid move. Makes the move and returns true if valid.
	 *
	 * @require none
	 * @ensure i < board.size() && i >= 0
	 */	
	public boolean requestMove() {
		int i = view.readInt(lang.desiredMove);
		if (i < board.size() && i >= 0) {
			if (board.slot(i) == State.slotE) {			
				move(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * move(int)
	 * Makes a move on behalf of the player.
	 *
	 * @require an int >= 0 and < gameBoardSize
	 * @ensure Opponent slots are overturned if applicable.
	 */	
	private void move(int i) {
	
		// Inserts the player's gamepiece into the slot given.
		board.slot(i, activePlayer.gamePiece());
		
		//////////////////////////////////////////////////////////
		// Checks if this move overturns any of the opponent's pieces.
		//////////////////////////////////////////////////////////
		
		if ( forwardCheck(i) ) {
			capture( activePlayer.gamePiece(), i, true);
		}
		
		if ( backwardCheck(i) ) {
			capture( activePlayer.gamePiece(), i, false);
		}	
		
	}
	
	
	/**
	 * backwardCheck()
	 * Checks if there are pieces to be captured to the left of the move slot chosen.
	 *
	 * @require the offset of the gamepiece just placed on the board.
	 * @ensure offSet < board.size() && offSet >= 0
	 */	
	 private boolean backwardCheck(int i) {
		boolean stop;
		boolean backward;
		int cnt;
		State cPiece;
		
		// Grab non-active player
		Player nonActivePlayer = (activePlayer == player1) ? player2 : player1;
		
		// Backward direction
		stop = false;
		backward = false;
		cnt = 1;
		while (stop == false && (i - cnt) >= 0 ) {		
			
			// Grab the current gameboard slot being looked at.
			cPiece = board.slot(i - cnt);
		
			// If the current slot does not contain an opponent's piece...
			if ( nonActivePlayer.gamePiece() != cPiece ) {
				
				// Stop Searching the board.
				stop = true;
				
				// If this slot contains one of the active player's pieces and is not right next to the newly
				// placed piece, turn over the opponent pieces.
				if (cnt > 1 && cPiece == activePlayer.gamePiece() ) {
					return true;
				}
				
			}
			
			// If the current slot holds one of the opponent pieces...
			else if ( nonActivePlayer.gamePiece() == board.slot(i - cnt) ) {
				// Check if the current slot is at the beginning of the board, or else do nothing.
				if ( (i - cnt) == 0 ) {
					return true;
				}
				else {
					cnt++;
				}
			}
			
			// If the current slot is empty or blocked...
			else {
				stop = true;
			}
		}
		return false;
	 }
	
	
	/**
	 * forwardCheck()
	 * Checks if there are pieces to be captured to the right of the move slot chosen.
	 *
	 * @require the offset of the gamepiece just placed on the board.
	 * @ensure offSet < board.size() && offSet >= 0
	 */	
	 private boolean forwardCheck(int i) {
		boolean stop;
		boolean forward;
		int cnt;
		State cPiece;
		
		// Grab non-active player
		Player nonActivePlayer = (activePlayer == player1) ? player2 : player1;
		
		// Forward direction check:
		stop = false;
		forward = false;
		cnt = 1;
		while (stop == false && (i + cnt) < board.size() ) {		
			
			// Grab the current gameboard slot being looked at.
			cPiece = board.slot(i + cnt);
		
			// If the current slot does not contain an opponent's piece...
			if ( nonActivePlayer.gamePiece() != cPiece ) {
				
				// Stop Searching the board.
				stop = true;
				
				// If this slot contains one of the active player's pieces and is not right next to the newly
				// placed piece, turn over the opponent pieces.
				if (cnt > 1 && cPiece == activePlayer.gamePiece() ) {
					return true;
				}
				
			}
			
			// If the current slot holds one of the opponent pieces...
			else if ( nonActivePlayer.gamePiece() == board.slot(i + cnt) ) {
			
				// Check if the current slot is at the end of the board, or else do nothing.
				if ( (i + cnt) == (board.size() - 1) ) {
					return true;
				}
				else {
					cnt++;
				}
				
			}
			
			// If the current slot is empty or blocked...
			else {
				stop = true;
			}
		}
		return false;
	 }
	
	
	/**
	 * capture()
	 * Takes the slot a piece was just put in and a direction to walk through the array
	 * and keeps turning all slots to be blocked UNTIL it encounters a piece of a given type (relPiece),
	 * at which time it stops.
	 *
	 * @require none
	 * @ensure none
	 */	
	private void capture( State relPiece, int offSet, boolean direction) {
		int cnt = 1;
		boolean stop = false;
		State blockedSlot;
		
		// Determines the relevant player.
		Player nonActivePlayer = (activePlayer == player1) ? player2 : player1;
		if (nonActivePlayer == player1) {
			blockedSlot = State.slotBx;
		}
		else {
			blockedSlot = State.slotBo;
		}
		
		// Forward
		if (direction) {
			while ( stop == false ) {
				
				if ( (offSet + cnt) < board.size() ) {
				
					// If the piece in the current slot is NOT what this method is looking for... then do stuff.
					if (board.slot(offSet + cnt) != relPiece) {
						board.slot( (offSet + cnt), blockedSlot );
						cnt++;
					}
					// If the piece matches relPiece, then stop.
					else {
						stop = true;
					}
					
				}
				// If off the board, stop.
				else {
					stop = true;
				}
				
			}
		}
		// Backward
		else {
			while ( stop == false ) {
				
				if ( (offSet - cnt) >= 0 ) {
				
				
					// If the piece in the current slot is NOT what this method is looking for... then do stuff.
					if (board.slot(offSet - cnt) != relPiece) {
						board.slot( (offSet - cnt), blockedSlot );
						cnt++;
					}
					// If the piece matches relPiece, then stop.
					else {
						stop = true;
					}
					
				}
				// If off the board, stop.
				else {
					stop = true;
				}
				
			}
		}
	}
	
	
	
	/**
	 * removeBlocked()
	 * Removes the blocked slots for the inactive player.
	 *
	 * @require none
	 * @ensure none
	 */	
	private void removeBlocked() {
		Player nonActivePlayer = (activePlayer == player1) ? player2 : player1;
		if (nonActivePlayer == player1) {
			board.replace(State.slotBx, State.slotE);
		}
		else {
			board.replace(State.slotBo, State.slotE);
		}
	}
	
	
	/**
	 * checkGameOver()
	 * Sees if the active player has any moves available to them. Returns true if no moves available.
	 *
	 * @require none
	 * @ensure none
	 */
	public boolean checkGameOver() {
		if (board.count(State.slotE) == 0) {
		
			Player nonActivePlayer = (activePlayer == player1) ? player2 : player1;
			if (nonActivePlayer == player1) {
				if (board.count(State.slotBx) == 0) {
					return true;
				}
			}
			else {
				if (board.count(State.slotBo) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * calculateWinner()
	 * Counts up the slots and determines who has more slots, or if it was a tie.
	 *
	 * @require none
	 * @ensure none
	 */	
	public void calculateWinner() {
		int p1 = board.count(State.slotX);
		int p2 = board.count(State.slotO);
		
		if (p1 > p2) {
			winner = player1;
		}
		else if (p1 == p2) {
			Player tie = new Player( player1.name() + " & " + player2.name() );
			winner = tie;
		}
		else {
			winner = player2;
		}
	}
	

}
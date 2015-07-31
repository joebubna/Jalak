package jalak.model;

import jalak.Alak.State;


/**
 * Alak game Player model.
 */
public class Player {

	private String name;		// The name of this player.
	private State gamePiece;	// The game piece this player is using.

	
	/**
	 * Player(String name)
	 * Creates a player with the given name.
	 *
	 * @require none
	 * @ensure A player is created with the given name.
	 */	
    public Player (String name) {
		this.name = name;
	}
	
	
	
	/**
	 * Player(String name, State gamePiece)
	 * Creates a player with the given name and gamePiece.
	 *
	 * @require none
	 * @ensure A player is created with the given name and gamePiece.
	 */	
	public Player (String name, State gamePiece) {
		this.name = name;
		this.gamePiece = gamePiece;
	}
	
	
	
	/**
	 * gamePiece(State gamePiece)
	 * Sets the game piece for this player.
	 *
	 * @require none
	 * @ensure Player game piece is set.
	 */	
	public void gamePiece(State gamePiece) {
		this.gamePiece = gamePiece;
	}
	
	
	
	/**
	 * gamePiece()
	 * Returns the gamePiece for this player.
	 *
	 * @require none
	 * @ensure none
	 */	
	public State gamePiece() {
		return this.gamePiece;
	}
	
	
	
	/**
	 * name()
	 * Returns this players name.
	 *
	 * @require none
	 * @ensure A string is returned.
	 */	
	public String name() {
		return this.name;
	}

}
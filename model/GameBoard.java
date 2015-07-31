package jalak.model;

import jalak.Alak.State;


/**
 * Alak game GameBoard Model.
 */
public class GameBoard {

	private int size;		// The size of this gameboard.
	private State[] slot;	// The slot array
	
	
	
	/**
	 * GameBoard()
	 * Creates the gameboard object.
	 *
	 * @require size > 6 && size < 27.
	 * @ensure GameBoard is created.
	 */	
    public GameBoard (int size) {
		this.size = size;
		this.slot = new State[size];
		for (int i=0; i < slot.length; i++) {
			slot[i] = State.slotE;
		}
	}
	
	
	
	/**
	 * size()
	 * The size of the game board.
	 *
	 * @require none
	 * @ensure none
	 */	
	public int size() {
		return size;
	}
	
	
	
	/**
	 * slot(int)
	 * The value of a board slot.
	 *
	 * @require num >= 0 && num < size
	 * @ensure a value.
	 */	
	public State slot(int num) {
		return slot[num];
	}
	
	
	
	/**
	 * slot(int, State)
	 * Inserts a value in a given slot.
	 *
	 * @require num >= 0 && num < size
	 * @ensure A value is inserted.
	 */	
	public void slot(int num, State val) {
		slot[num] = val;
	}
	
	
	
	/**
	 * slots()
	 * Returns the array of slots.
	 *
	 * @require none
	 * @ensure An array.
	 */	
	public State[] slots() {
		return this.slot;
	}
	
	
	
	/**
	 * count(State)
	 * Counts the number of given item in the slots array.
	 *
	 * @require none
	 * @ensure int >= 0 && int < size
	 */	
	public int count(State item) {
		int c = 0;
		for (int i=0; i < slot.length; i++) {
			if (slot[i] == item) {
				c++;
			}
		}
		return c;
	}
	
	
	
	/**
	 * replace(StateOLD, StateNEW)
	 * Replaces all instances of a (slot) State with that of another State
	 *
	 * @require none
	 * @ensure The first State parameter no longer exists in the slot array.
	 */	
	public void replace(State oldState, State newState) {
		for (int i=0; i < slot.length; i++) {
			if (slot[i] == oldState) {
				slot[i] = newState;
			}
		}
	}

}
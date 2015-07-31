package jalak.lang;


/**
 * Alak game simple language file.
 */
public class Lang {

	// The language to use.
	public String langUsed;
	
	// Language entries
	public String gameSize;
	public String p1Name;
	public String p2Name;
	public String desiredMove;
	public String invalidMove;
	public String currentTurn;
	public String gameOver;

	
	/**
	 * Lang()
	 * Creates a basic language object.
	 *
	 * @require none
	 * @ensure English is set as the language.
	 */	
	public Lang() {
		langUsed = "english";
		loadLang();
	}
	
	
	
	/**
	 * Lang(String lang)
	 * Creates a basic language object with a defined language.
	 *
	 * @require The language desired && the language exists.
	 * @ensure The desired language is set.
	 */
    public Lang(String lang) {
		// Load desired language.
		langUsed = lang;
		loadLang();
	}
	
	
	
	/**
	 * loadLang()
	 * Loads the desired langage into the class members.
	 *
	 * @require Desired language is defined.
	 * @ensure Desired language is loaded.
	 */
	private void loadLang() {
		if (langUsed == "english") {
			gameSize = "What size gameboard would you like? ";
			p1Name = "What would player 1 like to be named? ";
			p2Name = "What would player 2 like to be named? ";
			desiredMove = "Please choose a number cooresponding to your desired move. ";
			invalidMove = "Sorry, but the move you requested is invalid. Please try again. \n";
			currentTurn = "It is now %var%'s turn.";
			gameOver = "%var% has won the game!";
			
		}
	}
	
	
	
	/**
	 * parse( String var, String template )
	 * Takes a String as a "template" and inserts a given String variable into the defined place.
	 * Only parses first found match of variable format %var% in the template String.
	 *
	 * @require Template String must contain a %var%.
	 * @ensure A new string is created.
	 */
	public String parse( String var, String template ) {
		int idx = template.indexOf("%var%");
		return ( template.substring(0, idx) + var + template.substring(idx+5, template.length() ) );
	}

}
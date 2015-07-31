package jalak;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test for a <code>Alak</code>.
 */
public class AlakTest {
  
  private jalak.Alak game;
  
  @Before
  public void setUp () {
	game = new Alak(13, "John1", "John2");
  }
  
  /**
   * Test initial state.
   */
  @Test
  public void testInitialState () {
    assertFalse( game.checkGameOver() );
  }
  
  //////////////////////////////////////
  /* 
  All the other methods in this class take user input (which I DON'T know how to simulate)
  or are loops which call methods that request user input (again, I don't know how to test that), so
  I'm stuck for those reasons. I did use the javadoc to create test classes for the model objects.
  I looked in the book to see if it goes back to discuss testing further after it gets into tui's and
  user input, however, to my dismay the book DOES NOT appear to revisit testing after presenting the basics.
  */

}

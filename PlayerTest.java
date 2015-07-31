package jalak;
import org.junit.*;
import static org.junit.Assert.*;
import jalak.model.*;
import jalak.Alak.State;

/**
 * Test for a <code>Alak Player</code>.
 */
public class PlayerTest {
  
  private Player p1;
  private Player p2;
  
  @Before
  public void setUp () {
	p1 = new Player("John");
	p2 = new Player("Bob", State.slotX);
  }
  
  /**
   * Test iniital state.
   * This also indirectly tests name() and gamePiece().
   */
  @Test
  public void testInitialState () {
    assertEquals( p1.name(), "John");
	assertEquals( p2.name(), "Bob");
	assertEquals( p2.gamePiece(), State.slotX);	
  }
  
}

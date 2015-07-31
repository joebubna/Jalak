package jalak;
import org.junit.*;
import static org.junit.Assert.*;
import jalak.model.*;
import jalak.Alak.State;

/**
 * Test for a <code>Alak GameBoard</code>.
 */
public class GameBoardTest {
  
  private GameBoard gb1;
  private GameBoard gb2;
  
  @Before
  public void setUp () {
	gb1 = new GameBoard(7);
	gb2 = new GameBoard(26);
  }
  
  /**
   * Test inital state.
   * This also indirectly tests size().
   */
  @Test
  public void testInitialState () {
    assertEquals( gb1.size(), 7); // Make sure size is correct.
	assertEquals( gb1.slot(0), State.slotE); // Make sure beginning of array is empty piece
	assertEquals( gb1.slot(6), State.slotE); // ^ same for end.
	assertEquals( gb2.count(State.slotE), 26);	
  }
  
  /**
   * Test Insert
   */
  @Test
  public void testInsert() {
	gb1.slot(0, State.slotX);
	assertEquals( gb1.slot(0), State.slotX);
	
	gb1.slot(6, State.slotO);
	assertEquals( gb1.slot(6), State.slotO);
	
	gb2.slot(25, State.slotX);
	assertEquals( gb2.slot(25), State.slotX);
	gb2.slot(0, State.slotX);
	gb2.slot(1, State.slotX);
	gb2.slot(13, State.slotX);
	gb2.slot(14, State.slotO);
	assertEquals( gb2.count(State.slotX), 4);
	gb2.replace( State.slotX, State.slotO);
	assertEquals( gb2.count(State.slotO), 5);
	assertEquals( gb2.slot(13), State.slotO);
	
  }
  
}

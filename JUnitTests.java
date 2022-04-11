import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JUnitTests {

    @Test
    public void test1() 
	{
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(3, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) < 0, "Straight beats Three of a kind.");
    }
	@Test
	//Pair with larger kicker wins
	public void Test2(){
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(2, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(5, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Pair with larger kicker wins");
	}
	@Test
	//Royal Flush always wins
	public void Test3(){
		Cardable[] cards1 = {new Card(10, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.CLUB), new Card(1, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(9, Cardable.Suit.HEART), new Card(10, Cardable.Suit.HEART), new Card(11, Cardable.Suit.HEART), new Card(12, Cardable.Suit.HEART), new Card(13, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Royal Flush always wins");
	}
	@Test
	//Two pair beat pair
	public void Test4(){
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(4, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB), new Card(1, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(9, Cardable.Suit.HEART), new Card(9, Cardable.Suit.HEART), new Card(10, Cardable.Suit.DIAMOND), new Card(10, Cardable.Suit.HEART), new Card(13, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Two pairs beat pair");
	}
	@Test
	//When comparing equal pairs, highest - to lowest odd card
	public void Test5(){
		Cardable[] cards1 = {new Card(2, Cardable.Suit.CLUB), new Card(2, Cardable.Suit.HEART), new Card(3, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.CLUB), new Card(7, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(2, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(1, Cardable.Suit.HEART), new Card(7, Cardable.Suit.HEART), new Card(2, Cardable.Suit.DIAMOND)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "When comparing equal pairs, highest - to lowest odd card");
	}
	@Test
	//Full house beats flush
	public void Test6(){
		Cardable[] cards1 = {new Card(1, Cardable.Suit.CLUB), new Card(1, Cardable.Suit.HEART), new Card(1, Cardable.Suit.DIAMOND), new Card(4, Cardable.Suit.CLUB), new Card(4, Cardable.Suit.DIAMOND)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(2, Cardable.Suit.HEART), new Card(4, Cardable.Suit.HEART), new Card(6, Cardable.Suit.HEART), new Card(8, Cardable.Suit.HEART), new Card(10, Cardable.Suit.HEART)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) > 0, "Full House Beats flush");
	}
	@Test
	//Royal Flushes split the pot
	public void Test7(){
		Cardable[] cards1 = {new Card(10, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.CLUB), new Card(1, Cardable.Suit.CLUB)};
		TestableHand th1 = new Hand();
		th1.addCards(cards1);
		
		Cardable[] cards2 = {new Card(10, Cardable.Suit.CLUB), new Card(11, Cardable.Suit.CLUB), new Card(12, Cardable.Suit.CLUB), new Card(13, Cardable.Suit.CLUB), new Card(1, Cardable.Suit.CLUB)};
		TestableHand th2 = new Hand();
		th2.addCards(cards2);
		
		assertTrue(th1.compareTo(th2) == 0, "Royal Flushes split the pot");
	}

}


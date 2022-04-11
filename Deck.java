import java.util.Collections;
import java.util.LinkedList;
     // CLASS: Deck
     //
     // Author: Noel Omeiza, 7873571
     //
     // REMARKS: Stores a deck of Cards that has 
	 //          and implements the methods in the Deckable interface 
     //
     //-----------------------------------------
public class Deck implements Deckable {
   private  LinkedList<Cardable> deck;
	private int size;
	
	Deck(){
		size = 0;
		deck = new LinkedList<Cardable>();
		for (int i = 1;i<14;i++){
			for (int j = 0;j<4;j++){
				Cardable c = new Card(i,j);
				deck.add(c);
				size++;
			}
		}
		
		shuffle();
	}

	public void shuffle(){
		Collections.shuffle(deck);
	}
	public void returnToDeck(LinkedList<Cardable> discarded){
		while( !discarded.isEmpty() && size<=NUM_CARDS){
			Cardable c = discarded.pop();
			deck.add(c);
			size++;
		}
	}
		   //------------------------------------------------------
     // Draw a Card
     //
     // PURPOSE:   Removes the card at the top of the dexk
	 //            turns it to match "faceUp" and returns it
     // PARAMETERS:
     //     faceUp : Boolean, true if the carc should face Upwards,
	 //              false otherwise
     // Returns: result : the card drawn from the deck
     //------------------------------------------------------
	public Cardable drawACard(boolean faceUp){
		Cardable result = null;
		if(size>0){
		 result = deck.remove(0);
		result.setFaceUp(faceUp);
		size --;
		}
		return result;
	}
}

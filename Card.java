import java.util.Arrays;
  // CLASS: Card
     //
     // Author: Noel Omeiza, 7873571
     //
     // REMARKS: Stores a Card, an impkimntation of
	 //          the cardable interface  
     //
     //-----------------------------------------
public class Card implements Cardable{
    private int value;
	private boolean selected;
	private boolean facingUp;
	private Suit suit;

	//Constructor
	Card(int a, int j){
		value = a;
		suit = Suit.values()[j];
		selected = false;
		facingUp = false;
	}
    Card(int a, Cardable.Suit s){
		value = a;
		suit = s;
		selected = false;
		facingUp = false;
	}
	public boolean getSelected(){
		return selected;
	}
	public boolean getFaceUp(){
		return facingUp;
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		Suit s = suit;
		return Arrays.asList(Suit.values()).indexOf(s);
	}
	public int getValue(){
		int result = 0;
		if (this != null){
			result = value;
		}
		return result;
	}
	public void switchSelectedState(){
		if(!selected){
			selected = true;
		}else{
			selected = false;
		}
	}
	public void resetSelected(){
		selected = false;
	}
	public void setFaceUp(boolean faceUp){
		facingUp = faceUp;
	}
	//------------------------------------------------------
     // to String
     //
     // PURPOSE:    Returnss the string representation for values int a card
	 //             ie the string containing VALUE/SUIT
     // 
     // Returns: returns the string representing  the cards calue and 
	 //          suit
	 //         i.e 4 of hearts : "4 \u2556"
     //------------------------------------------------------
	public String toString(){
		String result = "";
		
		Suit s = suit;
		int index = Arrays.asList(Suit.values()).indexOf(s);
	
        
	    
        char c = '\u2665';
        if (index==2){
             c+=-5;
        }else if (index==3){
            c+=-2;
       }else{
           c+=index;
       }
       
       
		result+= c;
	    
		if (value == 1){
			result+= "A";
		
		}
		else if (value == 11){
			result+= "J";
		}
		else if (value == 12){
			result+= "Q";
		}
		else if (value == 13){
			result+= "K";
		}
		else{
			result+= value;
		}
		
	
		
		return result;

	}
}

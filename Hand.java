     // CLASS: Hand
     //
     // Author: Noel Omeiza,7874571
     //
     // REMARKS: Represents a playeys hand containing 5 cards at the
     //         most
     //
     //-----------------------------------------
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
public class Hand implements TestableHand{
   private  ArrayList<Cardable> hand;
	private int size;
	private int value;
  
	Hand(){
        value = 0;
        size = 0;

		hand = new ArrayList<Cardable>(5);
		size = 0;
		for (int i=0;i<HAND_SIZE;i++){
			hand.add(null);
		}
		
	}
	 //------------------------------------------------------
     // CompareTo
     //
     // PURPOSE: from the comparable interface,
     //          compares two hands based on given poker rankings
     // PARAMETERS:
     //     o : The handable object to be compared with
     //RESULT:
     //     result : -1 of thr hand is worse than o
     //      result: 0 if the hands are equal
     //      result: 1 if the hand is better than o
     //------------------------------------------------------
	public int compareTo(Handable o) {
		Hand other = (Hand)o;
		SimpleHand A = new SimpleHand(this);
        SimpleHand B = new SimpleHand(other);
        int result = 0;
        this.evaluateHand();
        other.evaluateHand();
        int rankA = this.getValue();
        int rankB = other.getValue();

        if(rankA != rankB){
            if(rankA>rankB){
                result = 1;
            }
            else{
                result = -1;
            }
        }
        else{
            if(rankA == 9 ||rankA == 10){
                //Compare Straight Flushes;
                 result = higherHand(other);
            }
            if(rankA == 8){
                //Compare Four of a kinds;
                if (A.getFour()!=B.getFour()){
                    result = A.getFour()>B.getFour()?1:-1;
                }
                else{

                    result = A.getKicker(1)>B.getKicker(1)?1:-1;
                }

            }
            if(rankA == 7){
                if (A.getThree()!=B.getThree()){
                    result = A.getThree()>B.getThree()?1:-1;
                }
                else{
                    result = A.getKicker(1)>B.getKicker(1)?1:-1;
                }
            }
            if(rankA == 6){
                //Compare Flushes
                result = higherHand(other);
            }
            if(rankA == 5){
                //Compare straight
                result = getHigh()>other.getHigh()?1:-1;
            }
            if(rankA == 4){
                //Compare Trips
                if (A.getThree()!=B.getThree()){
                    result = A.getThree()>B.getThree()?1:-1;
                }
                else{
                    if(A.getKicker(1)!=B.getKicker(1)){
                         result = A.getKicker(1)>B.getKicker(1)?1:-1;
                    }
                    else{
                        result = A.getKicker(2)>B.getKicker(2)?1:-1;
                    }
                }
            }
            if(rankA == 3){
                //Compare Two Pairs
                if (A.getTwo()!=B.getTwo()){
                    result = A.getTwo()>B.getTwo()?1:-1;
                }else{
                    if (A.getSumTwo()!=B.getSumTwo()){
                        result = A.getTwo()>B.getTwo()?1:-1;
                    }
                    else{
                        result = A.getKicker(1)>B.getKicker(1)?1:-1;
                    }
                }
            }
            if(rankA == 2){
                //Compare Pairs
                if (A.getTwo()!=B.getTwo()){
                    result = A.getTwo()>B.getTwo()?1:-1;
                }
                else{
                    if (A.getKicker(1)!=B.getKicker(1)){
                        result = A.getKicker(1)>B.getKicker(1)?1:-1;
                    }else{
                        if (A.getKicker(2)!=B.getKicker(2)){
                            result = A.getKicker(2)>B.getKicker(2)?1:-1;
                        }else{
                            result = A.getKicker(3)>B.getKicker(3)?1:-1;
                        }
                    }
                   
                }
            }
            if(rankA == 1){
                
                result = higherHand(other);
            }
        }

        
       
        return result;
	}
	
	public Cardable getCard(int i) {
		Cardable result =null;
		if(hand.get(i)!=null){
			result = hand.get(i);
		}
		return result;
	}
	//------------------------------------------------------
     // draw
     //
     // PURPOSE: 
     //          Takes a card from the top of deck d and adds it to the hand
     // PARAMETERS:
     //     d: the deck to be drawn from
     //     faceUp : Specifies wether or not the card drwan should face upwards
     //-------------------------------------------------------------------------
	public void draw(Deckable d, boolean faceUp) {
		
		while (size<HAND_SIZE){
			
			for (int i = 0;i<HAND_SIZE;i++){
				if(hand.get(i)==null){
					Cardable c = d.drawACard(faceUp);
                    if (c.getSelected()){
                        c.switchSelectedState();
                    }
					hand.set(i, c);
					size++;
				}
			}
		}
		
	}
	
	public void showAllCards() {
		for (int i = 0;i<HAND_SIZE;i++){
			if(hand.get(i)!=null){
				hand.get(i).setFaceUp(true);
			}
		}
		
	}
		//------------------------------------------------------
     // draw
     //
     // PURPOSE: 
     //          Takes a card from the top of deck d and adds it to the hand
     // PARAMETERS:
     //     d: the deck to be drawn from
     //     faceUp : Specifies wether or not the card drwan should face upwards
     //-------------------------------------------------------------------------
	
	public LinkedList<Cardable> discard() {
		LinkedList<Cardable> result = new LinkedList<>();
		for (int i = 0;i<HAND_SIZE;i++){
			if(hand.get(i)!=null){
				if(hand.get(i).getSelected()==true){
				result.add(hand.get(i));
				hand.set(i,null);
				size--;
				}
			}
		}
		
		return result;
	}
		//------------------------------------------------------
     //return Ceards
     //
     // PURPOSE: 
     //          Empties the hand. setting all cards in the hand to null
     // RETURNS : 
     //          RESULT : A ;inked list containing all the cards previously in the deck
     //-------------------------------------------------------------------------
	public LinkedList<Cardable> returnCards() {
		LinkedList<Cardable> result = new LinkedList<>();
		for (int i = 0;i<HAND_SIZE;i++){
			if(hand.get(i)!=null){
				
				result.add(hand.get(i));
				hand.set(i,null);
				size--;
				
			}
		}
		
		return result;
		
	}
    	//------------------------------------------------------
     // getHigh
     //
     // PURPOSE: 
     //          Retuens the highest value of cards in the hand
     //          NOTE : Ace - 1, K -13
     //RETURNS :The value of the highest Card in the hand
     //-------------------------------------------------------------------------
	public int getHigh(){
		int i = 0;
		Card c  = (Card)hand.get(i);
        int max = c.getValue();
        i = 1;
		while(i<HAND_SIZE){
            c  = (Card)hand.get(i);
            if(c.getValue()>max){
                max = c.getValue();
            }
			i++;
		}
        return max;
	}
    	//------------------------------------------------------
     // getIthlowest
     //
     // PURPOSE: 
     //          Takes an int n and returns the
     //          index of the nth lowest value 
     //          
     // PARAMETERS:
     //     n : The rank of the value to be found(0 indexing)
     //RETURNS:
     // result : -1 if out of bounds and the index of the nth lowet valued card otherwise
     //-------------------------------------------------------------------------
    public int getIthLowest(int n){
		int result = -1;
        int[] arr = new int [HAND_SIZE];
        int i = 0;
        if (n>-1 && n<HAND_SIZE){
	    	while(i<HAND_SIZE){
              Card c  = (Card)hand.get(i);
                arr[i] =  c.getValue();
		    	i++;
	    	}
          
        int[]temp = arr;
        Arrays.sort(arr);
        int tempindex = arr[HAND_SIZE-1-n];
        i = 0;
        boolean found = false;
		while(i<HAND_SIZE){
           
           if(temp[i] == tempindex &&!found){
               result = i;
               found = true;
           }
			i++;
		}
    }
        return result;
	}
   //------------------------------------------------------
     // getIthHigh
     //
     // PURPOSE: 
     //          Takes an int n and returns the
     //         nth highest
     //          
     // PARAMETERS:
     //     n : The rank of the value to be found(0 indexing)
     //RETURNS:
     // result : -1 if out of bounds and the i nth highest valued card otherwise
     //-------------------------------------------------------------------------
    public int getIthHigh(int n){
		int result = 0;
        int[] arr = new int [HAND_SIZE];
        int i = 0;
        if (n>-1 && n<HAND_SIZE){
		while(i<HAND_SIZE){
           Card c  = (Card)hand.get(i);
           arr[i] =  c.getValue();
			i++;
		}
        Arrays.sort(arr);
        result = arr[n];
    }
        return result;
	}
    public int getValue(){
        return value;
    }
   //------------------------------------------------------
     // contains
     //
     // PURPOSE: 
     //          Determises whether a hand contains a card of value val
     //          
     // PARAMETERS:
     //     val : The calue to be searched for
     //RETURNS:
     // result : -true if a card in the hand has value val and false otherwise
     //-------------------------------------------------------------------------
    public boolean contains(int val){
        boolean result = false;
        int i = 0;
        while(i<HAND_SIZE){
            Card c = (Card)hand.get(i);
            if (c.getValue() == val){
                result = true;
            }
            i++;
        }
        return result;
    }
      //------------------------------------------------------
     // inSequence
     //
     // PURPOSE: 
     //          Determises whether a hand contains cards in
     //          sequence by Value. ie 
     //          The hand is in sequence if its highest value is n
     //          and it also contains cards with values n-1,n-2,n-3,n-4
     //          
     // 
     //RETURNS:
     // result : true if the hand is in sequence and false otherwise
     //-------------------------------------------------------------------------
    public boolean inSequence(){
        boolean result = true;
      
        int a = getHigh();
        int i = 1;
        while(i<HAND_SIZE){
            if (!contains(a-i)){
                result = false;
            }
            i++;
        }
        return result;
    }
    //------------------------------------------------------
     // HigherHand
     //
     // PURPOSE: 
     //          Determines which of two hands is higher
     //          based on the first odd value
     //          
     // 
     //RETURNS:
     // result : 0 if hands are equal
     //          1 if hand has higher value than other
     //          -1 otherwise
     //-------------------------------------------------------------------------
    public int higherHand(Hand other){
        int result = 0; 
        int i = 0;
        boolean found=false;
        while(i<HAND_SIZE &&!found ){
            if(getIthHigh(i)!=other.getIthHigh(i)){
                found = true;
                result = getIthHigh(i)>other.getIthHigh(i)?1:-1;
            }
            i++;
        }
        return result;
    }
    	//------------------------------------------------------
     // evaluateHand
     //
     // PURPOSE: 
     //          Returns a string correspondingto the ranking of the hans
     //RETURNS:
     //       RESULT : AString ddescribing ranking of the hand
     //-------------------------------------------------------------------------
    
	public String evaluateHand() {

        
        //When we call evaluateHand to compute the value
        //We will also compare in the case of a tie
    
        value = 1;
        int x = getHigh();
        
        //Nothing by default
		
        SimpleHand simplified = new SimpleHand(this);
        String result = "High Card, "+simplified.parse(x);
        boolean same = simplified.allSame();
        
     
        
        if(same){
            if(inSequence()){
                //Straight Flush
                result = "Straight Flush, "+simplified.parse(x)+" High Card";
                value = 9;
            }
            else{
                //Royal Flush
                if(contains(1)&&contains(10)&&contains(11)&& contains(13)&&contains(13)){
                    result = "Royal Flush, "+simplified.parse(x) + " High card";
                    value = 10;
                }else{
                //Flush
                result = "Flush, "+simplified.parse(x) + " High card";
                value = 6;
                }
            }
        }else{
            if(simplified.quad()){
                //Quads
                result = "Four of a kind "+simplified.toString();
                value = 8;
            }else{
                if(simplified.three() && simplified.two()){
                    //full house
                    result = "Full House! "+simplified.toString();
                    value = 7;
                }
                if(simplified.three() && !simplified.two()){
                    //Three of a kind
                    result = "Three of a kind "+simplified.toString();
                    value = 4;
                }
                if(simplified.two() && simplified.numpairs()==2){
                    // Two pairs 
                    value = 3;
                    result = "Two pairs "+simplified.toString();
                }
                if(simplified.two() && simplified.numpairs()==1 && !simplified.three()){
                    //Pair
                    result ="One pair "+simplified.toString();
                    value = 2;
                }
                if(inSequence() && !simplified.two() && !simplified.quad() && !simplified.three() && !simplified.two()){
                    //Straight
                    value = 5;
                    result = "Straight ,High card"+simplified.parse(x);
                }
            }
        }


    
        
		return result;
	}

	public void selectCard(int i){
			Cardable toSelect = hand.get(i);
			if(toSelect!=null){
				if(!toSelect.getSelected()){
					toSelect.switchSelectedState();
				}
			}
	}

    
    public void addCards(Cardable[] cards) {
        while (size<HAND_SIZE){
			
			for (int i = 0;i<HAND_SIZE;i++){
				if(hand.get(i)==null){
					Cardable c = cards[i];
                    if (c.getSelected()){
                        c.switchSelectedState();
                    }
					hand.set(i, c);
					size++;
				}
			}
		}
    }
}

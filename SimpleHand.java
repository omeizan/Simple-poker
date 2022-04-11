import java.util.ArrayList;
     // CLASS: SimpleHand
     //
     // Author: 7873571
     //
     // REMARKS: A simplified version of the hand class
     //          organised by vakue and suit
     //
     //-----------------------------------------

public class SimpleHand {
   public static final int NUM_SUITS = 4;
   public static final int NUM_VALUES = 13;
   private ArrayList<Cardable>[] cardsBySuit;
   private ArrayList<Cardable>[] cardsByRank;
 
    //public enum Suit {HEART, DIAMOND, SPADE, CLUB};
    Hand x;
    int numpairs;
    int numthrees;
    int numfour;
    Boolean samesuit;
    Boolean noCombo;
    //------------------------------------------------------
     // SimpleHand
     //
     // PURPOSE:    Constructor for simple hand class
    //              takes a hand and sorts it inti two groups one by suit
    //              and one by value
     // PARAMETERs:
     //           h : the hand tro be simplified

     //------------------------------------------------------
  
    SimpleHand(Hand h){
        x= h;
        cardsBySuit = (ArrayList<Cardable>[]) new ArrayList[NUM_SUITS];
        cardsByRank = (ArrayList<Cardable>[]) new ArrayList[NUM_VALUES];
        for (int i = 0;i<NUM_SUITS;i++){
            cardsBySuit[i] = new ArrayList<Cardable>(Handable.HAND_SIZE);
            
        }
        for (int i = 0;i<NUM_VALUES;i++){
            cardsByRank[i] = new ArrayList<Cardable>();
        }
        numpairs = 0;
        numthrees = 0;
        numfour = 0;
        samesuit = false;
        noCombo = true;
        
       //Put the cards into the appropriate lists
        int i = 0;
        while(i<Handable.HAND_SIZE){
            Card c = (Card)h.getCard(i);
            if(c.getSuit() == Cardable.Suit.CLUB){
                cardsBySuit[3].add(c);
                
            }
            if(c.getSuit() == Cardable.Suit.HEART){
                cardsBySuit[0].add(c);
            }
            if(c.getSuit() == Cardable.Suit.SPADE){
                cardsBySuit[2].add(c);
            }
            if(c.getSuit() == Cardable.Suit.DIAMOND){
                cardsBySuit[1].add(c);
            }

            int val = c.getValue();
            cardsByRank[val-1].add(c);
            i++;
        }
        //ASsing the variables based on size of Suits
        i = 0;
        while (i<NUM_SUITS){
            if(cardsBySuit[i].size()==5){
                samesuit = true;
            }
            if(cardsBySuit[i].size()==4){
                numfour++;
            }
            if(cardsBySuit[i].size()==3){
                numthrees++;
            }
            if(cardsBySuit[i].size()==2){
                numpairs++;
            }
            i++;
        } 
       
    }

    public boolean allSame(){
        return samesuit;
    }
        //------------------------------------------------------
     // quad
     //
     // PURPOSE:   Determines wether or not the hand has 4 cards of the same value
     // RETURNS : result :true if there are four cards of the same value and false otherwise

     //------------------------------------------------------
    public boolean quad(){
        boolean result = false;
        int i = 0;
        while(i<NUM_VALUES && !result){
            if(cardsByRank[i].size()==4){
                result = true;
            }
            i++;
        }
        return result;
    }
       //------------------------------------------------------
     // three
     //
     // PURPOSE:   Determines wether or not the hand has 3 cards of the same value
     // RETURNS : result :true if there are 3 cards of the same value and false otherwise

     //------------------------------------------------------
    public boolean three(){
        boolean result = false;
        int i = 0;
        while(i<NUM_VALUES && !result){
            if(cardsByRank[i].size()==3){
                result = true;
            }
            i++;
        }
        return result;
    }
    //------------------------------------------------------
     // three
     //
     // PURPOSE:   Determines wether or not the hand has 2 cards of the same value
     // RETURNS : result :true if there are 2 cards of the same value and false otherwise

     //------------------------------------------------------
    public boolean two(){
        boolean result = false;
        int i = 0;
        while(i<NUM_VALUES){
            if(cardsByRank[i].size()==2){
                result = true;
            }
            i++;
        }
        return result;
    }
    //------------------------------------------------------
     // numpairs
     //
     // PURPOSE:   Determines the number of pairs(2 cards of same value) in the hand
     // RETURNS : result :the number of pairs in the hand

     //------------------------------------------------------
    public int numpairs(){
        int result = 0;
        int i = 0;
        while(i<NUM_VALUES){
            if(cardsByRank[i].size()==2){
                result++;
            }
            i++;
        }
        return result;
    }
    public String parse(int x){
        String h="";
        
        
        if (x == 1){
			h= "A";
		
		}
		else if (x == 11){
			h= "J";
		}
		else if (x == 12){
			h= "Q";
		}
		else if (x == 13){
			h= "K";
		}
		else{
			h = Integer.toString(x);
		}
        return h;
    }
    public String toString(){
        String result = "";
        if(!samesuit){
            {
                if(quad()){
                    //Quads
                    
                    int i = 0;
                    while(i<NUM_VALUES){
                        if(cardsByRank[i].size()==4){
                            result += ",Four "+parse(i+1);
                        }
                        i++;
                    }
                }else{
                    if(three() && two()){
                        //full house
                        int i = 0;
                        while(i<NUM_VALUES){
                            if(cardsByRank[i].size()==3){
                                result += ",Three "+parse(i+1);
                            }
                            if(cardsByRank[i].size()==2){
                                result += ",Two "+parse(i+1);
                            }
                            i++;
                        }
                    }
                    if(three() && !two()){
                        //Three of a kind
                        int i = 0;
                        while(i<NUM_VALUES){
                            if(cardsByRank[i].size()==3){
                                result += ",Three "+parse(i+1);
                            }
                            
                            i++;
                        }
                    }
                    if(two() && numpairs()==2 ){
                        // Two pairs 
                        int i = 0;
                        while(i<NUM_VALUES){
                            if(cardsByRank[i].size()==2){
                                result += ",Two "+parse(i+1);
                            }
                            
                            i++;
                        }
                    }
                    if(two() && numpairs()==1 && !three()){
                        //Pair
                        int i = 0;
                        while(i<NUM_VALUES){
                            if(cardsByRank[i].size()==2){
                                result += ",Two "+parse(i+1);
                            }
                            
                            i++;
                        }
                    }
                   
                }
            }
        }
        return result;
    }
        //------------------------------------------------------
     // geFour
     //
     // PURPOSE:  gets the highest ranking quad in a hand
     // RETURNS : result :the value of the highest ranking quad

     //------------------------------------------------------
    public int getFour(){
        int result = 0;
        boolean found = false;
        int i = NUM_SUITS-1;
        if(quad()){
            while(i>=0 && !found){
                if(cardsByRank[i].size()==4){
                    found = true;
                    result =i+1;
                }
                
                i--;
            }
        }
        return result;
    }
      //------------------------------------------------------
     // getTwo
     //
     // PURPOSE:  gets the highest ranking pair in a hand
     // RETURNS : result :the value of the highest ranking pair 

     //------------------------------------------------------
    public int getTwo(){
        int result = 0;
        boolean found = false;
        int i = NUM_SUITS-1;
        if(quad()){
            while(i>=0 && !found){
                if(cardsByRank[i].size()==2){
                    found = true;
                    result =i+1;
                }
                
                i--;
            }
        }
        return result;
    }
    //------------------------------------------------------
     // getSumTwo
     //
     // PURPOSE:  gets the sum of the value of all pairs in a hand (2 at most)
     // RETURNS : result :sum of the value of all pairs in a hand

     //------------------------------------------------------
    public int getSumTwo(){
        int result = 0;
        
        int i = NUM_SUITS-1;
        if(quad()){
            while(i>=0){
                if(cardsByRank[i].size()==2){
                   
                    result +=i+1;
                }
                
                i--;
            }
        }
        return result;
    }
     //------------------------------------------------------
     // getKicker
     //
     // PURPOSE:  gets the nth kicker(solo card) in the hand
     // RETURNS : result : the value of the nth largest kicker (0 indexing)

     //------------------------------------------------------
    public int getKicker(int n){
        int result = 0;
        boolean found = false;
        int count = 0;
        int i = NUM_SUITS-1;
    
            while(i>=0 && !found){
                if(cardsByRank[i].size()==1){
                    count++;
                    if(count == n){
                        found = true;
                        result = i+1;
                    }
                }
                
                i--;
            }
        
        return result;
    }
    //------------------------------------------------------
     // getTwo
     //
     // PURPOSE:  gets the highest ranking trip(three of a kind) in a hand
     // RETURNS : result :the value of the highest ranking trip

     //------------------------------------------------------
    public int getThree(){
        int result = 0;
        boolean found = false;
        int i = NUM_SUITS-1;
        if(quad()){
            while(i>=0 && !found){
                if(cardsByRank[i].size()==3){
                    found = true;
                    result =i+1;
                }
                
                i--;
            }
        }
        return result;
    }
}

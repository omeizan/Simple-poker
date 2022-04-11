
import java.util.LinkedList;
// INTERFACE : CPU
     //
     // Author: Noel,Omeiza
     //
     // REMARKS: Interface for CPU, declares methods
     //          all CPU classes must implement
     //
     //-----------------------------------------
public interface CPU {
    
    public LinkedList<Cardable> play(); //CPU selects and discards cards
    public String toString();  //Prints the AI name
}


// CLASS  : AI
     //
     // Author: Noel Omeiza, 7873571
     //
     // REMARKS: Stores the hand of an AI player
     //
     //-----------------------------------------
class AI{
    protected Handable AIhand;
    
    AI(Handable h){
        AIhand = (Hand)h;
    }
}
// CLASS  : DumbCPU
     //
     // Author: Noel Omeiza, 7873571
     //
     // REMARKS: Implementation of a DUMB CPU(makes random decisions)
     //
     //-----------------------------------------
class DumbCPU extends AI implements CPU{

    
    DumbCPU(Handable h) {
        super(h);
    }
   
    public String toString() {
      
        return "Dumb CPU";
    }
         //------------------------------------------------------
     // play (DumbCpu.play())
     //
     // PURPOSE:    Impements a play method for a dumb cpu
     //             Picks a random number of cards i
     //             and discards i random cards from the hand
     // Returns: result : a linked list containing all the discarde class
     //------------------------------------------------------
    public LinkedList<Cardable> play() {
        LinkedList<Cardable> result = new LinkedList<>();
        int numCards = (int)(Math.random()*(Handable.HAND_SIZE)+1); //Pick a random number between 1 and 5
        int i = 0;
        while (i < numCards){
            int toSelect = (int)(Math.random()*(Handable.HAND_SIZE)); //Pick a random number between 0 and 4
            Hand temp = (Hand)AIhand;
            if(!temp.getCard(toSelect).getSelected()){
                 temp.getCard(toSelect).switchSelectedState();
            }
            
            i++;
        }
        result = AIhand.discard();
        return result;
        
    }

}

// CLASS  : SmartCPU
     //
      // Author: Noel Omeiza, 7873571
     //
     // REMARKS: Implementation of a Smart CPU
     //          plays based on value of currentr hand,  no peeking
     //
     //-----------------------------------------

class SmartCPU extends AI implements CPU{

    
    SmartCPU(Handable h) {
        super(h);
    }

          //------------------------------------------------------
     // play (SmartCpu.play())
     //
     // PURPOSE:    Impements a play method for a smart cpu
     //             Settles for a flush and anithing higher
     //             otherwise removes all cards if the hand is anothing
     //             and x cards if the card has rank x(x>0,x<6)
     // Returns: result : a linked list containing all the discarded class
     //------------------------------------------------------
    public LinkedList<Cardable> play() {
        
        LinkedList<Cardable> result = new LinkedList<>();
        Hand temp = (Hand)AIhand;
        temp.evaluateHand();
        int initialvalue = temp.getValue();
        //If the hand is a "Nothing"
        if (initialvalue == 0){
            int x = temp.getHigh();
            int i = 0;
            while (i < Handable.HAND_SIZE){
                
                
                if(!temp.getCard(i).getSelected() && i!=x){
                     temp.getCard(i).switchSelectedState();
                }
                i++;
            }
        }

        if (initialvalue > 0 && initialvalue<6){
            //remove numcards -initial value lowest value cards
            int count = 0;
            
            while (count<initialvalue){
                int x = temp.getIthLowest(count);
                if(!temp.getCard(x).getSelected()){
                    temp.getCard(x).switchSelectedState();
               }
                count++;
            }
        }
        result = AIhand.discard();
        return result;
    }
  
    public String toString() {
        
        return "Smart CPU";
    }

   
}

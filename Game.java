// CLASS: Game
     //
     // Author: Noel Omeiza, 7873571
     //
     // REMARKS: What is the purpose of this class? 
     //
     //-----------------------------------------
import java.util.LinkedList;
public class Game implements GameLogicable{
    
   
    private int stage;
	private int count;
	private CPU ai;
    private Handable aiHand;
	private Handable playerHand;
	private Deck d;
	private int playerWins;
	private int AIwins;
    private int ties;
	 //------------------------------------------------------
     // Game(s)
     //
     // PURPOSE:    Constructor for Game class
     // PARAMETERS: String s : specifies what child of AI should be insstantiated
     //     		            "d" for dumb, smart otherwise
     //------------------------------------------------------
	Game(String s){
		d = new Deck();
		aiHand = new Hand();
        if(s=="d"){
		    ai = new DumbCPU(aiHand);
        }else{
            ai = new SmartCPU(aiHand);
        }
		aiHand.draw(d, false);
		playerHand = new Hand();
		playerHand.draw(d, true);
		stage = 1;
		count = 1;
        ties = 0;
        AIwins = 0;
        playerWins = 0;
	}

	public Handable getCPUHand(){
		return aiHand;
	}
	public Handable getHumanHand(){
		return playerHand;
	}
	 //------------------------------------------------------
     // myMethod
     //
     // PURPOSE:    Sets messages based on stage, increments
	 //             stage on each call and calls reset method when 
	 //             a new game should start
     // PARAMETERS:
     //     String[] messages = Array containing strings to be set
	 //                        based on current game states
     // Returns: true always
     //------------------------------------------------------
	public boolean nextState(String[] messages){
		if (stage>MAX_GAME_STATES){
			
			resetGame();
			
		}
		if (stage ==1){
			messages[0] = "Beginning of Game "+count;
			messages[1] = "Player 1, choose which cards to discard";
			messages[2] = "and click on the proceed Button";
			playerHand.showAllCards();
		}
		if(stage ==2){
			messages[0] = "Player 1 has discarded cards";
			messages[1] = ai.toString()+" is thinking";
			
			LinkedList<Cardable> temp = playerHand.discard();	
			d.returnToDeck(temp);
			d.shuffle();
		}
		if(stage ==3){
			messages[0] = ai.toString()+" has discarded cards";
			messages[1] = "Each player will be dealt the same number of cards they discarded";
			LinkedList<Cardable> temp = ai.play();
			d.returnToDeck(temp);
			d.shuffle();
			
		}
		if(stage ==4){
			messages[0] = "Each player has been dealt new cards";
			messages[1] = "Click on proceed to see the winner";
			playerHand.draw(d,true);
			aiHand.draw(d,false);
			
		}
		if(stage ==5){
			messages[0] = ai.toString()+" has: "+ aiHand.evaluateHand();
            messages[1] = "Player 1 has: "+ playerHand.evaluateHand();
            messages[2] = getResults();
            messages[3] = getStats();
			playerHand.showAllCards();
			aiHand.showAllCards();
		}
		if(stage ==6){
			LinkedList<Cardable> temp = playerHand.returnCards();
			d.returnToDeck(temp);
			temp = aiHand.returnCards();
			d.returnToDeck(temp);
			playerHand.draw(d,false);
			aiHand.draw(d,false);
			messages[1] = "Click on proceed to play a new game";
		}
		stage++;
		return true;
	}
	 //------------------------------------------------------
     // getResults
     //
     // PURPOSE:   Returns a string representing thre result of agame
     // PARAMETERS:
     //    
     // Returns: output : string that specifies if a game was won,who won it or a tie
     //------------------------------------------------------
    public String getResults(){
        String output = "Tie Game!";
        int result = playerHand.compareTo(aiHand);
        if (result == 1){
            output = "Player 1 wins!!!";
            playerWins++;
        }
        if (result == -1){
            output = ai.toString()+" Wins !!!";
            AIwins++;
        }
        if (result ==0){
            ties++;
        }
        return output;
    }
    public String getStats(){
        String output = "Player 1 has won "+playerWins+" games. "+ai.toString()+" has won "+AIwins +" games,";
        if(ties!=0){
            output+= " "+ties+" Games tied";
        }
        return output;
    }
	public void resetGame(){

		stage = 1;
		count++;
	}
}

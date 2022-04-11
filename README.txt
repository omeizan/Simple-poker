EXECUTION INSTRUCTIONS
_____________________


   
1. Compiling main Program
_________________________

    - To compile:
		
		javac PokerGame.java
		
	-To run executable;
		
		java PokerGame
		
2.Compiling test program
_________________________
	
	-To compile:
	
		javac -cp .:junit-platform-console-standalone-1.6.0.jar JUnitTests.java
		
	-To run:
	
		java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path

3.Program variations
_________________________

 -To run the program with DumbCPU;
      -----------------------------------
         call the constructor in PokerGme.java with "d" i.e  
            GameLogicable gl =  new Game("d");

      -To run the program with SmartCPU;
      -----------------------------------
         call the constructor in PokerGame.java with any string !="d" i.e  
            GameLogicable gl =  new Game("s");
		 

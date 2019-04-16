package bscardgameserver;

import java.util.ArrayList;

public class BSServerCommunication {
    String lobby;
    int numPlayers;
    String confirmR = "Recieved"; //reciept confirmation
    
    int currentTurn;
    int previousTurn;
    ArrayList[] PlayerHands;
    
    int actor;//player number
    int action;//what they did: 0 is play a card, 1 is challenge, 2 is win
    ArrayList<Integer> cardsPlayed;
    
    BSServerCommunication(String lobbycode)
    {
	lobby = lobbycode;
	PlayerHands = new ArrayList[6];
	cardsPlayed = new ArrayList<>();
    }
}

package bscardgameserver;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lobby extends Game
{
    Queue<Integer> Players;
    DiscardPile pile;
    int Turn;
    int LastTurn;
    int CurrentCard;
    ArrayList<Integer> lastCard;
    int numPlayers;
    String Lobby;
    Integer Winners[];
    boolean emptyPile;
    
    BSServerCommunication comms;
    Server server = new Server();
    Kryo kryo = server.getKryo(); 

    public Lobby(String code) 
    {
	Lobby = code;
	Players = new LinkedList<>();
	pile = new DiscardPile();
	emptyPile = true;
	
	kryo.register(BSServerCommunication.class);
	comms = new BSServerCommunication(Lobby);
	new Thread(server).start();
	try {
	    server.bind(54500, 54500);
	} catch (IOException ex) {
	    Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	server.addListener(new Listener() 
	{
	    @Override
	    public void received (Connection connection, Object object) 
	    {
		if (object instanceof BSServerCommunication) 
		{
		BSServerCommunication request = (BSServerCommunication)object;
		System.out.println(request.confirmR);
		 
		 
		connection.sendTCP(comms);
	      }
	    }
	});
    }

    public void Challenged()
    {
	ArrayList challengeDeck = (ArrayList)pile.empty();
	if (pile.topCard == lastCard) //challenger wrong if condition is met
	    comms.PlayerHands[comms.actor].addAll(challengeDeck);
	else
	    comms.PlayerHands[LastTurn].addAll(challengeDeck);
    }

    public void StartGame(int NumPlayers)
    {
	for(int count = 1; count <= NumPlayers; count++)
	{
	    Players.add(count);
	}
	numPlayers = NumPlayers;
	comms.currentTurn = 1;
	CurrentCard = 0;
	Winners = new Integer[numPlayers - 2];
	distributeCards();
	//NextPlayer();
    }
    
    public void distributeCards()
    {
	ArrayList<Integer> deck = new ArrayList<>();
	for(int counter = 0; counter < 52; counter++)
	{
	    deck.add(counter);
	}
	Collections.shuffle(deck);
	
	//split the cards evenly between the players
	int each = 52 - (52 % numPlayers);
	for(int i = 0; i < numPlayers; i++)
	{
	    for(int j = 0; j < each; j++)
	    {
		comms.PlayerHands[i].add(deck.remove(0));
	    }
	}
	//the remaining cards seed the discard pile
	pile.addCards(deck);
    }

    public void NextPlayer()
    {
	LastTurn = comms.currentTurn;
	comms.currentTurn = Players.poll();
	Players.add(Turn);
	//update the comm file and push it to clients
	
    }
    
    public void PushComms()
    {
	//connection.sendTCP(comms);
    }
}

package bscardgameserver;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import java.io.*;
import java.util.*;

public class Game 
{
    ///*
    public static Queue<Integer> Players = new LinkedList<Integer>();
    public static DiscardPile pile = new DiscardPile();
    public static int Turn = 1;
    public static int LastTurn = 1;
    public static int CurrentCard = 0;
    public static int lastCard;
    public static int numPlayers;

    public static Integer Winners[];
    public static String Lobby;
	
    //*/
    public static void main(String[] args) throws IOException 
    {
	/*
	given inputs from the client, different methods are called
	
	Challenge Sequance: (could integrate into Challenge() method)
	boolean correct = Challenged();
	ArrayList challengeDeck = pile.empty();
	if (correct)
	    //pass challengeDeck to lastTurn player
	else
	    //pass challengeDeck to initiating player
	
	
	*/
        
        
        
        Server server = new Server();
        Kryo kryo = server.getKryo(); 
	kryo.register(BSServerCommunication.class);
//kryo.register(PigeonDispenser.class); 
        //kryo.register(HomingPigeon.class);
        
        server.start();
        server.bind(54555, 54777);
        
        
        server.addListener(new Listener() {
           @Override
           public void received (Connection connection, Object object) {
              if (object instanceof BSServerCommunication) {
                 BSServerCommunication request = (BSServerCommunication)object;
                 System.out.println(request.text1);

                 //BSServerCommunication response = new BSServerCommunication();
                 request.text2 = "Thanks";
                 connection.sendTCP(request);
              }
           }
        });
        
	//System.out.println("Technically both compilable and executable");
    }
    
    public static void distributeCards()
    {
	ArrayList tempcards = new ArrayList<Card>();
	for(int counter = 0; counter < 52; counter++)
	{
	    tempcards.add(counter);
	}
	Collections.shuffle(tempcards);
	Integer deck[] = new Integer[52];
	deck = (Integer[]) tempcards.toArray(deck);
	int counter = 0;
	
	int each = 52 - (52 % numPlayers);
	//assign first 'each' cards to first player, second set of 'each' cards
	//to second player and so on. Need networking format before finalizeing
	//how the card numbers are stored before sending to the clients
	
	while (counter < 52)
	{
	    pile.addCard(new Card(deck[counter]));
	    counter++;
	}
    }

    public static Boolean Challenged()
    {
	return !(pile.topCard.Num == lastCard);
    }

    public static void StartGame(int NumPlayers)
    {
	for(int count = 1; count <= NumPlayers; count++)
	{
	    Players.add(count);
	}
	distributeCards();
	//send update to clients with their cards to enter the game lobby
	NextPlayer();
    }

    public static void NextPlayer()
    {
	LastTurn = Turn;
	Turn = Players.poll();
	Players.add(Turn);
	//send update to players to enable/disable their buttons based on what
	//player number they are
    }

    public static String JoinLobby(String code)
    {
	return "reserved for networking phase";
    }
}

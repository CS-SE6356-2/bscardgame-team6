package bscardgameserver;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import java.io.*;
import java.util.*;

public class Game 
{
    public static int portcounter = 500;
    public static void main(String[] args) throws IOException 
    {
        
        Server server = new Server();
        Kryo kryo = server.getKryo(); 
	kryo.register(BSServerCommunication.class);
        new Thread(server).start();
        server.bind(54777, 54777); //54500-54599 are reserved for lobbies
        
        server.addListener(new ThreadedListener(new Listener() {
           @Override
            public void connected (Connection connection) 
            {
                BSServerCommunication newlobby = new BSServerCommunication(portcounter);
                portcounter++;
                connection.sendTCP(newlobby);
            }
           @Override
           public void received (Connection connection, Object object) {
              if (object instanceof BSServerCommunication) {
                 BSServerCommunication request = (BSServerCommunication)object;
                 System.out.println("Lobby created");

                 connection.sendTCP(request);
              }
           }
        }));
        
	//System.out.println("Technically both compilable and executable");
    }

    public static String JoinLobby(String code)
    {
	
	return "reserved for networking phase";
    }
}

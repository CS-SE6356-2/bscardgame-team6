package bscardgameserver;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import java.io.*;
import java.util.*;

public class Game 
{
    public static void main(String[] args) throws IOException 
    {

        Server server = new Server();
        Kryo kryo = server.getKryo(); 
	kryo.register(BSServerCommunication.class);
        
        new Thread(server).start();
        server.bind(54555, 54777);
        
        server.addListener(new Listener() {
           @Override
           public void received (Connection connection, Object object) {
              if (object instanceof BSServerCommunication) {
                 BSServerCommunication request = (BSServerCommunication)object;
                 System.out.println("Lobby created");

                 connection.sendTCP(request);
              }
           }
        });
        
	//System.out.println("Technically both compilable and executable");
    }

    public static String JoinLobby(String code)
    {
	
	return "reserved for networking phase";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bscardgameclient;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Shravan Jambukesan
 */
public class GameRunner 
{
    static ClientStartupGUI startupGUI = null;
    final String SERVER_IP = "127.0.0.1";
    final int BASE_PORT = 54000;
    String gameCode = "default";
    boolean isLobbyCreator = false;
    Client client;
    int lobbyPort;
    public static volatile BSServerCommunication comm;
    
    public static void main(String args[]) throws IOException 
    {
            
            try
            {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException ex)
            {
            java.util.logging.Logger.getLogger(ClientStartupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            catch (InstantiationException ex)
            {
            java.util.logging.Logger.getLogger(ClientStartupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            catch (IllegalAccessException ex)
            {
            java.util.logging.Logger.getLogger(ClientStartupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            catch (javax.swing.UnsupportedLookAndFeelException ex)
            {
            java.util.logging.Logger.getLogger(ClientStartupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
            java.awt.EventQueue.invokeLater(new Runnable()
            {
            public void run() {
            startupGUI = new ClientStartupGUI(new javax.swing.JFrame(), true);
            startupGUI.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
            }
            });
            startupGUI.setVisible(true);
            }
            });
    }
    
    public GameRunner(String gameCode, boolean isLobbyCreator)
    {
	comm = new BSServerCommunication();
        this.isLobbyCreator = isLobbyCreator;
	initializeCommClient();
	if(isLobbyCreator)
        {
	    registerLobby();
	    //this.gameCode= comm.lobby.toString();
        }
	else
	{
	    this.gameCode = gameCode;
	    lobbyPort = BASE_PORT + Integer.parseInt(gameCode);
	    registerLobby(gameCode);
	}

System.out.println(comm.lobby);
	System.out.println(this.gameCode);
        launchLobbyGUI();
    }    
    public void launchLobbyGUI()
    {
        ClientLobbyGUI lobby = new ClientLobbyGUI(gameCode);
	lobby.setLobbyPort(lobbyPort);
        if(isLobbyCreator)
        {
            lobby.enableLobbyCreatorInterface();
            //registerLobby(gameCode);
            //System.out.println("Lobby Port: " + lobbyPort);
        }
        else
        {
            lobby.connectToServer();
        }
        startupGUI.setVisible(false);
        lobby.setVisible(true);
        lobby.toFront();
        lobby.repaint();
    }
   
    public void initializeCommClient()
    {
        try
        {
            client = new Client();
            Kryo kryo = client.getKryo();
            kryo.register(BSServerCommunication.class);
            kryo.register(java.util.ArrayList.class);
            client.start();
        }
        catch(Exception e)
        {
            System.out.println("Unable to initialize communication client");
        }
    }
    
    public void registerLobby(String gameCode)
    {        
        try
        {
            // Connect to game server lobby with the provided game code
            client.connect(5000, SERVER_IP, lobbyPort, lobbyPort);

            //BSServerCommunication comm = new BSServerCommunication();
            
            //client.sendTCP(gameCode);
	    Listener tempListener;
            client.addListener(tempListener = new Listener() 
            {

            public void received (Connection connection, Object object) 
            {
		if (object instanceof BSServerCommunication) 
		{
		    GameRunner.comm = (BSServerCommunication)object;
		    if(comm.started)
		     {
			 System.out.println("This game lobby has already started");
		     }
		     else
		     {
		       System.out.println("Connecting to lobby: " + comm.lobby);
		       lobbyPort = comm.lobby;
		     }
                    client.notify();
		}
            }
        });
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Unable connect to game server, please check your internet connection and try again.", "Server Connection Failed", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            
        }
    }
    
    public void registerLobby()
    {        
        try
        {
            // Connect to game server and register a new lobby
            client.connect(5000, SERVER_IP, 54777, 54777);

            //BSServerCommunication comm = new BSServerCommunication();
            
            //client.sendTCP(gameCode);
	    Listener tempListener;
            client.addListener(new Listener() 
            {
		public void received (Connection connection, Object object) 
		{
		    if (object instanceof BSServerCommunication) 
		    {
			comm = (BSServerCommunication)object;
			gameCode = comm.lobby.toString();
			lobbyPort = BASE_PORT + Integer.parseInt(gameCode);
			System.out.println("Connecting to lobby: " + gameCode);
		    }
                    client.notify();
		    //connection.close();
		}
	    });
            client.wait();
	    //client.removeListener(tempListener);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Unable connect to game server, please check your internet connection and try again.", "Server Connection Failed", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            
        }
    }
}

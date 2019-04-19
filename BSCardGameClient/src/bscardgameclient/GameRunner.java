/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bscardgameclient;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryo.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Shravan Jambukesan
 */
public class GameRunner 
{
    static ClientStartupGUI startupGUI = null;
    final int BASE_PORT = 5400;
    String gameCode = "";
    boolean isLobbyCreator = false;
    Client client;
    int lobbyPort;
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
        this.gameCode = gameCode;
        this.isLobbyCreator = isLobbyCreator;
        initializeCommClient();
        launchLobbyGUI();
        if(isLobbyCreator)
        {
            registerLobby(gameCode);
        }
    }    
    public void launchLobbyGUI()
    {
        ClientLobbyGUI lobby = new ClientLobbyGUI(gameCode);
        if(isLobbyCreator)
        {
            lobby.enableLobbyCreatorInterface();
            registerLobby(gameCode);
            //System.out.println("Lobby Port: " + lobbyPort);
            //lobby.setLobbyPort(lobbyPort);
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
            new Thread(client).start();
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
            // Connect to game server and register this lobby with the provided game code
            client.connect(5000, "127.0.0.1", 54777, 54777);

            BSServerCommunication comm = new BSServerCommunication();
            
            //client.sendTCP(gameCode);
            client.addListener(new Listener() 
            {

            public void received (Connection connection, Object object) 
            {
               if (object instanceof BSServerCommunication) 
               {
                  BSServerCommunication response = (BSServerCommunication)object;
                  System.out.println("Connected to: " + response.lobby);
                  lobbyPort = response.lobby;
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
    
    
}

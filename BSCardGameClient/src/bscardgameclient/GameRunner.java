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
        checkForInternetConnection();
        if(isLobbyCreator)
        {
            registerLobby(gameCode);
        }
    }
    
    public void checkForInternetConnection()
    {
        /*
            Verfify that our game server is reachable over the internet
        */
        try
        {
            //URL checkConnectionURL = new URL("http://97.99.238.31:54555");
            //URLConnection connection = checkConnectionURL.openConnection();
            //connection.connect();
            System.out.println("Internet connection successful!");
            // If successful, launch the lobby GUI
            launchLobbyGUI();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable connect to game server, please check your internet connection and try again.", "Server Connection Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void launchLobbyGUI()
    {
        ClientLobbyGUI lobby = new ClientLobbyGUI(gameCode);
        if(isLobbyCreator)
        {
            lobby.enableLobbyCreatorInterface();
        }
        startupGUI.setVisible(false);
        lobby.setVisible(true);
        lobby.toFront();
        lobby.repaint();
    }
    
    public void registerLobby(String gameCode)
    {
        int port = BASE_PORT + Integer.parseInt(gameCode);
        /*
        try
        {
            // Connect to game server and register this lobby with the provided game code
            Client client = new Client();
            Kryo kryo = client.getKryo();
            kryo.register(BSServerCommunication.class);

            new Thread(client).start();
            client.connect(5000, "97.99.238.31", 54555, 54777);

            BSServerCommunication comm = new BSServerCommunication(Integer.parseInt(lobbyCode));
            
            client.sendTCP(request);
            client.addListener(new Listener() 
            {

           public void received (Connection connection, Object object) {
              if (object instanceof BSServerCommunication) 
              {
                 BSServerCommunication response = (BSServerCommunication)object;
                 System.out.println(response.text2);
              }
            }
        });
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable connect to game server, please check your internet connection and try again.", "Server Connection Failed", JOptionPane.ERROR_MESSAGE);
        }
        */
    }
    
    
}

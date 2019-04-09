/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bscardgameclient;

import java.net.*;
import javax.swing.*;

/**
 *
 * @author shravanjambukesan
 */
public class GameRunner 
{
    static ClientStartupGUI startupGUI = null;
    String gameCode = "";
    public static void main(String args[]) 
    {
        /*
            Setup cross-platform look and feel so the GUI renders with OS specific style
        */
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

        /* Create and display the dialog */
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
    
    public GameRunner(String gameCode)
    {
        this.gameCode = gameCode;
        checkForInternetConnection();
    }
    
    public void checkForInternetConnection()
    {
        /*
            Verfify that our game server is reachable over the internet
        */
        try
        {
            URL checkConnectionURL = new URL("https://shravanj.com");
            URLConnection connection = checkConnectionURL.openConnection();
            connection.connect();
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
        ClientLobbyGUI lobby = new ClientLobbyGUI();
        startupGUI.setVisible(false);
        lobby.setVisible(true);
        lobby.toFront();
        lobby.repaint();
        
    }
    
    
}

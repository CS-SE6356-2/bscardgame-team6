/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bscardgameclient;

import javax.swing.*;

/**
 *
 * @author Shravan Jambukesan
 */
public class ClientStartupGUI extends javax.swing.JDialog {

    /**
     * Creates new form ClientStartupGUI
     */
    String gameCode = "";
    public ClientStartupGUI(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        setResizable(false);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startupLabel = new javax.swing.JLabel();
        gameCodeEntry = new javax.swing.JTextField();
        createLobbyButton = new javax.swing.JButton();
        joinGameButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        startupLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        startupLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        startupLabel.setText("BS Card Game");

        gameCodeEntry.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gameCodeEntry.setText("Enter Game Code");

        createLobbyButton.setText("Create Lobby");
        createLobbyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createLobbyButtonActionPerformed(evt);
            }
        });

        joinGameButton1.setText("Join Game");
        joinGameButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinGameButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(joinGameButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createLobbyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(gameCodeEntry)
                        .addComponent(startupLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(gameCodeEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(joinGameButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(createLobbyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
        Submit button click event handler
    */
    private void createLobbyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createLobbyButtonActionPerformed
        gameCode = gameCodeEntry.getText();
       // Check if the game code entered meets out requirements
       if(gameCode.isEmpty() || gameCode.length() < 5 || gameCode.contains(" "))
       {
           JOptionPane.showMessageDialog(null, "Please enter a valid Game Code. A valid Game Code is 5 alphanumerical characters long and contains no spaces.", "Invalid Game Code", JOptionPane.ERROR_MESSAGE);
       }
       else
       {
           System.out.println(gameCode);
           // Start the game runner
           GameRunner runner = new GameRunner(gameCode, true);
       }
       
    }//GEN-LAST:event_createLobbyButtonActionPerformed

    private void joinGameButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinGameButton1ActionPerformed
        gameCode = gameCodeEntry.getText();
       // Check if the game code entered meets out requirements
       if(gameCode.isEmpty() || gameCode.length() < 5 || gameCode.contains(" "))
       {
           JOptionPane.showMessageDialog(null, "Please enter a valid Game Code", "Invalid Game Code", JOptionPane.ERROR_MESSAGE);
       }
       else
       {
           System.out.println(gameCode);
           // Start the game runner
           GameRunner runner = new GameRunner(gameCode, false);
       }
    }//GEN-LAST:event_joinGameButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createLobbyButton;
    private javax.swing.JTextField gameCodeEntry;
    private javax.swing.JButton joinGameButton1;
    private javax.swing.JLabel startupLabel;
    // End of variables declaration//GEN-END:variables
}

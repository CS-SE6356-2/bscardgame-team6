/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSCardGameServer;

import bscardgameserver.DiscardPile;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alinta Wang
 */
public class DiscardPileTest {
    
    public DiscardPileTest() {
    }
	@Test
	public void addedCardsTest()
	{
		DiscardPile pile = new DiscardPile();
		int initialPileSize = pile.size();
		ArrayList cards = new ArrayList<java.awt.List>();
		for(int i = 0; i <= 3; i++) {	//adds 4 cards
			cards.add(i);
		}
		pile.addCards(cards);
		int currentPileSize = pile.size();
		boolean added = false;
		if((currentPileSize - initialPileSize) == 4) {
			added = true;
		}
		assertEquals(true, added);
	}
	
	@Test
	public void emptyTest()
	{
		DiscardPile pile = new DiscardPile();
		ArrayList cards = new ArrayList<java.awt.List>();
		for(int i = 0; i <= 3; i++) {	//adds 4 cards
			cards.add(i);
		}
		pile.addCards(cards);
		pile.empty();
		assertEquals(0, pile.size());
	}
    
        @Test
    	public void sizeTest()
	{
		DiscardPile pile = new DiscardPile();
		ArrayList cards = new ArrayList<java.awt.List>();
		for(int i = 0; i <= 3; i++) {	//adds 4 cards
			cards.add(i);
		}
		pile.addCards(cards);
		assertEquals(4, pile.size());
	}

    
}

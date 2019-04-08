package bscardgameclient;

import java.awt.List;
import java.util.ArrayList;

public class DiscardPile {
	
	ArrayList pile = new ArrayList<List>();
	Card topCard;
	
	public void addCard(Card c) {
		pile.add(c);
		topCard = c;
	}
	
	public int size() {
		return pile.size();
	}
	
	public void empty() {
		pile.clear();
		topCard = null;
	}

}

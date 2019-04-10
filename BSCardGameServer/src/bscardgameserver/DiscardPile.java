package bscardgameserver;

import java.util.List;
import java.util.ArrayList;

public class DiscardPile {
	
	ArrayList pile = new ArrayList<Card>();
	Card topCard;
	
	public void addCard(Card c) {
		pile.add(c);
		topCard = c;
	}
	
	public int size() {
		return pile.size();
	}
	
	public List empty() {
		List<Card> copy;
                copy = new ArrayList<>(pile);
                pile.clear();
		topCard = null;
                return copy;
	}

}

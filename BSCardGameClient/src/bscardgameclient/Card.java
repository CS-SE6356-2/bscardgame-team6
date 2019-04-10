package bscardgameclient;

public class Card {

	public enum faceValue {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
	public enum cardSuit {SPADES, CLUBS, DIAMONDS, HEARTS}
	faceValue facevalue;
	cardSuit cardsuit;
	
	public Card(int num) {
		facevalue = faceValue.values()[num % 13];
		
		if (isBetween(num, 0, 12)) 
		    cardsuit = cardSuit.SPADES;
		else if (isBetween(num, 13, 25)) 
		    cardsuit = cardSuit.CLUBS;
		else if (isBetween(num, 26, 38)) 
		    cardsuit = cardSuit.DIAMONDS;
		else 
		    cardsuit = cardSuit.HEARTS;
	}
	
	public faceValue getFaceValue() {
		return facevalue;
	}
	
	public cardSuit getCardSuit() {
		return cardsuit;
	}
	
	public static boolean isBetween(int n, int begin, int end) {
	    return n >= begin && n <= end;
	}
}

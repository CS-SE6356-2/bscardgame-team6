package bscardgameclient;

public class Card {

	public enum faceValue {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
	public enum cardSuit {CLUBS, DIAMONDS, HEARTS, SPADES}
	faceValue faceValue;
	cardSuit cardSuit;
	
	public Card(faceValue f, cardSuit s) {
		faceValue = f;
		cardSuit = s;
	}
	
	public faceValue getFaceValue() {
		return faceValue;
	}
	
	public cardSuit getCardSuit() {
		return cardSuit;
	}

}

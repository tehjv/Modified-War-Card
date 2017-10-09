package com.svi.modifiedwarcard;

/**
 * This class is used to create Card objects.
 */

public class Card {
	private Rank rank;
	private Suit suit;
	private int value;

	/**
	 * Creates card object, sets value, rank and suit value.
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		int initialValue = 0;
		switch (suit) {
		case CLUBS:
			initialValue = 0;
			break;
		case DIAMONDS:
			initialValue = 3;
			break;
		case HEARTS:
			initialValue = 2;
			break;
		case SPADES:
			initialValue = 1;
			break;
		default:
			break;
		}
		switch (rank) {
		case Ace:
			initialValue += 49;
			break;
		case Eight:
			initialValue += 25;
			break;
		case Five:
			initialValue += 13;
			break;
		case Four:
			initialValue += 9;
			break;
		case Jack:
			initialValue += 37;
			break;
		case King:
			initialValue += 45;
			break;
		case Nine:
			initialValue += 29;
			break;
		case Queen:
			initialValue += 41;
			break;
		case Seven:
			initialValue += 21;
			break;
		case Six:
			initialValue += 17;
			break;
		case Ten:
			initialValue += 33;
			break;
		case Three:
			initialValue += 5;
			break;
		case Two:
			initialValue += 1;
			break;
		default:
			break;
		}
		setValue(initialValue);
	}

	/**
	 * Gets card Rank.
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Sets card's Rank.
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	/**
	 * Gets card's Suit.
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Sets card's Suit.
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	/**
	 * Overrides original function to print card properties in format: Suit
	 * initial - Rank initial
	 */
	@Override
	public String toString() {
		String a = "";
		char b = ' ';

		switch (rank) {
		case Ace:
			a = "A";
			break;
		case Eight:
			a = "8";
			break;
		case Five:
			a = "5";
			break;
		case Four:
			a = "4";
			break;
		case Jack:
			a = "J";
			break;
		case King:
			a = "K";
			break;
		case Nine:
			a = "9";
			break;
		case Queen:
			a = "Q";
			break;
		case Seven:
			a = "7";
			break;
		case Six:
			a = "6";
			break;
		case Ten:
			a = "10";
			break;
		case Three:
			a = "3";
			break;
		case Two:
			a = "2";
			break;
		default:
			break;
		}

		switch (suit) {
		case CLUBS:
			b = 'C';
			break;
		case DIAMONDS:
			b = 'D';
			break;
		case HEARTS:
			b = 'H';
			break;
		case SPADES:
			b = 'S';
			break;
		default:
			break;
		}

		return b + "-" + a;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
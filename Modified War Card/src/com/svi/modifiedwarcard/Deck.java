package com.svi.modifiedwarcard;

import java.util.ArrayList;

/**
 * This class creates a Deck object which is just a collection of Card objects
 * with 52 unique Cards.
 */

@SuppressWarnings("serial")
public class Deck extends ArrayList<Card> {

	/**
	 * Creates a deck object which is a collection of 52 unique cards starting
	 * from Ace of Diamonds to 2 of Clubs.
	 */
	public Deck() {
		Suit suit = null;
		Rank rank = null;

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				suit = Suit.DIAMONDS;
			} else if (i == 1) {
				suit = Suit.HEARTS;
			} else if (i == 2) {
				suit = Suit.SPADES;
			} else {
				suit = Suit.CLUBS;
			}
			for (int j = 0; j < 13; j++) {
				if (j == 0) {
					rank = Rank.Ace;
				} else if (j == 1) {
					rank = Rank.King;
				} else if (j == 2) {
					rank = Rank.Queen;
				} else if (j == 3) {
					rank = Rank.Jack;
				} else if (j == 4) {
					rank = Rank.Ten;
				} else if (j == 5) {
					rank = Rank.Nine;
				} else if (j == 6) {
					rank = Rank.Eight;
				} else if (j == 7) {
					rank = Rank.Seven;
				} else if (j == 8) {
					rank = Rank.Six;
				} else if (j == 9) {
					rank = Rank.Five;
				} else if (j == 10) {
					rank = Rank.Four;
				} else if (j == 11) {
					rank = Rank.Three;
				} else if (j == 12) {
					rank = Rank.Two;
				}
				add(new Card(rank, suit));
			}
		}
	}
}
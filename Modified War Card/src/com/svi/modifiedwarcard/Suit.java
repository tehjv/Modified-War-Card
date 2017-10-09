package com.svi.modifiedwarcard;

/**
 * List of possible Suit values.
 */
public enum Suit {
	HEARTS(3), DIAMONDS(4), SPADES(2), CLUBS(1);

	private int value;

	Suit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
};
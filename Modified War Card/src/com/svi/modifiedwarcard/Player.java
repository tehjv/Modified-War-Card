package com.svi.modifiedwarcard;

import java.util.ArrayList;

/**
 * This class creates a Player instance.
 */

@SuppressWarnings("serial")
public class Player extends ArrayList<Card> {
	private int id;

	/**
	 * Creates Player object and set ID.
	 */
	public Player(int id) {
		setID(id);
	}

	/**
	 * Gets player ID.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets player ID.
	 */
	public void setID(int id) {
		this.id = id;
	}
}

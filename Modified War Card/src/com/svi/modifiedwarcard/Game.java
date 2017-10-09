package com.svi.modifiedwarcard;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	// Fields
	private Deck deck = new Deck();
	private ArrayList<Player> players = new ArrayList<Player>();
	private int endFlag;
	private int roundNumber;

	// Constructor
	public Game() {

	}

	public void startGame() {
		// welcome message
		System.out.println("*****WELCOME TO WAR CARD*****\n\n");

		// input for number of decks to use
		System.out.println("Please enter number of Decks to use: ");
		Scanner sc = new Scanner(System.in);
		double userInput = sc.nextDouble();
		int deckNumber = 0;
		while (deckNumber < 2) {
			if (userInput > Integer.MAX_VALUE || userInput < 2 || userInput % 2 != 0) {
				System.out.println("Please only enter an even integer value greater than 0");
				userInput = sc.nextDouble();
			} else {
				deckNumber = (int) userInput;
			}
		}

		// adding decks to game
		for (int i = 0; i < deckNumber; i++) {
			getDeck().addAll(new Deck());
		}

		// input for number of players
		System.out.println("Please enter number of Players: ");
		int playerNumber = 0;
		userInput = sc.nextDouble();
		while (playerNumber < 2) {
			if (userInput > Integer.MAX_VALUE || userInput < 2 || userInput > deck.size() / 2) {
				System.out.println(
						"Please only enter an even value greater than 0 up to half the number of cards to be used");
				userInput = sc.nextDouble();
			} else {
				playerNumber = (int) userInput;
			}
		}

		// input for number of cuts
		System.out.println("Please enter number of Cuts: ");
		int numberOfCuts = 0;
		userInput = sc.nextDouble();
		while (numberOfCuts < 1) {
			if (userInput > Integer.MAX_VALUE || deck.size() % userInput != 0) {
				System.out.println("Please enter a number of cuts divisible with the number of " + deck.size());
				userInput = sc.nextDouble();
			} else {
				numberOfCuts = (int) userInput;
			}
		}

		// input for number of shuffles
		System.out.println("Please enter number of Shuffle: ");
		userInput = sc.nextDouble();
		int numberOfShuffle = 0;
		while (numberOfShuffle < 1) {
			if (userInput > Integer.MAX_VALUE || userInput < 1) {
				System.out.println("Please only enter an integer value greater than 0");
				userInput = sc.nextDouble();
			} else {
				numberOfShuffle = (int) userInput;
			}
		}

		// input for number of max rounds
		System.out.println("Please enter number of Maximum Rounds: ");
		userInput = sc.nextDouble();
		int maxRound = 0;
		while (maxRound < 1) {
			if (userInput > Integer.MAX_VALUE || userInput < 1) {
				System.out.println("Please only enter an integer value greater than 0");
				userInput = sc.nextDouble();
			} else {
				maxRound = (int) userInput;
			}
		}
		sc.close();

		// run game with properties set by user
		runGame(playerNumber, numberOfCuts, numberOfShuffle, maxRound);

	}

	public void runGame(int playerNumber, int numberOfCuts, int numberOfShuffle, int maxRound) {
		// set/create Players
		setPlayers(playerNumber);

		// displaying deck
		System.out.println("\nInitial Deck:");
		System.out.println(getDeck().toString());

		// shuffling deck and displaying shuffled deck
		shuffleRepeatedly(numberOfShuffle, numberOfCuts);
		System.out.println("\nShuffled Deck:");
		System.out.println(getDeck().toString());

		// deal cards to players
		dealCards();

		// display player cards
		System.out.println("\nPLAYER CARDS BEFORE THE ROUNDS START");
		System.out.println("-----------------------------------------");
		displayPlayerCards();

		// rounds processing
		setRoundNumber(0);
		while (endFlag != 1) {

			beginRound();
			System.out.println("\nPLAYER CARDS AFTER ROUND " + getRoundNumber());
			System.out.println("-----------------------------------------");
			displayPlayerCards();

			// checking if only 1 player has cards
			if (getRoundNumber() == maxRound) {
				setEndFlag(1);
			} else {

				setEndFlag(players.size());
				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).size() == 0) {
						setEndFlag(getEndFlag() - 1);
					}
				}
			}
		}
		System.out.println(endGameMessage());
	}

	/**
	 * Handles the War Card rounds.
	 */
	private void beginRound() {
		ArrayList<ArrayList<Card>> table = new ArrayList<ArrayList<Card>>();
		ArrayList<Integer> competitors = new ArrayList<Integer>();
		ArrayList<Integer> winners = new ArrayList<Integer>();
		// placing cards on table, 2 per player
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).size() > 0) {
				table.add(new ArrayList<Card>());
				for (int j = 0; j < 2; j++) {
					if (players.get(i).size() != 0) {
						table.get(table.size() - 1).add(players.get(i).get(0));
						players.get(i).remove(0);
					}
				}
				competitors.add(i);

			}
		}

		// find winning value
		ArrayList<Card> winner = table.get(0);
		int winningValue = 0;
		for (int j = 1; j < table.size(); j++) {
			winner = compareCards(table.get(j), winner);
		}
		for (Card c : winner) {
			winningValue += c.getValue();
		}

		// compare each table stack to winning value and list down winners
		winners = compareToWinningValue(table, winningValue);

		while (winners.size() != 1) {

			// check for ties and add cards to winners' stack
			int winnersWithCards = winners.size();
			if (winners.size() > 1) {
				for (int i = 0; i < winners.size(); i++) {
					if (players.get(competitors.get(winners.get(i))).size() != 0) {
						table.get(winners.get(i)).add(players.get(competitors.get(winners.get(i))).get(0));
						players.get(competitors.get(winners.get(i))).remove(0);
					}
				}
			}

			System.out.println("Table: " + table.toString());
			displayPlayerCards();

			// re-do, find winning value
			winner = table.get(0);
			winningValue = 0;
			for (int j = 1; j < table.size(); j++) {
				winner = compareCards(table.get(j), winner);
			}
			for (Card c : winner) {
				winningValue += c.getValue();
			}

			// re-do, compare each table stack to winning value and list down
			// winners
			winners = compareToWinningValue(table, winningValue);

			System.out.println(winners.toString());

			// checking for players with no more cards
			for (int i = 0; i < winners.size(); i++) {
				if (players.get(competitors.get(winners.get(i))).size() == 0) {
					winnersWithCards -= 1;
				}
			}

			System.out.println(winnersWithCards);

			// If all cards are exhausted between winning players, the game ends
			if (winnersWithCards == 0) {
				setEndFlag(1);
			}
		}

		// add table cards to winner
		if (getEndFlag() != 1) {
			for (int i = winners.get(0); i < table.size(); i++) {
				players.get(competitors.get(winners.get(0))).addAll(table.get(i));
			}
			for (int i = 0; i < winners.get(0); i++) {
				players.get(competitors.get(winners.get(0))).addAll(table.get(i));
			}
		}

		// set round number
		setRoundNumber(getRoundNumber() + 1);
	}

	/**
	 * Returns message at the end of the game.
	 */
	public static void endGameMessage(ArrayList<Player> players, ArrayList<Integer> competitors,
			ArrayList<Integer> winners) {

		// declare winner
		System.out.println("\n-----------------------------------------");
		for (int i = 0; i < winners.size(); i++) {
			if (i == winners.size() - 1) {
				System.out.print("PLAYER" + players.get(competitors.get(winners.get(i))).getID() + " ");
			} else {
				System.out.print("PLAYER" + players.get(competitors.get(winners.get(i))).getID() + " & ");
			}
		}
		System.out.print("HAVE WON THE GAME!");

	}

	/**
	 * Returns message at the end of the game.
	 */
	public String endGameMessage() {
		int mostCards = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).size() > mostCards) {
				mostCards = players.get(i).size();
			}
		}

		// declare winner
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).size() != 0 && players.get(i).size() == mostCards) {
				return "\n-----------------------------------------\nPLAYER" + players.get(i).getID()
						+ " WON THE GAME!";
			}
		}
		return null;
	}

	public ArrayList<Integer> compareToWinningValue(ArrayList<ArrayList<Card>> table, int winningValue) {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		for (int i = 0; i < table.size(); i++) {
			int cardSetValue = 0;

			for (Card c : table.get(i)) {
				cardSetValue += c.getValue();
			}

			if (winningValue == cardSetValue) {
				winners.add(i);
			}
		}
		return winners;
	}

	/**
	 * Used to compare 2 cards.
	 */
	public ArrayList<Card> compareCards(ArrayList<Card> cardSet1, ArrayList<Card> cardSet2) {
		int cardSet1Value = 0;
		int cardSet2Value = 0;

		for (Card c : cardSet1) {
			cardSet1Value += c.getValue();
		}

		for (Card c : cardSet2) {
			cardSet2Value += c.getValue();
		}

		if (cardSet1Value > cardSet2Value) {
			return cardSet1;
		} else if (cardSet1Value < cardSet2Value) {
			return cardSet2;
		} else {
			return cardSet2;
		}
	}

	/**
	 * Handles displaying the cards a player has.
	 */
	public void displayPlayerCards() {
		for (int i = 0; i < players.size(); i++) {
			System.out.println("\nPlayer " + players.get(i).getID() + "'s cards:");
			System.out.println(players.get(i).toString());
		}
	}

	/**
	 * Handles giving each player cards and takes the cards from the top of
	 * deck.
	 */
	public void dealCards() {
		int initialDeckSize = deck.size();
		for (int i = 0; i < initialDeckSize;) {
			for (int j = 0; j < players.size(); j++) {
				if (deck.size() != 0) {
					players.get(j).add(0, deck.get(0));
					deck.remove(0);
				}
				i++;
			}
		}
	}

	/**
	 * Shuffles deck depending on how many times specified.
	 */
	public void shuffleRepeatedly(int numberOfShuffle, int numberOfCuts) {
		for (int i = 0; i < numberOfShuffle; i++) {
			shuffleOnce(deck, numberOfCuts);
		}
	}

	/**
	 * Handles shuffling the deck with a specific process that cuts the deck
	 * depending on number of cuts set, takes card from bottom, starting with
	 * second half of deck.
	 */
	public void shuffleOnce(Deck deck, int numberOfCuts) {
		ArrayList<ArrayList<Card>> deckCuts = new ArrayList<ArrayList<Card>>();
		int cardsInDeck = deck.size();

		for (int i = 0; i < numberOfCuts; i++) {
			deckCuts.add(new ArrayList<Card>());
		}

		for (int j = 0; j < numberOfCuts; j++) {

			for (int i = (deck.size() / numberOfCuts) * j; i < (deck.size() / numberOfCuts) * (j + 1); i++) {
				deckCuts.get(j).add(deck.get(i));
			}

		}

		deck.clear();
		for (int i = 0; i < (cardsInDeck / numberOfCuts); i++) {

			for (int j = 0; j < numberOfCuts; j++) {
				if (deckCuts.get(j).size() != 0) {
					deck.add(deckCuts.get(j).get(i));
				}
			}
		}

	}

	public void setPlayers(int playerNumber) {
		for (int x = 1; x <= playerNumber; x++) {
			addPlayer(new Player(x));
		}
	}

	/**
	 * Adds a Player instance.
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(int endFlag) {
		this.endFlag = endFlag;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

}

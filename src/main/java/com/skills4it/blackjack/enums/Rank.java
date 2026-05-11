package com.skills4it.blackjack.enums;

import static com.skills4it.blackjack.ui.BlackjackConsoleUI.*;

/**
 * Represents the rank/value printed on a card.
 * Each rank also has a Blackjack point value.
 */
public enum Rank {
	TWO(BR_WHITE + "2" + RESET, 2),
	THREE(BR_WHITE + "3" + RESET, 3),
	FOUR(BR_WHITE + "4" + RESET, 4),
	FIVE(BR_WHITE + "5" + RESET, 5),
	SIX(BR_WHITE + "6" + RESET, 6),
	SEVEN(BR_WHITE + "7" + RESET, 7),
	EIGHT(BR_WHITE + "8" + RESET, 8),
	NINE(BR_WHITE + "9" + RESET, 9),
	TEN(BR_WHITE + "10" + RESET, 10),

	// Face cards are worth 10 points in Blackjack.
	JACK(BR_WHITE + "J" + RESET, 10),
	QUEEN(BR_WHITE + "Q" + RESET, 10),
	KING(BR_WHITE + "K" + RESET, 10),

	// Ace is worth 11 by default.
	ACE(BR_WHITE + "A" + RESET, 11);

	// Text shown when displaying the card, for example "A" or "K".
	private final String displayName;

	// Default point value used for Blackjack scoring.
	private final int pointValue;

	/**
	 * Creates a rank with a display name and point value.
	 */
	Rank(String displayName, int pointValue) {
		this.displayName = displayName;
		this.pointValue = pointValue;
	}

	/**
	 * Returns the text shown for this rank.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Returns the Blackjack point value for this rank.
	 */
	public int getPointValue() {
		return pointValue;
	}
}
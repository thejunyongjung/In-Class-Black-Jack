package com.skills4it.blackjack.enums;

/**
 * Represents the four suits in a standard deck of cards.
 */
public enum Suit {
    HEARTS("♥"),
    SPADES("♠"),
    DIAMONDS("♦"),
    CLUBS("♣");

    private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

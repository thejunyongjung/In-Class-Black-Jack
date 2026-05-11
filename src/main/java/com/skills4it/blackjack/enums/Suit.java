package com.skills4it.blackjack.enums;

import static com.skills4it.blackjack.ui.BlackjackConsoleUI.*;

/**
 * Represents the four suits in a standard deck of cards.
 */
public enum Suit {
    HEARTS(RED + BOLD + "♥" + RESET),
    SPADES(BLACK + BOLD + "♠" + RESET),
    DIAMONDS(RED + BOLD + "♦" + RESET),
    CLUBS(BLACK + BOLD + "♣" + RESET);

    private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

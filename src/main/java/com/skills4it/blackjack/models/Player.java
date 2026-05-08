package com.skills4it.blackjack.models;

/**
 * A Player has a Hand.
 *
 * Responsibilities:
 * - know the player's name
 * - store the player's hand
 */
public class Player {
    private final String name;
    private final Hand hand;
    private int chips;
    private int currentBet;

    public Player(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown Player";
        } else {
            this.name = name.trim();
        }
        this.chips = 1000;
        this.currentBet = 0;

        this.hand = new Hand();
    }

    public int getChips() {
        return chips;
    }
    public int getCurrentBet() {
        return currentBet;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void receiveCard(Card card) {
        hand.deal(card);
    }

    public int getScore() {
        return hand.getValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public String toString() {
        return name + " - " + hand.display();
    }
}

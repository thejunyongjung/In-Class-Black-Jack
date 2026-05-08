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

    public Player(String name) {
        if (name == null || name.isBlank()) {
            this.name = "Unknown Player";
        } else {
            this.name = name.trim();
        }

        this.hand = new Hand();
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

package com.skills4it.blackjack.enums;

public enum BettingOption {
    LOW("Low bet", 100),
    MEDIUM("Medium bet", 250),
    LARGE("Large bet", 500),
    ALL_IN("All in", 1000);

    private final String displayName;
    private final int amount;

    BettingOption(String displayName, int amount) {
        this.displayName = displayName;
        this.amount = amount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAmount() {
        return amount;
    }
}



package com.skills4it.blackjack.enums;

import static com.skills4it.blackjack.ui.BlackjackConsoleUI.*;

public enum PlayerRank {
    GOLD(_GOLD + BOLD + "Gold" + RESET),
    SILVER(_SILVER + BOLD + "Silver" + RESET),
    BRONZE(_BRONZE + BOLD + "Bronze" + RESET);

    private final String displayName;

    PlayerRank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

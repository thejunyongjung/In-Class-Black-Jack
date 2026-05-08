package com.skills4it.blackjack;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.ui.BlackjackConsoleUI;
import java.util.Scanner;
import static com.skills4it.blackjack.ui.BlackjackConsoleUI.askForBettingOption;

/**
 * MainApp starts the program and manages console input/output.
 *
 * Teaching point:
 * MainApp coordinates the flow.
 * The real card logic is inside Card, Deck, Hand, Player, and BlackjackGame.
 */
public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        BettingOption bet = askForBettingOption();

        System.out.println();
        System.out.println("Selected bet: " + bet.getDisplayName());
        System.out.println("Bet amount: $" + bet.getAmount());
        System.out.println();

        BlackjackConsoleUI ui =  new BlackjackConsoleUI();
        ui.start();

    }
}

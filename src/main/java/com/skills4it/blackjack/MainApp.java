package com.skills4it.blackjack;

import com.skills4it.blackjack.ui.BlackjackConsoleUI;
import java.util.Scanner;

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

        BlackjackConsoleUI ui =  new BlackjackConsoleUI();
        ui.start();
    }
}

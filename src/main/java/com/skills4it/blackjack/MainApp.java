package com.skills4it.blackjack;

import java.util.ArrayList;
import java.util.List;
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
        printWelcome();

        int numberOfPlayers = askForNumberOfPlayers();
        List<String> playerNames = askForPlayerNames(numberOfPlayers);

        BlackjackGame game = new BlackjackGame(playerNames);
        game.dealStartingCards();

        playTurns(game);
        printResults(game);
    }

    private static void printWelcome() {
        System.out.println("================================");
        System.out.println("        Blackjack Demo");
        System.out.println("================================");
        System.out.println();
    }

    private static int askForNumberOfPlayers() {
        while (true) {
            System.out.print("How many players are playing? ");

            try {
                int numberOfPlayers = Integer.parseInt(scanner.nextLine());

                if (numberOfPlayers >= 2 && numberOfPlayers <= 6) {
                    return numberOfPlayers;
                }

                System.out.println("Please enter a number between 2 and 6.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine();
            names.add(name);
        }

        return names;
    }

    private static void playTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println();
            System.out.println(player.getName() + "'s turn");
            System.out.println(player);

            while (!player.isBust()) {
                System.out.print("Hit or Stay? (h/s): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("s") || choice.equals("stay")) {
                    break;
                }

                if (choice.equals("h") || choice.equals("hit")) {
                    game.hit(player);
                    System.out.println(player);
                } else {
                    System.out.println("Please type h for Hit or s for Stay.");
                }
            }
        }
    }

    private static void printResults(BlackjackGame game) {
        System.out.println();
        System.out.println("================================");
        System.out.println("            Results");
        System.out.println("================================");

        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }

        Player winner = game.determineWinner();

        System.out.println();

        if (winner == null) {
            System.out.println("Everyone busted. There is no winner.");
        } else if (game.hasTieForWinningScore()) {
            System.out.println("There is a tie with " + winner.getScore() + " points.");
        } else {
            System.out.println("Winner: " + winner.getName() + " with " + winner.getScore() + " points.");
            System.out.println(PlayerRank.GOLD.getDisplayName() + " title is given to " + winner.getName());
        }
    }
}

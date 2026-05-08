package com.skills4it.blackjack;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.enums.PlayerRank;
import com.skills4it.blackjack.model.Player;
import com.skills4it.blackjack.game.BlackjackGame;

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

        BettingOption bet = askForBettingOption();

        System.out.println();
        System.out.println("Selected bet: " + bet.getDisplayName());
        System.out.println("Bet amount: $" + bet.getAmount());
        System.out.println();

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

    private static BettingOption askForBettingOption() {
        System.out.println("Choose your betting option:");
        System.out.println("1. Low bet - $100");
        System.out.println("2. Medium bet - $250");
        System.out.println("3. High bet - $500");
        System.out.println("4. VIP bet - $1000");

        while (true) {
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                return BettingOption.LOW;
            } else if (choice.equals("2")) {
                return BettingOption.MEDIUM;
            } else if (choice.equals("3")) {
                return BettingOption.HIGH;
            } else if (choice.equals("4")) {
                return BettingOption.VIP;
            } else {
                System.out.println("Please choose 1, 2, 3, or 4.");
            }
        }
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

    private static PlayerRank getPlayerRank(Player player, List<Player> players) {
        if (player.isBust()) {
            return null;
        }

        int playersWithBetterScore = 0;

        for (Player otherPlayer : players) {
            if (!otherPlayer.isBust() && otherPlayer.getScore() > player.getScore()) {
                playersWithBetterScore++;
            }
        }

        if (playersWithBetterScore == 0) {
            return PlayerRank.GOLD;
        } else if (playersWithBetterScore == 1) {
            return PlayerRank.SILVER;
        } else if (playersWithBetterScore == 2) {
            return PlayerRank.BRONZE;
        } else {
            return null;
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
        }

        System.out.println();

        for (Player player : game.getPlayers()) {
            PlayerRank rank = getPlayerRank(player, game.getPlayers());

            if (rank == null) {
                System.out.println(player.getName() + " has no Rank.");
            } else {
                System.out.println(player.getName() + " receives " + rank.getDisplayName());
            }
        }

    }
}

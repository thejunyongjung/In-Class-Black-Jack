package com.skills4it.blackjack.ui;

import com.skills4it.blackjack.enums.BettingOption;
import com.skills4it.blackjack.enums.PlayerAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skills4it.blackjack.enums.PlayerRank;
import com.skills4it.blackjack.game.BlackjackGame;
import com.skills4it.blackjack.models.Player;

public class BlackjackConsoleUI {
    private static final int MIN_PLAYERS =2;
    private static final int MAX_PLAYERS =6;

    private static Scanner scanner = new Scanner(System.in);

    public void start(){
        printWelcome();

        int numberOfPlayers = askForNumberOfPlayers();
        System.out.println("Number of players: " + numberOfPlayers);
        List<String> playerNames = askForPlayerNames(numberOfPlayers);

        BlackjackGame game = new BlackjackGame(playerNames);
        game.dealStartingCards();

        // ASCII ART for the Game Start
        System.out.println(
                "                                                                                                       \n" +
                "                                                                                                       \n" +
                "   █████████                                         █████████    ███                          ███     \n" +
                "  ███░░░░░███                                       ███░░░░░███ ░░███                         ░░███    \n" +
                " ███     ░░░   ██████   █████████████    ██████    ░███    ░░░  ███████    ██████   ████████  ███████  \n" +
                "░███          ░░░░░███ ░░███░░███░░███  ███░░███   ░░█████████ ░░░███░    ░░░░░███ ░░███░░███░░░███░   \n" +
                "░███    █████  ███████  ░███ ░███ ░███ ░███████     ░░░░░░░░███  ░███      ███████  ░███ ░░░   ░███    \n" +
                "░░███  ░░███  ███░░███  ░███ ░███ ░███ ░███░░░      ███    ░███  ░███ ███ ███░░███  ░███       ░███ ███\n" +
                " ░░█████████ ░░████████ █████░███ █████░░██████    ░░█████████   ░░█████ ░░████████ █████      ░░█████ \n" +
                "  ░░░░░░░░░   ░░░░░░░░ ░░░░░ ░░░ ░░░░░  ░░░░░░      ░░░░░░░░░     ░░░░░   ░░░░░░░░ ░░░░░        ░░░░░  \n" +
                "                                                                                                       \n" +
                "                                                                                                       \n" +
                "                                                                                                       ");

        playTurns(game);
        printResults(game);
    }



    public void printWelcome() {
        System.out.println("================================");
        System.out.println("      Welcome to Blackjack");
        System.out.println("================================");
        System.out.println();
    }

    public int askForNumberOfPlayers() {
        while (true) {
            System.out.print("How many players are playing? ");
            System.out.print("Enter a number between "+ MIN_PLAYERS+" and "+MAX_PLAYERS+ ": ");

            String input = scanner.nextLine().trim();
              if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a number.");
                continue;
              }
            try { int numberOfPlayers = Integer.parseInt(input);

                if(numberOfPlayers >= MIN_PLAYERS && numberOfPlayers <= MAX_PLAYERS){
                    return numberOfPlayers;
                }
                System.out.println("Please enter a number between "+ MIN_PLAYERS + " and "+ MAX_PLAYERS + ".");
            } catch (NumberFormatException exception){
                    System.out.println("'" + input + "' is not a valid whole number." );}
                }
            }

    public static List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine().trim();
            names.add(name);
        }

        return names;
    }

    private void playTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println();
            System.out.println(player.getName() + "'s turn");
            System.out.println(player);

            while (!player.isBust()) {
                PlayerAction action = askForPlayerAction();

                if (action == PlayerAction.STAY) {
                    break;
                }

                if (action == PlayerAction.HIT) {
                    game.hit(player);
                    System.out.println(player);
                }
            }

        }
    }

    private PlayerRank getPlayerRank(Player player, List<Player> players) {
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


    private void printResults(BlackjackGame game) {
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

    private PlayerAction askForPlayerAction() {

        while (true) {
            System.out.print("Hit or Stay (h/s): ");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "h":
                case "hit":
                    return PlayerAction.HIT;
                case "s":
                case "stay":
                    return PlayerAction.STAY;
                default:
                    System.out.println("Please type 'h' for Hit or 's' for Stay.");
            }
        }
    }



    public static BettingOption askForBettingOption() {
        System.out.println("Choose your betting option:");
        System.out.println("1. Low bet - $100");
        System.out.println("2. Medium bet - $250");
        System.out.println("3. Large bet - $500");
        System.out.println("4. All in bet - $1000");

        while (true) {
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                return BettingOption.LOW;
            } else if (choice.equals("2")) {
                return BettingOption.MEDIUM;
            } else if (choice.equals("3")) {
                return BettingOption.LARGE;
            } else if (choice.equals("4")) {
                return BettingOption.ALL_IN;
            } else {
                System.out.println("Please choose 1, 2, 3, or 4.");
            }
        }
    }

}

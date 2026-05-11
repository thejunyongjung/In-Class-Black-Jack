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

    // ANSI COLOR CODES FOR CLI APP DESIGN
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLACK = "\u001B[90m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String BR_CYAN = "\u001B[96m";
    public static final String BR_MAGENTA = "\u001B[95m";
    public static final String BR_WHITE = "\u001B[97m";
    public static final String _GOLD = "\u001B[33m";
    public static final String _SILVER = "\u001B[37m";
    public static final String _BRONZE = "\u001B[38;5;166m";


    private static Scanner scanner = new Scanner(System.in);

    public void start(){
        printWelcome();

        BettingOption bet = askForBettingOption();
        printBettingResult(bet);

        int numberOfPlayers = askForNumberOfPlayers();
        System.out.println("Number of players: " + numberOfPlayers);
        List<String> playerNames = askForPlayerNames(numberOfPlayers);

        BlackjackGame game = new BlackjackGame(playerNames);
        game.dealStartingCards();

        // ASCII ART for the Game Start
        System.out.println(BR_WHITE +
                "                                                                        \n" +
                "                                                                        \n" +
                "██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗\n" +
                "██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ \n" +
                "██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ \n" +
                "██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗\n" +
                "╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝\n" +
                "                                                                          " + RESET);

        playTurns(game);
        printResults(game);
    }



    public void printWelcome() {
        System.out.println(CYAN + "================================");
        System.out.println("      Welcome to Blackjack");
        System.out.println("================================" + RESET);
    }

    public int askForNumberOfPlayers() {
        while (true) {
            System.out.print("How many players are playing? ");
            System.out.print("Enter a number between "+ MIN_PLAYERS+" and "+MAX_PLAYERS+ ": ");

            String input = scanner.nextLine().trim();
              if (input.isEmpty()) {
                System.out.println(_GOLD + "Input cannot be empty. Please enter a number."+ RESET);
                continue;
              }
            try { int numberOfPlayers = Integer.parseInt(input);

                if(numberOfPlayers >= MIN_PLAYERS && numberOfPlayers <= MAX_PLAYERS){
                    return numberOfPlayers;
                }
                System.out.println(_GOLD + "Please enter a number between "+ MIN_PLAYERS + " and "+ MAX_PLAYERS + "." + RESET);
            } catch (NumberFormatException exception){
                    System.out.println(_GOLD + "'" + input + "' is not a valid whole number." + RESET);}
                }
            }

    /*private List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = scanner.nextLine().trim();
            names.add(name);
        }

        return names;
    }*/

    private List<String> askForPlayerNames(int numberOfPlayers) {
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            String name = askForSinglePlayerName(i, names);
            names.add(name);
        }
        return names;
    }

    private String askForSinglePlayerName(int playerNumber, List<String> existingNames) {
        while (true) {
            System.out.print("Enter name for" + BOLD + " player " + playerNumber + RESET + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(_GOLD + "Name cannot be empty." + RESET);
                continue;
            }
            if (nameAlreadyExists(name, existingNames)) {
                System.out.println(_GOLD + "This name is already used. Please choose another name." + RESET);
                continue;
            }
            return name;
        }
    }


    private boolean nameAlreadyExists(String name, List<String> existingNames) {
        for (String existingName : existingNames) {
            if (existingName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private void playTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println();
            System.out.println(BOLD + player.getName() + RESET + "'s turn");
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
        System.out.println(CYAN + "================================");
        System.out.println("            Results");
        System.out.println("================================" + RESET);

        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }

        Player winner = game.determineWinner();

        System.out.println();

        if (winner == null) {
            System.out.println(RED + "Everyone busted. " + RESET + "There is no winner.");
        } else if (game.hasTieForWinningScore()) {
            System.out.println("There is a tie with " + winner.getScore() + " points.");
        } else {
            System.out.println("Winner: " + BOLD + winner.getName() + RESET + " with " + BR_CYAN + winner.getScore() + RESET + " points.");
        }

        System.out.println();

        for (Player player : game.getPlayers()) {
            PlayerRank rank = getPlayerRank(player, game.getPlayers());

            if (rank == null) {
                System.out.println(BOLD + player.getName() + RESET + " has no Rank.");
            } else {
                System.out.println(rank.getDisplayName() + ": " + BOLD + player.getName() + RESET);
            }
        }

    }

    private PlayerAction askForPlayerAction() {

        while (true) {
            System.out.print("Hit or Stay " + BOLD + "(h/s): " + RESET);
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "h":
                case "hit":
                    return PlayerAction.HIT;
                case "s":
                case "stay":
                    return PlayerAction.STAY;
                default:
                    System.out.println(_GOLD + "Please type 'h' for Hit or 's' for Stay." + RESET);
            }
        }
    }



    private BettingOption askForBettingOption() {
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
                System.out.println(_GOLD + "Please choose 1, 2, 3, or 4." + RESET);
            }
        }
    }

    private void printBettingResult(BettingOption bet) {
        System.out.println();
        System.out.println("Selected bet: " + BOLD + bet.getDisplayName() + RESET);
        System.out.println("Bet amount: " + BOLD + "$" + bet.getAmount() + RESET);
        System.out.println();
    }

}

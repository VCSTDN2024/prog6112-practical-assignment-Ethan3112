package com.mycompany.battleshipgame;

import java.awt.Point;
import java.util.Scanner;

/** Console loop & I/O. Called by your BattleshipGame main. */
public class GameEngine {
    private final Board board = new Board();

    public GameEngine() {
        board.setupStandardFleet();
    }

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== BATTLESHIPS ===");
            System.out.println("Type M to show map, F for fleet, or fire at A1..J10. Q quits.");

            boolean playing = true;
            while (playing) {
                System.out.print("Target >> ");
                String token = sc.nextLine().trim();

                if (token.equalsIgnoreCase("Q")) {
                    System.out.println("You quit. Final board (reveal ships):");
                    System.out.println(board.render(true));
                    break;
                }
                if (token.equalsIgnoreCase("M")) {
                    System.out.println(board.render(false));
                    continue;
                }
                if (token.equalsIgnoreCase("F")) {
                    printFleet();
                    continue;
                }

                Point p = Board.parseCoordinate(token);
                Board.FireResult res = board.fire(p);
                switch (res) {
                    case OUT_OF_BOUNDS -> System.out.println("Invalid coordinate. Use A1..J10.");
                    case ALREADY_TARGETED -> System.out.println("Already targeted. Try a new cell.");
                    case MISS -> System.out.println("Miss.");
                    case HIT -> System.out.println("Hit!");
                    case SUNK -> { System.out.println("You sunk a ship!"); printFleet(); }
                    case WIN -> {
                        System.out.println("Hit! Last ship down — YOU WIN!");
                        System.out.println(board.render(true));
                        playing = false;
                    }
                }
            }
        }
    }

    private void printFleet() {
        System.out.println("Fleet status:");
        for (var s : board.getFleet()) System.out.println(" - " + s);
    }
}

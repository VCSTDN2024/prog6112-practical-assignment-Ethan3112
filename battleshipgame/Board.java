package com.mycompany.battleshipgame;

import java.awt.Point;
import java.util.*;

/**
 * 10x10 board. '·' = unknown, 'O' = miss, 'X' = hit.
 */
public class Board {
    public static final int SIZE = 10;

    private final char[][] view = new char[SIZE][SIZE];       // what player sees
    private final boolean[][] occupied = new boolean[SIZE][SIZE]; // ships occupancy (hidden)
    private final List<Ship> fleet = new ArrayList<>();
    private final Random rng;

    public Board() { this(new Random(42)); }                   // deterministic for easier testing
    public Board(Random rng) {
        this.rng = rng;
        for (int r = 0; r < SIZE; r++) Arrays.fill(view[r], '·');
    }

    /** Build standard fleet and place randomly (no overlaps, in-bounds). */
    public void setupStandardFleet() {
        fleet.clear();
        fleet.add(new Carrier());
        fleet.add(new BattleshipShip());
        fleet.add(new Cruiser());
        fleet.add(new Submarine());
        fleet.add(new Destroyer());
        for (Ship s : fleet) placeRandomly(s);
    }

    private void placeRandomly(Ship ship) {
        while (true) {
            boolean horiz = rng.nextBoolean();
            int row = rng.nextInt(SIZE);
            int col = rng.nextInt(SIZE);

            if (horiz) {
                if (col + ship.getSize() > SIZE) continue;
            } else {
                if (row + ship.getSize() > SIZE) continue;
            }

            boolean ok = true;
            for (int i = 0; i < ship.getSize(); i++) {
                int r = row + (horiz ? 0 : i);
                int c = col + (horiz ? i : 0);
                if (occupied[r][c]) { ok = false; break; }
            }
            if (!ok) continue;

            for (int i = 0; i < ship.getSize(); i++) {
                int r = row + (horiz ? 0 : i);
                int c = col + (horiz ? i : 0);
                occupied[r][c] = true;
                ship.addCell(new Point(r, c));
            }
            break;
        }
    }

    public enum FireResult { HIT, MISS, ALREADY_TARGETED, SUNK, WIN, OUT_OF_BOUNDS }

    /** Parse "A1".."J10" to a Point(row, col) or null if invalid. */
    public static Point parseCoordinate(String token) {
        if (token == null || token.isBlank()) return null;
        token = token.trim().toUpperCase(Locale.ROOT);

        char colChar = token.charAt(0);
        if (colChar < 'A' || colChar > 'J') return null;
        int col = colChar - 'A';

        String rowPart = token.substring(1);
        try {
            int rowNum = Integer.parseInt(rowPart);
            if (rowNum < 1 || rowNum > 10) return null;
            return new Point(rowNum - 1, col);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Fire at p; update view/fleet and return result. */
    public FireResult fire(Point p) {
        if (p == null || p.x < 0 || p.x >= SIZE || p.y < 0 || p.y >= SIZE) return FireResult.OUT_OF_BOUNDS;

        char curr = view[p.x][p.y];
        if (curr == 'X' || curr == 'O') return FireResult.ALREADY_TARGETED;

        Ship hit = null;
        for (Ship s : fleet) {
            for (Point c : s.getCells()) { if (c.equals(p)) { hit = s; break; } }
            if (hit != null) break;
        }

        if (hit == null) {
            view[p.x][p.y] = 'O';
            return FireResult.MISS;
        } else {
            hit.registerHit(p);
            view[p.x][p.y] = 'X';
            if (hit.isSunk()) {
                if (allSunk()) return FireResult.WIN;
                return FireResult.SUNK;
            }
            return FireResult.HIT;
        }
    }

    public boolean allSunk() {
        for (Ship s : fleet) if (!s.isSunk()) return false;
        return true;
    }

    /** Pretty render of the player board; reveal=true shows ships (S). */
    public String render(boolean revealShips) {
        StringBuilder sb = new StringBuilder();
        sb.append("    A B C D E F G H I J\n");
        sb.append("    -------------------\n");
        for (int r = 0; r < SIZE; r++) {
            sb.append(String.format("%2d |", r + 1));
            for (int c = 0; c < SIZE; c++) {
                char ch = view[r][c];
                if (revealShips && ch == '·' && occupied[r][c]) sb.append(" S");
                else sb.append(' ').append(ch);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public List<Ship> getFleet() { return Collections.unmodifiableList(fleet); }
}

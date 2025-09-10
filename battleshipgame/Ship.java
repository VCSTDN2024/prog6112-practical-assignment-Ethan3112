package com.mycompany.battleshipgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/** Abstract base for all ships. */
public abstract class Ship {
    private final String name;
    private final int size;
    private final List<Point> cells = new ArrayList<>();
    private int hits = 0;

    protected Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public int getHits() { return hits; }
    public List<Point> getCells() { return cells; }

    void addCell(Point p) { cells.add(p); }

    /** Register a hit if p belongs to this ship.
     * @param p
     * @return  */
    public boolean registerHit(Point p) {
        for (Point c : cells) {
            if (c.equals(p)) { hits++; return true; }
        }
        return false;
    }

    public boolean isSunk() { return hits >= size; }

    @Override public String toString() {
        return name + " (" + size + ") hits=" + hits + "/" + size + (isSunk() ? " [SUNK]" : "");
    }
}

package org.digitalsmile.hexgrid.hexagon;

import java.util.Arrays;
import java.util.Objects;

public class Hexagon {

    // --- Extension Hook ---

    /**
     * The factory used by all geometric operations to create new Hexagon instances.
     * Game extensions (like GameHex) must override this static field in a static
     * block to ensure all math results return the extended type.
     */
    protected static IHexagonFactory factory = Hexagon::new;

    // --- Internal State ---

    private final int q, r, s;
    private final BaseHexagon base;

    // --- Constructor (Used by Library Users) ---

    public Hexagon(int q, int r, int s) {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("q + r + s must be 0");
        }
        this.q = q;
        this.r = r;
        this.s = s;
        // Initialize the base worker with the coordinates and the current factory
        this.base = new BaseHexagon(q, r, s, factory);
    }

    // --- Public Accessors ---

    public int q() { return q; }
    public int r() { return r; }
    public int s() { return s; }

    // --- Geometric Operations (Delegation) ---

    public Hexagon add(Hexagon hexagon) {
        return base.add(hexagon);
    }

    public Hexagon subtract(Hexagon hexagon) {
        return base.subtract(hexagon);
    }

    public Hexagon scale(int unitScale) {
        return base.scale(unitScale);
    }

    public Hexagon rotateLeft() {
        return base.rotateLeft();
    }

    public Hexagon rotateRight() {
        return base.rotateRight();
    }

    public Hexagon reflectQ() {
        return base.reflectQ();
    }

    public Hexagon reflectR() {
        return base.reflectR();
    }

    public Hexagon reflectS() {
        return base.reflectS();
    }

    public Hexagon negate() {
        return base.negate();
    }

    public HexagonDirection direction(Hexagon hexagon) {
        return Arrays.stream(HexagonDirection.values())
                .filter(direction -> add(direction.getDeltaHexagon()).equals(hexagon))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Hexagon is not a direct neighbor."));
    }

    public Hexagon neighbor(HexagonDirection direction) {
        return base.neighbor(direction);
    }

    public Hexagon diagonalNeighbor(int direction) {
        return base.diagonalNeighbor(direction);
    }

    public int distance(Hexagon hexagon) {
        return base.distance(hexagon);
    }

    public int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Hexagon hexagon)) return false;

        return q == hexagon.q() && r == hexagon.r() && s == hexagon.s();
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r, s);
    }

    @Override
    public String toString() {
        return "(" + q + ", " + r + ", " + s + ")";
    }
}

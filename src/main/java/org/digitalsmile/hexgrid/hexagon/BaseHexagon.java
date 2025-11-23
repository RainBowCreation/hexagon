package org.digitalsmile.hexgrid.hexagon;

import java.util.List;

public class BaseHexagon implements IHexagon{
    private final int q, r, s;
    private final IHexagonFactory factory;

    // Use HexagonFactory to produce the result object
    private static final List<Hexagon> DIAGONALS = List.of(
            new Hexagon(2, -1, -1), new Hexagon(1, -2, 1), new Hexagon(-1, -1, 2),
            new Hexagon(-2, 1, 1), new Hexagon(-1, 2, -1), new Hexagon(1, 1, -2)
    );

    BaseHexagon(int q, int r, int s, IHexagonFactory factory) {
        this.q = q;
        this.r = r;
        this.s = s;
        this.factory = factory;
    }

    // --- Geometric Operations ---

    @Override
    public Hexagon add(Hexagon hexagon) {
        return factory.create(q + hexagon.q(), r + hexagon.r(), s + hexagon.s());
    }

    @Override
    public Hexagon subtract(Hexagon hexagon) {
        return factory.create(q - hexagon.q(), r - hexagon.r(), s - hexagon.s());
    }

    @Override
    public Hexagon scale(int unitScale) {
        return factory.create(q * unitScale, r * unitScale, s * unitScale);
    }

    @Override
    public Hexagon rotateLeft() {
        return factory.create(-s, -q, -r);
    }

    @Override
    public Hexagon rotateRight() {
        return factory.create(-r, -s, -q);
    }

    @Override
    public Hexagon reflectQ() {
        return factory.create(q, s, r);
    }

    @Override
    public Hexagon reflectR() {
        return factory.create(s, r, q);
    }

    @Override
    public Hexagon reflectS() {
        return factory.create(r, q, s);
    }

    @Override
    public Hexagon negate() {
        return factory.create(-q, -r, -s);
    }

    // --- Neighbor and Distance Operations ---

    // Note: Direction lookup is done on the Hexagon class due to its complexity
    @Override
    public Hexagon neighbor(HexagonDirection direction) {
        return add(direction.getDeltaHexagon());
    }

    @Override
    public Hexagon diagonalNeighbor(int direction) {
        if (direction < 0 || direction >= DIAGONALS.size()) {
            throw new IndexOutOfBoundsException("Direction index must be between 0 and " + (DIAGONALS.size() - 1));
        }
        return add(DIAGONALS.get(direction));
    }

    @Override
    public int distance(Hexagon hexagon) {
        return subtract(hexagon).length();
    }

    @Override
    public int length() {
        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }
}

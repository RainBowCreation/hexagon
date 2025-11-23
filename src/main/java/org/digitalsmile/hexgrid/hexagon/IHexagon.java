package org.digitalsmile.hexgrid.hexagon;

public interface IHexagon {
    Hexagon add(Hexagon hexagon);
    Hexagon subtract(Hexagon hexagon);
    Hexagon scale(int unitScale);
    Hexagon rotateLeft();
    Hexagon rotateRight();
    Hexagon reflectQ();
    Hexagon reflectR();
    Hexagon reflectS();
    Hexagon negate();
    Hexagon neighbor(HexagonDirection direction);
    Hexagon diagonalNeighbor(int direction);
    int distance(Hexagon hexagon);
    int length();
}
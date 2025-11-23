package org.digitalsmile.hexgrid.hexagon;

@FunctionalInterface
interface IHexagonFactory {
    Hexagon create(int q, int r, int s);
}

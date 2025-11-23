package org.digitalsmile.hexgrid.hexagon;

@FunctionalInterface
public interface IHexagonFactory {
    Hexagon create(int q, int r, int s);
}

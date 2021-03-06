package com.github.martinfrank.delauny.map;

import java.util.List;

public interface Polygon {

    List<Edge> getEdges();

    Node getCenter();

    boolean surrounds(Node n);
}

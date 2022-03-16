package com.github.martinfrank.delauny.map;

import java.util.List;


//FIXME from Polygon!
public interface Triangle {

    Node getA();

    Node getB();

    Node getC();

    Edge getAB();

    Edge getAC();

    Edge getBC();

    Node getCenter();

    double getArea();

    boolean surrounds(Node node);

    List<Node> getNodes();

    List<Edge> getEdges();

    boolean isInCircumference(Node x);
}

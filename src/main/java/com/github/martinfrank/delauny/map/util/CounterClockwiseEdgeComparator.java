package com.github.martinfrank.delauny.map.util;


import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.model.DefaultNode;

import java.util.Comparator;

//https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
public class CounterClockwiseEdgeComparator implements Comparator<Edge> {

    private final Node center;
    private final CounterClockwiseNodeComparator nodeComparator;

    public CounterClockwiseEdgeComparator(Node center) {
        this.center = center;
        this.nodeComparator = new CounterClockwiseNodeComparator(center);
    }

    public CounterClockwiseEdgeComparator() {
        this(new DefaultNode(0, 0));
    }

    @Override
    public int compare(Edge e1, Edge e2) {
        Node distant1 = e1.getA().equals(center) ? e1.getB() : e1.getA();
        Node distant2 = e2.getA().equals(center) ? e2.getB() : e2.getA();
        return nodeComparator.compare(distant1, distant2);
    }
}

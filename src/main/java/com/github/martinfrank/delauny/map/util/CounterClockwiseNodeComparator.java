package com.github.martinfrank.delauny.map.util;


import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.model.DefaultNode;

import java.util.Comparator;
//https://en.wikipedia.org/wiki/Polar_coordinate_system#Converting_between_polar_and_Cartesian_coordinates
public class CounterClockwiseNodeComparator implements Comparator<Node> {

    private final Node center;

    public CounterClockwiseNodeComparator(Node a, Node b, Node c) {
        this(new DefaultNode((a.getX() + b.getX() + c.getX()) / 3d, (a.getY() + b.getY() + c.getY()) / 3d));
    }

    public CounterClockwiseNodeComparator(Node center) {
        this.center = center;
    }

    public CounterClockwiseNodeComparator() {
        this(new DefaultNode(0, 0));
    }

    @Override
    public int compare(Node o1, Node o2) {
        double theta1 = Math.atan2(center.getY() - o1.getY(), center.getX() - o1.getX());
        double theta2 = Math.atan2(center.getY() - o2.getY(), center.getX() - o2.getX());
        return Double.compare(theta1, theta2);
    }
}

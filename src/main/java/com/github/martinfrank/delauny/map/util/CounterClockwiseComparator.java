package com.github.martinfrank.delauny.map.util;


import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.model.DefaultNode;

import java.util.Comparator;

public class CounterClockwiseComparator implements Comparator<Node> {

    private final Node center;

    public CounterClockwiseComparator(Node a, Node b, Node c) {
        this.center = new DefaultNode((a.getX() + b.getX() + c.getX()) / 3d, (a.getY() + b.getY() + c.getY()) / 3d);
    }

    public CounterClockwiseComparator() {
        this.center = new DefaultNode(0, 0);
    }

    @Override
    public int compare(Node o1, Node o2) {
        double theta1 = Math.atan2(center.getY() - o1.getY(), center.getX() - o1.getX());
        double theta2 = Math.atan2(center.getY() - o2.getY(), center.getX() - o2.getX());
        return Double.compare(theta1, theta2);
    }
}

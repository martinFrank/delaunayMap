package com.github.martinfrank.delauny.map.model;

import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.util.CounterClockwiseNodeComparator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DefaultEdge implements Edge {

    private final Node a;
    private final Node b;
    private final double length;

    public DefaultEdge(Node a, Node b) {
        this.a = a;
        this.b = b;
        length = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
    }


    @Override
    public Node getA() {
        return a;
    }

    @Override
    public Node getB() {
        return b;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return a + " -> " + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultEdge that = (DefaultEdge) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) ||
                Objects.equals(a, that.b) && Objects.equals(b, that.a);
    }

    @Override
    public int hashCode() {
        List<Node> nodes = Arrays.asList(a, b);
        nodes.sort(new CounterClockwiseNodeComparator(a, b, new DefaultNode(0, 0)));
        return Objects.hash(nodes.get(0), nodes.get(1));
    }
}

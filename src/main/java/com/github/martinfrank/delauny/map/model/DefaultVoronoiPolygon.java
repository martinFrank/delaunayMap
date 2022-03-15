package com.github.martinfrank.delauny.map.model;

import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.VoronoiPolygon;
import com.github.martinfrank.delauny.map.util.CounterClockwiseNodeComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultVoronoiPolygon implements VoronoiPolygon {

    private final List<Node> nodes;
    private final List<Edge> edges = new ArrayList<>();
    private final Node center;

    public DefaultVoronoiPolygon(Collection<Node> nodes, Node center) {
        this.nodes = new ArrayList<>(nodes);
        this.nodes.sort(new CounterClockwiseNodeComparator(center));
        this.center = center;
        createEdges();

    }

    private void createEdges() {
        for (int i = 0; i < nodes.size(); i++) {
            Node a = nodes.get(i);
            Node b = nodes.get(i == 0 ? nodes.size() - 1 : i - 1);
            edges.add(new DefaultEdge(a, b));
        }
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public Node getCenter() {
        return center;
    }

    @Override
    public boolean surrounds(Node n) {
        //winding rule
        //https://algorithmtutor.com/Computational-Geometry/Check-if-a-point-is-inside-a-polygon/
        for (int i = 0; i < nodes.size(); i++) {
            Node a = nodes.get(i);
            Node b = nodes.get(i == 0 ? nodes.size() - 1 : i - 1);

            double bigA = -1 * (b.getY() - a.getY());
            double bigB = b.getX() - a.getX();
            double bigC = -1 * (bigA * a.getX() + bigB * a.getY());

            double d = bigA * n.getX() + bigB * n.getY() + bigC;

            if (d > 0) {
                return false;
            }
        }
        return true;
    }
}

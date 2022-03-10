package com.github.martinfrank.delauny.map.util;


import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.Triangle;
import com.github.martinfrank.delauny.map.model.DefaultTriangle;

import java.util.*;

public class Flipper {

    private Flipper() {

    }

    public static List<Triangle> flip(Triangle t1, Triangle t2, Edge edge) {

        List<Node> withoutA = getNodes(t1, t2);
        withoutA.remove(edge.getA());
        Triangle t3 = new DefaultTriangle(withoutA.get(0), withoutA.get(1), withoutA.get(2));

        List<Node> withoutB = getNodes(t1, t2);
        withoutB.remove(edge.getB());
        Triangle t4 = new DefaultTriangle(withoutB.get(0), withoutB.get(1), withoutB.get(2));

        return Arrays.asList(t3, t4);
    }

    private static List<Node> getNodes(Triangle t1, Triangle t2) {
        Set<Node> nodes = new HashSet<>(t1.getNodes());
        nodes.addAll(t2.getNodes());
        return new ArrayList<>(nodes);
    }
}

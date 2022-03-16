package com.github.martinfrank.delauny.map.util;

import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.Triangle;

import java.util.ArrayList;
import java.util.List;

public class CircumferenceConditionChecker {

    private CircumferenceConditionChecker() {

    }

    public static boolean isViolated(Triangle t1, Triangle t2) {
        List<Node> xNodes = new ArrayList<>(t2.getNodes());
        xNodes.removeAll(t1.getNodes());
        Node x = xNodes.get(0);

        List<Node> yNodes = new ArrayList<>(t1.getNodes());
        yNodes.removeAll(t2.getNodes());
        Node y = yNodes.get(0);
        return t1.isInCircumference(x) || t2.isInCircumference(y);
    }
}

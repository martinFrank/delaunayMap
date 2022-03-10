package com.github.martinfrank.delauny.map.util;

import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.Triangle;

import java.util.ArrayList;
import java.util.List;

public class CircumferenceConditionChecker {

    private CircumferenceConditionChecker() {

    }

    public static boolean isViolated(Triangle t1, Triangle t2) {
        List<Node> nodes = new ArrayList<>(t2.getNodes());
        nodes.removeAll(t1.getNodes());
        double det = Matrix.getDeterminante(t1.getA(), t1.getB(), t1.getC(), nodes.get(0));
        return det > 0;
    }
}

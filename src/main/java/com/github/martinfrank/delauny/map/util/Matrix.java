package com.github.martinfrank.delauny.map.util;

import com.github.martinfrank.delauny.map.Node;

import java.util.Arrays;
import java.util.List;

public class Matrix {

    public static double getDeterminante(Node i, Node j, Node k, Node x) {
        List<Node> nodes = Arrays.asList(i, j, k);
        nodes.sort(new CounterClockwiseComparator(i, j, k));
        Node a = nodes.get(0);
        Node b = nodes.get(1);
        Node c = nodes.get(2);

        double m11 = a.getX() - x.getX();
        double m12 = a.getY() - x.getY();
        double m13 = (Math.pow(a.getX(), 2) - Math.pow(x.getX(), 2)) +
                (Math.pow(a.getY(), 2) - Math.pow(x.getY(), 2));

        double m21 = b.getX() - x.getX();
        double m22 = b.getY() - x.getY();
        double m23 = (Math.pow(b.getX(), 2) - Math.pow(x.getX(), 2)) +
                (Math.pow(b.getY(), 2) - Math.pow(x.getY(), 2));

        double m31 = c.getX() - x.getX();
        double m32 = c.getY() - x.getY();
        double m33 = (Math.pow(c.getX(), 2) - Math.pow(x.getX(), 2)) +
                (Math.pow(c.getY(), 2) - Math.pow(x.getY(), 2));

        return (m11 * m22 * m33) + (m12 * m23 * m31) + (m13 * m21 * m32) -
                (m13 * m22 * m31) - (m11 * m23 * m32) - (m12 * m21 * m33);
    }

}

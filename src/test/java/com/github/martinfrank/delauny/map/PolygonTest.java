package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.model.DefaultPolygon;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PolygonTest {

    @Test
    public void surroundTest() {
        Node a = new DefaultNode(0, 0);
        Node b = new DefaultNode(1, -1);
        Node c = new DefaultNode(2, 0);
        Node d = new DefaultNode(2, 1);
        Node e = new DefaultNode(1, 2);
        Node f = new DefaultNode(0, 1);
        Node center = new DefaultNode(1, 0.5);
        List<Node> nodes = Arrays.asList(a, b, c, d, e, f);
        Polygon polygon = new DefaultPolygon(nodes, center);

        Node inside = new DefaultNode(0.5, 0.5);
        Node outside = new DefaultNode(-1, 1);
        Node outside2 = new DefaultNode(2.5, 1);

        Assert.assertTrue(polygon.surrounds(inside));
        Assert.assertFalse(polygon.surrounds(outside));
        Assert.assertFalse(polygon.surrounds(outside2));
    }
}

package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultEdge;
import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.model.DefaultTriangle;
import com.github.martinfrank.delauny.map.util.DoubleApproximity;
import org.junit.Assert;
import org.junit.Test;

public class CircumferenceTest {

    @Test
    public void testDistance() {
        Node a = new DefaultNode(0, 0);
        Node b = new DefaultNode(3, 0);
        Node c = new DefaultNode(2, 1);

        Triangle t = new DefaultTriangle(a, b, c);

        Edge au = new DefaultEdge(a, t.getCenter());
        Edge bu = new DefaultEdge(a, t.getCenter());
        Edge cu = new DefaultEdge(a, t.getCenter());

        Assert.assertTrue(DoubleApproximity.isApproximatelyEqual(au.getLength(), bu.getLength()));
        Assert.assertTrue(DoubleApproximity.isApproximatelyEqual(au.getLength(), cu.getLength()));
        Assert.assertTrue(DoubleApproximity.isApproximatelyEqual(bu.getLength(), cu.getLength()));
    }

    @Test
    public void testInside() {
        Node a = new DefaultNode(0, 0);
        Node b = new DefaultNode(3, 0);
        Node c = new DefaultNode(2, 1);

        Triangle t = new DefaultTriangle(a, b, c);

        Node x = new DefaultNode(3, 2);
        boolean out = t.isInCircumference(x);
        Assert.assertFalse(out);

        Node y = new DefaultNode(2, 0.5);
        boolean in = t.isInCircumference(y);
        Assert.assertTrue(in);

        Assert.assertTrue(t.isInCircumference(a));
        Assert.assertTrue(t.isInCircumference(b));
        Assert.assertTrue(t.isInCircumference(c));

    }
}

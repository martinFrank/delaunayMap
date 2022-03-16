package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.model.DefaultTriangle;
import com.github.martinfrank.delauny.map.util.CircumferenceConditionChecker;
import org.junit.Assert;
import org.junit.Test;

public class CircumferenceCheckerTest {

    @Test
    public void testCircumferenceChecker() {
        Node a = new DefaultNode(-1, 0);
        Node b = new DefaultNode(1, 0);
        Node c = new DefaultNode(0, 1);

        Node x = new DefaultNode(0, -2);

        Triangle t1 = new DefaultTriangle(a, b, c);
        Triangle t2 = new DefaultTriangle(a, b, x);

        Assert.assertFalse(CircumferenceConditionChecker.isViolated(t1, t2));

        Node y = new DefaultNode(0, -0.5);
        Triangle t3 = new DefaultTriangle(a, b, y);

        Assert.assertTrue(CircumferenceConditionChecker.isViolated(t1, t3));

    }

    @Test
    public void testCircumferenceChecker2() {
        DefaultNode a = new DefaultNode(0, 0);
        DefaultNode b = new DefaultNode(4, 0);
        DefaultNode c = new DefaultNode(2, 1);

        DefaultNode x = new DefaultNode(2, -1);

        DefaultTriangle t1 = new DefaultTriangle(a, b, c);
        DefaultTriangle t2 = new DefaultTriangle(a, b, x);

        System.out.println("is inside?" + t1.isInCircumference(x));
        Assert.assertTrue(CircumferenceConditionChecker.isViolated(t1, t2));

        Triangle t1_flipped = new DefaultTriangle(a, x, c);
        Triangle t2_flipped = new DefaultTriangle(x, c, b);
        Assert.assertFalse(CircumferenceConditionChecker.isViolated(t1_flipped, t2_flipped));
    }
}

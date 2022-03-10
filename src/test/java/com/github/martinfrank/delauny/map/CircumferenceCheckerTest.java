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
        DefaultNode b = new DefaultNode(1, 0);
        DefaultNode c = new DefaultNode(0, 1);
        DefaultNode x = new DefaultNode(-1, -1);

        //t3: a:Node(-1/-1)  b:Node(0/0)  c:Node(0/1)
        //t4: a:Node(-1/-1)  b:Node(1/0)  c:Node(0/0)

        //original Triangle t3 = new DefaultTriangle(a,c,x);
        DefaultTriangle t3 = new DefaultTriangle(x, a, c);
        //original Triangle t4 = new DefaultTriangle(a,b,x);
        DefaultTriangle t4 = new DefaultTriangle(x, b, a);

        Assert.assertTrue(CircumferenceConditionChecker.isViolated(t3, t4));

    }
}

package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.model.DefaultTriangle;
import org.junit.Test;

public class FlipperTest {

    @Test
    public void testFlip() {
        Node a = new DefaultNode(0, 0);
        DefaultNode b = new DefaultNode(1, 0);
        DefaultNode c = new DefaultNode(0, 1);

        DefaultNode x = new DefaultNode(-1, -1);

        Triangle t1 = new DefaultTriangle(a, b, c);
        Triangle t2 = new DefaultTriangle(b, c, x);

//        Assert.assertTrue(CircumferenceConditionChecker.isViolated(t1, t2));
//
//        Set<DefaultTriangle> triangles = new HashSet<>(Arrays.asList(t1, t2));
//        List<DefaultTriangle> result = new ArrayList<>(Flipper.flip(triangles));
//        Assert.assertFalse(CircumferenceConditionChecker.isViolated(result.get(0), result.get(1)));

    }
}

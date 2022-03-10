package com.github.martinfrank.delauny.map;


import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.util.CounterClockwiseComparator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ClockwiseSortTest {

    @Test
    public void testSort() {
        Node a = new DefaultNode(1, 1);
        Node b = new DefaultNode(0, 1);
        Node c = new DefaultNode(-1, 1);
        Node d = new DefaultNode(-1, 0);
        Node e = new DefaultNode(-1, -1);
        Node f = new DefaultNode(0, -1);
        Node g = new DefaultNode(1, -1);
        Node h = new DefaultNode(1, 0);
        List<Node> manualSorted = Arrays.asList(a, b, c, d, e, f, g, h);

        List<Node> testee = Arrays.asList(b, c, a, h, d, e, f, g);
        testee.sort(new CounterClockwiseComparator());

        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(testee.get(i), manualSorted.get(i));
        }

    }
}


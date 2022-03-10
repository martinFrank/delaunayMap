package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.util.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void testMatrix() {
        Node a = new DefaultNode(0, 0);
        Node b = new DefaultNode(2, 0);
        Node c = new DefaultNode(0, 2);
        Node x = new DefaultNode(2, 2);
        double detOut = Matrix.getDeterminante(a, b, c, x);
        Assert.assertTrue(detOut >= 0);


        DefaultNode y = new DefaultNode(0.5, 0.5);
        double detIn = Matrix.getDeterminante(a, b, c, y);
        Assert.assertTrue(detIn < 0);
    }
}

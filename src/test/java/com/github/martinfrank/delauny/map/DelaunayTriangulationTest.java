package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import org.junit.Test;

public class DelaunayTriangulationTest {

    @Test
    public void noExceptionsTest() {
        DelaunayTriangulation delaunayTriangulation = new DelaunayTriangulation();
        delaunayTriangulation.insertNode(new DefaultNode(0, 0));
    }
}

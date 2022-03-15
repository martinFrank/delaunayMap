package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.model.Triangles;
import com.github.martinfrank.delauny.map.util.CircumferenceConditionChecker;
import com.github.martinfrank.delauny.map.util.Flipper;

import java.util.Arrays;
import java.util.List;

public class DelaunayTriangulation {

    private final Triangles triangles;

    public DelaunayTriangulation() {
        this(new DefaultNode(-10, -10), new DefaultNode(10, 10));
    }

    public DelaunayTriangulation(Node min, Node max) {
        triangles = new Triangles(min, max);
    }

    public void insertNode(Node node) {
        if (triangles.contains(node)) {
            return;
        }
        triangles.insert(node);
        boolean done = false;
        while (!done) {
            done = true;
            for (Triangle triangle : triangles.getTriangles()) {
                done = done & checkTriangle(triangle);
            }
            if (done) {
                triangles.updateVoronoi();
            }
        }
    }

    private boolean checkTriangle(Triangle triangle) {
        boolean isOk = true;
        for (Edge e : triangle.getEdges()) {
            isOk = isOk && checkEdge(e);
        }
        return isOk;
    }

    private boolean checkEdge(Edge edge) {
        boolean isOk = true;
        List<Triangle> neighbors = triangles.getTriangles(edge);
        if (neighbors.size() == 2) {
            Triangle t1 = neighbors.get(0);
            Triangle t2 = neighbors.get(1);
            if (CircumferenceConditionChecker.isViolated(t1, t2)) {
                isOk = false;
                List<Triangle> flipped = Flipper.flip(t1, t2, edge);
                Triangle t3 = flipped.get(0);
                Triangle t4 = flipped.get(1);
                triangles.remove(t1);
                triangles.remove(t2);
                triangles.add(t3);
                triangles.add(t4);
            }
        }
        return isOk;
    }

    public List<Edge> getTriangleEdges() {
        return triangles.getEdges();
    }

    public boolean isInBounds(double x, double y) {
        return triangles.isInBounds(x, y);
    }

    public List<Edge> getVoronoiEdges() {
        return triangles.getVoronoiEdges();
    }

    public List<Edge> getInnerTriangleEdges() {
        return triangles.getInnerEdges();
    }


    public List<Node> getBounds() {
        return Arrays.asList(triangles.getMinBounds(), triangles.getMaxBounds());
    }
}

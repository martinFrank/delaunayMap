package com.github.martinfrank.delauny.map;

import com.github.martinfrank.delauny.map.model.DefaultMap;
import com.github.martinfrank.delauny.map.model.DefaultNode;
import com.github.martinfrank.delauny.map.util.CircumferenceConditionChecker;
import com.github.martinfrank.delauny.map.util.Flipper;

import java.util.Arrays;
import java.util.List;

public class DelaunayTriangulation {

    private final DefaultMap map;

    public DelaunayTriangulation() {
        this(new DefaultNode(-10, -10), new DefaultNode(10, 10));
    }

    public DelaunayTriangulation(Node min, Node max) {
        map = new DefaultMap(min, max);
    }

    public void insertNode(Node node) {
        map.insert(node);
        while (map.getEdges().stream().anyMatch(this::flipIfRequired)) ;
        map.updateVoronoi();
    }

    /**
     * @param edge to check
     * @return true if flip was performed.
     */
    private boolean flipIfRequired(Edge edge) {
        boolean isFlipNeeded = false;
        List<Triangle> neighbors = map.getTriangles(edge);
        if (neighbors.size() == 2) {
            Triangle t1 = neighbors.get(0);
            Triangle t2 = neighbors.get(1);
            if (CircumferenceConditionChecker.isViolated(t1, t2)) {
                isFlipNeeded = true;
                List<Triangle> flipped = Flipper.flip(t1, t2, edge);
                Triangle t3 = flipped.get(0);
                Triangle t4 = flipped.get(1);
                map.remove(t1);
                map.remove(t2);
                map.add(t3);
                map.add(t4);
            }
        }
        return isFlipNeeded;
    }

    public Map getMap() {
        return map;
    }

    public List<Edge> getTriangleEdges() {
        return map.getEdges();
    }

    public boolean isInBounds(double x, double y) {
        return map.isInBounds(x, y);
    }

    public List<Edge> getVoronoiEdges() {
        return map.getVoronoiEdges();
    }

    public List<Edge> getInnerTriangleEdges() {
        return map.getInnerEdges();
    }


    public List<Node> getBounds() {
        return Arrays.asList(map.getMinBounds(), map.getMaxBounds());
    }

    public List<Triangle> getTriangles() {
        return map.getTriangles();
    }
}

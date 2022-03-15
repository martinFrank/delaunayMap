package com.github.martinfrank.delauny.map.model;

import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.Triangle;
import com.github.martinfrank.delauny.map.VoronoiPolygon;

import java.util.*;
import java.util.stream.Collectors;

public class Triangles {

    private Set<Triangle> triangles = new HashSet<>();
    private Set<VoronoiPolygon> polygons = new HashSet<>();
    private Set<Node> nodes = new HashSet<>();
    private final Node min;
    private final Node max;
    private final Triangle surroundingTriangle;

    public Triangles(Node min, Node max) {
        this.min = min;
        this.max = max;
        surroundingTriangle = new DefaultTriangle(
                new DefaultNode(min.getX() - 1, min.getY() - 1),
                new DefaultNode((3 * max.getX()) + 1, min.getY()),
                new DefaultNode(min.getX(), (3 * max.getY()) + 1));
    }


    public List<Triangle> insert(Node node) {
        nodes.add(node);
        Triangle surrounding = findSurroundingTriangle(node);
        triangles.remove(surrounding);
        Triangle t1 = new DefaultTriangle(surrounding.getA(), surrounding.getB(), node);
        Triangle t2 = new DefaultTriangle(surrounding.getA(), surrounding.getC(), node);
        Triangle t3 = new DefaultTriangle(surrounding.getB(), surrounding.getC(), node);
        triangles.add(t1);
        triangles.add(t2);
        triangles.add(t3);

        return Arrays.asList(t1, t2, t3);
    }

    private Triangle findSurroundingTriangle(Node node) {
        return triangles.stream().filter(t -> t.surrounds(node)).findAny().orElse(surroundingTriangle);
    }


    public List<Triangle> getTriangles(Edge e) {
        return triangles.stream().filter(t -> t.getEdges().contains(e)).collect(Collectors.toList());
    }

    public List<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        triangles.forEach(t -> edges.addAll(t.getEdges()));
//        if(triangles.isEmpty()){
        edges.addAll(surroundingTriangle.getEdges());
//        }
        return new ArrayList<>(edges);
    }

    public boolean isInBounds(double x, double y) {
        return x > min.getX() && x < max.getX() && y > min.getY() && y < max.getY();
    }


    public List<Triangle> getTriangles() {
        return new ArrayList<>(triangles);
    }

    public void remove(Triangle triangle) {
        triangles.remove(triangle);
    }

    public void add(Triangle triangle) {
        triangles.add(triangle);
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    public void updateVoronoi() {
        polygons.clear();
        for (Node node : nodes) {
            List<Edge> edges = findEdges(node);
            boolean isSurrounded = !edges.isEmpty();
            for (Edge e : edges) {
                if (getTriangles(e).size() != 2) {
                    isSurrounded = false;
                    break;
                }
            }
            if (isSurrounded) {
                Set<Node> polygonNodes = new HashSet<>();
                edges.forEach(e -> getTriangles(e).forEach(t -> polygonNodes.add(t.getCenter())));
                polygons.add(new DefaultVoronoiPolygon(polygonNodes, node));
            }
        }
    }


    private List<Edge> findEdges(Node node) {
        return getEdges().stream()
                .filter(e -> e.getB().equals(node) || e.getA().equals(node))
                .collect(Collectors.toList());
    }

    public List<Edge> getVoronoiEdges() {
        Set<Edge> edges = new HashSet<>();
        polygons.forEach(p -> edges.addAll(p.getEdges()));
        return new ArrayList<>(edges);
    }

    public List<Edge> getInnerEdges() {
        List<Edge> edges = getEdges();
        return edges.stream()
                .filter(e -> !
                        (surroundingTriangle.getNodes().contains(e.getB())
                                || surroundingTriangle.getNodes().contains(e.getA())))
                .collect(Collectors.toList());
    }

    public Node getMaxBounds() {
        return max;
    }

    public Node getMinBounds() {
        return min;
    }
}

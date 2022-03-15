package com.github.martinfrank.delauny.map.model;

import com.github.martinfrank.delauny.map.Edge;
import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.Triangle;
import com.github.martinfrank.delauny.map.util.CounterClockwiseNodeComparator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.github.martinfrank.delauny.map.util.DoubleApproximity.isApproximatelyEqual;

public class DefaultTriangle implements Triangle {

    private final Node a;
    private final Node b;
    private final Node c;

    private final Edge ab;
    private final Edge ac;
    private final Edge bc;

    public final double area;

    private final Node u;

    public DefaultTriangle(Node a, Node b, Node c) {
        this.a = a;
        this.b = b;
        this.c = c;

        ab = new DefaultEdge(a, b);
        ac = new DefaultEdge(a, c);
        bc = new DefaultEdge(b, c);

        area = calculateArea();
        u = calculateCircumscribedCenter();
    }

    private Node calculateCircumscribedCenter() {
        //https://en.wikipedia.org/wiki/Circumscribed_circle
        double d = 2 * (
                a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
        );
        double ux =
                (Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * (b.getY() - c.getY()) +
                        (Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)) * (c.getY() - a.getY()) +
                        (Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)) * (a.getY() - b.getY());
        double uy =
                (Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * (c.getX() - b.getX()) +
                        (Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)) * (a.getX() - c.getX()) +
                        (Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)) * (b.getX() - a.getX());
        return new DefaultNode(ux / d, uy / d);

    }

    private double calculateArea() {
        double la = bc.getLength();
        double lb = ac.getLength();
        double lc = ab.getLength();

        //https://en.wikipedia.org/wiki/Heron%27s_formula
        return 0.25 * Math.sqrt((la + lb + lc) * (-1 * la + lb + lc) * (la - lb + lc) * (la + lb - lc));
    }

    @Override
    public Node getA() {
        return a;
    }

    @Override
    public Node getB() {
        return b;
    }

    @Override
    public Node getC() {
        return c;
    }

    @Override
    public Edge getAB() {
        return ab;
    }

    @Override
    public Edge getAC() {
        return ac;
    }

    @Override
    public Edge getBC() {
        return bc;
    }

    @Override
    public Node getU() {
        return u;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public boolean surrounds(Node node) {
        //https://discuss.codechef.com/t/best-way-to-check-if-a-point-lies-inside-a-triangle-or-not/13528
        Triangle abn = new DefaultTriangle(a, b, node);
        Triangle acn = new DefaultTriangle(a, c, node);
        Triangle bcn = new DefaultTriangle(b, c, node);
        return isApproximatelyEqual(area, abn.getArea() + acn.getArea() + bcn.getArea());
    }

    @Override
    public List<Node> getNodes() {
        return Arrays.asList(a, b, c);
    }

    @Override
    public List<Edge> getEdges() {
        return Arrays.asList(ab, ac, bc);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultTriangle that = (DefaultTriangle) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(c, that.c) ||
                Objects.equals(a, that.b) && Objects.equals(b, that.a) && Objects.equals(c, that.c) ||
                Objects.equals(a, that.c) && Objects.equals(b, that.b) && Objects.equals(c, that.a) ||
                Objects.equals(a, that.b) && Objects.equals(b, that.c) && Objects.equals(c, that.a) ||
                Objects.equals(a, that.a) && Objects.equals(b, that.c) && Objects.equals(c, that.b) ||
                Objects.equals(a, that.c) && Objects.equals(b, that.a) && Objects.equals(c, that.b)
                ;
    }

    @Override
    public int hashCode() {
        List<Node> nodes = Arrays.asList(a, b, c);
        nodes.sort(new CounterClockwiseNodeComparator(a, b, c));
        return Objects.hash(nodes.get(0), nodes.get(1), nodes.get(2));
    }

    @Override
    public String toString() {
        return a + " -> " + b + " -> " + c;
    }

    public static Edge getEdge(Triangle t1, Triangle t2) {
        return t1.getEdges().stream().filter(e -> t2.getEdges().contains(e)).findAny().orElseThrow(IllegalAccessError::new);
    }
}

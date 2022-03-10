package com.github.martinfrank.delauny.map.model;

import com.github.martinfrank.delauny.map.Node;
import com.github.martinfrank.delauny.map.util.DoubleFormat;

import java.util.Objects;

public class DefaultNode implements Node {

    private final double x;
    private final double y;

    public DefaultNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + DoubleFormat.format(x) + "/" + DoubleFormat.format(y) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultNode that = (DefaultNode) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

package com.github.martinfrank.delauny.map;

public interface Edge {

    Node getA();

    Node getB();

    double getLength();
}

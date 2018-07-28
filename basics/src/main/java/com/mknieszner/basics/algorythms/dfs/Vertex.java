package com.mknieszner.basics.algorythms.dfs;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Vertex {
    private String name;
    private boolean visited;
    public List<Vertex> neighbours;

    public Vertex(final String name) {
        this.name = name;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(final Vertex vertex) {
        this.neighbours.add(vertex);
    }
}

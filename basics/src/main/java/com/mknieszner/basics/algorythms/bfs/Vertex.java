package com.mknieszner.basics.algorythms.bfs;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Vertex {
    private int data;
    private boolean visited;
    private List<Vertex> neighbours;

    public Vertex(final int data) {
        this.data = data;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(final Vertex vertex) {
        this.neighbours.add(vertex);
    }
}

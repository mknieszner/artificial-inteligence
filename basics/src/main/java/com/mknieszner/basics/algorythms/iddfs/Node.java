package com.mknieszner.basics.algorythms.iddfs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class Node {
    private String name;
    private int dephLevel = 0;
    private List<Node> adjacenties;

    public Node(String name) {
        this.name = name;
        adjacenties = new ArrayList<>();
    }

    public void addNeighbour(Node node) {
        adjacenties.add(node);
    }
}

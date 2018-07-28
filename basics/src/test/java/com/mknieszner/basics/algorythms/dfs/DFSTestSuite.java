package com.mknieszner.basics.algorythms.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DFSTestSuite {

    @Test
    public void dfsTest() {
        List<Vertex> vertices = prepareVertices();

        List<String> order = DFS.dfs(vertices);

        Assert.assertEquals(
                order,
                Stream.of("1", "3", "4", "5", "2").collect(Collectors.toList())
        );
    }


    @Test
    public void dfsRecursiveTest() {
        List<Vertex> vertices = prepareVertices();

        List<String> order = DFS.dfsRecursive(vertices);

        Assert.assertEquals(
                Stream.of("1", "2", "3", "4", "5").collect(Collectors.toList()),
                order
        );
    }



    private List<Vertex> prepareVertices() {
        List<Vertex> vertices = new ArrayList<>();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");


        v1.addNeighbour(v2);
        v1.addNeighbour(v3);
        v3.addNeighbour(v4);
        v4.addNeighbour(v5);

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        return vertices;
    }
}

package com.mknieszner.basics.algorythms.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSTestSuite {

    @Test
    public void bfsTest() {
        //given
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);
        Vertex vertex6 = new Vertex(6);

        vertex1.addNeighbour(vertex6);
        vertex6.addNeighbour(vertex3);
        vertex2.addNeighbour(vertex3);
        vertex3.addNeighbour(vertex4);
        vertex4.addNeighbour(vertex5);
        vertex5.addNeighbour(vertex6);
        vertex4.addNeighbour(vertex2);

        //when
        List<Integer> order = BFS.bfs(vertex1);

        //then
        Assert.assertEquals(
                order,
                Stream.of(1, 6, 3, 4, 5, 2).collect(Collectors.toList())
        );
    }
}

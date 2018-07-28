package com.mknieszner.basics.algorythms.aStar;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AStarTestSuite {

    @Test
    public void AStarTest() {
        //given
        AStar aStar = new AStar();
        List<Node> expectedPath = Stream.of(
                new Node(1, 6),
                new Node(1, 5),
                new Node(1, 4),
                new Node(1, 3),
                new Node(1, 2),
                new Node(2, 2),
                new Node(3, 2),
                new Node(3, 3)
        ).collect(Collectors.toList());

        //when
        aStar.search();
        List<Node> path = aStar.getPath();

        //then
        Assert.assertEquals(expectedPath, path);
    }
}

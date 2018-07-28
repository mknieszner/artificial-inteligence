package com.mknieszner.basics.algorythms.iddfs;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IDDFSTestSuite {

    @Test
    public void IDDFFoundSTest(){

        //given
        Node vertex0 = new Node("A");
        Node vertex1 = new Node("B");
        Node vertex2 = new Node("C");
        Node vertex3 = new Node("D");
        Node vertex4 = new Node("E");

        vertex0.addNeighbour(vertex1);
        vertex0.addNeighbour(vertex2);
        vertex1.addNeighbour(vertex3);
        vertex3.addNeighbour(vertex4);

        //when
        IDDFS iddfs = new IDDFS(vertex4);
        Node result = iddfs.runDeepeningSearch(vertex0);

        //then
        assertEquals(result, vertex4);
    }
}

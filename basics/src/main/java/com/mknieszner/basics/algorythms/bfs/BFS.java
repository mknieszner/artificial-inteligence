package com.mknieszner.basics.algorythms.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Breadth-simple search
 */
public class BFS {

    public static List<Integer> bfs(Vertex root) {
        List<Integer> order = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        root.setVisited(true);
        queue.add(root);

        while (!queue.isEmpty()) {

            Vertex actualVertex = queue.remove();
            order.add(actualVertex.getData());
            for (Vertex v : actualVertex.getNeighbours()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.add(v);
                }
            }
        }
        return order;
    }
}

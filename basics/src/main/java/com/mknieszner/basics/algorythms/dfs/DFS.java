package com.mknieszner.basics.algorythms.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Depth-simple search
 */
public class DFS {


    public static List<String> dfsRecursive(List<Vertex> vertices) {
        List<String> order = new ArrayList<>();

        for (Vertex v : vertices) {
            if (!v.isVisited()) {
                v.setVisited(true);
                order.addAll(dfsRecursive(v));
            }
        }
        return order;
    }

    private static List<String> dfsRecursive(Vertex actualVertex) {
        List<String> order = new ArrayList<>();
        order.add(actualVertex.getName());
        for (Vertex v : actualVertex.getNeighbours()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                order.addAll(dfsRecursive(v));
            }
        }
        return order;
    }


    public static List<String> dfs(List<Vertex> vertices) {
        List<String> order = new ArrayList<>();

        for (Vertex v : vertices) {
            if (!v.isVisited()) {
                v.setVisited(true);
                order.addAll(dfsWithStack(v));
            }
        }
        return order;
    }

    private static List<String> dfsWithStack(Vertex root) {
        List<String> order = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();
        stack.add(root);
        root.setVisited(true);

        while (!stack.isEmpty()) {
            Vertex actualVertex = stack.pop();
            order.add(actualVertex.getName());

            for (Vertex v : actualVertex.getNeighbours()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    stack.push(v);
                }
            }
        }
        return order;
    }
}

package com.mknieszner.basics.algorythms.iddfs;

import java.util.Stack;

public class IDDFS {

    private Node targetVertex;
    private volatile boolean isTargetFound;

    public IDDFS(Node targetVertex) {
        this.targetVertex = targetVertex;
    }

    public Node runDeepeningSearch(Node rootVertex) {
        Node result = null;
        int depth = 0;
        while (!isTargetFound) {
            result = dfs(rootVertex, depth);
            depth++;
        }
        return result;
    }

    private Node dfs(Node sourceVertex, int depthLevel) {
        Stack<Node> stack = new Stack<>();
        sourceVertex.setDephLevel(0);
        stack.push(sourceVertex);
        while (!stack.isEmpty()) {
            Node actualNode = stack.pop();
            System.out.println(actualNode.getName() + " ");
            if (actualNode.getName().equals(this.targetVertex.getName())) {
                this.isTargetFound = true;
                return actualNode;
            }
            if (actualNode.getDephLevel() >= depthLevel) {
                continue;
            }
            for (Node node : actualNode.getAdjacenties()) {
                node.setDephLevel(actualNode.getDephLevel() + 1);
                stack.push(node);
            }
        }
        return null;
    }
}

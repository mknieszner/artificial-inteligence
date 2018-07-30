package com.mknieszner.basics.algorythms.aStar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {

    private Node[][] searchSpace;
    private Node startNode;
    private Node finalNode;
    private List<Node> closedSet;
    private Queue<Node> openSet;

    public AStar() {
        this.searchSpace = new Node[Constants.NUM_ROWS][Constants.NUM_COLS];
        this.openSet = new PriorityQueue<>(new NodeComparator());
        this.closedSet = new ArrayList<>();
        initializeSearchSpace();
    }

    public void search() {
        startNode.setH(manhattanHeuristics(startNode, finalNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            if (currentNode.equals(finalNode)) return;

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            for (Node neighbor : getAllNeighbours(currentNode)) {
                if (closedSet.contains(neighbor)) continue;
                if (!openSet.contains(neighbor)) openSet.add(neighbor);

                neighbor.setPredecessor(currentNode);
            }
        }
    }

    public List<Node> getPath() {
        List<Node> path = new ArrayList<>();
        Node node = this.finalNode;
        while (node != null) {
            path.add(node);
            node = node.getPredecessor();
        }
        return path;
    }

    private int manhattanHeuristics(Node node1, Node node2) {
        return (Math.abs(node1.getRowIndex() - node2.getRowIndex()) +
                Math.abs(node1.getColIndex() - node2.getColIndex()))
                * 10;
    }

    private List<Node> getAllNeighbours(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int row = node.getRowIndex();
        int col = node.getColIndex();

        if (row - 1 >= 0 && !this.searchSpace[row - 1][col].isBlock()) {
            searchSpace[row - 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row - 1][col].setH(manhattanHeuristics(searchSpace[row - 1][col], finalNode));
            neighbors.add(this.searchSpace[row - 1][col]);
        }

        if (row + 1 < Constants.NUM_ROWS && !this.searchSpace[row + 1][col].isBlock()) {
            searchSpace[row + 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row + 1][col].setH(manhattanHeuristics(searchSpace[row + 1][col], finalNode));
            neighbors.add(this.searchSpace[row + 1][col]);
        }

        if (col - 1 >= 0 && !this.searchSpace[row][col - 1].isBlock()) {
            searchSpace[row][col - 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row][col - 1].setH(manhattanHeuristics(searchSpace[row][col - 1], finalNode));
            neighbors.add(this.searchSpace[row][col - 1]);
        }

        if (col + 1 < Constants.NUM_COLS && !this.searchSpace[row][col + 1].isBlock()) {
            searchSpace[row][col + 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row][col + 1].setH(manhattanHeuristics(searchSpace[row][col + 1], finalNode));
            neighbors.add(this.searchSpace[row][col + 1]);
        }

        return neighbors;
    }


    private void initializeSearchSpace() {

        for (int rowIndex = 0; rowIndex < Constants.NUM_ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < Constants.NUM_COLS; colIndex++) {
                this.searchSpace[rowIndex][colIndex] = new Node(rowIndex, colIndex);
            }
        }

        this.searchSpace[1][7].setBlock(true);
        this.searchSpace[2][3].setBlock(true);
        this.searchSpace[2][4].setBlock(true);
        this.searchSpace[2][5].setBlock(true);
        this.searchSpace[2][6].setBlock(true);
        this.searchSpace[2][7].setBlock(true);

        this.startNode = this.searchSpace[3][3];
        this.finalNode = this.searchSpace[1][6];
    }
}

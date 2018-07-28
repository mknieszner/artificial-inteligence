package com.mknieszner.basics.algorythms.aStar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private int g;
    private int h;
    private int rowIndex;
    private int colIndex;
    private Node predecessor;
    private boolean isBlock;

    public Node(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getF() {
        return g + h;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return this.rowIndex == node.getRowIndex() && this.colIndex == node.colIndex;
    }

    @Override
    public String toString() {
        if (predecessor != null) {
            return "Node (" + rowIndex + "," + colIndex + ") g=" + g + ", h=" + h + ", " +
                    "predecessor =(" + predecessor.getRowIndex() +","+ predecessor.getColIndex() + ").";
        } else {
            return "Node (" + rowIndex + "," + colIndex + ") g=" + g + ", h=" + h + ", " +
                    "predecessor=" + null + ".";
        }
    }
}

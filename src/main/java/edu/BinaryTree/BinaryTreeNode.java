package edu.binaryTree;

public class BinaryTreeNode {
    private int positionShift = 1;
    private final char value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void incrementPositionShift(int shift) {
        positionShift += shift;
    }

    public int getPositionShift() {
        return positionShift;
    }
}
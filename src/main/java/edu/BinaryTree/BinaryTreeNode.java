package edu.binarytree;

public final class BinaryTreeNode {
    private final char value;
    private final BinaryTreeNode left;
    private final BinaryTreeNode right;

    public BinaryTreeNode(char value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public char getValue() {
        return value;
    }

    public BinaryTreeNode getLeftNode() {
        return left;
    }

    public BinaryTreeNode getRightNode() {
        return right;
    }
}
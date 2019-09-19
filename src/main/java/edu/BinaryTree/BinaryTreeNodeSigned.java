package edu.binarytree;

public class BinaryTreeNodeSigned {
    private final int value;
    private final BinaryTreeNode node;

    public BinaryTreeNodeSigned(BinaryTreeNode node, int value) {
        this.node = node;
        this.value = value;
    }

    public BinaryTreeNode getNode() {
        return node;
    }

    @Override
    public int hashCode() {
        return value;
    }
}

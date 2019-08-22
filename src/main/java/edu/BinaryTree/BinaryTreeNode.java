package edu.BinaryTree;

public class BinaryTreeNode {
    public char value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(char value) {
        this.value = value;
        right = null;
        left = null;
    }

    public boolean isEqual(BinaryTreeNode node) {
        if (value != node.value || right == null || left == null || node.right == null || node.left == null)
            return false;
        return (left.value == node.left.value && right.value == node.right.value);
    }

}

package edu.binaryTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTreeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);

    public static BinaryTreeNode createTree(String s) {
        LOGGER.debug("Create tree from '{}'", s);
        return createTreeRecursive(s, 0);
    }

    private static BinaryTreeNode createTreeRecursive(String s, int pos) {
        if (s.charAt(pos) == '_')
            return null;

        final BinaryTreeNode tree = new BinaryTreeNode(s.charAt(pos));

        final BinaryTreeNode leftNode = createTreeRecursive(s, pos + tree.getPositionShift());
        if (leftNode != null) {
            tree.incrementPositionShift(leftNode.getPositionShift());
            tree.left = leftNode;
        } else
            tree.incrementPositionShift(1);

        final BinaryTreeNode rightNode = createTreeRecursive(s, pos + tree.getPositionShift());
        if (rightNode != null) {
            tree.incrementPositionShift(rightNode.getPositionShift());
            tree.right = rightNode;
        } else
            tree.incrementPositionShift(1);

        return tree;
    }

    public static void printTree(BinaryTreeNode tree) {
        if (tree == null) {
            System.out.print("_");
            return;
        }

        System.out.print(tree.getValue());
        printTree(tree.left);
        printTree(tree.right);
    }
}

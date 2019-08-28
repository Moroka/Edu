package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTreeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);
    private String str;
    private int strIndex;

    public BinaryTreeNode createTree(String s) {
        str = s;
        strIndex = 0;
        LOGGER.debug("Create tree from '{}'", str);
        return createTreeRecursive();
    }

    private BinaryTreeNode createTreeRecursive() {
        char c = getNextChar();
        strIndex += 1;

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = createTreeRecursive();
        final BinaryTreeNode rightNode = createTreeRecursive();

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private char getNextChar() {
        return str.charAt(strIndex);
    }

    public static void printTree(BinaryTreeNode tree) {
        if (tree == null) {
            System.out.print("_");
            return;
        }

        System.out.print(tree.getValue());
        printTree(tree.getLeftNode());
        printTree(tree.getRightNode());
    }
}

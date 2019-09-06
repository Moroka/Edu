package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class BinaryTreeBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);
    private final String str;
    private int strIndex;

    public BinaryTreeBuilder(String s) {
        this.str = s;
    }

    public BinaryTreeNode createTree() {
        BinaryTreeNode tree = createTreeRecursive();
        if (!BinaryTreeHelper.treeToString(tree).equals(str)) {
            LOGGER.debug("Invalid input format: {}", str);
            return null;
        }

        return tree;
    }

    private BinaryTreeNode createTreeRecursive() {
        char c = getNextChar();

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = createTreeRecursive();
        final BinaryTreeNode rightNode = createTreeRecursive();

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private char getNextChar() {
        if (strIndex + 1 > str.length())
            return '_';
        strIndex += 1;
        return str.charAt(strIndex - 1);
    }
}

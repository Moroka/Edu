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
        if (!canCreateTree(str)) {
            LOGGER.debug("Invalid input text: '{}'", str);
            return null;
        } else {
            LOGGER.debug("Create tree from '{}'", str);

            return createTreeRecursive();
        }
    }

    private BinaryTreeNode createTreeRecursive() {
        char c = getNextChar();

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = createTreeRecursive();
        final BinaryTreeNode rightNode = createTreeRecursive();

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private static boolean canCreateTree(String s) {
        if (s.length() < 3)
            return false;
        return s.substring(s.length() - 2).equals("__");
    }

    private char getNextChar() {
        strIndex += 1;
        return str.charAt(strIndex - 1);
    }
}

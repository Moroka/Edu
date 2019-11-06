package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class BinaryTreeBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeBuilder.class);
    private final String str;
    private int strIndex;

    public BinaryTreeBuilder(String s) {
        this.str = s;
    }

    public BinaryTreeNode createTree() {
        BinaryTreeNode tree = createTreeRecursive();
        if (strIndex != str.length()) {
            LOGGER.error("Invalid input format: {}", str);
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
        strIndex += 1;
        char result = '_';
        try {
            result = str.charAt(strIndex - 1);
        } catch (StringIndexOutOfBoundsException e) {
            LOGGER.error("Invalid input format. Cant get char at index: {}", (strIndex - 1));
        }

        return result;
    }
}

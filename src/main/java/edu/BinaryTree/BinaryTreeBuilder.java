package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

final class BinaryTreeBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);
    private ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
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

    public ArrayList<BinaryTreeNode> getNodes() {
        return nodes;
    }

    private BinaryTreeNode createTreeRecursive() {
        char c = getNextChar();

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = createTreeRecursive();
        final BinaryTreeNode rightNode = createTreeRecursive();

        BinaryTreeNode node = new BinaryTreeNode(c, leftNode, rightNode);
        nodes.add(node);
        return node;
    }

    private char getNextChar() {
        if (strIndex + 1 > str.length())
            return '_';
        strIndex += 1;
        return str.charAt(strIndex - 1);
    }
}

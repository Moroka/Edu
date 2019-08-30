package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BinaryTreeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);
    private String str;
    private String treeToString;
    private int strIndex;

    public static BinaryTreeNode createTree(String s) {
        if (!canCreateTree(s)) {
            LOGGER.debug("Invalid input text: '{}'", s);
            return null;
        } else {
            LOGGER.debug("Create tree from '{}'", s.charAt(s.length() - 2));

            BinaryTreeHelper helper = new BinaryTreeHelper();
            helper.str = s;
            helper.strIndex = 0;
            return helper.createTreeRecursive();
        }
    }

    public static String treeToString(BinaryTreeNode node) {
        BinaryTreeHelper helper = new BinaryTreeHelper();
        helper.treeToString = "";
        return helper.treeToStringRecursive(node);
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
        return str.charAt(strIndex - 1);
    }

    private String treeToStringRecursive(BinaryTreeNode tree) {
        if (tree == null) {
            treeToString += "_";
            return treeToString;
        }

        treeToString += tree.getValue();
        treeToString = treeToStringRecursive(tree.getLeftNode());
        treeToString = treeToStringRecursive(tree.getRightNode());
        return treeToString;
    }

    private static boolean canCreateTree(String s) {
        if (s.length() < 3)
            return false;
        return s.substring(s.length() - 2).equals("__");
    }
}

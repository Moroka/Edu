package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

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
            LOGGER.debug("Create tree from '{}'", s);

            BinaryTreeHelper helper = new BinaryTreeHelper();
            helper.str = s;
            return helper.createTreeRecursive();
        }
    }

    public static String treeToString(BinaryTreeNode node) {
        BinaryTreeHelper helper = new BinaryTreeHelper();
        helper.treeToString = "";
        return helper.treeToStringRecursive(node);
    }

    public static void visualizeTree(BinaryTreeNode node) {
        int depth = treeDepth(node);
        ArrayList<BinaryTreeNode> currentLevel = new ArrayList<>();
        ArrayList<BinaryTreeNode> nextLevel = new ArrayList<>();
        currentLevel.add(node);

        while (!currentLevel.isEmpty()) {
            for (BinaryTreeNode i : currentLevel) {
                if (i.getLeftNode() != null)
                    nextLevel.add(i.getLeftNode());
                if (i.getRightNode() != null)
                    nextLevel.add(i.getRightNode());
            final String currentSeparator = separator(depth + 1);
            System.out.print(currentSeparator + i.getValue() + currentSeparator);
            }
            System.out.println();
            depth /= 2;
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
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

    private static int treeDepth(BinaryTreeNode tree) {
        return tree == null ? 0 : 1 + Math.max(treeDepth(tree.getLeftNode()), treeDepth(tree.getRightNode()));
    }

    private static boolean canCreateTree(String s) {
        if (s.length() < 3)
            return false;
        return s.substring(s.length() - 2).equals("__");
    }

    private static String separator(int n){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            sb.append("  ");
        }
        return sb.toString();
    }
}

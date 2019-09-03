package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public final class BinaryTreeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);
    private final String str;
    private int strIndex;

    public BinaryTreeHelper(String s) {
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

    public static String treeToString(BinaryTreeNode node) {
        return treeToStringRecursive(node, "");
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

    private static String treeToStringRecursive(BinaryTreeNode tree, String result) {
        if (tree == null) {
            result += "_";
            return result;
        }

        result += tree.getValue();
        result = treeToStringRecursive(tree.getLeftNode(), result);
        result = treeToStringRecursive(tree.getRightNode(), result);
        return result;
    }

    private static int treeDepth(BinaryTreeNode tree) {
        return tree == null ? 0 : 1 + Math.max(treeDepth(tree.getLeftNode()), treeDepth(tree.getRightNode()));
    }

    private static boolean canCreateTree(String s) {
        if (s.length() < 3)
            return false;
        return s.substring(s.length() - 2).equals("__");
    }

    private static String separator(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}

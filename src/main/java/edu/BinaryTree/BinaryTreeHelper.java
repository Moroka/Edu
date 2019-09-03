package edu.binarytree;

import java.util.ArrayList;

public final class BinaryTreeHelper {
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
                final String currentSeparator = separator(depth + 1);
                if (i != null) {
                    nextLevel.add(i.getLeftNode());
                    nextLevel.add(i.getRightNode());
                }
                System.out.print(currentSeparator + (i != null ? i.getValue() : "*") + currentSeparator);
            }
            System.out.println();
            depth /= 2;
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
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

    private static String separator(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}

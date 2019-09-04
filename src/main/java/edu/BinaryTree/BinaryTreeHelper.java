package edu.binarytree;

import java.util.ArrayList;
import java.util.Random;

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

    public static String generateTree() {
        return generateTreeRecursive("", 100);
    }

    private static String generateTreeRecursive(String result, int chance) {
        result += generateChar(chance);
        if (result.length() > 4) {
            if (result.substring(result.length() - 2).equals("__"))
                return result;
        }

        return generateTreeRecursive(result, chance - 5);
    }

    private static char generateChar(int chance) {
        final Random random = new Random();
        if (random.nextInt(100) <= chance)
            return (char) ('a' + random.nextInt(26));
        else return '_';
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

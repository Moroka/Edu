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
            for (BinaryTreeNode currentLevelNode : currentLevel) {
                final String currentSeparator = separator(depth + 1);
                if (currentLevelNode != null) {
                    nextLevel.add(currentLevelNode.getLeftNode());
                    nextLevel.add(currentLevelNode.getRightNode());
                }
                System.out.print(currentSeparator + (currentLevelNode != null ? currentLevelNode.getValue() : " ") + currentSeparator);
            }
            System.out.println();
            depth /= 2;
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }
    }

    public static BinaryTreeNode generateTree() {
        return generateTreeRecursive(1d);
    }

    private static BinaryTreeNode generateTreeRecursive(double chance) {
        final Random random = new Random();
        char c = generateChar(chance, random);

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = generateTreeRecursive(chance - 0.1d);
        final BinaryTreeNode rightNode = generateTreeRecursive(chance - 0.1d);

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private static char generateChar(double chance, Random random) {
        if (random.nextDouble() <= chance) {
            return (char) ('a' + random.nextInt(26));
        }
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

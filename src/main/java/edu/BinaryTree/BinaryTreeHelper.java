package edu.binarytree;

import java.util.*;

public final class BinaryTreeHelper {
    public static StringBuilder treeToStringBuilder(BinaryTreeNode tree) {
        if (tree == null) {
            return new StringBuilder().append('_');
        }

        final StringBuilder result = new StringBuilder();

        result.append(tree.getValue());
        result.append(treeToStringBuilder(tree.getLeftNode()));
        result.append(treeToStringBuilder(tree.getRightNode()));
        return result;
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

    public static BinaryTreeNode generateTree(double desireToNullSpeed) {
        return generateTree(1d, desireToNullSpeed);
    }

    public static boolean hasSameCharSet(BinaryTreeNode tree) {
        final List<BinaryTreeNode> treeNodes = getTreeNodesRecursive(tree);
        return hasSameCharSet(treeNodes);
    }

    private static List<BinaryTreeNode> getTreeNodesRecursive(BinaryTreeNode node) {
        if (node == null) {
            return Collections.emptyList();
        }

        final List<BinaryTreeNode> result = new ArrayList<>();

        result.add(node);
        result.addAll(getTreeNodesRecursive(node.getLeftNode()));
        result.addAll(getTreeNodesRecursive(node.getRightNode()));
        return result;
    }

    private static boolean hasSameCharSet(List<BinaryTreeNode> treeNodes) {
        HashMap<HashSet<Character>, Integer> storage = new HashMap<>();

        for (BinaryTreeNode node : treeNodes) {
            final HashSet<Character> charSet = treeUniqueChars(node);
            final Integer value = storage.get(charSet);
            if (value == null)
                storage.put(charSet, 1);
            else
                return true;
        }

        return false;
    }

    private static HashSet<Character> treeUniqueChars(BinaryTreeNode tree) {
        if (tree == null) {
            return new HashSet<>();
        }

        final HashSet<Character> result = new HashSet<>();
        result.add(tree.getValue());
        result.addAll(treeUniqueChars(tree.getLeftNode()));
        result.addAll(treeUniqueChars(tree.getRightNode()));
        return result;
    }

    private static BinaryTreeNode generateTree(double chance, double desireToNullSpeed) {
        final Random random = new Random();
        final char c = generateChar(chance, random);

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = generateTree(chance - desireToNullSpeed, desireToNullSpeed);
        final BinaryTreeNode rightNode = generateTree(chance - desireToNullSpeed, desireToNullSpeed);

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private static char generateChar(double chance, Random random) {
        if (random.nextDouble() <= chance) {
            return (char) ('a' + random.nextInt(26));
        } else
            return '_';
    }

    private static int treeDepth(BinaryTreeNode tree) {
        return tree == null ? 0 : 1 + Math.max(treeDepth(tree.getLeftNode()), treeDepth(tree.getRightNode()));
    }

    private static String separator(int n) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}

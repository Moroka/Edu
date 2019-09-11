package edu.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public static BinaryTreeNode generateTree(double desireToNullSpeed) {
        return generateTreeRecursive(1d, desireToNullSpeed);
    }

    public static boolean hasEqualSubtrees(ArrayList<BinaryTreeNode> treeNodes) {
        HashMap<BinaryTreeNode, HashSet<Character>> storage = new HashMap<>();
        for (BinaryTreeNode node : treeNodes) {
            storage.put(node, getTreeChars(node));
        }

        for (HashMap.Entry<BinaryTreeNode, HashSet<Character>> entry1 : storage.entrySet()) {
            for (HashMap.Entry<BinaryTreeNode, HashSet<Character>> entry2 : storage.entrySet()) {
                if (entry1.getValue().hashCode() == entry2.getValue().hashCode())
                    return true;
            }
        }

        return false;
    }

    private static HashSet<Character> getTreeChars(BinaryTreeNode tree) {
        return getTreeCharsRecursive(tree, new HashSet<>());
    }

    private static HashSet<Character> getTreeCharsRecursive(BinaryTreeNode tree, HashSet<Character> result) {
        if (tree == null) {
            return result;
        }

        result.add(tree.getValue());
        result = getTreeCharsRecursive(tree.getLeftNode(), result);
        result = getTreeCharsRecursive(tree.getRightNode(), result);
        return result;
    }

    private static BinaryTreeNode generateTreeRecursive(double chance, double desireToNullSpeed) {
        final Random random = new Random();
        char c = generateChar(chance, random);

        if (c == '_')
            return null;

        final BinaryTreeNode leftNode = generateTreeRecursive(chance - desireToNullSpeed, desireToNullSpeed);
        final BinaryTreeNode rightNode = generateTreeRecursive(chance - desireToNullSpeed, desireToNullSpeed);

        return new BinaryTreeNode(c, leftNode, rightNode);
    }

    private static char generateChar(double chance, Random random) {
        if (random.nextDouble() <= chance) {
            return (char) ('a' + random.nextInt(26));
        } else
            return '_';
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

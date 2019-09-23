package edu.binarytree;

import java.util.*;

public final class BinaryTreeHelper {

    /**
     * Tree to string
     */
    public static String treeToString(BinaryTreeNode tree) {
        return treeToStringBuilder(tree).toString();
    }

    private static StringBuilder treeToStringBuilder(BinaryTreeNode tree) {
        if (tree == null) {
            return new StringBuilder().append('_');
        }

        final StringBuilder result = new StringBuilder();

        result.append(tree.getValue());
        result.append(treeToStringBuilder(tree.getLeftNode()));
        result.append(treeToStringBuilder(tree.getRightNode()));
        return result;
    }

    /**
     * Visualize tree
     */
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

    /**
     * Generate random tree
     */
    public static BinaryTreeNode generateTree(double desireToNullSpeed) {
        return generateTree(1d, desireToNullSpeed);
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

    /**
     * Detect same charset
     */
    public static BinaryTreeNode[] hasSameCharSet(BinaryTreeNode tree) {
        final List<BinaryTreeNode> nodes = getTreeNodesRecursive(tree);
        final HashMap<Integer, BinaryTreeNode> storage = new HashMap<>();
        final BinaryTreeNode[] result = new BinaryTreeNode[2];

        for (BinaryTreeNode node : nodes) {
            final int id = treeBinaryRepresentation(node);
            final BinaryTreeNode value = storage.get(id);
            if (value == null) {
                storage.put(id, node);
            } else {
                result[0] = node;
                result[1] = value;
            }
        }
        return result;
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

    private static int treeBinaryRepresentation(BinaryTreeNode tree) {
        return treeBinaryRepresentationRecursive(tree, 0);
    }

    private static int treeBinaryRepresentationRecursive(BinaryTreeNode tree, int binaryRepresentation) {
        if (tree == null) {
            return binaryRepresentation;
        }

        int result = modifyBinaryRepresentation(tree.getValue(), binaryRepresentation);
        result = treeBinaryRepresentationRecursive(tree.getLeftNode(), result);
        result = treeBinaryRepresentationRecursive(tree.getRightNode(), result);
        return result;
    }

    private static int modifyBinaryRepresentation(char c, int binaryRepresentation) {
        final int mask = 1 << (c - 'a');
        return binaryRepresentation | mask;
    }
}

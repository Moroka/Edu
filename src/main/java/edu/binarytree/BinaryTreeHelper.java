package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class BinaryTreeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeHelper.class);

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
        final BinaryTreeNode[] result = new BinaryTreeNode[2];
        hasSameCharSet(tree, new HashMap<>(), result);
        return result;
    }

    private static int hasSameCharSet(BinaryTreeNode node, HashMap<Integer, BinaryTreeNode> storage, BinaryTreeNode[] result) {
        if (result[0] != null)
            return -1;
        if (node == null)
            return 0;

        final int leftId = hasSameCharSet(node.getLeftNode(), storage, result);
        if (result[0] != null)
            return -1;
        final int rightId = hasSameCharSet(node.getRightNode(), storage, result);
        if (result[0] != null)
            return -1;

        LOGGER.info(":::::::::::: Processing node: {}", node.getValue());

        int id = modifyBinaryRepresentation(node.getValue(), 0);
        LOGGER.info("Node raw binary value: {}", Integer.toBinaryString(id));
        LOGGER.info("Left id: {}.", Integer.toBinaryString(leftId));
        LOGGER.info("Right id: {}.", Integer.toBinaryString(rightId));
        id = id | leftId | rightId;
        LOGGER.info("New node id after merging sub-nodes: {}", Integer.toBinaryString(id));

        final BinaryTreeNode value = storage.get(id);
        if (value == null) {
            LOGGER.info("Add node '{}' to storage with id: {}", node.getValue(), Integer.toBinaryString(id));
            storage.put(id, node);
        } else {
            LOGGER.info("Finding nodes with equal ids: {} {}", node.getValue(), value.getValue());
            result[0] = node;
            result[1] = value;
        }

        return id;
    }

    private static int modifyBinaryRepresentation(char c, int binaryRepresentation) {
        final int mask = 1 << (c - 'a');
        return binaryRepresentation | mask;
    }
}

package edu.BinaryTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BinaryTree {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTree.class);

    private BinaryTreeNode root;
    private ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
    private ArrayList<BinaryTreeNode> possibleRepetitive = new ArrayList<>();
    private int count = 0;

    private void addRecursive(BinaryTreeNode current, char value, boolean addToPossibleRepetitive) {
        count++;

        if (current.left == null) {
            LOGGER.debug("Add left child to node {}: {}", current.value, value);
            current.left = createNode(value, addToPossibleRepetitive);
            return;
        } else if (current.right == null) {
            LOGGER.debug("Add right child to node {}: {}", current.value, value);
            current.right = createNode(value, addToPossibleRepetitive);
            return;
        }

        addRecursive(count % 2 == 0 ? current.right : current.left, value, addToPossibleRepetitive);
    }

    private void add(char value, boolean addToPossibleRepetitive) {
        if (root == null) {
            LOGGER.debug("Create root node: {}", value);
            root = createNode(value, addToPossibleRepetitive);
        } else
            addRecursive(root, value, addToPossibleRepetitive);
    }

    private BinaryTreeNode createNode(char value, boolean addToPossibleRepetitive) {
        final BinaryTreeNode node = new BinaryTreeNode(value);
        if (addToPossibleRepetitive)
            possibleRepetitive.add(node);
        else
            nodes.add(node);
        return node;
    }

    private boolean hasEqualSubtrees() {
        for (BinaryTreeNode i : possibleRepetitive) {
            for (BinaryTreeNode j : nodes) {
                if (i.value == j.value)
                    return i.isEqual(j);
            }
        }
        return false;
    }

    public static boolean hasEqualSubtrees(String s) {
        BinaryTree tree = new BinaryTree();

        for (int i = 0; i < s.length(); i++) {
            tree.add(s.charAt(i), s.substring(i + 1).indexOf(s.charAt(i)) >= 0);
        }

        if (tree.possibleRepetitive.isEmpty())
            return false;
        else {
            return tree.hasEqualSubtrees();
        }
    }
}

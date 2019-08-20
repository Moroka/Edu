package edu.BinaryTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BinaryTree {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTree.class);

    private BinaryTreeNode root;
    private ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
    private ArrayList<BinaryTreeNode> repetitiveNodes = new ArrayList<>();

    private BinaryTreeNode addRecursive(BinaryTreeNode current, char value) {
        if (current != null) {
            LOGGER.debug("Test {}", current.value);
            if (current.value == value) {
                repetitiveNodes.add(current);
            }
        } else {
            LOGGER.debug("Create new node: {}", value);
            BinaryTreeNode node = new BinaryTreeNode(value);
            nodes.add(node);
            return node;
        }

        if (current.left == null) {
            LOGGER.debug("Add left child to node {}: {}", current.value, value);
            current.left = addRecursive(current.left, value);
            return current.left;
        } else if (current.right == null) {
            LOGGER.debug("Add right child to node {}: {}", current.value, value);
            current.right = addRecursive(current.right, value);
            return current.right;
        }

        if (current.left.left == null || current.left.right == null) {
            current.left = addRecursive(current.left, value);
            return current.left;
        } else if (current.right.left == null || current.right.right == null) {
            current.right = addRecursive(current.right, value);
            return current.right;
        }

        return current;
    }

    private void add(char value) {
        root = addRecursive(root, value);
    }

    private void test() {
        for (int i = 0; i < repetitiveNodes.size(); i++) {
            System.out.println(repetitiveNodes.get(i));
        }
    }

    public static boolean hasEqualSubtrees(String s) {
        BinaryTree bt = new BinaryTree();

        for (int i = 0; i < s.length(); i++) {
            bt.add(s.charAt(i));
        }

        bt.test();
        return true;
    }
}

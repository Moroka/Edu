package edu.BinaryTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BinaryTree {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTree.class);

    private BinaryTreeNode root;
    private ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
    private int count = 0;

    private void addRecursive(BinaryTreeNode current, char value) {
        count ++;

        if (current.left == null) {
            LOGGER.debug("Add left child to node {}: {}", current.value, value);
            current.left = createNode(value);
            return;
        } else if (current.right == null) {
            LOGGER.debug("Add right child to node {}: {}", current.value, value);
            current.right = createNode(value);
            return;
        }

        addRecursive(count % 2 == 0 ? current.left : current.right, value);
    }

    private void add(char value) {
        if (root == null) {
            LOGGER.debug("Create root node: {}", value);
            root = createNode(value);
        }
        else
            addRecursive(root, value);
    }

    private BinaryTreeNode createNode(char value) {
        BinaryTreeNode node = new BinaryTreeNode(value);
        nodes.add(node);
        return node;
    }

    private void test() {
//        for (int i = 0; i < repetitiveNodes.size(); i++) {
//            System.out.println(repetitiveNodes.get(i));
//        }

        System.out.println(root);
        System.out.println(nodes);
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
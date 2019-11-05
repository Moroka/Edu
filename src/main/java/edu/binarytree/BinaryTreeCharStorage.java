package edu.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public final class BinaryTreeCharStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeCharStorage.class);
    private final BinaryTreeNode tree;
    private BinaryTreeNode[] result = new BinaryTreeNode[2];
    private HashMap<Integer, BinaryTreeNode> storage = new HashMap<>();

    public BinaryTreeCharStorage(BinaryTreeNode tree) {
        this.tree = tree;
    }

    public BinaryTreeNode[] sameCharSet() {
        sameCharSet(tree);
        return result;
    }

    private int sameCharSet(BinaryTreeNode node) {
        if (node == null)
            return 0;

        final int leftId = sameCharSet(node.getLeftNode());
        if (result[0] != null)
            return -1;
        final int rightId = sameCharSet(node.getRightNode());
        if (result[0] != null)
            return -1;

        LOGGER.info(":::::::::::: Processing node: {}", node.getValue());

        final int rawId = 1 << (node.getValue() - 'a');
        LOGGER.info("Node raw binary value: {}", Integer.toBinaryString(rawId));
        LOGGER.info("Left id: {}.", Integer.toBinaryString(leftId));
        LOGGER.info("Right id: {}.", Integer.toBinaryString(rightId));
        final int id = rawId | leftId | rightId;
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
}

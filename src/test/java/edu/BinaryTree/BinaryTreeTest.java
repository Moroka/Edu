package edu.binaryTree;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void repetitiveCharsTest() {
        /*
                  a
             b          c
           d   e      g   a
        */
        BinaryTreeNode tree = BinaryTreeHelper.createTree("abd__e__cg__a__");
        BinaryTreeHelper.printTree(tree);
    }
}

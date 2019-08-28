package edu.binarytree;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void repetitiveCharsTest() {
        /*
                  a
             b          c
           d   e      g   a
        */
        BinaryTreeHelper treeHelper = new BinaryTreeHelper();
        BinaryTreeNode node = treeHelper.createTree("abd__e__cg__a__");
        BinaryTreeHelper.printTree(node);
    }
}

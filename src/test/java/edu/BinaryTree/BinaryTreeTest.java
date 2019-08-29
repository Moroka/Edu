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
        BinaryTreeNode node = BinaryTreeHelper.createTree("abd__e__cg__a__");
        System.out.println(BinaryTreeHelper.treeToString(node));
    }
}

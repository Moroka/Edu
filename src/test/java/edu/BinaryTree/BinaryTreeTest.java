package edu.binaryTree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void repetitiveCharsTest() {
        /*
                  a
             b          c
           d   e      g   a
        */
        BinaryTreeNode tree = BinaryTreeHelper.createTree("abd__e__cg__a__");
        System.out.println(BinaryTreeHelper.printTreeRecursive(tree, ""));
    }

    @Test
    public void repetitiveCharsTest2() {
        /*
                  a
             b          c
        */
//        BinaryTreeNode tree = BinaryTreeHelper.createTree("ab__c__");
//        BinaryTreeHelper.printTreeRecursive(tree);
    }
}
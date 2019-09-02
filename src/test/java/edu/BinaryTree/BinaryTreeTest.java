package edu.binarytree;

import org.junit.Test;


import static org.junit.Assert.*;

public final class BinaryTreeTest {
    @Test
    public void oneNodeTreeTest() {
        /*
                  a
        */
        final String inputString = "a__";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void twoNodesTreeTest() {
        /*
                  a
              b
        */
        final String inputString = "ab___";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }


    @Test
    public void threeNodesTreeTest() {
        /*
                  a
             b          c
        */
        final String inputString = "ab__c__";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void repetitiveNodesTest() {
        /*
                  a
             b          c
           d   e      g   a
        */
        final String inputString = "abd__e__cg__a__";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
        BinaryTreeHelper.visualizeTree(node);
    }

    @Test
    public void tooShortTreeTest() {
        final String inputString = "__";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertNull(node);
    }

    @Test
    public void invalidEndTreeTest() {
        final String inputString = "ab_";
        final BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertNull(node);
    }
}

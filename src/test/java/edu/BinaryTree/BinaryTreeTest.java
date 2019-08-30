package edu.binarytree;

import org.junit.Test;


import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void oneNodeTreeTest() {
        /*
                  a
        */
        String inputString = "a__";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void twoNodesTreeTest() {
        /*
                  a
              b
        */
        String inputString = "ab___";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }


    @Test
    public void threeNodesTreeTest() {
        /*
                  a
             b          c
        */
        String inputString = "ab__c__";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void repetitiveNodesTest() {
        /*
                  a
             b          c
           d   e      g   a
        */
        String inputString = "abd__e__cg__a__";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void tooShortTreeTest() {
        String inputString = "__";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertNull(node);
    }

    @Test
    public void invalidEndTreeTest() {
        String inputString = "ab_";
        BinaryTreeNode node = BinaryTreeHelper.createTree(inputString);
        assertNull(node);
    }
}

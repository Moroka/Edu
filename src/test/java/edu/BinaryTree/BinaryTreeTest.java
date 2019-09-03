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
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }

    @Test
    public void twoNodesTreeTest() {
        /*
                  a
              b
        */
        final String inputString = "ab___";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
    }


    @Test
    public void threeNodesTreeTest() {
        /*
                  a
             b          c
        */
        final String inputString = "ab__c__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();
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
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
        BinaryTreeHelper.visualizeTree(node);
    }

    @Test
    public void repetitiveNodesTest2() {
        /*
                  a
             b          c
           d   e      g   a
        */
        final String inputString = "nxu___pl__v_q__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();
        assertEquals(inputString, BinaryTreeHelper.treeToString(node));
        BinaryTreeHelper.visualizeTree(node);
    }


    @Test
    public void tooShortTreeTest() {
        final BinaryTreeBuilder tree = new BinaryTreeBuilder("__");
        final BinaryTreeNode node = tree.createTree();

        assertNull(node);
    }

    @Test
    public void invalidEndTreeTest() {
        final BinaryTreeBuilder tree = new BinaryTreeBuilder("ab_");
        final BinaryTreeNode node = tree.createTree();
        assertNull(node);
    }

    @Test
    public void invalidTreeFormatTest() {
        final String inputString = "a____b____";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder("a____b____");
        final BinaryTreeNode node = tree.createTree();
        assertNotEquals(inputString, BinaryTreeHelper.treeToString(node));
    }
}

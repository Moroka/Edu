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

    @Test
    public void treeGeneratorTest() {
        final BinaryTreeNode tree = BinaryTreeHelper.generateTree(0.1d);
        BinaryTreeHelper.visualizeTree(tree);
    }

    @Test
    public void hasSameCharSetTreesTest1() {
        /*
                  a
             b          b
        */
        final String inputString = "ab__b__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();

        final BinaryTreeNode[] foundedNodes = BinaryTreeHelper.hasSameCharSet(node);
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[0]), "b__");
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[1]), "b__");
    }

    @Test
    public void hasSameCharSetTreesTest2() {
        /*
                  a
             c          b
           d   b      c   d
        */
        final String inputString = "acd__b__bc__d__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();

        final BinaryTreeNode[] foundedNodes = BinaryTreeHelper.hasSameCharSet(node);
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[0]), "bc__d__");
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[1]), "cd__b__");
    }

    @Test
    public void hasSameCharSetTreesTest3() {
        /*
                  a
             c          k
           d   b      c   d
        */
        final String inputString = "acd__b__kc__d__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();

        final BinaryTreeNode[] foundedNodes = BinaryTreeHelper.hasSameCharSet(node);
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[0]), "d__");
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[1]), "d__");
    }

    @Test
    public void hasSameCharSetTreesTest4() {
        /*
                      a
                c            k
            d     b     c        d
         x    z       n        z    x
        */
        final String inputString = "acdx__z__b__kcn___dz__x__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();

        final BinaryTreeNode[] foundedNodes = BinaryTreeHelper.hasSameCharSet(node);
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[0]), "dz__x__");
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[1]), "dx__z__");
    }

    @Test
    public void hasNotSameCharSetTreesTest() {
        /*
                  a
             c          e
           d   z      f   g
        */
        final String inputString = "acd__z__ef__g__";
        final BinaryTreeBuilder tree = new BinaryTreeBuilder(inputString);
        final BinaryTreeNode node = tree.createTree();

        final BinaryTreeNode[] foundedNodes = BinaryTreeHelper.hasSameCharSet(node);
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[0]), "_");
        assertEquals(BinaryTreeHelper.treeToString(foundedNodes[1]), "_");
    }
}
